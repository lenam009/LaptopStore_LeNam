package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller.admin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResUploadFileDTO;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.FileService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.annotation.ApiMessage;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.exception.InvalidException;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    private final FileService fileService;

    @Value("${lenam.upload-file.base-uri}")
    private String baseURI;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("")
    @ApiMessage("Upload file")
    public ResponseEntity<ResUploadFileDTO> uploadFile(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("folder") String folder) throws URISyntaxException, IOException, InvalidException {

        // validate
        if (file == null || file.isEmpty()) {
            throw new InvalidException("File is empty...");
        }

        String fileName = this.fileService.truncateSpace(file.getOriginalFilename());

        List<String> allowedExtensions = Arrays.asList("pdf", "jpg", "jpeg", "png", "doc", "docx");
        // Note anymatch
        boolean isValidContainExtension = allowedExtensions.stream().anyMatch(x -> fileName.toLowerCase().endsWith(x));

        if (!isValidContainExtension) {
            throw new InvalidException("Extension file not valid...");
        }

        // create a directory if folder not exists getResource
        this.fileService.createDirectory(baseURI);
        this.fileService.createDirectory(baseURI + folder);

        // store file
        String uploadFileName = this.fileService.store(file, folder, fileName);

        // create dto
        ResUploadFileDTO resUploadFileDTO = new ResUploadFileDTO(uploadFileName, Instant.now());

        return ResponseEntity.ok().body(resUploadFileDTO);
    }

    @PutMapping("")
    @ApiMessage("Move file")
    public ResponseEntity<String> moveFile(
            @RequestParam("folderFrom") String folderFrom, @RequestParam("folderTo") String folderTo,
            @RequestParam("fileName") String fileName)
            throws URISyntaxException, IOException, InvalidException {

        // create a directory if folder not exists getResource
        this.fileService.createDirectory(baseURI + folderTo);

        // move file
        this.fileService.moveFile(fileName, folderFrom, folderTo);

        return ResponseEntity.ok().body("Move file successful");
    }

    @DeleteMapping("")
    @ApiMessage("Delete file")
    public ResponseEntity<String> deleteFile(
            @RequestParam("folder") String folder,
            @RequestParam("fileName") String fileName)
            throws URISyntaxException, IOException, InvalidException {

        this.fileService.deleteFile(fileName, folder);

        return ResponseEntity.ok().body("Delete file successful");
    }

}
