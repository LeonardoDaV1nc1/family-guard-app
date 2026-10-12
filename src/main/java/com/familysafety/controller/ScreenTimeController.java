package com.familysafety.controller;

import com.familysafety.model.ScreenTimeLimit;
import com.familysafety.model.User;
import com.familysafety.repository.ScreenTimeRepository;
import com.familysafety.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/screentime")
public class ScreenTimeController {

    @Autowired
    private ScreenTimeRepository screenTimeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{childId}")
    public ResponseEntity<?> getScreenTime(@PathVariable Long childId) {
        Optional<User> childOpt = userRepository.findById(childId);
        if (childOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Criança não encontrada.");
        }

        ScreenTimeLimit limit = screenTimeRepository.findByChild(childOpt.get())
                .orElseGet(() -> {
                    ScreenTimeLimit defaultLimit = new ScreenTimeLimit();
                    defaultLimit.setChild(childOpt.get());
                    return screenTimeRepository.save(defaultLimit);
                });

        return ResponseEntity.ok(limit);
    }

    @PostMapping("/configure/{childId}")
    public ResponseEntity<?> configureLimit(
            @PathVariable Long childId,
            @RequestParam int maxMinutes) {
        
        Optional<User> childOpt = userRepository.findById(childId);
        if (childOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Criança não encontrada.");
        }

        ScreenTimeLimit limit = screenTimeRepository.findByChild(childOpt.get())
                .orElse(new ScreenTimeLimit());

        limit.setChild(childOpt.get());
        limit.setMaxMinutesPerDay(maxMinutes);
        
        ScreenTimeLimit saved = screenTimeRepository.save(limit);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/consume/{childId}")
    public ResponseEntity<?> consumeTime(
            @PathVariable Long childId,
            @RequestParam int minutesSpent) {
        
        Optional<User> childOpt = userRepository.findById(childId);
        if (childOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Criança não encontrada.");
        }

        Optional<ScreenTimeLimit> limitOpt = screenTimeRepository.findByChild(childOpt.get());
        if (limitOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Regra de tempo de ecrã não encontrada.");
        }

        ScreenTimeLimit limit = limitOpt.get();
        int totalUsed = limit.getMinutesUsed() + minutesSpent;
        limit.setMinutesUsed(totalUsed);

        screenTimeRepository.save(limit);

        boolean isBlocked = totalUsed > limit.getMaxMinutesPerDay();
        return ResponseEntity.ok("Tempo atualizado. Total gasto: " + totalUsed + " mins. Bloqueado: " + isBlocked);
    }
}