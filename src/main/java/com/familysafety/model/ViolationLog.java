package com.familysafety.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "violation_logs")
public class ViolationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long childId;

    @Column(nullable = false)
    private String attemptedContent; 

    @Column(nullable = false)
    private String reason;

    private LocalDateTime timestamp = LocalDateTime.now();

    public ViolationLog() {}

    public ViolationLog(Long childId, String attemptedContent, String reason) {
        this.childId = childId;
        this.attemptedContent = attemptedContent;
        this.reason = reason;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getChildId() { return childId; }
    public void setChildId(Long childId) { this.childId = childId; }

    public String getAttemptedContent() { return attemptedContent; }
    public void setAttemptedContent(String attemptedContent) { this.attemptedContent = attemptedContent; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}