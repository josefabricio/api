package com.digitalholics.iotheraphy.Therapy.domain.service;

import com.digitalholics.iotheraphy.Therapy.domain.model.entity.Appointment;
import com.digitalholics.iotheraphy.Therapy.resource.Appointment.CreateAppointmentResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {

    List<Appointment> getAll();
    Page<Appointment> getAll(Pageable pageable);

    Appointment getById(Integer appointmentId);

    Appointment getAppointmentByTherapyId(Integer therapyId);

    Appointment create(CreateAppointmentResource appointment);

    Appointment update(Integer appointmentId, Appointment request);

    ResponseEntity<?> delete(Integer appointmentId);


    List<Appointment> getAppointmentsByTherapyByPatientId(Integer patientId);
    List<Appointment> getAppointmentsByTherapyByPhysiotherapistId(Integer physiotherapistId);

}
