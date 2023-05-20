package com.bosgii.internshipmanagement.controllers;

import com.bosgii.internshipmanagement.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class DocumentController {

    DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents/{folder}/{requestID}")
    public ResponseEntity<Resource> getDocumentByRequestID(@PathVariable("folder") String folder,@PathVariable("requestID") Long requestID) {
        return documentService.getDocumentByFolderNameAndRequestID(folder,requestID);
    }

    @PostMapping("/documents/{folder}/{requestID}")
    public String uploadFileRequestByRequestID(@PathVariable("requestID") Long id, @PathVariable("folder") String folder, @RequestParam("file") MultipartFile file) {
        return documentService.saveDocument(file, folder, id);
    }

}
