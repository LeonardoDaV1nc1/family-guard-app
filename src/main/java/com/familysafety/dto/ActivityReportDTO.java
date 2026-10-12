package com.familysafety.dto;

public class ActivityReportDTO {

    private Long totalActivities;
    private Long totalSuitable;
    private Long totalInappropriate;

    public ActivityReportDTO(Long totalActivities, Long totalSuitable, Long totalInappropriate) {
        this.totalActivities = totalActivities;
        this.totalSuitable = totalSuitable;
        this.totalInappropriate = totalInappropriate;
    }

    public Long getTotalActivities() { return totalActivities; }
    public void setTotalActivities(Long totalActivities) { this.totalActivities = totalActivities; }

    public Long getTotalSuitable() { return totalSuitable; }
    public void setTotalSuitable(Long totalSuitable) { this.totalSuitable = totalSuitable; }

    public Long getTotalInappropriate() { return totalInappropriate; }
    public void setTotalInappropriate(Long totalInappropriate) { this.totalInappropriate = totalInappropriate; }
}