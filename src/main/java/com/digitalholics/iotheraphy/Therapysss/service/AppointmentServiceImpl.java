package com.digitalholics.iotheraphy.Therapysss.service;

import com.digitalholics.iotheraphy.profile.domain.model.entities.Diagnosis;
import com.digitalholics.iotheraphy.profile.infrastructure.jpa.repositories.DiagnosisRepository;
import com.digitalholics.iotheraphy.Therapysss.domain.model.entity.Appointment;
import com.digitalholics.iotheraphy.Therapysss.domain.persistence.AppointmentRepository;
import com.digitalholics.iotheraphy.Therapysss.domain.service.AppointmentService;
import com.digitalholics.iotheraphy.Therapysss.resource.Appointment.CreateAppointmentResource;
import com.digitalholics.iotheraphy.shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.shared.Exception.ResourceValidationException;
import com.digitalholics.iotheraphy.shared.Exception.UnauthorizedException;
import com.digitalholics.iotheraphy.Therapysss.domain.model.entity.Therapy;
import com.digitalholics.iotheraphy.Therapysss.domain.persistence.TherapyRepository;
import com.digitalholics.iotheraphy.Therapysss.resource.Appointment.UpdateAppointmentResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final String ENTITY = "Appointment";

    private final AppointmentRepository appointmentRepository;
    private final TherapyRepository therapyRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final Validator validator;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, TherapyRepository therapyRepository, DiagnosisRepository diagnosisRepository, Validator validator) {
        this.appointmentRepository = appointmentRepository;
        this.therapyRepository = therapyRepository;
        this.diagnosisRepository = diagnosisRepository;
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
    public List<Appointment> getAppointmentByTherapyId(Integer therapyId) {
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

        if (therapy.getPatient().getUser().getUsername().equals(username) || therapy.getPhysiotherapist().getUser().getUsername().equals(username)){
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
    public Appointment update(Integer appointmentId, UpdateAppointmentResource request) {
        Appointment appointment = getById(appointmentId);

        if (request.getDone() != null) {
            appointment.setDone(request.getDone());
        }
        if (request.getTopic() != null) {
            appointment.setTopic(request.getTopic());
        }
        if (request.getDiagnosis() != null) {
            appointment.setDiagnosis(request.getDiagnosis());
        }
        if (request.getDate() != null) {
            appointment.setDate(request.getDate());
        }
        if (request.getHour() != null) {
            appointment.setHour(request.getHour());
        }
        if (request.getPlace() != null) {
            appointment.setPlace(request.getPlace());
        }



        return appointmentRepository.save(appointment);
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

    @Override
    public Appointment getAppointmentByDateAndTherapyId(Integer therapyId, String date) {
        return appointmentRepository.findAppointmentByDateAndTherapyId(therapyId, date);
    }

    @Override
    public Appointment updateDiagnosis(Integer appointmentId, String diagnosis) {
        Appointment appointment = getById(appointmentId);

        if (diagnosis != null) {
            appointment.setDiagnosis(diagnosis);
        }
        if (!appointment.getDone()) {
            appointment.setDone(true);
        }

        Diagnosis diagnosisResource = new Diagnosis();
        diagnosisResource.setDiagnosis(diagnosis);
        diagnosisResource.setPatient(appointment.getTherapy().getPatient());
        diagnosisResource.setPhysiotherapist(appointment.getTherapy().getPhysiotherapist());
        diagnosisResource.setDate(String.valueOf(LocalDate.now()));

        diagnosisRepository.save(diagnosisResource);

        return appointmentRepository.save(appointment);
    }
}
