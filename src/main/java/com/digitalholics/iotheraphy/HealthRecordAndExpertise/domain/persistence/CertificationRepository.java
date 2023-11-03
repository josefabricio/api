package com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.persistence;

import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificationRepository extends JpaRepository<Certification,Integer> {

    Optional<Certification> findById(Integer certificationId);

    List<Certification> findByPhysiotherapistId(Integer physiotherapistId);
}
