package com.digitalholics.iotheraphy.Socialsss.mapping;


import com.digitalholics.iotheraphy.Shared.EnhancedModelMapper;
import com.digitalholics.iotheraphy.Socialsss.domain.model.entity.Review;
import com.digitalholics.iotheraphy.Socialsss.resource.CreateReviewResource;
import com.digitalholics.iotheraphy.Socialsss.resource.ReviewResource;
import com.digitalholics.iotheraphy.Socialsss.resource.UpdateReviewResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ReviewMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public ReviewResource toResource(Review model) {
        return mapper.map(model, ReviewResource.class);
    }

    public Review toModel(CreateReviewResource resource) {
        return mapper.map(resource, Review.class);
    }

    public Review toModel(UpdateReviewResource resource) {
        return mapper.map(resource, Review.class);
    }

    public Page<ReviewResource> modelListPage(List<Review> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ReviewResource.class), pageable, modelList.size());
    }
}
