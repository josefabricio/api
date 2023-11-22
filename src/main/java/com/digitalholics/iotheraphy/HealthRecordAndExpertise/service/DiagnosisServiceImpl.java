package com.digitalholics.iotheraphy.HealthRecordAndExpertise.service;

import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.Diagnosis;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.persistence.DiagnosisRepository;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.service.DiagnosisService;
import com.digitalholics.iotheraphy.Profiless.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profiless.domain.persistence.PatientRepository;
import com.digitalholics.iotheraphy.shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.shared.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {
    private static final String ENTITY = "Diagnosis";

    private final DiagnosisRepository diagnosisRepository;

    private final PatientRepository patientRepository;
    private final Validator validator;


    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository, PatientRepository patientRepository, Validator validator) {
        this.diagnosisRepository = diagnosisRepository;
        this.patientRepository = patientRepository;
        this.validator = validator;
    }

    @Override
    public Diagnosis getLast() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Patient> patientOptional = Optional.ofNullable(patientRepository.findPatientsByUserUsername(username));
        Patient patient = patientOptional.orElseThrow(() -> new NotFoundException("Not found patient with email: " + username));


        List<Diagnosis> allDiagnoses = diagnosisRepository.findByPatientId(patient.getId());

        if (allDiagnoses.isEmpty()) {
            throw new ResourceNotFoundException("Diagnoses not found.");
        }

        allDiagnoses.sort(Comparator.comparing(Diagnosis::getId).reversed());

        return allDiagnoses.get(0);
    }

    @Override
    public List<Diagnosis> getByPatientId(Integer patientId) {
        List<Diagnosis> diagnosis = diagnosisRepository.findByPatientId(patientId);

        if(diagnosis.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Diagnosis for this patient");

        return diagnosis;
    }

    @Override
    public Diagnosis create(Diagnosis diagnosisResource) {
        Set<ConstraintViolation<Diagnosis>> violations = validator.validate(diagnosisResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return diagnosisRepository.save(diagnosisResource);
    }

    @Override
    public ResponseEntity<?> delete(Integer diagnosisId) {
        return diagnosisRepository.findById(diagnosisId).map(diagnosis -> {
            diagnosisRepository.delete(diagnosis);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,diagnosisId));
    }
}
