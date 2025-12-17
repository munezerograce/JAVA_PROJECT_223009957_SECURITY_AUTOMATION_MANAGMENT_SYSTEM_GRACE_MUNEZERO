package com.sfms.view;

import javax.swing.*;
import java.awt.*;

/**
 * System administration panel for admin users.
 */
public class SystemAdministrationPanel extends JPanel {
    private JButton backupButton;
    private JButton restoreButton;
    private JButton systemSettingsButton;
    private JTextArea logArea;

    public SystemAdministrationPanel() {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        backupButton = new JButton("Backup Database");
        restoreButton = new JButton("Restore Database");
        systemSettingsButton = new JButton("System Settings");

        logArea = new JTextArea(10, 50);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        // Add some dummy log entries
        logArea.append("System started successfully.\n");
        logArea.append("Database connection established.\n");
        logArea.append("All services running.\n");
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backupButton);
        buttonPanel.add(restoreButton);
        buttonPanel.add(systemSettingsButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(logArea), BorderLayout.CENTER);
    }

    // Getters for buttons
    public JButton getBackupButton() { return backupButton; }
    public JButton getRestoreButton() { return restoreButton; }
    public JButton getSystemSettingsButton() { return systemSettingsButton; }
    public JTextArea getLogArea() { return logArea; }
}
