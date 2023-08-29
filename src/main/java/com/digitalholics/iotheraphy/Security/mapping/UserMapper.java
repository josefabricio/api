package com.digitalholics.iotheraphy.Security.mapping;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.resource.CreatePatientResource;
import com.digitalholics.iotheraphy.Profile.resource.PatientResource;
import com.digitalholics.iotheraphy.Profile.resource.UpdatePatientResource;
import com.digitalholics.iotheraphy.Security.User.User;
import com.digitalholics.iotheraphy.Security.User.UserResource;
import com.digitalholics.iotheraphy.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public UserResource toResource(User model) {
        return mapper.map(model, UserResource.class);
    }


    public Page<UserResource> modelListPage(List<User> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, UserResource.class), pageable, modelList.size());
    }



}
