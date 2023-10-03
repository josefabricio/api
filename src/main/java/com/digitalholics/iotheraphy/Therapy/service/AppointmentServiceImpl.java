package com.digitalholics.iotheraphy.Therapy.service;

import com.digitalholics.iotheraphy.Therapy.domain.model.entity.Appointment;
import com.digitalholics.iotheraphy.Therapy.domain.persistence.AppointmentRepository;
import com.digitalholics.iotheraphy.Therapy.domain.service.AppointmentService;
import com.digitalholics.iotheraphy.Therapy.resource.Appointment.CreateAppointmentResource;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceValidationException;
import com.digitalholics.iotheraphy.Shared.Exception.UnauthorizedException;
import com.digitalholics.iotheraphy.Therapy.domain.model.entity.Therapy;
import com.digitalholics.iotheraphy.Therapy.domain.persistence.TherapyRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final String ENTITY = "Appointment";

    private final AppointmentRepository appointmentRepository;
    private final TherapyRepository therapyRepository;

    private final Validator validator;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, TherapyRepository therapyRepository, Validator validator) {
        this.appointmentRepository = appointmentRepository;
        this.therapyRepository = therapyRepository;
        this.validator = validator;
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Page<Appointment> getAll(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    @Override
    public Appointment getById(Integer appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentId));
    }

    @Override
    public Appointment getAppointmentByTherapyId(Integer therapyId) {
        return appointmentRepository.findAppointmentByTherapyId(therapyId);
    }

    @Override
    public Appointment create(CreateAppointmentResource appointmentResource) {
        Set<ConstraintViolation<CreateAppointmentResource>> violations = validator.validate(appointmentResource);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Appointment appointmentWithTopic = appointmentRepository.findByTopic(appointmentResource.getTopic());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if(appointmentWithTopic != null)
            throw new ResourceValidationException(ENTITY,
                    "A Appointment with the same topic already exists.");

        Optional<Therapy> therapyOptional = therapyRepository.findById(appointmentResource.getTherapyId());

        Therapy therapy = therapyOptional.orElseThrow(()-> new NotFoundException("This therapy not found with ID: "+ appointmentResource.getTherapyId()));
        Appointment appointment = new Appointment();

        if (therapy.getPatientId().getUser().getUsername().equals(username) || therapy.getPhysiotherapistId().getUser().getUsername().equals(username)){
            appointment.setDone(appointmentResource.getDone());
            appointment.setTopic(appointmentResource.getTopic());
            appointment.setDiagnosis(appointmentResource.getDiagnosis());
            appointment.setDate(appointmentResource.getDate());
            appointment.setHour(appointmentResource.getHour());
            appointment.setPlace(appointmentResource.getPlace());
            appointment.setTherapy(therapy);
            return appointmentRepository.save(appointment);
        }else {
            throw new UnauthorizedException("You do not have permission to create an appointment for this therapy.");
        }
    }

    @Override
    public Appointment update(Integer appointmentId, Appointment request) {
        Set<ConstraintViolation<Appointment>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return appointmentRepository.findById(appointmentId).map(appointment ->
                        appointmentRepository.save(
                                appointment.withTopic(request.getTopic())
                                        .withDiagnosis(request.getDiagnosis())
                                        .withDone(request.getDone())
                                        .withPlace(request.getPlace())
                                        .withHour(request.getHour())
                                        .withDate(request.getDate())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentId));
    }

    @Override
    public ResponseEntity<?> delete(Integer appointmentId) {
        return appointmentRepository.findById(appointmentId).map( appointment -> {
            appointmentRepository.delete(appointment);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,appointmentId));
    }

    @Override
    public List<Appointment> getAppointmentsByTherapyByPatientId(Integer patientId) {
        return appointmentRepository.findAppointmentsByTherapyByPatientId(patientId);
    }

    @Override
    public List<Appointment> getAppointmentsByTherapyByPhysiotherapistId(Integer physiotherapistId) {
        return appointmentRepository.findAppointmentsByTherapyByPhysiotherapistId(physiotherapistId);
    }
}
