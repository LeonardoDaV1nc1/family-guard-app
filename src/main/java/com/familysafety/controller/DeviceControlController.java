package com.familysafety.controller;

import com.familysafety.model.DeviceStatus;
import com.familysafety.repository.DeviceStatusRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/device")
@Tag(name = "device-control-controller", description = "Controlo Remoto e Modo de Bloqueio (Lockdown)")
public class DeviceControlController {

    @Autowired
    private DeviceStatusRepository deviceStatusRepository;

    @PostMapping("/lockdown/{childId}")
    @Operation(summary = "Ativar ou desativar o modo de bloqueio imediato para o filho")
    public ResponseEntity<DeviceStatus> setLockdown(
            @PathVariable Long childId, 
            @RequestParam boolean locked) {
        
        DeviceStatus status = deviceStatusRepository.findByChildId(childId)
                .orElse(new DeviceStatus(childId, false));
        
        status.setLocked(locked);
        DeviceStatus saved = deviceStatusRepository.save(status);
        
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/status/{childId}")
    @Operation(summary = "Verificar se o dispositivo do filho está bloqueado (utilizado pelo app do filho)")
    public ResponseEntity<DeviceStatus> getLockdownStatus(@PathVariable Long childId) {
        DeviceStatus status = deviceStatusRepository.findByChildId(childId)
                .orElse(new DeviceStatus(childId, false));
        
        return ResponseEntity.ok(status);
    }
}