package com.familysafety.repository;

import com.familysafety.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    List<ActivityLog> findByChildIdOrderByTimestampDesc(Long childId);

    List<ActivityLog> findByChildId(Long childId);

    @Query("SELECT COUNT(a) FROM ActivityLog a WHERE a.childId = :childId")
    Long countByChildId(@Param("childId") Long childId);

    @Query("SELECT COUNT(a) FROM ActivityLog a WHERE a.childId = :childId AND a.category = :category")
    Long countByChildIdAndRating(@Param("childId") Long childId, @Param("category") String category);
}