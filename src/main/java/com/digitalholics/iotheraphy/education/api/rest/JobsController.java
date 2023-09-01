package com.digitalholics.iotheraphy.education.api.rest;


import com.digitalholics.iotheraphy.education.domain.service.JobService;
import com.digitalholics.iotheraphy.education.mapping.JobMapper;
import com.digitalholics.iotheraphy.education.resource.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/", produces = "application/json")
@Tag(name = "Jobs", description = "Create, read, update and delete jobs")
public class JobsController {
    private final JobService jobService;

    private final JobMapper mapper;


    public JobsController(JobService jobService, JobMapper mapper) {
        this.jobService = jobService;
        this.mapper = mapper;
    }

    @GetMapping("allJobs")
    public Page<JobResource> getAllJobs(Pageable pageable) {
        return mapper.modelListPage(jobService.getAll(), pageable);
    }

    @GetMapping("jobById/{jobId}")
    public JobResource getJobById(@PathVariable Integer jobId) {
        return mapper.toResource(jobService.getById(jobId));
    }


    @PostMapping("registration-job")
    public ResponseEntity<JobResource> createJob(@RequestBody CreateJobResource resource) {
        return new ResponseEntity<>(mapper.toResource(jobService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @PutMapping("updatedJobById{jobId}")
    public JobResource updateJob(@PathVariable Integer jobId,
                                                     @RequestBody UpdateJobResource resource) {
        return mapper.toResource(jobService.update(jobId, mapper.toModel(resource)));
    }

    @DeleteMapping("deleteJobById/{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable Integer jobId) {
        return jobService.delete(jobId);
    }
}
