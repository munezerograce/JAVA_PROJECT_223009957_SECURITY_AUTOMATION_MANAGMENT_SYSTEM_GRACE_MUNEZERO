package com.sfms.controller;

import com.sfms.view.ShiftSchedulingPanel;
import com.sfms.dao.GuardDAO;
import com.sfms.model.Guard;
import com.sfms.exception.DatabaseConnectionException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controller for shift scheduling functionality.
 */
public class ShiftSchedulingController {
    private ShiftSchedulingPanel panel;
    private GuardDAO guardDAO;

    public ShiftSchedulingController(ShiftSchedulingPanel panel) {
        this.panel = panel;
        this.guardDAO = new GuardDAO();
        setupListeners();
        loadShifts();
    }

    private void setupListeners() {
        panel.getAddShiftButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addShift();
            }
        });

        panel.getEditShiftButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editShift();
            }
        });

        panel.getDeleteShiftButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteShift();
            }
        });
    }

    private void loadShifts() {
        // Dummy data for now
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Guard");
        model.addColumn("Site");
        model.addColumn("Start Time");
        model.addColumn("End Time");
        model.addColumn("Status");

        // Add some dummy rows
        model.addRow(new Object[]{1, "John Doe", "Hospital A", "2023-10-01 08:00", "2023-10-01 16:00", "Scheduled"});
        model.addRow(new Object[]{2, "Jane Smith", "Bank HQ", "2023-10-01 16:00", "2023-10-02 00:00", "Started"});

        panel.getShiftTable().setModel(model);
    }

    private void addShift() {
        try {
            List<Guard> guards = guardDAO.findAll();
            String[] guardNames = guards.stream()
                .map(guard -> guard.getName() + " (" + guard.getEmployeeId() + ")")
                .toArray(String[]::new);
            JComboBox<String> guardCombo = new JComboBox<>(guardNames);
            JTextField siteField = new JTextField();
            JTextField startTimeField = new JTextField("2023-10-01 08:00");
            JTextField endTimeField = new JTextField("2023-10-01 16:00");
            JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Scheduled", "Started", "Completed", "Canceled"});

            Object[] message = {
                "Guard:", guardCombo,
                "Site:", siteField,
                "Start Time:", startTimeField,
                "End Time:", endTimeField,
                "Status:", statusCombo
            };

            int option = JOptionPane.showConfirmDialog(panel, message, "Add Shift", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                DefaultTableModel model = (DefaultTableModel) panel.getShiftTable().getModel();
                model.addRow(new Object[]{
                    model.getRowCount() + 1,
                    guardCombo.getSelectedItem(),
                    siteField.getText().trim(),
                    startTimeField.getText().trim(),
                    endTimeField.getText().trim(),
                    statusCombo.getSelectedItem()
                });
                JOptionPane.showMessageDialog(panel, "Shift added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Error loading guards: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editShift() {
        int selectedRow = panel.getShiftTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(panel, "Please select a shift to edit.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) panel.getShiftTable().getModel();
        String currentGuard = (String) model.getValueAt(selectedRow, 1);
        String currentSite = (String) model.getValueAt(selectedRow, 2);
        String currentStart = (String) model.getValueAt(selectedRow, 3);
        String currentEnd = (String) model.getValueAt(selectedRow, 4);
        String currentStatus = (String) model.getValueAt(selectedRow, 5);

        try {
            List<Guard> guards = guardDAO.findAll();
            String[] guardNames = guards.stream()
                .map(guard -> guard.getName() + " (" + guard.getEmployeeId() + ")")
                .toArray(String[]::new);
            JComboBox<String> guardCombo = new JComboBox<>(guardNames);
            guardCombo.setSelectedItem(currentGuard);
            JTextField siteField = new JTextField(currentSite);
            JTextField startTimeField = new JTextField(currentStart);
            JTextField endTimeField = new JTextField(currentEnd);
            JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Scheduled", "Started", "Completed", "Canceled"});
            statusCombo.setSelectedItem(currentStatus);

            Object[] message = {
                "Guard:", guardCombo,
                "Site:", siteField,
                "Start Time:", startTimeField,
                "End Time:", endTimeField,
                "Status:", statusCombo
            };

            int option = JOptionPane.showConfirmDialog(panel, message, "Edit Shift", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                model.setValueAt(guardCombo.getSelectedItem(), selectedRow, 1);
                model.setValueAt(siteField.getText().trim(), selectedRow, 2);
                model.setValueAt(startTimeField.getText().trim(), selectedRow, 3);
                model.setValueAt(endTimeField.getText().trim(), selectedRow, 4);
                model.setValueAt(statusCombo.getSelectedItem(), selectedRow, 5);
                JOptionPane.showMessageDialog(panel, "Shift updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Error loading guards: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteShift() {
        int selectedRow = panel.getShiftTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(panel, "Please select a shift to delete.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int option = JOptionPane.showConfirmDialog(panel, "Are you sure you want to delete this shift?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) panel.getShiftTable().getModel();
            model.removeRow(selectedRow);
            JOptionPane.showMessageDialog(panel, "Shift deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
