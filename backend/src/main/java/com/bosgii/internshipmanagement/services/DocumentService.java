package com.bosgii.internshipmanagement.services;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DocumentService {

    public DocumentService() {}

    public String saveDocument(MultipartFile multipartFile,String folder, Long requestID) {

        String path = new FileSystemResource("").getFile().getAbsolutePath();
        Path root = Path.of(path);
        root = root.resolve("documents").resolve(folder).resolve(requestID + ".pdf");


        try {
            Files.copy(multipartFile.getInputStream(), root);
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                return "A file of that name already exists.";
            }

            return e.getMessage();
        }

        return "File saved successfully!";


    }

    public ResponseEntity<Resource> getDocumentByFolderNameAndRequestID(String folder, Long requestID) {

            String path = new FileSystemResource("").getFile().getAbsolutePath();
            Path root = Path.of(path);
            root = root.resolve("documents").resolve(folder).resolve(requestID + ".pdf");

            Resource resource;
            try {
                resource = new UrlResource(root.toUri());
                return new ResponseEntity<>(resource, HttpStatus.OK);
            } catch (MalformedURLException ex) {
                throw new RuntimeException("Could not read the file!");
            }
    }


}
