package com.familysafety.controller;

import com.familysafety.model.PanicAlert;
import com.familysafety.model.User;
import com.familysafety.repository.PanicAlertRepository;
import com.familysafety.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*")
public class AlertController {

    @Autowired
    PanicAlertRepository panicAlertRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/panic")
    public ResponseEntity<PanicAlert> triggerPanic(@RequestParam Long childId) {
        User child = userRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Filho não encontrado"));

        PanicAlert alert = new PanicAlert();
        alert.setChild(child);

        PanicAlert savedAlert = panicAlertRepository.save(alert);
        return ResponseEntity.ok(savedAlert);
    }

    @GetMapping
    public ResponseEntity<List<PanicAlert>> getAllAlerts() {
        return ResponseEntity.ok(panicAlertRepository.findAll());
    }
}