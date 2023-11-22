package com.digitalholics.iotheraphy.iotmanagement.interfaces.rest;

import com.digitalholics.iotheraphy.iotmanagement.domain.services.IotDeviceQueryService;
import com.digitalholics.iotheraphy.iotmanagement.interfaces.rest.transform.IotResultMapper;
import com.digitalholics.iotheraphy.iotmanagement.interfaces.rest.resources.CreateIotResultResource;
import com.digitalholics.iotheraphy.iotmanagement.interfaces.rest.resources.IotResultResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/iotResults", produces = "application/json")
@Tag(name = "IotResult", description = "Create, read, update and delete iotResults")
public class IotResultController {

    private final IotDeviceQueryService iotResultService;

    private final IotResultMapper mapper;

    public IotResultController(IotDeviceQueryService iotResultService, IotResultMapper mapper) {
        this.iotResultService = iotResultService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<IotResultResource> getAllConsultations(Pageable pageable) {
        return mapper.modelListPage(iotResultService.getAll(), pageable);
    }

    @GetMapping("{resultId}")
    public IotResultResource getResultById(@PathVariable Integer resultId) {
        return mapper.toResource(iotResultService.getById(resultId));
    }

    @GetMapping("byTherapyId/{therapyId}")
    public IotResultResource getResultByTreatmentId(@PathVariable String therapyId) {
        return mapper.toResource(iotResultService.getByTherapyId(therapyId));
    }

    @GetMapping("byTherapyId/{therapyId}/Date/{date}")
    public Page<IotResultResource> getByTherapyIdAndDate(@PathVariable String therapyId, @PathVariable String date, Pageable pageable) {
        return mapper.modelListPage(iotResultService.getByTherapyIdAndDate(therapyId, date), pageable);
    }

    @PostMapping
    public ResponseEntity<IotResultResource> createConsultation(@RequestBody CreateIotResultResource resource) {
        return new ResponseEntity<>(mapper.toResource(iotResultService.create(resource)), HttpStatus.CREATED);
    }

    @DeleteMapping("{resultId}")
    public ResponseEntity<?> deleteResult(@PathVariable Integer resultId) {
        return iotResultService.delete(resultId);
    }

}
