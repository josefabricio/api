package com.digitalholics.iotheraphy.Appointment.domain.persistence;

import com.digitalholics.iotheraphy.Appointment.domain.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    Appointment findByTopic(String topic);

}
