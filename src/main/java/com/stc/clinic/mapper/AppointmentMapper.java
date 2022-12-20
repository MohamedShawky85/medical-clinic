package com.stc.clinic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import com.stc.clinic.dto.AppointmentsDto;
import com.stc.clinic.entity.Appointment;

@Mapper(componentModel = "spring",nullValueCheckStrategy =   NullValueCheckStrategy.ALWAYS)
public interface AppointmentMapper extends BasicMapper<Appointment, AppointmentsDto>{
	
	@Mapping(source = "appointmentId", target = "id")
	@Mapping(source = "patientId", target = "patient.id")
	Appointment dtoToEntity(AppointmentsDto dto);
	
	
	
	@Mapping(source = "id", target = "appointmentId")
	@Mapping(source = "patient.id", target = "patientId")
	AppointmentsDto entityToDto(Appointment entity);


}
