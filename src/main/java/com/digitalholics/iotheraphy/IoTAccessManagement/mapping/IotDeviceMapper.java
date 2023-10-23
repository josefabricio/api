package com.digitalholics.iotheraphy.IoTAccessManagement.mapping;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotDevice;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.CreateIotDeviceResource;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.IotDeviceResource;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.UpdateIotDeviceResource;
import com.digitalholics.iotheraphy.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class IotDeviceMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public IotDeviceResource toResource(IotDevice model) {
        return mapper.map(model, IotDeviceResource.class);
    }

    public IotDevice toModel(CreateIotDeviceResource resource) {
        return mapper.map(resource, IotDevice.class);
    }

    public IotDevice toModel(UpdateIotDeviceResource resource) {
        return mapper.map(resource, IotDevice.class);
    }

    public Page<IotDeviceResource> modelListPage(List<IotDevice> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, IotDeviceResource.class), pageable, modelList.size());
    }
}
