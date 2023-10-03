package com.digitalholics.iotheraphy.Therapy.domain.persistence;


import com.digitalholics.iotheraphy.Therapy.domain.model.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

    List<Treatment> findTreatmentByTherapyId(Integer therapyId);

}
