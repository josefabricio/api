package com.digitalholics.iotheraphy.Theraphy.mapping;

import com.digitalholics.iotheraphy.Shared.EnhancedModelMapper;
import com.digitalholics.iotheraphy.Theraphy.domain.model.entity.Theraphy;
import com.digitalholics.iotheraphy.Theraphy.resource.CreateTheraphyResource;
import com.digitalholics.iotheraphy.Theraphy.resource.TheraphyResource;
import com.digitalholics.iotheraphy.Theraphy.resource.UpdateTheraphyResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TheraphyMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public TheraphyResource toResource(Theraphy model){
        return mapper.map(model, TheraphyResource.class);
    }

    public Theraphy toModel(CreateTheraphyResource resource) {
        return mapper.map(resource, Theraphy.class);
    }

    public Theraphy toModel(UpdateTheraphyResource resource) { return mapper.map(resource, Theraphy.class);}

    public Page<TheraphyResource> modelListPage(List<Theraphy> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TheraphyResource.class), pageable, modelList.size());
    }

}
