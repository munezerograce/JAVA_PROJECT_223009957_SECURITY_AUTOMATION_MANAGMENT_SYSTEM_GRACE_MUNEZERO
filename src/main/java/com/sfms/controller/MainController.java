package com.sfms.controller;

import com.sfms.view.SFMSMainFrame;
import com.sfms.view.LoginPanel;

/**
 * Main controller for the SFMS application.
 */
public class MainController {
    private SFMSMainFrame mainFrame;

    public MainController() {
        this.mainFrame = new SFMSMainFrame(this);
        initializeControllers();
    }

    private void initializeControllers() {
        // Initialize login controller
        LoginController loginController = new LoginController(mainFrame.getLoginPanel(), this);

        // Show login panel initially
        mainFrame.showLoginPanel();
    }

    public void onLoginSuccess(String role) {
        switch (role) {
            case "Admin":
                mainFrame.showAdminDashboardPanel();
                // Initialize controllers for admin dashboard
                new GuardManagementController(mainFrame.getAdminDashboardPanel().getGuardManagementPanel());
                new ShiftSchedulingController(mainFrame.getAdminDashboardPanel().getShiftSchedulingPanel());
                new IncidentReportsController(mainFrame.getAdminDashboardPanel().getIncidentReportsPanel());
                break;
            case "Manager":
                mainFrame.showManagerDashboardPanel();
                // Initialize controllers for manager dashboard
                new GuardManagementController(mainFrame.getManagerDashboardPanel().getGuardManagementPanel());
                new ShiftSchedulingController(mainFrame.getManagerDashboardPanel().getShiftSchedulingPanel());
                new IncidentReportsController(mainFrame.getManagerDashboardPanel().getIncidentReportsPanel());
                break;
            case "Guard":
                mainFrame.showGuardDashboardPanel();
                // Initialize controllers for guard dashboard
                new IncidentReportsController(mainFrame.getGuardDashboardPanel().getIncidentReportsPanel());
                break;
            default:
                mainFrame.showLoginPanel();
                break;
        }
    }

    public void onLogout() {
        mainFrame.showLoginPanel();
    }

    public SFMSMainFrame getMainFrame() {
        return mainFrame;
    }
}
