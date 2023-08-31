package com.digitalholics.iotheraphy.Appointment.domain.persistence;

import com.digitalholics.iotheraphy.Appointment.domain.model.entity.CheckRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckRequestRepository extends JpaRepository<CheckRequest,Integer> {

    List<CheckRequest> findByPatientId(Integer patientId);

    //Optional<CheckRequest> findByTherapistId(Integer therapistId);

    List<CheckRequest> findCheckRequestsByAccepted(Boolean status);
}
