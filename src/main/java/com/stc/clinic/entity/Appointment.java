package com.stc.clinic.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "APPOINTMENT")
@Data

public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "IS_CANCELLED")
	private Boolean isCancelled;
	 
	@JoinColumn(updatable = false , name = "PATIENT_ID", referencedColumnName = "ID")
	@ManyToOne(cascade =CascadeType.PERSIST,fetch=FetchType.EAGER, optional=true)
	@ToString.Exclude
	private Patient patient;
	
	
	@Column(name = "APPOINTMENT_DATE")
	@Temporal(TemporalType.DATE)
	private Date appointmentDate;
	
	@Column(name = "REASON")
	private String reasonForCancellation;

	

}
