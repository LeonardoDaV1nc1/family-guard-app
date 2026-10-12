package com.familysafety.model;

import jakarta.persistence.*;

@Entity
@Table(name = "device_status")
public class DeviceStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long childId;

    @Column(nullable = false)
    private boolean locked = false;

    public DeviceStatus() {}

    public DeviceStatus(Long childId, boolean locked) {
        this.childId = childId;
        this.locked = locked;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getChildId() { return childId; }
    public void setChildId(Long childId) { this.childId = childId; }

    public boolean isLocked() { return locked; }
    public void setLocked(boolean locked) { this.locked = locked; }
}