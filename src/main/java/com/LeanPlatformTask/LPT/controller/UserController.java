package com.LeanPlatformTask.LPT.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.LeanPlatformTask.LPT.dao.ClientRepo;
import com.LeanPlatformTask.LPT.dao.ConsultantRepo;
import com.LeanPlatformTask.LPT.model.ClientModel;
import com.LeanPlatformTask.LPT.model.ConsultantModel;
@RestController
public class UserController {
	@Autowired
	ClientRepo clientRepository;
	@Autowired
	ConsultantRepo  consultantRepository;
	
	@PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody ConsultantModel userRequest) {
		boolean emailExists = clientRepository.existsByEmail(userRequest.getEmail()) || consultantRepository.existsByEmail(userRequest.getEmail());
	    if (emailExists) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists!");
	    }
        String role = userRequest.getRole();
        if ("client".equalsIgnoreCase(role)) {
        	ClientModel client=new ClientModel();
        	client.setName(userRequest.getName());
        	client.setAge(userRequest.getAge());
        	client.setEmail(userRequest.getEmail());
        	client.setPhoneNo(userRequest.getPhoneNo());
        	client.setRole(userRequest.getRole());
            clientRepository.save(client);
            return ResponseEntity.ok("Client registered successfully!");
        } else if ("consultant".equalsIgnoreCase(role)) {
        	ConsultantModel consultant=new ConsultantModel();
        	consultant.setName(userRequest.getName());
        	consultant.setAge(userRequest.getAge());
        	consultant.setEmail(userRequest.getEmail());
        	consultant.setPhoneNo(userRequest.getPhoneNo());
        	consultant.setRole(userRequest.getRole());
        	consultant.setExperience(userRequest.getExperience());
        	consultant.setAreaOfExpertise(userRequest.getAreaOfExpertise());
            consultantRepository.save(consultant);
            return ResponseEntity.ok("Consultant registered successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid role provided!");
        }
    }
	@GetMapping("list/clients")
    public List<ClientModel> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("list/consultants")
    public List<ConsultantModel> getAllConsultants() {
        return consultantRepository.findAll();
    }
}
