package com.digitalholics.iotheraphy.Profile.api.rest;

import com.digitalholics.iotheraphy.Profile.domain.service.PatientService;
import com.digitalholics.iotheraphy.Profile.domain.service.PhysiotherapistService;
import com.digitalholics.iotheraphy.Profile.mapping.PatientMapper;
import com.digitalholics.iotheraphy.Profile.mapping.PhysiotherapistMapper;
import com.digitalholics.iotheraphy.Profile.resource.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/physiotherapists", produces = "application/json")
@Tag(name = "Physiotherapists", description = "Create, read, update and delete physiotherapists")
public class PhysiotherapistController {
    private final PhysiotherapistService physiotherapistService;

    private final PhysiotherapistMapper mapper;

    public PhysiotherapistController(PhysiotherapistService physiotherapistService, PhysiotherapistMapper mapper) {
        this.physiotherapistService = physiotherapistService;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public Page<PhysiotherapistResource> getAllPPhysiotherapist(Pageable pageable) {
        return mapper.modelListPage(physiotherapistService.getAll(), pageable);
    }

    @GetMapping("{physiotherapistId}")
    //@PreAuthorize("hasAuthority('patient:read')")
    public PhysiotherapistResource getPhysiotherapistById(@PathVariable Integer physiotherapistId) {
        return mapper.toResource(physiotherapistService.getById(physiotherapistId));
    }

    @GetMapping("userId={value}")
    public PhysiotherapistResource getPhysiotherapistByUserId(@PathVariable Integer value) {
        return mapper.toResource(physiotherapistService.getByUserId(value));
    }

    @PostMapping("registration-physiotherapist")
    public ResponseEntity<PhysiotherapistResource> createPhysiotherapist(@RequestBody CreatePhysiotherapistResource resource) {
        return new ResponseEntity<>(mapper.toResource(physiotherapistService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @PutMapping("{physiotherapistId}")
    public PhysiotherapistResource updatePhysiotherapist(@PathVariable Integer physiotherapistId,
                                         @RequestBody UpdatePhysiotherapistResource resource) {
        return mapper.toResource(physiotherapistService.update(physiotherapistId, mapper.toModel(resource)));
    }

    @DeleteMapping("{physiotherapistId}")
    public ResponseEntity<?> deletePhysiotherapist(@PathVariable Integer physiotherapistId) {
        return physiotherapistService.delete(physiotherapistId);
    }
}
