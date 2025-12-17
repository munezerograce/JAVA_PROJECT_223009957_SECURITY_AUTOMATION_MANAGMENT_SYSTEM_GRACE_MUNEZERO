-- Security Force Management System (SFMS) Database Schema
-- MySQL Script to create the sfms_db database and tables

CREATE DATABASE IF NOT EXISTS sfms_db;
USE sfms_db;

-- GUARD table
CREATE TABLE GUARD (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    employee_id VARCHAR(50) UNIQUE NOT NULL,
    contact_info VARCHAR(255),
    role ENUM('Guard', 'Supervisor') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- SITE table
CREATE TABLE SITE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    address VARCHAR(500),
    contact_person VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- CHECKPOINT table
CREATE TABLE CHECKPOINT (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    identifier VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (site_id) REFERENCES SITE(id) ON DELETE CASCADE
);

-- SHIFT table
CREATE TABLE SHIFT (
    id INT AUTO_INCREMENT PRIMARY KEY,
    guard_id INT NOT NULL,
    site_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    status ENUM('Scheduled', 'Started', 'Completed', 'Canceled') DEFAULT 'Scheduled',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (guard_id) REFERENCES GUARD(id) ON DELETE CASCADE,
    FOREIGN KEY (site_id) REFERENCES SITE(id) ON DELETE CASCADE
);

-- PATROL_LOG table
CREATE TABLE PATROL_LOG (
    id INT AUTO_INCREMENT PRIMARY KEY,
    shift_id INT NOT NULL,
    checkpoint_id INT NOT NULL,
    scan_time DATETIME NOT NULL,
    latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (shift_id) REFERENCES SHIFT(id) ON DELETE CASCADE,
    FOREIGN KEY (checkpoint_id) REFERENCES CHECKPOINT(id) ON DELETE CASCADE
);

-- INCIDENT table
CREATE TABLE INCIDENT (
    id INT AUTO_INCREMENT PRIMARY KEY,
    shift_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    severity ENUM('Low', 'Medium', 'High') NOT NULL,
    report_time DATETIME NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (shift_id) REFERENCES SHIFT(id) ON DELETE CASCADE
);

-- Indexes for better performance
CREATE INDEX idx_shift_guard_id ON SHIFT(guard_id);
CREATE INDEX idx_shift_site_id ON SHIFT(site_id);
CREATE INDEX idx_shift_status ON SHIFT(status);
CREATE INDEX idx_patrol_log_shift_id ON PATROL_LOG(shift_id);
CREATE INDEX idx_incident_shift_id ON INCIDENT(shift_id);
CREATE INDEX idx_checkpoint_site_id ON CHECKPOINT(site_id);

-- USERS table for authentication
CREATE TABLE USERS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('Admin', 'Manager', 'Guard') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Sample data (optional, for testing)
INSERT INTO GUARD (name, employee_id, contact_info, role) VALUES
('John Doe', 'G001', 'john.doe@example.com', 'Guard'),
('Jane Smith', 'G002', 'jane.smith@example.com', 'Supervisor');

INSERT INTO SITE (name, address, contact_person) VALUES
('Hospital A', '123 Main St, City', 'Dr. Johnson'),
('Bank HQ', '456 Finance Ave, City', 'Mr. Brown');

INSERT INTO CHECKPOINT (site_id, name, identifier) VALUES
(1, 'Main Entrance', 'NFC001'),
(1, 'Emergency Exit', 'NFC002'),
(2, 'Vault Door', 'QR001');

-- Sample users (passwords are plain text for simplicity)
INSERT INTO USERS (username, password_hash, role) VALUES
('admin', 'admin123', 'Admin'),
('manager', 'manager123', 'Manager'),
('guard', 'guard123', 'Guard');
