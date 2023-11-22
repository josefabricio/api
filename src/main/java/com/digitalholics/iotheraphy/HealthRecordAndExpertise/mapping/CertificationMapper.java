package com.digitalholics.iotheraphy.HealthRecordAndExpertise.mapping;

import com.digitalholics.iotheraphy.shared.EnhancedModelMapper;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.Certification;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.Certification.CertificationResource;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.Certification.CreateCertificationResource;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.Certification.UpdateCertificationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CertificationMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public CertificationResource toResource(Certification model) {
        return mapper.map(model, CertificationResource.class);
    }

    public Certification toModel(CreateCertificationResource resource) {
        return mapper.map(resource, Certification.class);
    }

    public Certification toModel(UpdateCertificationResource resource) {
        return mapper.map(resource, Certification.class);
    }

    public Page<CertificationResource> modelListPage(List<Certification> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, CertificationResource.class), pageable, modelList.size());
    }
}
