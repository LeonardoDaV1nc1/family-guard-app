package com.familysafety.repository;

import com.familysafety.model.ContentRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRuleRepository extends JpaRepository<ContentRule, Long> {

    List<ContentRule> findByParentIdAndActiveTrue(Long parentId);

    List<ContentRule> findByParentId(Long parentId);
}