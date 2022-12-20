package com.stc.clinic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stc.clinic.dao.PatientDao;
import com.stc.clinic.dto.PatientDto;
import com.stc.clinic.entity.Patient;
import com.stc.clinic.mapper.PatientMapper;
import com.stc.clinic.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientMapper patientMapper;
	
	@Autowired
	PatientDao patientDao;
	
	/**
	 * This method used for adding a new patient and return the added one with a @param (PatientDto)
	 */
	@Override
	public PatientDto addPatient(PatientDto request)  {
		
		Patient patientEntity = patientMapper.dtoToEntity(request);

		Patient patient = patientDao.save(patientEntity);
		
		return patientMapper.entityToDto(patient);
	}

}
