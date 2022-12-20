package com.stc.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.stc.clinic.entity.Appointment;

public interface AppointmentDao  extends JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment>{

}
