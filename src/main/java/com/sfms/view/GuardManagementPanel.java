package com.sfms.view;

import javax.swing.*;
import java.awt.*;

public class GuardManagementPanel extends JPanel {
    private JTable guardTable;
    private JButton addGuardButton;
    private JButton editGuardButton;
    private JButton deleteGuardButton;

    public GuardManagementPanel() {
        setLayout(new BorderLayout());

        // Table for guards
        guardTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(guardTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        addGuardButton = new JButton("Add Guard");
        editGuardButton = new JButton("Edit Guard");
        deleteGuardButton = new JButton("Delete Guard");

        buttonPanel.add(addGuardButton);
        buttonPanel.add(editGuardButton);
        buttonPanel.add(deleteGuardButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Getters for buttons
    public JButton getAddGuardButton() { return addGuardButton; }
    public JButton getEditGuardButton() { return editGuardButton; }
    public JButton getDeleteGuardButton() { return deleteGuardButton; }
    public JTable getGuardTable() { return guardTable; }
}
