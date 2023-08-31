package com.digitalholics.iotheraphy.Profile.mapping;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotheraphist;
import com.digitalholics.iotheraphy.Profile.resource.*;
import com.digitalholics.iotheraphy.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PhysiotherapistMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public PhysiotherapistResource toResource(Physiotheraphist model) {
        return mapper.map(model, PhysiotherapistResource.class);
    }

    public Physiotheraphist toModel(CreatePhysiotherapistResource resource) {
        return mapper.map(resource, Physiotheraphist.class);
    }

    public Physiotheraphist toModel(UpdatePhysiotherapistResource resource) { return mapper.map(resource, Physiotheraphist.class);}

    public Page<PhysiotherapistResource> modelListPage(List<Physiotheraphist> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, PhysiotherapistResource.class), pageable, modelList.size());
    }



}
