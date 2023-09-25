package com.digitalholics.iotheraphy.Profile.domain.persistence;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotheraphist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhysiotherapistRepository extends JpaRepository<Physiotheraphist,Integer> {


    Physiotheraphist findPhysiotheraphistByDni(String dni);

    Physiotheraphist findPhysiotheraphistByUserUsername(String username);

    Optional<Physiotheraphist> findByUserId(Integer userId);

}