package com.familysafety.repository;

import com.familysafety.model.PanicAlert;
import com.familysafety.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PanicAlertRepository extends JpaRepository<PanicAlert, Long> {
    List<PanicAlert> findByChild(User child);
}