package com.digitalholics.iotheraphy.Profiless.api.rest;


import com.digitalholics.iotheraphy.Profiless.domain.service.PhysiotherapistService;
import com.digitalholics.iotheraphy.Profiless.mapping.PhysiotherapistMapper;
import com.digitalholics.iotheraphy.Profiless.resource.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public Page<PhysiotherapistResource> getAllPPhysiotherapist(Pageable pageable) {
        return mapper.modelListPage(physiotherapistService.getAll(), pageable);
    }

    @GetMapping("{physiotherapistId}")
    //@PreAuthorize("hasAuthority('patient:read')")
    public PhysiotherapistResource getPhysiotherapistById(@PathVariable Integer physiotherapistId) {
        return mapper.toResource(physiotherapistService.getById(physiotherapistId));
    }

    @GetMapping("byUserId/{userId}")
    public PhysiotherapistResource getPhysiotherapistByUserId(@PathVariable Integer userId) {
        return mapper.toResource(physiotherapistService.getByUserId(userId));
    }

    @GetMapping("/profile")
    public PhysiotherapistResource getLoggedInPhysiotherapistProfile() {
        return mapper.toResource(physiotherapistService.getLoggedInPhysiotherapist());
    }

    @PostMapping("registration-physiotherapist")
    public ResponseEntity<PhysiotherapistResource> createPhysiotherapist(@RequestBody CreatePhysiotherapistResource resource) {
        return new ResponseEntity<>(mapper.toResource(physiotherapistService.create(resource)), HttpStatus.CREATED);
    }

    @PatchMapping("{physiotherapistId}")
    public ResponseEntity<PhysiotherapistResource> patchPhysiotherapist(
            @PathVariable Integer physiotherapistId,
            @RequestBody UpdatePhysiotherapistResource request) {

        return new  ResponseEntity<>(mapper.toResource(physiotherapistService.update(physiotherapistId,request)), HttpStatus.CREATED);
    }

    @DeleteMapping("{physiotherapistId}")
    public ResponseEntity<?> deletePhysiotherapist(@PathVariable Integer physiotherapistId) {
        return physiotherapistService.delete(physiotherapistId);
    }
}
