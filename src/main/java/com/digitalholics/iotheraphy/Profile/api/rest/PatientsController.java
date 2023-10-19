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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/patients", produces = "application/json")
@Tag(name = "Patients", description = "Create, read, update and delete patients")
public class PatientsController {
    private final PatientService patientService;

    private final PatientMapper mapper;

    public PatientsController(PatientService patientService, PatientMapper mapper) {
        this.patientService = patientService;
        this.mapper = mapper;
    }

    @GetMapping("/profile")
    public PatientResource getLoggedInPatientProfile() {
        return mapper.toResource(patientService.getLoggedInPatient());
    }

    @GetMapping
    public Page<PatientResource> getAllPatients(Pageable pageable) {
        return mapper.modelListPage(patientService.getAll(), pageable);
    }

    @GetMapping("{patientId}")
    public PatientResource getPatientById(@PathVariable Integer patientId) {
        return mapper.toResource(patientService.getById(patientId));
    }

    @GetMapping("byUserId/{userId}")
    public PatientResource getPatientByUserId(@PathVariable Integer userId) {
        return mapper.toResource(patientService.getByUserId(userId));
    }

    @PostMapping("registration-patient")
    public ResponseEntity<PatientResource> createPatient(@RequestBody CreatePatientResource resource) {
        return new ResponseEntity<>(mapper.toResource(patientService.create(resource)), HttpStatus.CREATED);
    }

    @PatchMapping("{patientId}")
    public ResponseEntity<PatientResource> patchPatient(
            @PathVariable Integer patientId,
            @RequestBody UpdatePatientResource request) {

        return new  ResponseEntity<>(mapper.toResource(patientService.update(patientId,request)), HttpStatus.CREATED);
    }

    @DeleteMapping("{patientId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer patientId) {
        return patientService.delete(patientId);
    }
}