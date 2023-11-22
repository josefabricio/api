package com.digitalholics.iotheraphy.Consultationsss.api.rest;

import com.digitalholics.iotheraphy.Consultationsss.domain.service.ConsultationService;
import com.digitalholics.iotheraphy.Consultationsss.mapping.ConsultationMapper;
import com.digitalholics.iotheraphy.Consultationsss.resource.ConsultationResource;
import com.digitalholics.iotheraphy.Consultationsss.resource.CreateConsultationResource;
import com.digitalholics.iotheraphy.Consultationsss.resource.UpdateConsultationResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/consultations", produces = "application/json")
@Tag(name = "Consultations", description = "Create, read, update and delete consultations")
public class ConsultationsController {

    private final ConsultationService consultationService;
    private final ConsultationMapper mapper;

    public ConsultationsController(ConsultationService consultationService, ConsultationMapper mapper) {
        this.consultationService = consultationService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<ConsultationResource> getAllConsultations(Pageable pageable) {
        return mapper.modelListPage(consultationService.getAll(), pageable);
    }

    @GetMapping("{consultationId}")
    public ConsultationResource getConsultationById(@PathVariable Integer consultationId) {
        return mapper.toResource(consultationService.getById(consultationId));
    }

    @GetMapping("byPatientId/{patientId}")
    public Page<ConsultationResource> getConsultationsByPatientId(@PathVariable Integer patientId, Pageable pageable) {
        return mapper.modelListPage(consultationService.getByPatientId(patientId), pageable);
    }

    @GetMapping("byPhysiotherapistId/{physiotherapistId}")
    public Page<ConsultationResource> getConsultationsByPhysiotherapistId(@PathVariable Integer physiotherapistId, Pageable pageable) {
        return mapper.modelListPage(consultationService.getByPhysiotherapistId(physiotherapistId), pageable);
    }

    @GetMapping("consultationByPhysiotherapistId/{physiotherapistId}")
    public ConsultationResource getConsultationByPhysiotherapistId(@PathVariable Integer physiotherapistId) {
        return mapper.toResource(consultationService.getConsultationByPhysiotherapistId(physiotherapistId));
    }

    @PostMapping
    public ResponseEntity<ConsultationResource> createConsultation(@RequestBody CreateConsultationResource resource) {
        return new ResponseEntity<>(mapper.toResource(consultationService.create(resource)), HttpStatus.CREATED);
    }

    @PatchMapping("{consultationId}")
    public ResponseEntity<ConsultationResource> patchConsultation(
            @PathVariable Integer consultationId,
            @RequestBody UpdateConsultationResource request) {

        return new ResponseEntity<>(mapper.toResource(consultationService.update(consultationId,request)), HttpStatus.CREATED);
    }

    @DeleteMapping("{consultationId}")
    public ResponseEntity<?> deleteConsultation(@PathVariable Integer consultationId) {
        return consultationService.delete(consultationId);
    }

    @PatchMapping("updateDiagnosis/{consultationId}")
    public ResponseEntity<ConsultationResource> updateConsultationDiagnosis(
            @PathVariable Integer consultationId,
            @RequestBody String diagnosis) {

        return new  ResponseEntity<>(mapper.toResource(consultationService.updateDiagnosis(consultationId,diagnosis)), HttpStatus.CREATED);
    }
}
