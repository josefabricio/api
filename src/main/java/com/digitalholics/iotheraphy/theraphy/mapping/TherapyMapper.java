package com.digitalholics.iotheraphy.theraphy.mapping;

import com.digitalholics.iotheraphy.shared.EnhancedModelMapper;
import com.digitalholics.iotheraphy.theraphy.domain.model.entity.Therapy;
import com.digitalholics.iotheraphy.theraphy.resource.Therapy.CreateTherapyResource;
import com.digitalholics.iotheraphy.theraphy.resource.Therapy.TherapyResource;
import com.digitalholics.iotheraphy.theraphy.resource.Therapy.UpdateTherapyResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TherapyMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public TherapyResource toResource(Therapy model){
        return mapper.map(model, TherapyResource.class);
    }

    public Therapy toModel(CreateTherapyResource resource) {
        return mapper.map(resource, Therapy.class);
    }

    public Therapy toModel(UpdateTherapyResource resource) { return mapper.map(resource, Therapy.class);}

    public Page<TherapyResource> modelListPage(List<Therapy> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TherapyResource.class), pageable, modelList.size());
    }

}
