package com.sfms.view;

import com.sfms.controller.MainController;
import javax.swing.*;
import java.awt.*;

public class SFMSMainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private ManagerDashboardPanel managerDashboardPanel;
    private AdminDashboardPanel adminDashboardPanel;
    private GuardDashboardPanel guardDashboardPanel;

    private MainController mainController;

    public SFMSMainFrame(MainController mainController) {
        this.mainController = mainController;
        setTitle("Security Force Management System (SFMS)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize panels
        loginPanel = new LoginPanel();
        managerDashboardPanel = new ManagerDashboardPanel(this);
        adminDashboardPanel = new AdminDashboardPanel(this);
        guardDashboardPanel = new GuardDashboardPanel(this);

        // Add panels to the main panel
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(managerDashboardPanel, "ManagerDashboard");
        mainPanel.add(adminDashboardPanel, "AdminDashboard");
        mainPanel.add(guardDashboardPanel, "GuardDashboard");

        add(mainPanel);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public void addPanel(JPanel panel, String name) {
        mainPanel.add(panel, name);
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public ManagerDashboardPanel getManagerDashboardPanel() {
        return managerDashboardPanel;
    }

    public AdminDashboardPanel getAdminDashboardPanel() {
        return adminDashboardPanel;
    }

    public GuardDashboardPanel getGuardDashboardPanel() {
        return guardDashboardPanel;
    }

    public void showLoginPanel() {
        showPanel("Login");
    }

    public void showManagerDashboardPanel() {
        showPanel("ManagerDashboard");
    }

    public void showAdminDashboardPanel() {
        showPanel("AdminDashboard");
    }

    public void showGuardDashboardPanel() {
        showPanel("GuardDashboard");
    }

    public void logout() {
        mainController.onLogout();
    }
}
