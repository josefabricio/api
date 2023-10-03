package com.digitalholics.iotheraphy.Therapy.domain.persistence;

import com.digitalholics.iotheraphy.Therapy.domain.model.entity.Therapy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TherapyRepository extends JpaRepository<Therapy, Integer> {



}
