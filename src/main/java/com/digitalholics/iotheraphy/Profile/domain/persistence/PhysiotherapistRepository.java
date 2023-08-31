package com.digitalholics.iotheraphy.Profile.domain.persistence;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotheraphist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhysiotherapistRepository extends JpaRepository<Physiotheraphist,Integer> {

    @Query("""
        select t.id, t.age, t.appointmentQuantity, t.birthdayDate, t.photoUrl, t.user.id from Patient t inner join User u on t.user.id = u.id
                                                                 where t.user.firstname = :firstname
    """)
    Physiotheraphist findByFirstName(String firstname);

    Optional<Physiotheraphist> findByUserId(Integer userId);

}