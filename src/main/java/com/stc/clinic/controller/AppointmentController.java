package com.stc.clinic.controller;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stc.clinic.dto.AppointmentsDto;
import com.stc.clinic.dto.PatientDto;
import com.stc.clinic.payload.PagedResponse;
import com.stc.clinic.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${api.prefix.url}/appointment")
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;

	@GetMapping({"/appointments","/appointments/{appointmentDate}"})
	public PagedResponse<AppointmentsDto> getAppointments(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortColumn,
			@RequestParam(defaultValue = "DESC") String sortType, @PathVariable(required=false) String appointmentDate) {

		return appointmentService.getAppointments(pageNo, pageSize, sortColumn, sortType, appointmentDate);
	}

	@PostMapping("/add-appointment")
	public AppointmentsDto addAppointment(@Valid @RequestBody AppointmentsDto request) throws NoSuchElementException {

		return appointmentService.addAppointment(request);

	}

	@PatchMapping("/cancel/{appointmentID}")
	public AppointmentsDto cancelAppointment(@RequestParam String reason, @PathVariable Long appointmentID)
			throws NoSuchElementException {

		return appointmentService.cancelAppointment(reason, appointmentID);

	}

	@PostMapping("/filter-by-patientname")
	public PagedResponse<AppointmentsDto> filterByPatientName(@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortColumn,
			@RequestParam(defaultValue = "DESC") String sortType,@RequestBody PatientDto patient)
			 {
		return appointmentService.filterByPatientName(pageNo, pageSize,sortColumn, sortType,patient.getName());

	}
	
	@GetMapping("/{patientId}")
	public PagedResponse<AppointmentsDto> viewPatientAppointments(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortColumn,
			@RequestParam(defaultValue = "DESC") String sortType, @PathVariable  Long patientId) throws NoSuchElementException {

		return appointmentService.viewPatientAppointments(pageNo, pageSize, sortColumn, sortType, patientId);
	}	
	

}
