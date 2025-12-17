package com.sfms.dao;

import com.sfms.model.DatabaseManager;
import com.sfms.model.Guard;
import com.sfms.exception.DatabaseConnectionException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Guard entity.
 */
public class GuardDAO {

    public void create(Guard guard) throws SQLException, DatabaseConnectionException {
        String sql = "INSERT INTO GUARD (name, employee_id, contact_info, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, guard.getName());
            stmt.setString(2, guard.getEmployeeId());
            stmt.setString(3, guard.getContactInfo());
            stmt.setString(4, guard.getRole());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    guard.setId(rs.getInt(1));
                }
            }
        }
    }

    public Guard read(int id) throws SQLException, DatabaseConnectionException {
        String sql = "SELECT * FROM GUARD WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToGuard(rs);
                }
            }
        }
        return null;
    }

    public void update(Guard guard) throws SQLException, DatabaseConnectionException {
        String sql = "UPDATE GUARD SET name = ?, employee_id = ?, contact_info = ?, role = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, guard.getName());
            stmt.setString(2, guard.getEmployeeId());
            stmt.setString(3, guard.getContactInfo());
            stmt.setString(4, guard.getRole());
            stmt.setInt(5, guard.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException, DatabaseConnectionException {
        String sql = "DELETE FROM GUARD WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Guard> findAll() throws SQLException, DatabaseConnectionException {
        List<Guard> guards = new ArrayList<>();
        String sql = "SELECT * FROM GUARD";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                guards.add(mapResultSetToGuard(rs));
            }
        }
        return guards;
    }

    private Guard mapResultSetToGuard(ResultSet rs) throws SQLException {
        Guard guard = new Guard();
        guard.setId(rs.getInt("id"));
        guard.setName(rs.getString("name"));
        guard.setEmployeeId(rs.getString("employee_id"));
        guard.setContactInfo(rs.getString("contact_info"));
        guard.setRole(rs.getString("role"));
        guard.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        guard.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return guard;
    }
}
