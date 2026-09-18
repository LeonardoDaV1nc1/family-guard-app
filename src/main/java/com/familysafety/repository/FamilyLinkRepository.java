package com.familysafety.repository;

import com.familysafety.model.FamilyLink;
import com.familysafety.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FamilyLinkRepository extends JpaRepository<FamilyLink, Long> {
    List<FamilyLink> findByParent(User parent);
    List<FamilyLink> findByChild(User child);
}