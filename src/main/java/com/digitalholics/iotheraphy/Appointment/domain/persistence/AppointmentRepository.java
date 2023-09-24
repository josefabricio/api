package com.digitalholics.iotheraphy.Appointment.domain.persistence;

import com.digitalholics.iotheraphy.Appointment.domain.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    Appointment findByTopic(String topic);

    Appointment findAppointmentByTheraphyId(Integer theraphyId);

    //@Query("select * from Appointment a inner join Theraphy b  on  a.theraphy.id = b.id where b.patientId = :patientId");
    @Query("select a from Appointment  a where a.theraphy.patientId.id = :patientId")
    List<Appointment> findAppointmentsByTheraphyByPatientId(Integer patientId);

    @Query("select a from Appointment  a where a.theraphy.physiotheraphistId.id = :physiotherapistId")
    List<Appointment> findAppointmentsByTheraphyByPhysiotherapistId(Integer physiotherapistId);

}
