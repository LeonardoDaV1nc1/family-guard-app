package com.familysafety.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "content_rules")
public class ContentRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long parentId; 

    @Column(nullable = false)
    private String keyword; 

    @Column(nullable = false)
    private String category; 

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public ContentRule() {}

    public ContentRule(Long parentId, String keyword, String category) {
        this.parentId = parentId;
        this.keyword = keyword.toLowerCase().trim();
        this.category = category;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getParentId() { return parentId; }
    public void setParentId(Long parentId) { this.parentId = parentId; }

    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword.toLowerCase().trim(); }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}