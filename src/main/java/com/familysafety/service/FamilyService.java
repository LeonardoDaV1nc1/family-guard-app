package com.familysafety.service;

import com.familysafety.model.FamilyLink;
import com.familysafety.model.User;
import com.familysafety.repository.FamilyLinkRepository;
import com.familysafety.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FamilyLinkRepository familyLinkRepository;

    public FamilyLink sendInvite(Long parentId, Long childId) {
        User parent = userRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));
        
        User child = userRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Filho não encontrado"));

        FamilyLink link = new FamilyLink();
        link.setParent(parent);
        link.setChild(child);
        link.setStatus(FamilyLink.Status.PENDING);

        return familyLinkRepository.save(link);
    }

    public List<FamilyLink> getLinksByParent(Long parentId) {
        User parent = userRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));
        return familyLinkRepository.findByParent(parent);
    }

    public FamilyLink updateInviteStatus(Long linkId, String status) {
        FamilyLink link = familyLinkRepository.findById(linkId)
            .orElseThrow(() -> new RuntimeException("Vínculo familiar não encontrado"));
        
        link.setStatus(FamilyLink.Status.valueOf(status));
        return familyLinkRepository.save(link);
    }


}