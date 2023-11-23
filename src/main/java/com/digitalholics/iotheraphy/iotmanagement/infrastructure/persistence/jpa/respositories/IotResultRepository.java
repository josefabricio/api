package com.digitalholics.iotheraphy.iotmanagement.infrastructure.persistence.jpa.respositories;

import com.digitalholics.iotheraphy.iotmanagement.domain.model.entities.IotResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotResultRepository extends JpaRepository<IotResult, Integer> {
    IotResult findByIotDeviceId(String therapyId);

    @Query("select a from IotResult  a where a.iotDeviceId= :therapyId and a.date= :date")
    List<IotResult> findByTherapyByDate(String therapyId, String date);

}
