package com.digitalholics.iotheraphy.social.service;


import com.digitalholics.iotheraphy.profile.domain.model.aggregates.Patient;
import com.digitalholics.iotheraphy.profile.domain.model.aggregates.Physiotherapist;
import com.digitalholics.iotheraphy.profile.infrastructure.jpa.repositories.PatientRepository;
import com.digitalholics.iotheraphy.profile.infrastructure.jpa.repositories.PhysiotherapistRepository;
import com.digitalholics.iotheraphy.shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.shared.Exception.ResourceValidationException;
import com.digitalholics.iotheraphy.social.domain.model.entity.Review;
import com.digitalholics.iotheraphy.social.domain.persistence.ReviewRepository;
import com.digitalholics.iotheraphy.social.domain.service.ReviewService;
import com.digitalholics.iotheraphy.social.resource.CreateReviewResource;
import com.digitalholics.iotheraphy.social.resource.UpdateReviewResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final String ENTITY = "Review";

    private final ReviewRepository reviewRepository;
    private final PhysiotherapistRepository physiotherapistRepository;
    private final PatientRepository patientRepository;
    private final Validator validator;

    public ReviewServiceImpl(ReviewRepository reviewRepository, PhysiotherapistRepository physiotherapistRepository, PatientRepository patientRepository, Validator validator) {
        this.reviewRepository = reviewRepository;
        this.physiotherapistRepository = physiotherapistRepository;
        this.patientRepository = patientRepository;
        this.validator = validator;
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Page<Review> getAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Override
    public Review getById(Integer reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, reviewId));
    }

    @Override
    public List<Review> getByPhysiotherapistId(Integer physiotherapistId) {
        List<Review> reviews = reviewRepository.findByPhysiotherapistId(physiotherapistId);

        if(reviews.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Reviews for this physiotherapist");

        return reviews;
    }

    @Override
    public Review create(CreateReviewResource reviewResource) {
        Set<ConstraintViolation<CreateReviewResource>> violations = validator.validate(reviewResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Patient> patientOptional = Optional.ofNullable(patientRepository.findPatientsByUserUsername(username));
        Patient patient = patientOptional.orElseThrow(() -> new NotFoundException("Not found patient with email: " + username));


        Optional<Physiotherapist> physiotherapistOptional = physiotherapistRepository.findById(reviewResource.getPhysiotherapistId());
        Physiotherapist physiotherapist = physiotherapistOptional.orElseThrow(() -> new NotFoundException("Not found physiotherapist with ID: " + reviewResource.getPhysiotherapistId()));

        Review review = new Review();
        review.setPatient(patient);
        review.setPhysiotherapist(physiotherapist);
        review.setContent(reviewResource.getContent());
        review.setScore(reviewResource.getScore());

        double ratingPhysiotherapist = 0.0;
        List<Review> reviewsPhysiotherapist = reviewRepository.findByPhysiotherapistId(physiotherapist.getId());

        for (Review existingReview : reviewsPhysiotherapist) {
            ratingPhysiotherapist = ratingPhysiotherapist + existingReview.getScore();
;        }
        ratingPhysiotherapist = ratingPhysiotherapist + review.getScore();
        ratingPhysiotherapist = ratingPhysiotherapist/(reviewsPhysiotherapist.size()+1) ;

        physiotherapist.setRating(ratingPhysiotherapist);

        physiotherapistRepository.save(physiotherapist);


        return reviewRepository.save(review);
    }

    @Override
    public Review update(Integer reviewId, UpdateReviewResource request) {
        Review review = getById(reviewId);

        if (request.getContent() != null) {
            review.setContent(request.getContent());
        }
        if (request.getScore() != null) {
            review.setScore(request.getScore());
        }

        return reviewRepository.save(review);
    }


    @Override
    public ResponseEntity<?> delete(Integer reviewId) {
        return reviewRepository.findById(reviewId).map(review -> {
            reviewRepository.delete(review);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,reviewId));
    }
}
