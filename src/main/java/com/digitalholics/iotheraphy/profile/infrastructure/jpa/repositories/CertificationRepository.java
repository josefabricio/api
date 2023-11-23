package com.digitalholics.iotheraphy.profile.infrastructure.jpa.repositories;

import com.digitalholics.iotheraphy.profile.domain.model.entities.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificationRepository extends JpaRepository<Certification,Integer> {

    Optional<Certification> findById(Integer certificationId);

    List<Certification> findByPhysiotherapistId(Integer physiotherapistId);
}
