package com.digitalholics.iotheraphy.HealthRecordAndExpertise.service;

import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.Job.CreateJobResource;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.Job.UpdateJobResource;

import com.digitalholics.iotheraphy.Profiless.domain.model.entity.Physiotherapist;
import com.digitalholics.iotheraphy.Profiless.domain.persistence.PhysiotherapistRepository;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceValidationException;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.Job;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.persistence.JobRepository;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.service.JobService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JobServiceImpl implements JobService {
    private static final String ENTITY = "Job";

    private final JobRepository jobRepository;
    private final PhysiotherapistRepository physiotherapistRepository;

    private final Validator validator;

    public JobServiceImpl(JobRepository jobRepository, PhysiotherapistRepository physiotherapistRepository, Validator validator) {
        this.jobRepository = jobRepository;
        this.physiotherapistRepository = physiotherapistRepository;
        this.validator = validator;
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    @Override
    public Page<Job> getAll(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    @Override
    public Job getById(Integer jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, jobId));
    }

    @Override
    public List<Job> getByPhysiotherapistId(Integer physiotherapistId) {
        List<Job> jobs = jobRepository.findByPhysiotherapistId(physiotherapistId);

        if(jobs.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Jobs for this physiotherapist");

        return jobs;
    }
    @Override
    public Job create(CreateJobResource jobResource) {
        Set<ConstraintViolation<CreateJobResource>> violations = validator.validate(jobResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Physiotherapist> physiotherapistOptional = Optional.ofNullable(physiotherapistRepository.findPhysiotherapistByUserUsername(username));
        Physiotherapist physiotherapist = physiotherapistOptional.orElseThrow(()->new NotFoundException("This physiotherapist not found with ID: "+ username));

        Job job= new Job();
        job.setPhysiotherapist(physiotherapist);
        job.setPosition(jobResource.getPosition());
        job.setOrganization(jobResource.getOrganization());

        return jobRepository.save(job);
    }

    @Override
    public Job update(Integer jobId, UpdateJobResource request) {
        Job job = getById(jobId);

        if (request.getPosition() != null) {
            job.setPosition(request.getPosition());
        }
        if (request.getOrganization() != null) {
            job.setOrganization(request.getOrganization());
        }


        return jobRepository.save(job);
    }

    @Override
    public ResponseEntity<?> delete(Integer jobId) {
        return jobRepository.findById(jobId).map(job -> {
            jobRepository.delete(job);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,jobId));
    }
}
