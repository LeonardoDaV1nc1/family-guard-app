package com.familysafety.controller;

import com.familysafety.model.ScheduleRule;
import com.familysafety.repository.ScheduleRuleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule-rules")
@Tag(name = "schedule-rule-controller", description = "Gestão de Regras Flexíveis de Calendário e Horários")
public class ScheduleRuleController {

    private final ScheduleRuleRepository scheduleRuleRepository;

    public ScheduleRuleController(ScheduleRuleRepository scheduleRuleRepository) {
        this.scheduleRuleRepository = scheduleRuleRepository;
    }

    @PostMapping("/create")
    @Operation(summary = "Criar nova regra de calendário/fim de semana para o filho")
    public ResponseEntity<ScheduleRule> createRule(@RequestBody ScheduleRule rule) {
        ScheduleRule saved = scheduleRuleRepository.save(rule);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/child/{childId}")
    @Operation(summary = "Listar regras ativas do filho")
    public ResponseEntity<List<ScheduleRule>> getRulesByChild(@PathVariable Long childId) {
        return ResponseEntity.ok(scheduleRuleRepository.findByChildIdAndActiveTrue(childId));
    }
}