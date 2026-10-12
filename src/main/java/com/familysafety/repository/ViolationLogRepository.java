package com.familysafety.repository;

import com.familysafety.model.ViolationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ViolationLogRepository extends JpaRepository<ViolationLog, Long> {
    List<ViolationLog> findByChildIdOrderByTimestampDesc(Long childId);
}