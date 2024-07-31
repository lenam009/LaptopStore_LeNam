package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    @Value("${lenam.upload-file.base-uri}")
    private String baseURI;

    public String truncateSpace(String file) {
        return file.replaceAll("\\s", "");
    }

    public void createDirectory(String uriDirectory) throws URISyntaxException {

        // Convert to Path because url in property not a path
        URI uri = new URI(uriDirectory);
        Path path = Paths.get(uri);

        File tmpDir = new File(path.toString());
        if (!tmpDir.isDirectory()) {
            try {
                Files.createDirectory(tmpDir.toPath());
                System.out.println(">>> CREATE NEW DIRECTORY SUCCESSFUL, PATH = " + tmpDir.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(">>> SKIP MAKING DIRECTORY, ALREADY EXISTS");
        }
    }

    public String store(MultipartFile file, String folder, String fileName) throws URISyntaxException, IOException {

        // create unique filename
        String finalName = System.currentTimeMillis() + "-" + fileName;

        URI uri = new URI(baseURI + folder + "/" + finalName);
        Path path = Paths.get(uri);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path,
                    StandardCopyOption.REPLACE_EXISTING);
        }

        return finalName;
    }

    public long getFileLength(String fileName, String folder) throws URISyntaxException {
        URI uri = new URI(baseURI + folder + "/" + fileName);
        Path path = Paths.get(uri);

        File tmpDir = new File(path.toString());

        // file không tồn tại, hoặc file là 1 directory => return 0
        if (!tmpDir.exists() || tmpDir.isDirectory())
            return 0;
        return tmpDir.length();
    }

    public InputStreamResource getResource(String fileName, String folder)
            throws URISyntaxException, FileNotFoundException {
        URI uri = new URI(baseURI + folder + "/" + fileName);
        Path path = Paths.get(uri);

        File file = new File(path.toString());
        return new InputStreamResource(new FileInputStream(file));
    }

    public void deleteFile(String fileName, String folder) throws URISyntaxException {

        URI uri = new URI(baseURI + folder + "/" + fileName);
        Path path = Paths.get(uri);
        File file = new File(path.toString());

        try {

            if (file.exists() && file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed or file not exists... ");
            }
        } catch (Exception e) {
            System.out.println("Failed to Delete image !!");
        }
    }

    public void moveFile(String fileName, String folderFrom, String folderTo) throws URISyntaxException {

        URI uriFrom = new URI(baseURI + folderFrom + "/" + fileName);
        Path pathFrom = Paths.get(uriFrom);
        File fileFrom = new File(pathFrom.toString());

        URI uriTo = new URI(baseURI + folderTo);
        Path pathTo = Paths.get(uriTo);
        File fileTo = new File(pathTo.toString());

        try {

            if (fileFrom.exists() && fileTo.isDirectory()) {
                fileTo = new File(pathTo.toString() + "/" + fileName);

                Files.move(fileFrom.toPath(), fileTo.toPath(), StandardCopyOption.REPLACE_EXISTING);

                System.out.println(fileFrom.getName() + " is moved!");
            } else {
                System.out.println("Move operation is failed or file not exists... ");
            }
        } catch (Exception e) {
            System.out.println("Failed to Move image !!");
        }
    }

}
