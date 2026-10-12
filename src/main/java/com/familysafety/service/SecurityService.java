package com.familysafety.service;

import com.familysafety.repository.FamilyLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private FamilyLinkRepository familyLinkRepository;

    public void validateParentChildAccess(Long parentId, Long childId) {
        boolean isLinked = familyLinkRepository.findByParentIdAndChildId(parentId, childId).isPresent();
        
        if (!isLinked) {
            throw new SecurityException("Acesso Negado: O utilizador não tem permissão para aceder aos dados desta criança.");
        }
    }
}