package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller.admin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Product;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResUploadFileDTO;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.FileService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.ProductService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.UserService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.SecurityUtil;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.annotation.ApiMessage;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.exception.InvalidException;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    private final FileService fileService;
    private final ProductService productService;

    @Value("${lenam.upload-file.base-uri}")
    private String baseURI;

    public FileController(FileService fileService, ProductService productService) {
        this.fileService = fileService;
        this.productService = productService;
    }

    @PostMapping("")
    @ApiMessage("Upload file")
    public ResponseEntity<ResUploadFileDTO> uploadFile(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("folder") String folder) throws URISyntaxException, IOException, InvalidException {

        ResUploadFileDTO resUploadFileDTO = this.fileService.handleCreateFile(file, folder);

        return ResponseEntity.ok().body(resUploadFileDTO);
    }

    @PutMapping("/updateFileProduct")
    @ApiMessage("Upload file")
    public ResponseEntity<ResUploadFileDTO> updateFile(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("folder") String folder, @RequestParam("idProduct") Long idProduct)
            throws URISyntaxException,
            IOException, InvalidException {

        Product product = this.productService.getProductById(idProduct);

        if (!product.getImage().isEmpty()) {
            this.fileService.deleteFile(product.getImage(), folder);
        }

        ResUploadFileDTO resUploadFileDTO = this.fileService.handleCreateFile(file, folder);

        product.setImage(resUploadFileDTO.getFileName());
        this.productService.handleSaveProduct(product);

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
