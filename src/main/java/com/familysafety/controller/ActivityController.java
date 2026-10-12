package com.familysafety.controller;

import com.familysafety.dto.ActivityReportDTO;
import com.familysafety.model.ActivityLog;
import com.familysafety.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
@Tag(name = "activity-controller", description = "Gestão e Registo de Atividades")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping("/log")
    @Operation(summary = "Registar nova atividade (com classificação automática por IA e verificação de regras)")
    public ResponseEntity<ActivityLog> logActivity(
            @RequestParam Long childId,
            @RequestParam String contentType,
            @RequestParam String contentValue,
            @RequestParam(required = false) Long parentId) {
        
        ActivityLog log = activityService.logActivity(childId, contentType, contentValue, parentId);
        return ResponseEntity.ok(log);
    }

    @GetMapping("/child/{childId}")
    @Operation(summary = "Listar histórico de atividades de um filho ordenado por data")
    public ResponseEntity<List<ActivityLog>> getActivitiesByChild(@PathVariable Long childId) {
        List<ActivityLog> logs = activityService.getActivitiesByChild(childId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/child/{childId}/report")
    @Operation(summary = "Obter relatório consolidado de atividades (Total, Adequadas e Inadequadas)")
    public ResponseEntity<ActivityReportDTO> getChildReport(@PathVariable Long childId) {
        ActivityReportDTO report = activityService.getChildReport(childId);
        return ResponseEntity.ok(report);
    }
}