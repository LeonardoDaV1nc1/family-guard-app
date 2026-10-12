package com.familysafety.controller;

import com.familysafety.model.AppInstallRequest;
import com.familysafety.model.User;
import com.familysafety.repository.AppInstallRepository;
import com.familysafety.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/apps")
public class AppInstallController {

    @Autowired
    private AppInstallRepository appInstallRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/request/{childId}")
    public ResponseEntity<?> requestAppInstallation(
            @PathVariable Long childId,
            @RequestBody AppInstallRequest requestDetails) {
        
        Optional<User> childOpt = userRepository.findById(childId);
        if (childOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Criança não encontrada.");
        }

        requestDetails.setChild(childOpt.get());
        requestDetails.setStatus("PENDING");
        
        AppInstallRequest savedRequest = appInstallRepository.save(requestDetails);
        return ResponseEntity.ok(savedRequest);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<AppInstallRequest>> getPendingRequests() {
        List<AppInstallRequest> pending = appInstallRepository.findByStatus("PENDING");
        return ResponseEntity.ok(pending);
    }

    @PutMapping("/{id}/review")
    public ResponseEntity<?> reviewAppInstallation(
            @PathVariable Long id,
            @RequestParam String status) { 
        
        Optional<AppInstallRequest> requestOpt = appInstallRepository.findById(id);
        if (requestOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Pedido não encontrado.");
        }

        AppInstallRequest request = requestOpt.get();
        request.setStatus(status.toUpperCase());
        appInstallRepository.save(request);

        return ResponseEntity.ok("Pedido atualizado com sucesso para: " + request.getStatus());
    }
}