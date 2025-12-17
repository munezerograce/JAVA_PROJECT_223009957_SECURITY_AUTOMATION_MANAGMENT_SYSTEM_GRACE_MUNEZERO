package com.sfms.view;

import javax.swing.*;
import java.awt.*;

public class IncidentReportsPanel extends JPanel {
    private JTable incidentTable;
    private JButton viewIncidentButton;
    private JButton addIncidentButton;

    public IncidentReportsPanel() {
        setLayout(new BorderLayout());

        // Table for incidents
        incidentTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(incidentTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        viewIncidentButton = new JButton("View Incident");
        addIncidentButton = new JButton("Add Incident");

        buttonPanel.add(viewIncidentButton);
        buttonPanel.add(addIncidentButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Getters for buttons
    public JButton getViewIncidentButton() { return viewIncidentButton; }
    public JButton getAddIncidentButton() { return addIncidentButton; }
    public JTable getIncidentTable() { return incidentTable; }
}
