package com.stc.clinic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class PatientDto {

	long patientId;
	@NonNull
	String name;

	long age;
}
