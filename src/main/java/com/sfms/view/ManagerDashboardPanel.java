package com.sfms.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Manager dashboard panel with tabbed navigation.
 */
public class ManagerDashboardPanel extends JPanel {
    private JTabbedPane tabbedPane;
    private JButton logoutButton;

    // Panels for each tab
    private GuardManagementPanel guardManagementPanel;
    private ShiftSchedulingPanel shiftSchedulingPanel;
    private IncidentReportsPanel incidentReportsPanel;

    private SFMSMainFrame mainFrame;

    public ManagerDashboardPanel(SFMSMainFrame mainFrame) {
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

        tabbedPane.addTab("Guard Management", guardManagementPanel);
        tabbedPane.addTab("Shift Scheduling", shiftSchedulingPanel);
        tabbedPane.addTab("Incident Reports", incidentReportsPanel);
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
}
