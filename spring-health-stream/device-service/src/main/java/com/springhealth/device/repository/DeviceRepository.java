package com.springhealth.device.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springhealth.device.domain.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

	Device findDeviceByDeviceCode(String deviceCode);
}
