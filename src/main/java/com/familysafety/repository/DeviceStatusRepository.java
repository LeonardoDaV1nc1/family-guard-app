package com.familysafety.repository;

import com.familysafety.model.DeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DeviceStatusRepository extends JpaRepository<DeviceStatus, Long> {
    Optional<DeviceStatus> findByChildId(Long childId);
}