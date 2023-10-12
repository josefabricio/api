package com.digitalholics.iotheraphy.HealthRecordAndExpertise.service;


import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.MedicalHistory;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.persistence.MedicalHistoryRepository;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.service.MedicalHistoryService;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.MedicalHistory.CreateMedicalHistoryResource;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.MedicalHistory.UpdateMedicalHistoryResource;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.domain.persistence.PatientRepository;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceValidationException;
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
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private static final String ENTITY = "MedicalHistory";

    private final MedicalHistoryRepository medicalHistoryRepository;
    private final PatientRepository patientRepository;
    private final Validator validator;


    public MedicalHistoryServiceImpl(MedicalHistoryRepository medicalHistoryRepository, PatientRepository patientRepository, Validator validator) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.patientRepository = patientRepository;
        this.validator = validator;
    }

    @Override
    public List<MedicalHistory> getAll() {
        return medicalHistoryRepository.findAll();
    }

    @Override
    public Page<MedicalHistory> getAll(Pageable pageable) {
        return medicalHistoryRepository.findAll(pageable);
    }

    @Override
    public MedicalHistory getById(Integer medicalHistoryId) {
        return medicalHistoryRepository.findById(medicalHistoryId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, medicalHistoryId));
    }

    @Override
    public MedicalHistory getByPatientId(Integer patientId) {
       MedicalHistory medicalHistory = medicalHistoryRepository.findByPatientId(patientId);

        if(medicalHistory == null)
            throw new ResourceValidationException(ENTITY,
                    "Not found Medical History for this patient");

        return medicalHistory;
    }

    @Override
    public MedicalHistory create(CreateMedicalHistoryResource medicalHistoryResource) {
        Set<ConstraintViolation<CreateMedicalHistoryResource>> violations = validator.validate(medicalHistoryResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Patient> patientOptional = Optional.ofNullable(patientRepository.findPatientsByUserUsername(username));
        Patient patient = patientOptional.orElseThrow(() -> new NotFoundException("Not found patient with email: " + username));

        MedicalHistory medicalHistoryWithPatient = medicalHistoryRepository.findByPatientId(patient.getId());

        if(medicalHistoryWithPatient != null)
            throw new ResourceValidationException(ENTITY,
                    "A Medical History for this patient already exits");

        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setPatient(patient);
        medicalHistory.setGender(medicalHistoryResource.getGender());
        medicalHistory.setSize(medicalHistoryResource.getSize());
        medicalHistory.setWeight(medicalHistoryResource.getWeight());
        medicalHistory.setBirthplace(medicalHistoryResource.getBirthplace());
        medicalHistory.setHereditaryHistory(medicalHistoryResource.getHereditaryHistory());
        medicalHistory.setNonPathologicalHistory(medicalHistoryResource.getNonPathologicalHistory());
        medicalHistory.setPathologicalHistory(medicalHistoryResource.getPathologicalHistory());


        return medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public MedicalHistory update(Integer medicalHistoryId, UpdateMedicalHistoryResource request) {
        MedicalHistory medicalHistory = getById(medicalHistoryId);

        if(medicalHistory == null)
            throw new ResourceValidationException(ENTITY,
                    "Not found Medical History with ID:"+ medicalHistoryId);

        if (request.getGender() != null) {
            medicalHistory.setGender(request.getGender());
        }
        if (request.getSize() != null) {
            medicalHistory.setSize(request.getSize());
        }
        if (request.getWeight() != null) {
            medicalHistory.setWeight(request.getWeight());
        }
        if (request.getBirthplace() != null) {
            medicalHistory.setBirthplace(request.getBirthplace());
        }
        if (request.getHereditaryHistory() != null) {
            medicalHistory.setHereditaryHistory(request.getHereditaryHistory());
        }
        if (request.getNonPathologicalHistory() != null) {
            medicalHistory.setNonPathologicalHistory(request.getNonPathologicalHistory());
        }
        if (request.getPathologicalHistory() != null) {
            medicalHistory.setPathologicalHistory(request.getPathologicalHistory());
        }

        return medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public ResponseEntity<?> delete(Integer medicalHistoryId) {
        return medicalHistoryRepository.findById(medicalHistoryId).map(medicalHistory -> {
            medicalHistoryRepository.delete(medicalHistory);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,medicalHistoryId));
    }

}
