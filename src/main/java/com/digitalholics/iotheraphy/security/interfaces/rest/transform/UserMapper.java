package com.digitalholics.iotheraphy.security.interfaces.rest.transform;

import com.digitalholics.iotheraphy.security.domain.model.aggregates.User;
import com.digitalholics.iotheraphy.security.interfaces.rest.resources.UserResource;
import com.digitalholics.iotheraphy.shared.EnhancedModelMapper;
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
