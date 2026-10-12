package com.familysafety.controller;

import com.familysafety.model.ViolationLog;
import com.familysafety.repository.ViolationLogRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/violations")
@Tag(name = "violation-controller", description = "Registo e Consulta de Tentativas Bloqueadas")
public class ViolationController {

    @Autowired
    private ViolationLogRepository violationLogRepository;

    @PostMapping("/log")
    @Operation(summary = "Registar uma nova tentativa de violação/bloqueio")
    public ResponseEntity<ViolationLog> logViolation(@RequestBody ViolationLog violation) {
        ViolationLog saved = violationLogRepository.save(violation);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/child/{childId}")
    @Operation(summary = "Listar todas as violações registadas para um determinado filho")
    public ResponseEntity<List<ViolationLog>> getViolationsByChild(@PathVariable Long childId) {
        List<ViolationLog> violations = violationLogRepository.findByChildIdOrderByTimestampDesc(childId);
        return ResponseEntity.ok(violations);
    }
}