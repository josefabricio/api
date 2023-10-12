package com.digitalholics.iotheraphy.Therapy.api.rest;


import com.digitalholics.iotheraphy.Therapy.domain.service.TherapyService;
import com.digitalholics.iotheraphy.Therapy.mapping.TherapyMapper;
import com.digitalholics.iotheraphy.Therapy.resource.Therapy.CreateTherapyResource;
import com.digitalholics.iotheraphy.Therapy.resource.Therapy.TherapyResource;
import com.digitalholics.iotheraphy.Therapy.resource.Therapy.UpdateTherapyResource;
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
