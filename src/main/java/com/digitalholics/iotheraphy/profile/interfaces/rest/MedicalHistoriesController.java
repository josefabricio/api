package com.digitalholics.iotheraphy.profile.interfaces.rest;

import com.digitalholics.iotheraphy.profile.domain.services.MedicalHistoryService;
import com.digitalholics.iotheraphy.profile.interfaces.rest.transform.entitiesmapper.MedicalHistoryMapper;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.MedicalHistory.CreateMedicalHistoryResource;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.MedicalHistory.MedicalHistoryResource;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.MedicalHistory.UpdateMedicalHistoryResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/medical-histories", produces = "application/json")
@Tag(name = "MedicalHistories", description = "Create, read, update and delete medical histories")
public class MedicalHistoriesController {

    private final MedicalHistoryService medicalHistoryService;
    private final MedicalHistoryMapper mapper;

    public MedicalHistoriesController(MedicalHistoryService medicalHistoryService, MedicalHistoryMapper mapper) {
        this.medicalHistoryService = medicalHistoryService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<MedicalHistoryResource> getAllMedicalHistories(Pageable pageable) {
        return mapper.modelListPage(medicalHistoryService.getAll(), pageable);
    }

    @GetMapping("{medicalHistoryId}")
    public MedicalHistoryResource getMedicalHistoryById(@PathVariable Integer medicalHistoryId) {
        return mapper.toResource(medicalHistoryService.getById(medicalHistoryId));
    }

    @GetMapping("byPatientId/{patientId}")
    public MedicalHistoryResource getMedicalHistoryByPatientId(@PathVariable Integer patientId, Pageable pageable) {
        return mapper.toResource(medicalHistoryService.getByPatientId(patientId));
    }

    @PostMapping
    public ResponseEntity<MedicalHistoryResource> createMedicalHistory(@RequestBody CreateMedicalHistoryResource resource) {
        return new ResponseEntity<>(mapper.toResource(medicalHistoryService.create(resource)), HttpStatus.CREATED);
    }

    @PatchMapping("{medicalHistoryId}")
    public ResponseEntity<MedicalHistoryResource> patchMedicalHistory(
            @PathVariable Integer medicalHistoryId,
            @RequestBody UpdateMedicalHistoryResource request) {

        return new  ResponseEntity<>(mapper.toResource(medicalHistoryService.update(medicalHistoryId,request)), HttpStatus.CREATED);
    }

    @DeleteMapping("{medicalHistoryId}")
    public ResponseEntity<?> deleteMedicalHistory(@PathVariable Integer medicalHistoryId) {
        return medicalHistoryService.delete(medicalHistoryId);
    }
}
