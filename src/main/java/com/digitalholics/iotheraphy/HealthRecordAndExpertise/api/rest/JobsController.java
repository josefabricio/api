package com.digitalholics.iotheraphy.HealthRecordAndExpertise.api.rest;


import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.service.JobService;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.mapping.JobMapper;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/jobs", produces = "application/json")
@Tag(name = "Jobs", description = "Create, read, update and delete jobs")
public class JobsController {
    private final JobService jobService;

    private final JobMapper mapper;


    public JobsController(JobService jobService, JobMapper mapper) {
        this.jobService = jobService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<JobResource> getAllJobs(Pageable pageable) {
        return mapper.modelListPage(jobService.getAll(), pageable);
    }

    @GetMapping("{jobId}")
    public JobResource getJobById(@PathVariable Integer jobId) {
        return mapper.toResource(jobService.getById(jobId));
    }


    @GetMapping("byPhysiotherapistId/{physiotherapistId}")
    public Page<JobResource> getJobsByPhysiotherapistId(@PathVariable Integer physiotherapistId, Pageable pageable) {
        return mapper.modelListPage(jobService.getByPhysiotherapistId(physiotherapistId), pageable);
    }

    @PostMapping
    public ResponseEntity<JobResource> createJob(@RequestBody CreateJobResource resource) {
        return new ResponseEntity<>(mapper.toResource(jobService.create(resource)), HttpStatus.CREATED);
    }

    @PatchMapping("{jobId}")
    public ResponseEntity<JobResource> patchJob(@PathVariable Integer jobId,
                                                          @RequestBody UpdateJobResource request) {
        return new ResponseEntity<>(mapper.toResource(jobService.update(jobId,request)), HttpStatus.CREATED);
    }

    @DeleteMapping("{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable Integer jobId) {
        return jobService.delete(jobId);
    }
}
