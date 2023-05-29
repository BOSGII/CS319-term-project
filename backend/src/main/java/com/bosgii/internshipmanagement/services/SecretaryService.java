package com.bosgii.internshipmanagement.services;

import com.bosgii.internshipmanagement.entities.Secretary;

import com.bosgii.internshipmanagement.exceptions.InvalidMailAddressException;

import com.bosgii.internshipmanagement.repos.SecretaryRepository;
import com.bosgii.internshipmanagement.requests.AddSecretaryRequest;
import com.bosgii.internshipmanagement.requests.ChangeSecretaryRequest;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bosgii.internshipmanagement.services.InternshipService;
import java.util.List;

@Service
public class SecretaryService {
    private SecretaryRepository secretaryRepository;
    private final PasswordEncoder passwordEncoder;


    public SecretaryService(SecretaryRepository secretaryRepository, PasswordEncoder passwordEncoder) {
        this.secretaryRepository = secretaryRepository;
        this.passwordEncoder = passwordEncoder;
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
        //note that we have random string generater method to cretae a random passsword. this is just for test and simplicity
        newSec.setPassword(passwordEncoder.encode(newSec.getId().toString()));
        InternshipService.sendEmail(newSec.getMail(), newSec.getId().toString());
        return secretaryRepository.save(newSec);

    }

    public void deleteSecretary(Long id) {
        secretaryRepository.deleteById(id);
    }

    public Secretary changeSecretary(Long secretaryId, ChangeSecretaryRequest req) throws InvalidMailAddressException {
		Secretary toBeUpdated;
		Optional<Secretary> opt = secretaryRepository.findById(secretaryId);

		if (opt.isPresent()) {
			toBeUpdated = opt.get();
		} else {
			//toBeUpdated = new Secretary();
			//toBeUpdated.setRole("secretary");
            throw new IllegalArgumentException("Secretary already exist");
		}

		toBeUpdated.setFullName(req.getFullName());
		toBeUpdated.setMail(req.getMail());


		return secretaryRepository.save(toBeUpdated);

    }

    
}
