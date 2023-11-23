package com.digitalholics.iotheraphy.profile.infrastructure.jpa.repositories;

import com.digitalholics.iotheraphy.profile.domain.model.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>{
    Optional<Job> findById(Integer jobId);

    List<Job> findByPhysiotherapistId(Integer physiotherapistId);
}
