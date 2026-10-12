package com.familysafety.model;

import jakarta.persistence.*;

@Entity
@Table(name = "screen_time_limits")
public class ScreenTimeLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private User child;

    private int maxMinutesPerDay = 120; 
    private int minutesUsed = 0;     

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getChild() { return child; }
    public void setChild(User child) { this.child = child; }

    public int getMaxMinutesPerDay() { return maxMinutesPerDay; }
    public void setMaxMinutesPerDay(int maxMinutesPerDay) { this.maxMinutesPerDay = maxMinutesPerDay; }

    public int getMinutesUsed() { return minutesUsed; }
    public void setMinutesUsed(int minutesUsed) { this.minutesUsed = minutesUsed; }
}