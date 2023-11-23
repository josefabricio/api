package com.digitalholics.iotheraphy.profile.interfaces.rest;


import com.digitalholics.iotheraphy.profile.domain.services.DiagnosisService;
import com.digitalholics.iotheraphy.profile.interfaces.rest.transform.entitiesmapper.DiagnosisMapper;

import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.DiagnosisResource;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/diagnoses", produces = "application/json")
@Tag(name = "Diagnoses", description = "Read and delete diagnoses")
public class DiagnosesController {

    private final DiagnosisService diagnosisService;

    private final DiagnosisMapper mapper;

    public DiagnosesController(DiagnosisService diagnosisService, DiagnosisMapper mapper) {
        this.diagnosisService = diagnosisService;
        this.mapper = mapper;
    }

    @GetMapping
    public DiagnosisResource getLast() {
        return mapper.toResource(diagnosisService.getLast());
    }

    @GetMapping("byPatientId/{patientId}")
    public Page<DiagnosisResource> getDiagnosisByPatientId(@PathVariable Integer patientId, Pageable pageable) {
        return mapper.modelListPage(diagnosisService.getByPatientId(patientId), pageable);
    }
}
