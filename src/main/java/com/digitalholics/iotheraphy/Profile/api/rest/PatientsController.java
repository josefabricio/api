package com.digitalholics.iotheraphy.Profile.api.rest;

import com.digitalholics.iotheraphy.Profile.domain.service.PatientService;
import com.digitalholics.iotheraphy.Profile.mapping.PatientMapper;
import com.digitalholics.iotheraphy.Profile.resource.CreatePatientResource;
import com.digitalholics.iotheraphy.Profile.resource.PatientResource;
import com.digitalholics.iotheraphy.Profile.resource.UpdatePatientResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/patients", produces = "application/json")
@Tag(name = "Patients", description = "Create, read, update and delete patients")
@PreAuthorize("hasRole('PATIENT')")
public class PatientsController {
    private final PatientService patientService;

    private final PatientMapper mapper;

    public PatientsController(PatientService patientService, PatientMapper mapper) {
        this.patientService = patientService;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public Page<PatientResource> getAllPatients(Pageable pageable) {
        return mapper.modelListPage(patientService.getAll(), pageable);
    }

    @GetMapping("{patientId}")
    //@PreAuthorize("hasAuthority('patient:read')")
    public PatientResource getPatientById(@PathVariable Integer patientId) {
        return mapper.toResource(patientService.getById(patientId));
    }

    @GetMapping("userId={value}")
    public PatientResource getPatientByUserId(@PathVariable Integer value) {
        return mapper.toResource(patientService.getByUserId(value));
    }

    @PostMapping("registration-patient")
    @PreAuthorize("hasAuthority('patient:create')")
    public ResponseEntity<PatientResource> createPatient(@RequestBody CreatePatientResource resource) {
        return new ResponseEntity<>(mapper.toResource(patientService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @PutMapping("{patientId}")
    public PatientResource updatePatient(@PathVariable Integer patientId,
                                         @RequestBody UpdatePatientResource resource) {
        return mapper.toResource(patientService.update(patientId, mapper.toModel(resource)));
    }

    @DeleteMapping("{patientId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer patientId) {
        return patientService.delete(patientId);
    }
}