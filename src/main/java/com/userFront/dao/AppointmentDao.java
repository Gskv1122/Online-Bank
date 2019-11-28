package com.userFront.dao;

import com.userFront.domain.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentDao extends CrudRepository<Appointment,Long> {
    List<Appointment> findAll();
}
