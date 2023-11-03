package com.digitalholics.iotheraphy.IoTAccessManagement.domain.persistence;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IotDeviceRepository extends JpaRepository<IotDevice, Integer> {

    IotDevice findByTemperature(String temperature);

}
