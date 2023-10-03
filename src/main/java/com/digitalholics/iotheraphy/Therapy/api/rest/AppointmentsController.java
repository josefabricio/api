package com.digitalholics.iotheraphy.Therapy.api.rest;


import com.digitalholics.iotheraphy.Therapy.domain.service.AppointmentService;
import com.digitalholics.iotheraphy.Therapy.mapping.AppointmentMapper;
import com.digitalholics.iotheraphy.Therapy.resource.Appointment.AppointmentResource;
import com.digitalholics.iotheraphy.Therapy.resource.Appointment.CreateAppointmentResource;
import com.digitalholics.iotheraphy.Therapy.resource.Appointment.UpdateAppointmentResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/appointments", produces = "application/json")
@Tag(name = "Appointments", description = "Create, read, update and delete appointments")
public class AppointmentsController {
    private final AppointmentService appointmentService;
    private final AppointmentMapper mapper;

    public AppointmentsController(AppointmentService appointmentService, AppointmentMapper mapper) {
        this.appointmentService = appointmentService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<AppointmentResource> getAllAppointments(Pageable pageable) {
        return mapper.modelListPage(appointmentService.getAll(), pageable);
    }

    @GetMapping("{appointmentId}")
    public AppointmentResource getAppointmentById(@PathVariable Integer appointmentId) {
        return mapper.toResource(appointmentService.getById(appointmentId));
    }

    @GetMapping("byTherapyId/{therapyId}")
    public AppointmentResource getAppointmentByTherapyId(@PathVariable Integer therapyId) {
        return mapper.toResource(appointmentService.getAppointmentByTherapyId(therapyId));
    }


    @GetMapping("appointment/therapy-patient/{patientId}")
    public Page<AppointmentResource> getAppointmentsByTherapyByPatientId(@PathVariable Integer patientId, Pageable pageable) {
        return mapper.modelListPage(appointmentService.getAppointmentsByTherapyByPatientId(patientId),pageable);
    }

    @GetMapping("appointment/therapy-physiotherapist/{physiotherapistId}")
    public Page<AppointmentResource> getAppointmentsByTherapyByPhysiotherapistId(@PathVariable Integer physiotherapistId, Pageable pageable) {
        return mapper.modelListPage(appointmentService.getAppointmentsByTherapyByPhysiotherapistId(physiotherapistId), pageable);
    }

    @PostMapping
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource resource) {
        return new ResponseEntity<>(mapper.toResource(appointmentService.create((resource))), HttpStatus.CREATED);
    }

    @PutMapping("{appointmentId}")
    public AppointmentResource updateAppointment(@PathVariable Integer appointmentId,
                                                 @RequestBody UpdateAppointmentResource resource) {
        return mapper.toResource(appointmentService.update(appointmentId, mapper.toModel(resource)));
    }

    @DeleteMapping("{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Integer appointmentId) {
        return appointmentService.delete(appointmentId);
    }

}
