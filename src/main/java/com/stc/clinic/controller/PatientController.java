package com.stc.clinic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stc.clinic.dto.PatientDto;
import com.stc.clinic.service.PatientService;


@RequestMapping("${api.prefix.url}/patient")
@RestController
public class PatientController {
	@Autowired
	PatientService patientService;
    
	@PostMapping("/add-patient")
	public PatientDto addPatient(@Valid @RequestBody PatientDto request)  {
		
		return patientService.addPatient(request);

	}
	
}
