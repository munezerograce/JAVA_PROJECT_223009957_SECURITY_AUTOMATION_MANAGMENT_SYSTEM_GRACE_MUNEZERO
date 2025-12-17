package com.sfms.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Admin dashboard panel with full access to all features.
 */
public class AdminDashboardPanel extends JPanel {
    private JTabbedPane tabbedPane;
    private JButton logoutButton;

    // Panels for each tab
    private GuardManagementPanel guardManagementPanel;
    private ShiftSchedulingPanel shiftSchedulingPanel;
    private IncidentReportsPanel incidentReportsPanel;
    private SystemAdministrationPanel systemAdministrationPanel;

    private SFMSMainFrame mainFrame;

    public AdminDashboardPanel(SFMSMainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.logout();
            }
        });

        tabbedPane = new JTabbedPane();

        guardManagementPanel = new GuardManagementPanel();
        shiftSchedulingPanel = new ShiftSchedulingPanel();
        incidentReportsPanel = new IncidentReportsPanel();
        systemAdministrationPanel = new SystemAdministrationPanel();

        tabbedPane.addTab("Guard Management", guardManagementPanel);
        tabbedPane.addTab("Shift Scheduling", shiftSchedulingPanel);
        tabbedPane.addTab("Incident Reports", incidentReportsPanel);
        tabbedPane.addTab("System Administration", systemAdministrationPanel);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        add(logoutButton, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    public GuardManagementPanel getGuardManagementPanel() {
        return guardManagementPanel;
    }

    public ShiftSchedulingPanel getShiftSchedulingPanel() {
        return shiftSchedulingPanel;
    }

    public IncidentReportsPanel getIncidentReportsPanel() {
        return incidentReportsPanel;
    }

    public SystemAdministrationPanel getSystemAdministrationPanel() {
        return systemAdministrationPanel;
    }
}
