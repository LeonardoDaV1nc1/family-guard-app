package com.familysafety.model;

import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "schedule_rules")
public class ScheduleRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long childId;

    @ElementCollection(targetClass = DayOfWeek.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "schedule_rule_days", joinColumns = @JoinColumn(name = "schedule_rule_id"))
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysOfWeek; 

    @Column(nullable = false)
    private Integer dailyLimitMinutes; 
    private LocalTime startTimeAllowed; 
    private LocalTime endTimeAllowed;   

    private boolean isWeekendRule;
    private boolean active = true;

    public ScheduleRule() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getChildId() { return childId; }
    public void setChildId(Long childId) { this.childId = childId; }

    public Set<DayOfWeek> getDaysOfWeek() { return daysOfWeek; }
    public void setDaysOfWeek(Set<DayOfWeek> daysOfWeek) { this.daysOfWeek = daysOfWeek; }

    public Integer getDailyLimitMinutes() { return dailyLimitMinutes; }
    public void setDailyLimitMinutes(Integer dailyLimitMinutes) { this.dailyLimitMinutes = dailyLimitMinutes; }

    public LocalTime getStartTimeAllowed() { return startTimeAllowed; }
    public void setStartTimeAllowed(LocalTime startTimeAllowed) { this.startTimeAllowed = startTimeAllowed; }

    public LocalTime getEndTimeAllowed() { return endTimeAllowed; }
    public void setEndTimeAllowed(LocalTime endTimeAllowed) { this.endTimeAllowed = endTimeAllowed; }

    public boolean isWeekendRule() { return isWeekendRule; }
    public void setWeekendRule(boolean weekendRule) { isWeekendRule = weekendRule; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}