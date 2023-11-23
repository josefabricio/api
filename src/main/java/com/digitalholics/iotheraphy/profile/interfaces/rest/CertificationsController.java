package com.digitalholics.iotheraphy.profile.interfaces.rest;

import com.digitalholics.iotheraphy.profile.domain.services.CertificationService;
import com.digitalholics.iotheraphy.profile.interfaces.rest.transform.entitiesmapper.CertificationMapper;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.Certification.CertificationResource;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.Certification.CreateCertificationResource;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.Certification.UpdateCertificationResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/certifications", produces = "application/json")
@Tag(name = "Certifications", description = "Create, read, update and delete certifications")
public class CertificationsController {
    private final CertificationService certificationService;

    private final CertificationMapper mapper;


    public CertificationsController(CertificationService certificationService, CertificationMapper mapper) {
        this.certificationService = certificationService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<CertificationResource> getAllCertifications(Pageable pageable) {
        return mapper.modelListPage(certificationService.getAll(), pageable);
    }

    @GetMapping("{certificationId}")
    public CertificationResource getCertificationById(@PathVariable Integer certificationId) {
        return mapper.toResource(certificationService.getById(certificationId));
    }

    @GetMapping("byPhysiotherapistId/{physiotherapistId}")
    public Page<CertificationResource> getCertificationsByPhysiotherapistId(@PathVariable Integer physiotherapistId, Pageable pageable) {
        return mapper.modelListPage(certificationService.getByPhysiotherapistId(physiotherapistId), pageable);
    }

    @PostMapping
    public ResponseEntity<CertificationResource> createCertification(@RequestBody CreateCertificationResource resource) {
        return new ResponseEntity<>(mapper.toResource(certificationService.create(resource)), HttpStatus.CREATED);
    }

    @PatchMapping("{certificationId}")
    public ResponseEntity<CertificationResource> patchCertification(@PathVariable Integer certificationId,
                                                                    @RequestBody UpdateCertificationResource request) {

        return new ResponseEntity<>(mapper.toResource(certificationService.update(certificationId,request)), HttpStatus.CREATED);
    }

    @DeleteMapping("{certificationId}")
    public ResponseEntity<?> deleteCertification(@PathVariable Integer certificationId) {
        return certificationService.delete(certificationId);
    }

}
