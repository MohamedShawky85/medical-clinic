package com.stc.clinic.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class AppointmentsDto {

	long appointmentId;

	Boolean isCancelled;

	@NonNull
	Long patientId;

	String patientName;

	Date appointmentDate;

	String reasonForCancellation;
}
