package com.sfms.controller;

import com.sfms.view.IncidentReportsPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for incident reports functionality.
 */
public class IncidentReportsController {
    private IncidentReportsPanel panel;

    public IncidentReportsController(IncidentReportsPanel panel) {
        this.panel = panel;
        setupListeners();
        loadIncidents();
    }

    private void setupListeners() {
        panel.getViewIncidentButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewIncident();
            }
        });

        panel.getAddIncidentButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addIncident();
            }
        });
    }

    private void loadIncidents() {
        // Dummy data for now
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Title");
        model.addColumn("Description");
        model.addColumn("Severity");
        model.addColumn("Report Time");

        // Add some dummy rows
        model.addRow(new Object[]{1, "Unauthorized Access", "Someone tried to enter restricted area", "Medium", "2023-10-01 10:30"});
        model.addRow(new Object[]{2, "Equipment Malfunction", "Security camera offline", "Low", "2023-10-01 14:15"});

        panel.getIncidentTable().setModel(model);
    }

    private void viewIncident() {
        int selectedRow = panel.getIncidentTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(panel, "Please select an incident to view.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) panel.getIncidentTable().getModel();
        String title = (String) model.getValueAt(selectedRow, 1);
        String description = (String) model.getValueAt(selectedRow, 2);
        String severity = (String) model.getValueAt(selectedRow, 3);
        String reportTime = (String) model.getValueAt(selectedRow, 4);

        String message = String.format("Title: %s\nDescription: %s\nSeverity: %s\nReport Time: %s",
            title, description, severity, reportTime);
        JOptionPane.showMessageDialog(panel, message, "Incident Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addIncident() {
        JTextField titleField = new JTextField();
        JTextArea descriptionArea = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        JComboBox<String> severityCombo = new JComboBox<>(new String[]{"Low", "Medium", "High"});
        JTextField reportTimeField = new JTextField("2023-10-01 10:30");

        Object[] message = {
            "Title:", titleField,
            "Description:", scrollPane,
            "Severity:", severityCombo,
            "Report Time:", reportTimeField
        };

        int option = JOptionPane.showConfirmDialog(panel, message, "Add Incident", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            DefaultTableModel model = (DefaultTableModel) panel.getIncidentTable().getModel();
            model.addRow(new Object[]{
                model.getRowCount() + 1,
                titleField.getText().trim(),
                descriptionArea.getText().trim(),
                severityCombo.getSelectedItem(),
                reportTimeField.getText().trim()
            });
            JOptionPane.showMessageDialog(panel, "Incident added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
