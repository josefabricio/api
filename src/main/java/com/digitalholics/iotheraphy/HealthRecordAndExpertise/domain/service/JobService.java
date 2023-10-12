package com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.service;


import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.Certification;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.Job;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.CreateJobResource;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.UpdateJobResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobService {
    List<Job> getAll();
    Page<Job> getAll(Pageable pageable);
    Job getById(Integer jobId);
    List<Job> getByPhysiotherapistId(Integer physiotherapistId);
    Job create(CreateJobResource job);
    Job update(Integer jobId, UpdateJobResource request);
    ResponseEntity<?> delete(Integer jobId);
}
