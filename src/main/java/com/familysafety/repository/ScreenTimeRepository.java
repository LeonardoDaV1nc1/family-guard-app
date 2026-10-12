package com.familysafety.repository;

import com.familysafety.model.ScreenTimeLimit;
import com.familysafety.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ScreenTimeRepository extends JpaRepository<ScreenTimeLimit, Long> {
    Optional<ScreenTimeLimit> findByChild(User child);
}