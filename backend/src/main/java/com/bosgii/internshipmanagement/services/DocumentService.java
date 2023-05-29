package com.bosgii.internshipmanagement.services;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DocumentService {

    public DocumentService() {}

    public String saveDocument(MultipartFile multipartFile, String folder, Long requestID) {
        String path = new FileSystemResource("").getFile().getAbsolutePath();
        Path root = Path.of(path);
        root = root.resolve("backend/documents").resolve(folder).resolve(requestID + ".pdf");

        try {
            Files.copy(multipartFile.getInputStream(), root);
        } catch (Exception e) {
            try {
                Files.delete(root);
                Files.copy(multipartFile.getInputStream(), root);
            } catch (Exception ex){
                return ex.getMessage();
            }
            if (e instanceof FileAlreadyExistsException) {
                return "A file of that name already exists.";
            }

            return e.getMessage();
        }

        return "File saved successfully!";
    }

    public Resource getDocumentByFolderNameAndRequestID(String folder, Long requestID) {
            String path = new FileSystemResource("").getFile().getAbsolutePath();
            Path root = Path.of(path);
            root = root.resolve("backend/documents").resolve(folder).resolve(requestID + ".pdf");


            try {
                return new UrlResource(root.toUri());
            } catch (MalformedURLException ex) {
                throw new RuntimeException("Could not read the file!");
            }
    }
}
