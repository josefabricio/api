package com.digitalholics.iotheraphy.profile.interfaces.rest.transform.entitiesmapper;

import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.Job.CreateJobResource;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.Job.JobResource;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.Job.UpdateJobResource;
import com.digitalholics.iotheraphy.shared.EnhancedModelMapper;
import com.digitalholics.iotheraphy.profile.domain.model.entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class JobMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public JobResource toResource(Job model) {
        return mapper.map(model, JobResource.class);
    }

    public Job toModel(CreateJobResource resource) {
        return mapper.map(resource, Job.class);
    }

    public Job toModel(UpdateJobResource resource) {
        return mapper.map(resource, Job.class);
    }

    public Page<JobResource> modelListPage(List<Job> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, JobResource.class), pageable, modelList.size());
    }

}
