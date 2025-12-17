package com.sfms.controller;

import com.sfms.view.GuardManagementPanel;
import com.sfms.dao.GuardDAO;
import com.sfms.model.Guard;
import com.sfms.exception.DatabaseConnectionException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controller for guard management functionality.
 */
public class GuardManagementController {
    private GuardManagementPanel panel;
    private GuardDAO guardDAO;

    public GuardManagementController(GuardManagementPanel panel) {
        this.panel = panel;
        this.guardDAO = new GuardDAO();
        setupListeners();
        loadGuards();
    }

    private void setupListeners() {
        panel.getAddGuardButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGuard();
            }
        });

        panel.getEditGuardButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editGuard();
            }
        });

        panel.getDeleteGuardButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteGuard();
            }
        });
    }

    private void loadGuards() {
        try {
            List<Guard> guards = guardDAO.findAll();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Employee ID");
            model.addColumn("Contact Info");
            model.addColumn("Role");

            for (Guard guard : guards) {
                model.addRow(new Object[]{
                    guard.getId(),
                    guard.getName(),
                    guard.getEmployeeId(),
                    guard.getContactInfo(),
                    guard.getRole()
                });
            }

            panel.getGuardTable().setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Error loading guards: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addGuard() {
        JTextField nameField = new JTextField();
        JTextField employeeIdField = new JTextField();
        JTextField contactInfoField = new JTextField();
        JTextField roleField = new JTextField();

        Object[] message = {
            "Name:", nameField,
            "Employee ID:", employeeIdField,
            "Contact Info:", contactInfoField,
            "Role:", roleField
        };

        int option = JOptionPane.showConfirmDialog(panel, message, "Add Guard", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                Guard guard = new Guard();
                guard.setName(nameField.getText().trim());
                guard.setEmployeeId(employeeIdField.getText().trim());
                guard.setContactInfo(contactInfoField.getText().trim());
                guard.setRole(roleField.getText().trim());

                guardDAO.create(guard);
                loadGuards();
                JOptionPane.showMessageDialog(panel, "Guard added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(panel, "Error adding guard: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editGuard() {
        int selectedRow = panel.getGuardTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(panel, "Please select a guard to edit", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) panel.getGuardTable().getModel();
        int id = (Integer) model.getValueAt(selectedRow, 0);
        String currentName = (String) model.getValueAt(selectedRow, 1);
        String currentEmployeeId = (String) model.getValueAt(selectedRow, 2);
        String currentContactInfo = (String) model.getValueAt(selectedRow, 3);
        String currentRole = (String) model.getValueAt(selectedRow, 4);

        JTextField nameField = new JTextField(currentName);
        JTextField employeeIdField = new JTextField(currentEmployeeId);
        JTextField contactInfoField = new JTextField(currentContactInfo);
        JTextField roleField = new JTextField(currentRole);

        Object[] message = {
            "Name:", nameField,
            "Employee ID:", employeeIdField,
            "Contact Info:", contactInfoField,
            "Role:", roleField
        };

        int option = JOptionPane.showConfirmDialog(panel, message, "Edit Guard", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                Guard guard = new Guard();
                guard.setId(id);
                guard.setName(nameField.getText().trim());
                guard.setEmployeeId(employeeIdField.getText().trim());
                guard.setContactInfo(contactInfoField.getText().trim());
                guard.setRole(roleField.getText().trim());

                guardDAO.update(guard);
                loadGuards();
                JOptionPane.showMessageDialog(panel, "Guard updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(panel, "Error updating guard: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteGuard() {
        int selectedRow = panel.getGuardTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(panel, "Please select a guard to delete", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) panel.getGuardTable().getModel();
        int id = (Integer) model.getValueAt(selectedRow, 0);
        String name = (String) model.getValueAt(selectedRow, 1);

        int option = JOptionPane.showConfirmDialog(panel, "Are you sure you want to delete guard '" + name + "'?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                guardDAO.delete(id);
                loadGuards();
                JOptionPane.showMessageDialog(panel, "Guard deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(panel, "Error deleting guard: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
