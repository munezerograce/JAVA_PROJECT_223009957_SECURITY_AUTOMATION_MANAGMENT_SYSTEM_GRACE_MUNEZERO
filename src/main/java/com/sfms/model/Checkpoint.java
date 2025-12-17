package com.sfms.model;

import java.time.LocalDateTime;

/**
 * Model class representing a Checkpoint entity.
 */
public class Checkpoint {
    private int id;
    private int siteId;
    private String name;
    private String identifier; // NFC or QR code value
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Checkpoint() {}

    public Checkpoint(int siteId, String name, String identifier) {
        this.siteId = siteId;
        this.name = name;
        this.identifier = identifier;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSiteId() { return siteId; }
    public void setSiteId(int siteId) { this.siteId = siteId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Checkpoint{" +
                "id=" + id +
                ", siteId=" + siteId +
                ", name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}
