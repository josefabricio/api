package com.digitalholics.iotheraphy.IoTAccessManagement.mapping;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotDevice;
import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.Result;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.*;
import com.digitalholics.iotheraphy.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ResultMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public ResultResource toResource(Result model) {
        return mapper.map(model, ResultResource.class);
    }

    public Result toModel(CreateResultResource resource) {
        return mapper.map(resource, Result.class);
    }

    public Result toModel(UpdateResultResource resource) {
        return mapper.map(resource, Result.class);
    }

    public Page<ResultResource> modelListPage(List<Result> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ResultResource.class), pageable, modelList.size());
    }

}
