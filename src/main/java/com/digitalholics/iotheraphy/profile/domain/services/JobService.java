package com.digitalholics.iotheraphy.profile.domain.services;


import com.digitalholics.iotheraphy.profile.domain.model.entities.Job;

import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.Job.CreateJobResource;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.Job.UpdateJobResource;

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
