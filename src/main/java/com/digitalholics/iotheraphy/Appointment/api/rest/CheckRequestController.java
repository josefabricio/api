package com.digitalholics.iotheraphy.Appointment.api.rest;

import com.digitalholics.iotheraphy.Appointment.domain.service.CheckRequestService;
import com.digitalholics.iotheraphy.Appointment.mapping.CheckRequestMapper;
import com.digitalholics.iotheraphy.Appointment.resource.CheckRequestResource;
import com.digitalholics.iotheraphy.Appointment.resource.CreateCheckRequestResource;
import com.digitalholics.iotheraphy.Appointment.resource.UpdateCheckRequestResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/", produces = "application/json")
@Tag(name = "CheckRequests", description = "Create, read, update and delete CheckRequests")
public class CheckRequestController {

    private final CheckRequestService checkRequestService;

    private final CheckRequestMapper mapper;

    public CheckRequestController(CheckRequestService checkRequestService, CheckRequestMapper mapper) {
        this.checkRequestService = checkRequestService;
        this.mapper = mapper;
    }


    @GetMapping("allCheckRequests")
    public Page<CheckRequestResource> getAllCheckRequests(Pageable pageable) {
        return mapper.modelListPage(checkRequestService.getAll(), pageable);
    }

    @GetMapping("checkRequestById/{checkRequestId}")
    public CheckRequestResource getCheckRequestById(@PathVariable Integer checkRequestId) {
        return mapper.toResource(checkRequestService.getById(checkRequestId));
    }

    @GetMapping("checkRequestByPatientId/patientId={value}")
    public Page<CheckRequestResource> getCheckRequestByPatientId(@PathVariable Integer value, Pageable pageable) {
        return mapper.modelListPage(checkRequestService.getByPatientId(value), pageable);
    }

   /*
    @GetMapping("checkRequestByTherapistId/therapistId={value}")
    public Page<CheckRequestResource> getCheckRequestByTherapistId(@PathVariable Integer value, Pageable pageable) {
        return mapper.modelListPage(checkRequestService.getByTherapistId(value), pageable);
    }*/


    @GetMapping("checkRequestByStatus/{value}")
    public Page<CheckRequestResource> getCheckRequestByStatus(@PathVariable Boolean value, Pageable pageable) {
        return mapper.modelListPage(checkRequestService.getByStatus(value), pageable);
    }

    @PostMapping("create-checkRequest")
    public ResponseEntity<CheckRequestResource> createCheckRequest(@RequestBody CreateCheckRequestResource resource) {
        return new ResponseEntity<>(mapper.toResource(checkRequestService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @PutMapping("updateCheckRequestById{checkRequestId}")
    public CheckRequestResource updateCheckRequest(@PathVariable Integer checkRequestId,
                                                   @RequestBody UpdateCheckRequestResource resource) {
        return mapper.toResource(checkRequestService.update(checkRequestId, mapper.toModel(resource)));
    }

    @DeleteMapping("deleteCheckRequestById/{checkRequestId}")
    public ResponseEntity<?> deleteCheckRequest(@PathVariable Integer checkRequestId) {
        return checkRequestService.delete(checkRequestId);
    }
}
