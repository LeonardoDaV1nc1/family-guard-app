package com.familysafety.controller;

import com.familysafety.model.ContentRule;
import com.familysafety.repository.ContentRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class ContentRuleController {

    @Autowired
    private ContentRuleRepository contentRuleRepository;

    @PostMapping("/create")
    public ResponseEntity<ContentRule> createRule(@RequestParam Long parentId,
                                                 @RequestParam String keyword,
                                                 @RequestParam String category) {
        ContentRule rule = new ContentRule(parentId, keyword, category);
        return ResponseEntity.ok(contentRuleRepository.save(rule));
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<ContentRule>> getRulesByParent(@PathVariable Long parentId) {
        List<ContentRule> rules = contentRuleRepository.findByParentId(parentId);
        return ResponseEntity.ok(rules);
    }

    @PutMapping("/{ruleId}/toggle")
    public ResponseEntity<ContentRule> toggleRuleStatus(@PathVariable Long ruleId) {
        return contentRuleRepository.findById(ruleId).map(rule -> {
            rule.setActive(!rule.getActive());
            return ResponseEntity.ok(contentRuleRepository.save(rule));
        }).orElse(ResponseEntity.notFound().build());
    }
}