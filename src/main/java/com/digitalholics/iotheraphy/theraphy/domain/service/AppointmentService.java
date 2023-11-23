package com.digitalholics.iotheraphy.theraphy.domain.service;

import com.digitalholics.iotheraphy.theraphy.domain.model.entity.Appointment;
import com.digitalholics.iotheraphy.theraphy.resource.Appointment.CreateAppointmentResource;
import com.digitalholics.iotheraphy.theraphy.resource.Appointment.UpdateAppointmentResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {

    List<Appointment> getAll();
    Page<Appointment> getAll(Pageable pageable);

    Appointment getById(Integer appointmentId);

    List<Appointment> getAppointmentByTherapyId(Integer therapyId);

    Appointment create(CreateAppointmentResource appointment);

    Appointment update(Integer appointmentId, UpdateAppointmentResource request);

    ResponseEntity<?> delete(Integer appointmentId);


    List<Appointment> getAppointmentsByTherapyByPatientId(Integer patientId);
    List<Appointment> getAppointmentsByTherapyByPhysiotherapistId(Integer physiotherapistId);

    Appointment getAppointmentByDateAndTherapyId(Integer therapyId, String date);

    Appointment updateDiagnosis(Integer appointmentId, String diagnosis);

}
