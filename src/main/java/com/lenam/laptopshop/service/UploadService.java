package com.lenam.laptopshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadService {

    private final ServletContext servletContext;

    public UploadService(ServletContext servletContext) {

        this.servletContext = servletContext;
    }

    public String handleSaveUploadFile(MultipartFile avatarFile, String targetFolder) {
        try {
            byte[] bytes;
            bytes = avatarFile.getBytes();

            // Creating the directory to store file
            // To directory ..../webapp + /resources/images
            String rootPath = this.servletContext.getRealPath("/resources/images");

            File dir = new File(rootPath + File.separator + targetFolder);
            if (!dir.exists())
                dir.mkdirs();

            String fileName = System.currentTimeMillis() + "-" + avatarFile.getOriginalFilename();
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath()
                    + File.separator + fileName);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            return fileName;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public void deleteFile(String fileName) {
        String rootPath = this.servletContext.getRealPath("/resources/images");
        try {
            File file = new File(rootPath + "/avatar/" + fileName);
            file.getAbsolutePath();
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed ");
            }
        } catch (Exception e) {
            System.out.println("Failed to Delete image !!");
        }
    }
}
