package com.digitalholics.iotheraphy.Therapysss.api.rest;


import com.digitalholics.iotheraphy.Therapysss.domain.service.TherapyService;
import com.digitalholics.iotheraphy.Therapysss.mapping.TherapyMapper;
import com.digitalholics.iotheraphy.Therapysss.resource.Therapy.CreateTherapyResource;
import com.digitalholics.iotheraphy.Therapysss.resource.Therapy.TherapyResource;
import com.digitalholics.iotheraphy.Therapysss.resource.Therapy.UpdateTherapyResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/therapies", produces = "application/json")
@Tag(name = "Therapies", description = "Create, read, update and delete therapies")
public class TherapiesController {

    private final TherapyService therapyService;

    private final TherapyMapper mapper;

    public TherapiesController(TherapyService therapyService, TherapyMapper mapper) {
        this.therapyService = therapyService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<TherapyResource> getAllTherapies(Pageable pageable) {
        return mapper.modelListPage(therapyService.getAll(), pageable);
    }

    @GetMapping("{therapyId}")
    public TherapyResource getTherapyById(@PathVariable Integer therapyId) {
        return mapper.toResource(therapyService.getById(therapyId));
    }


    @GetMapping("byPatientId/{patientId}")
    public Page<TherapyResource> getTherapyByPatientId(@PathVariable Integer patientId, Pageable pageable) {
        return mapper.modelListPage(therapyService.getTherapyByPatientId(patientId), pageable);
    }

    @GetMapping("activeTherapyByPatientId")
    public TherapyResource getActiveTherapyByPatientId() {
        return mapper.toResource(therapyService.getActiveTherapyByPatientId());
    }

    @GetMapping("byPhysioAndPatient/{physiotherapistId}/{patientId}")
    public TherapyResource getTherapyByPhysiotherapistIdAndPatientId(@PathVariable Integer patientId, @PathVariable Integer physiotherapistId){
        return mapper.toResource((therapyService.getTherapyByPhysiotherapistIdAndPatientId(physiotherapistId, patientId)));
    }

    @PostMapping
    public ResponseEntity<TherapyResource> createTherapy(@RequestBody CreateTherapyResource resource) {
        return new ResponseEntity<>(mapper.toResource(therapyService.create((resource))), HttpStatus.CREATED);
    }

    @PatchMapping("{therapyId}")
    public ResponseEntity<TherapyResource> patchTherapy(
            @PathVariable Integer therapyId,
            @RequestBody UpdateTherapyResource request) {

        return new  ResponseEntity<>(mapper.toResource(therapyService.update(therapyId,request)), HttpStatus.CREATED);
    }

    @DeleteMapping("{therapyId}")
    public ResponseEntity<?> deleteTherapy(@PathVariable Integer therapyId) {
        return therapyService.delete(therapyId);
    }
}
