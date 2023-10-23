package com.digitalholics.iotheraphy.IoTAccessManagement.api.res;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.service.ResultService;
import com.digitalholics.iotheraphy.IoTAccessManagement.mapping.ResultMapper;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.CreateResultResource;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.ResultResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/result", produces = "application/json")
@Tag(name = "Result", description = "Create, read, update and delete iotDevice")
public class ResultController {

    private final ResultService resultService;

    private final ResultMapper mapper;

    public ResultController(ResultService resultService, ResultMapper mapper) {
        this.resultService = resultService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<ResultResource> getAllConsultations(Pageable pageable) {
        return mapper.modelListPage(resultService.getAll(), pageable);
    }

    @GetMapping("{resultId}")
    public ResultResource getResultById(@PathVariable Integer resultId) {
        return mapper.toResource(resultService.getById(resultId));
    }

    @GetMapping("result/{treatmentId}")
    public ResultResource getResultByTreatmentId(@PathVariable Integer treatmentId) {
        return mapper.toResource(resultService.getByTreatmentId(treatmentId));
    }

    @PostMapping
    public ResponseEntity<ResultResource> createConsultation(@RequestBody CreateResultResource resource) {
        return new ResponseEntity<>(mapper.toResource(resultService.create(resource)), HttpStatus.CREATED);
    }

    @DeleteMapping("{resultId}")
    public ResponseEntity<?> deleteResult(@PathVariable Integer resultId) {
        return resultService.delete(resultId);
    }

}
