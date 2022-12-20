package com.stc.clinic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import com.stc.clinic.dto.PatientDto;
import com.stc.clinic.entity.Patient;


@Mapper(componentModel = "spring",nullValueCheckStrategy =   NullValueCheckStrategy.ALWAYS)
public interface PatientMapper extends BasicMapper<Patient, PatientDto>{
	
	@Mapping(source = "patientId", target = "id")
	Patient dtoToEntity(PatientDto dto);
	
	
	
	@Mapping(source = "id", target = "patientId")
	PatientDto entityToDto(Patient entity);

}
