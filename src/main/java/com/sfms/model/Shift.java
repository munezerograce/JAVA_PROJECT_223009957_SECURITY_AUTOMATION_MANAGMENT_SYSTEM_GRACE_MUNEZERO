package com.sfms.model;

import java.time.LocalDateTime;

/**
 * Model class representing a Shift entity.
 */
public class Shift {
    private int id;
    private int guardId;
    private int siteId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status; // 'Scheduled', 'Started', 'Completed', 'Canceled'
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Shift() {}

    public Shift(int guardId, int siteId, LocalDateTime startTime, LocalDateTime endTime, String status) {
        this.guardId = guardId;
        this.siteId = siteId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getGuardId() { return guardId; }
    public void setGuardId(int guardId) { this.guardId = guardId; }

    public int getSiteId() { return siteId; }
    public void setSiteId(int siteId) { this.siteId = siteId; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", guardId=" + guardId +
                ", siteId=" + siteId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                '}';
    }
}
