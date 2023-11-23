package com.digitalholics.iotheraphy.theraphy.api.rest;

import com.digitalholics.iotheraphy.theraphy.domain.service.TreatmentService;
import com.digitalholics.iotheraphy.theraphy.mapping.TreatmentMapper;
import com.digitalholics.iotheraphy.theraphy.resource.Treatment.CreateTreatmentResource;
import com.digitalholics.iotheraphy.theraphy.resource.Treatment.TreatmentResource;
import com.digitalholics.iotheraphy.theraphy.resource.Treatment.UpdateTreatmentResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/treatments", produces = "application/json")
@Tag(name = "Treatments", description = "Create, read, update and delete treatments")
public class TreatmentsController {
    private final TreatmentService treatmentService;

    private final TreatmentMapper mapper;

    public TreatmentsController(TreatmentService treatmentService, TreatmentMapper mapper) {
        this.treatmentService = treatmentService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<TreatmentResource> getAllTreatments(Pageable pageable) {
        return mapper.modelListPage(treatmentService.getAll(), pageable);
    }

    @GetMapping("{treatmentId}")
    public TreatmentResource getTreatmentById(@PathVariable Integer treatmentId) {
        return mapper.toResource(treatmentService.getById(treatmentId));
    }


    @GetMapping("byTherapyId/{therapyId}")
    public Page<TreatmentResource> getTreatmentByTherapyId(@PathVariable Integer therapyId, Pageable pageable){
        return mapper.modelListPage(treatmentService.getTreatmentByTherapyId(therapyId), pageable);
    }

    @GetMapping("byDate/{date}/TherapyId/{therapyId}")
    public  TreatmentResource getTreatmentByDateAndTherapyId(@PathVariable String date, @PathVariable Integer therapyId) {
        return mapper.toResource(treatmentService.getTreatmentByDateAndTherapyId(therapyId, date));
    }

    @PostMapping
    public ResponseEntity<TreatmentResource> createTreatment(@RequestBody CreateTreatmentResource resource) {
        return new ResponseEntity<>(mapper.toResource(treatmentService.create(resource)), HttpStatus.CREATED);
    }

    @PatchMapping("{treatmentId}")
    public ResponseEntity<TreatmentResource> patchTreatment(
            @PathVariable Integer treatmentId,
            @RequestBody UpdateTreatmentResource request) {

        return new  ResponseEntity<>(mapper.toResource(treatmentService.update(treatmentId,request)), HttpStatus.CREATED);
    }

    @DeleteMapping("{treatmentId}")
    public ResponseEntity<?> deleteTreatment(@PathVariable Integer treatmentId) {
        return treatmentService.delete(treatmentId);
    }



}
