package com.digitalholics.iotheraphy.Consultation.api.rest;


import com.digitalholics.iotheraphy.Consultation.domain.service.AvailableHourService;
import com.digitalholics.iotheraphy.Consultation.mapping.AvailableHourMapper;
import com.digitalholics.iotheraphy.Consultation.resource.AvailableHour.AvailableHourResource;
import com.digitalholics.iotheraphy.Consultation.resource.AvailableHour.CreateAvailableHourResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/available-hours", produces = "application/json")
@Tag(name = "AvailableHours", description = "Create, read, update and delete available hours")
public class AvailableHoursController {

    private final AvailableHourService availableHourService;

    private final AvailableHourMapper mapper;

    public AvailableHoursController(AvailableHourService availableHourService, AvailableHourMapper mapper) {
        this.availableHourService = availableHourService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<AvailableHourResource> getAllAvailableHours(Pageable pageable) {
        return mapper.modelListPage(availableHourService.getAll(), pageable);
    }

    @GetMapping("{availableHourId}")
    public AvailableHourResource getAvailableHourById(@PathVariable Integer availableHourId) {
        return mapper.toResource(availableHourService.getById(availableHourId));
    }

    @GetMapping("byPhysiotherapistId/{physiotherapistId}")
    public Page<AvailableHourResource> getAvailableHoursByPhysiotherapistId(@PathVariable Integer physiotherapistId, Pageable pageable) {
        return mapper.modelListPage(availableHourService.getByPhysiotherapistId(physiotherapistId), pageable);
    }

    @PostMapping
    public ResponseEntity<AvailableHourResource> createAvailableHour(@RequestBody CreateAvailableHourResource resource){
        return new ResponseEntity<>(mapper.toResource(availableHourService.create(resource)), HttpStatus.CREATED);
    }



    @DeleteMapping("{availableHourId}")
    public ResponseEntity<?> deleteAvailableHour(@PathVariable Integer availableHourId) {
        return availableHourService.delete(availableHourId);
    }
}
