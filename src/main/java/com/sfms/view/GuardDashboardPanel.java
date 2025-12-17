package com.sfms.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Guard dashboard panel with access to patrol logs and incident reporting.
 */
public class GuardDashboardPanel extends JPanel {
    private JTabbedPane tabbedPane;
    private JButton logoutButton;

    // Panels for each tab
    private IncidentReportsPanel incidentReportsPanel;
    private JPanel patrolLogsPanel; // Placeholder for patrol logs

    private SFMSMainFrame mainFrame;

    public GuardDashboardPanel(SFMSMainFrame mainFrame) {
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

        incidentReportsPanel = new IncidentReportsPanel();
        patrolLogsPanel = new JPanel(); // TODO: Implement PatrolLogsPanel

        tabbedPane.addTab("Incident Reports", incidentReportsPanel);
        tabbedPane.addTab("Patrol Logs", patrolLogsPanel);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        add(logoutButton, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    public IncidentReportsPanel getIncidentReportsPanel() {
        return incidentReportsPanel;
    }

    public JPanel getPatrolLogsPanel() {
        return patrolLogsPanel;
    }
}
