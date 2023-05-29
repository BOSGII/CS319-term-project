package com.bosgii.internshipmanagement.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.services.ImportService;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api")
public class ImportController {
    private final ImportService importService;

    public ImportController(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importInternshipsFromExcelFile(@RequestBody MultipartFile file) {

        try {
            importService.importInternshipsFromExcelFile(file);
            return ResponseEntity.ok("File imported succesfully.");
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
