package com.digitalholics.iotheraphy.HealthRecordAndExpertise.mapping;

import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.MedicalHistory;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.MedicalHistory.CreateMedicalHistoryResource;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.MedicalHistory.MedicalHistoryResource;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.MedicalHistory.UpdateMedicalHistoryResource;
import com.digitalholics.iotheraphy.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class MedicalHistoryMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public MedicalHistoryResource toResource(MedicalHistory model) {
        return mapper.map(model, MedicalHistoryResource.class);
    }

    public MedicalHistory toModel(CreateMedicalHistoryResource resource) {
        return mapper.map(resource, MedicalHistory.class);
    }

    public MedicalHistory toModel(UpdateMedicalHistoryResource resource) {
        return mapper.map(resource, MedicalHistory.class);
    }

    public Page<MedicalHistoryResource> modelListPage(List<MedicalHistory> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, MedicalHistoryResource.class), pageable, modelList.size());
    }
}
