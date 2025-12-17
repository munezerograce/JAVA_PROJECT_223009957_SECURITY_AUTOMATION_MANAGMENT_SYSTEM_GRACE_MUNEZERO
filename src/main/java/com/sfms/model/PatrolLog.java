package com.sfms.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Model class representing a PatrolLog entity.
 */
public class PatrolLog {
    private int id;
    private int shiftId;
    private int checkpointId;
    private LocalDateTime scanTime;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public PatrolLog() {}

    public PatrolLog(int shiftId, int checkpointId, LocalDateTime scanTime, BigDecimal latitude, BigDecimal longitude) {
        this.shiftId = shiftId;
        this.checkpointId = checkpointId;
        this.scanTime = scanTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getShiftId() { return shiftId; }
    public void setShiftId(int shiftId) { this.shiftId = shiftId; }

    public int getCheckpointId() { return checkpointId; }
    public void setCheckpointId(int checkpointId) { this.checkpointId = checkpointId; }

    public LocalDateTime getScanTime() { return scanTime; }
    public void setScanTime(LocalDateTime scanTime) { this.scanTime = scanTime; }

    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }

    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "PatrolLog{" +
                "id=" + id +
                ", shiftId=" + shiftId +
                ", checkpointId=" + checkpointId +
                ", scanTime=" + scanTime +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
