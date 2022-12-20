package com.stc.clinic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.clinic.entity.Patient;

public interface PatientDao extends JpaRepository<Patient, Long> {
	
	List<Patient> findByNameLike(String patientName);

}
