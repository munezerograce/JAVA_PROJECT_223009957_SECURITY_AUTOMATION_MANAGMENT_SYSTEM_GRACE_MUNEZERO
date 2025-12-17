package com.sfms.model;

import java.time.LocalDateTime;

/**
 * Model class representing an Incident entity.
 */
public class Incident {
    private int id;
    private int shiftId;
    private String title;
    private String description;
    private String severity; // 'Low', 'Medium', 'High'
    private LocalDateTime reportTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Incident() {}

    public Incident(int shiftId, String title, String description, String severity, LocalDateTime reportTime) {
        this.shiftId = shiftId;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.reportTime = reportTime;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getShiftId() { return shiftId; }
    public void setShiftId(int shiftId) { this.shiftId = shiftId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public LocalDateTime getReportTime() { return reportTime; }
    public void setReportTime(LocalDateTime reportTime) { this.reportTime = reportTime; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", shiftId=" + shiftId +
                ", title='" + title + '\'' +
                ", severity='" + severity + '\'' +
                ", reportTime=" + reportTime +
                '}';
    }
}
