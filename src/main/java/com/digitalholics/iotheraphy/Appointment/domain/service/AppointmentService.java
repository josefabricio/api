package com.digitalholics.iotheraphy.Appointment.domain.service;

import com.digitalholics.iotheraphy.Appointment.domain.model.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {

    List<Appointment> getAll();
    Page<Appointment> getAll(Pageable pageable);

    Appointment getById(Integer appointmentId);

    Appointment getTheraphyByAppointmentId(Integer theraphyId);

    Appointment create(Appointment appointment);

    Appointment update(Integer appointmentId, Appointment request);

    ResponseEntity<?> delete(Integer appointmentId);


}
