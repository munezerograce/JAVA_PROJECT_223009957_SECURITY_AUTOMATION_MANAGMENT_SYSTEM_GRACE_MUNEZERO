package com.sfms.controller;

import com.sfms.view.LoginPanel;
import com.sfms.model.DatabaseManager;
import com.sfms.exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller for login functionality.
 */
public class LoginController {
    private LoginPanel loginPanel;
    private MainController mainController;
    private String userRole;

    public LoginController(LoginPanel loginPanel, MainController mainController) {
        this.loginPanel = loginPanel;
        this.mainController = mainController;
        setupListeners();
    }

    private void setupListeners() {
        loginPanel.setLoginListener(new LoginPanel.LoginListener() {
            @Override
            public void onLoginAttempt(String username, String password) {
                if (authenticate(username, password)) {
                    mainController.onLoginSuccess(userRole);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(loginPanel, "Invalid credentials", "Login Failed", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean authenticate(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return false;
        }

        try {
            DatabaseManager dbManager = DatabaseManager.getInstance();
            Connection conn = dbManager.getConnection();

            String sql = "SELECT password_hash, role FROM USERS WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password_hash");
                String role = rs.getString("role");
                if (password.equals(storedPassword)) { // Plain text comparison for simplicity
                    // Store the role for later use
                    this.userRole = role;
                    return true;
                }
            }
        } catch (DatabaseConnectionException | SQLException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(loginPanel, "Database error: " + e.getMessage(), "Login Failed", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }
}
