package com.digitalholics.iotheraphy.IoTAccessManagement.mapping;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotResult;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.*;
import com.digitalholics.iotheraphy.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class IotResultMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public IotResultResource toResource(IotResult model) {
        return mapper.map(model, IotResultResource.class);
    }

    public IotResult toModel(CreateIotResultResource resource) {
        return mapper.map(resource, IotResult.class);
    }

    public IotResult toModel(UpdateIotResultResource resource) {
        return mapper.map(resource, IotResult.class);
    }

    public Page<IotResultResource> modelListPage(List<IotResult> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, IotResultResource.class), pageable, modelList.size());
    }

}
