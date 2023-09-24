package com.digitalholics.iotheraphy.Appointment.api.rest;


import com.digitalholics.iotheraphy.Appointment.domain.service.AppointmentService;
import com.digitalholics.iotheraphy.Appointment.mapping.AppointmentMapper;
import com.digitalholics.iotheraphy.Appointment.resource.AppointmentResource;
import com.digitalholics.iotheraphy.Appointment.resource.CreateAppointmentResource;
import com.digitalholics.iotheraphy.Appointment.resource.UpdateAppointmentResource;
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

    @GetMapping("allAppointments")
    public Page<AppointmentResource> getAllAppointments(Pageable pageable) {
        return mapper.modelListPage(appointmentService.getAll(), pageable);
    }

    @GetMapping("{appointmentId}")
    public AppointmentResource getAppointmentById(@PathVariable Integer appointmentId) {
        return mapper.toResource(appointmentService.getById(appointmentId));
    }

    @GetMapping("appointment/{theraphyId}")
    public AppointmentResource getAppointmentByTheraphyId(@PathVariable Integer theraphyId) {
        return mapper.toResource(appointmentService.getAppointmentByTheraphyId(theraphyId));
    }


    @GetMapping("appointment/theraphy-patient/{patientId}")
    public Page<AppointmentResource> getAppointmentsByTheraphyByPatientId(@PathVariable Integer patientId, Pageable pageable) {
        return mapper.modelListPage(appointmentService.getAppointmentsByTheraphyByPatientId(patientId),pageable);
    }

    @GetMapping("appointment/theraphy-physiotherapist/{physiotherapistId}")
    public Page<AppointmentResource> getAppointmentsByTheraphyByPhysiotherapistId(@PathVariable Integer physiotherapistId, Pageable pageable) {
        return mapper.modelListPage(appointmentService.getAppointmentsByTheraphyByPhysiotherapistId(physiotherapistId), pageable);
    }



    @PostMapping("create_appintment")
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource resource) {
        return new ResponseEntity<>(mapper.toResource(appointmentService.create(mapper.toModel(resource))), HttpStatus.CREATED);
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
