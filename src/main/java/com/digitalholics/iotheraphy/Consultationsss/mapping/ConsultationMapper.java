package com.digitalholics.iotheraphy.Consultationsss.mapping;

import com.digitalholics.iotheraphy.Consultationsss.domain.model.entity.Consultation;
import com.digitalholics.iotheraphy.Consultationsss.resource.ConsultationResource;
import com.digitalholics.iotheraphy.Consultationsss.resource.CreateConsultationResource;
import com.digitalholics.iotheraphy.Consultationsss.resource.UpdateConsultationResource;
import com.digitalholics.iotheraphy.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ConsultationMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ConsultationResource toResource(Consultation model) {
        return mapper.map(model, ConsultationResource.class);
    }

    public Consultation toModel(CreateConsultationResource resource) {
        return mapper.map(resource, Consultation.class);
    }

    public Consultation toModel(UpdateConsultationResource resource) {
        return mapper.map(resource, Consultation.class);
    }

    public Page<ConsultationResource> modelListPage(List<Consultation> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ConsultationResource.class), pageable, modelList.size());
    }
}
