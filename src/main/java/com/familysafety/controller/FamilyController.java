package com.familysafety.controller;

import com.familysafety.model.FamilyLink;
import com.familysafety.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/family")
public class FamilyController {

    @Autowired
    FamilyService familyService;

    @PostMapping("/invite")
    public ResponseEntity<FamilyLink> sendInvite(@RequestParam Long parentId, @RequestParam Long childId) {
        FamilyLink link = familyService.sendInvite(parentId, childId);
        return ResponseEntity.ok(link);
    }

    @GetMapping("/links/{parentId}")
    public ResponseEntity<List<FamilyLink>> getLinksByParent(@PathVariable Long parentId) {
        List<FamilyLink> links = familyService.getLinksByParent(parentId);
        return ResponseEntity.ok(links);
    }

    @PutMapping("/invite/{id}/status")
    public ResponseEntity<FamilyLink> updateInviteStatus(
        @PathVariable Long id, 
        @RequestParam String status) {
    FamilyLink updatedLink = familyService.updateInviteStatus(id, status);
    return ResponseEntity.ok(updatedLink);
}

}