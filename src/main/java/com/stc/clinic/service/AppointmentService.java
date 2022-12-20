package com.stc.clinic.service;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.stc.clinic.dto.AppointmentsDto;
import com.stc.clinic.payload.PagedResponse;

public interface AppointmentService {

	PagedResponse<AppointmentsDto> getAppointments(Integer pageNo, Integer pageSize, String sortColumn, String sortType,
			String appointmentDate);

	AppointmentsDto addAppointment(AppointmentsDto request) throws NoSuchElementException;

	AppointmentsDto cancelAppointment(String reason, Long appointmentID) throws NoSuchElementException;

    PagedResponse<AppointmentsDto> filterByPatientName(@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortColumn,
			@RequestParam(defaultValue = "DESC") String sortType,@RequestParam String patientName);
    
    PagedResponse<AppointmentsDto> viewPatientAppointments(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortColumn,
			@RequestParam(defaultValue = "DESC") String sortType, @PathVariable  Long patientId) throws NoSuchElementException; 
}
