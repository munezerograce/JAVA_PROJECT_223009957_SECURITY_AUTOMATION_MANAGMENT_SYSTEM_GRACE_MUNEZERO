package com.sfms.model;

import java.time.LocalDateTime;

/**
 * Model class representing a Guard entity.
 */
public class Guard {
    private int id;
    private String name;
    private String employeeId;
    private String contactInfo;
    private String role; // 'Guard' or 'Supervisor'
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Guard() {}

    public Guard(String name, String employeeId, String contactInfo, String role) {
        this.name = name;
        this.employeeId = employeeId;
        this.contactInfo = contactInfo;
        this.role = role;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Guard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
