package com.stc.clinic.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.stc.clinic.dao.AppointmentDao;
import com.stc.clinic.dao.PatientDao;
import com.stc.clinic.dto.AppointmentsDto;
import com.stc.clinic.entity.Appointment;
import com.stc.clinic.entity.Patient;
import com.stc.clinic.mapper.AppointmentMapper;
import com.stc.clinic.payload.PagedResponse;
import com.stc.clinic.service.AppointmentService;
import com.stc.clinic.specification.AppointmentSpecification;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentDao appointmentDao;

	@Autowired
	PatientDao patientDao;

	@Autowired
	AppointmentMapper appointmentMapper;

	@Override
	public PagedResponse<AppointmentsDto> getAppointments(Integer pageNo, Integer pageSize, String sortColumn,
			String sortType, String appointmentDate) {

		AppointmentSpecification specification = new AppointmentSpecification();

		Date appointmentsDate = null;
		// use this condition for setting the appointments date
		if (null != appointmentDate) {
			appointmentsDate = Date.valueOf(appointmentDate);
		} else {
			appointmentsDate = new Date(System.currentTimeMillis());
		}

		specification.add(new com.stc.clinic.specification.SearchCriteria("appointmentDate", appointmentsDate,
				com.stc.clinic.specification.SearchOperation.EQUAL));

		Page<Appointment> pagedResult = appointmentDao.findAll(specification, PageRequest.of(pageNo, pageSize,
				Sort.by("ASC".equalsIgnoreCase(sortType) ? Sort.Direction.ASC : Sort.Direction.DESC, sortColumn)));

		return new PagedResponse<>(
				pagedResult.getContent().stream().map(this::appointmentToDto)
						.collect(Collectors.toList()),
				pagedResult.getNumber(), pagedResult.getSize(), pagedResult.getTotalElements(),
				pagedResult.getTotalPages(), pagedResult.isLast());

	}

	

	public AppointmentsDto appointmentToDto(Appointment appointment) {
		ModelMapper mapper = new ModelMapper();

		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		mapper.getConfiguration().setAmbiguityIgnored(true);

		PropertyMap<Appointment, AppointmentsDto> mappedObject = new PropertyMap<Appointment, AppointmentsDto>() {
			protected void configure() {

				map().setPatientId(source.getPatient().getId());
				map().setPatientName(source.getPatient().getName());
				map().setAppointmentId(source.getId());

			}
		};
		mapper.addMappings(mappedObject);

		return mapper.map(appointment, AppointmentsDto.class);
	}

	@Override
	public AppointmentsDto addAppointment(AppointmentsDto request) throws NoSuchElementException {

		Appointment appointmentEntity = appointmentMapper.dtoToEntity(request);
		Patient patient = patientDao.findById(request.getPatientId())
				.orElseThrow(() -> new NoSuchElementException("No patient with this Id"));
		appointmentEntity.setPatient(patient);

		Appointment addedAppointment = appointmentDao.save(appointmentEntity);

		return appointmentMapper.entityToDto(addedAppointment);

	}

	@Override
	public AppointmentsDto cancelAppointment(String reason, Long appointmentID) throws NoSuchElementException {

		Appointment appointmentEntity = appointmentDao.findById(appointmentID)
				.orElseThrow(() -> new NoSuchElementException("No appointment with this Id"));

		appointmentEntity.setIsCancelled(true);
		appointmentEntity.setReasonForCancellation(reason);

		Appointment cancelledAppointment = appointmentDao.save(appointmentEntity);

		return appointmentMapper.entityToDto(cancelledAppointment);
	}

   @Override
	public PagedResponse<AppointmentsDto> filterByPatientName(Integer pageNo, Integer pageSize, String sortColumn,
			String sortType, String patientName)  {
		
		if("".equalsIgnoreCase(patientName))
		{	
			return new PagedResponse<>(
		
				 new ArrayList<>(),
					0, pageSize, 0,
					0, true);
		}
		
		
		List<Patient> searchedPatient = patientDao.findByNameLike("%"+patientName+"%");
		
		AppointmentSpecification specification = new AppointmentSpecification();
		
		specification.add(new com.stc.clinic.specification.SearchCriteria("patient", searchedPatient,
				com.stc.clinic.specification.SearchOperation.IN));
	
		
		Page<Appointment> pagedResult = appointmentDao.findAll(specification, PageRequest.of(pageNo, pageSize,
				Sort.by("ASC".equalsIgnoreCase(sortType) ? Sort.Direction.ASC : Sort.Direction.DESC, sortColumn)));

		return new PagedResponse<>(
				pagedResult.getContent().stream().map(this::appointmentToDto)
						.collect(Collectors.toList()),
				pagedResult.getNumber(), pagedResult.getSize(), pagedResult.getContent().stream().map(this::appointmentToDto)
				.collect(Collectors.toList()).size(),
				pagedResult.getTotalPages(), pagedResult.isLast());
	}



@Override
public PagedResponse<AppointmentsDto> viewPatientAppointments(Integer pageNo, Integer pageSize, String sortColumn,
		String sortType, Long patientId) throws NoSuchElementException {
	if(null == patientId)
	{	
		return new PagedResponse<>(
	
			 new ArrayList<>(),
				0, pageSize, 0,
				0, true);
	}
	
	
	Patient searchedPatient = patientDao.findById(patientId).orElseThrow(() -> new NoSuchElementException("No patient with this Id"));

	
	AppointmentSpecification specification = new AppointmentSpecification();
	
	specification.add(new com.stc.clinic.specification.SearchCriteria("patient", searchedPatient,
			com.stc.clinic.specification.SearchOperation.EQUAL));

	
	Page<Appointment> pagedResult = appointmentDao.findAll(specification, PageRequest.of(pageNo, pageSize,
			Sort.by("ASC".equalsIgnoreCase(sortType) ? Sort.Direction.ASC : Sort.Direction.DESC, sortColumn)));

	return new PagedResponse<>(
			pagedResult.getContent().stream().map(this::appointmentToDto)
					.collect(Collectors.toList()),
			pagedResult.getNumber(), pagedResult.getSize(), pagedResult.getContent().stream().map(this::appointmentToDto)
			.collect(Collectors.toList()).size(),
			pagedResult.getTotalPages(), pagedResult.isLast());
}



}
