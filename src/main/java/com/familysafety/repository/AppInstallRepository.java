package com.familysafety.repository;

import com.familysafety.model.AppInstallRequest;
import com.familysafety.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppInstallRepository extends JpaRepository<AppInstallRequest, Long> {
    List<AppInstallRequest> findByChild(User child);
    List<AppInstallRequest> findByStatus(String status);
}