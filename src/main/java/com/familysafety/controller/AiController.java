package com.familysafety.controller;

import com.familysafety.service.AiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@Tag(name = "ai-controller", description = "Classificação Inteligente de Aplicativos com IA")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/classify")
    @Operation(summary = "Classificar aplicativo automaticamente via IA")
    public ResponseEntity<String> classify(@RequestParam String appName) {
        String category = aiService.classifyApp(appName);
        return ResponseEntity.ok(category);
    }
}