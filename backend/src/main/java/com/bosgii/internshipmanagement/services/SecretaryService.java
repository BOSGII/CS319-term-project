package com.bosgii.internshipmanagement.services;

import com.bosgii.internshipmanagement.entities.Secretary;

import com.bosgii.internshipmanagement.exceptions.InvalidMailAddressException;

import com.bosgii.internshipmanagement.repos.SecretaryRepository;
import com.bosgii.internshipmanagement.requests.AddSecretaryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretaryService {
    private SecretaryRepository secretaryRepository;

    public SecretaryService(SecretaryRepository secretaryRepository){
        this.secretaryRepository = secretaryRepository;
    }

    public List<Secretary> getAllSecretaries(){
        return secretaryRepository.findAll();
    }


    public Secretary createSecretary(AddSecretaryRequest req) throws InvalidMailAddressException{

        Secretary newSec = new Secretary();
        newSec.setId(req.getId());
        newSec.setFullName(req.getFullName());
        newSec.setMail(req.getMail());
        newSec.setRole("secretary");

        return secretaryRepository.save(newSec);
    }

    public void deleteSecretary(Long id) {
        secretaryRepository.deleteById(id);
    }
}
