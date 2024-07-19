package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller.admin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

        String fileName = file.getOriginalFilename();
        List<String> allowedExtensions = Arrays.asList("pdf", "jpg", "jpeg", "png", "doc", "docx");
        // Note anymatch
        boolean isValidContainExtension = allowedExtensions.stream().anyMatch(x -> fileName.toLowerCase().endsWith(x));

        if (!isValidContainExtension) {
            throw new InvalidException("Extension file not valid...");
        }

        // create a directory if folder not existsgetResource
        this.fileService.createDirectory(baseURI);
        this.fileService.createDirectory(baseURI + folder);

        // store file
        String uploadFileName = this.fileService.store(file, folder);

        // create dto
        ResUploadFileDTO resUploadFileDTO = new ResUploadFileDTO(uploadFileName, Instant.now());

        return ResponseEntity.ok().body(resUploadFileDTO);
    }

}
