package com.sfms.view;

import javax.swing.*;
import java.awt.*;

public class ShiftSchedulingPanel extends JPanel {
    private JTable shiftTable;
    private JButton addShiftButton;
    private JButton editShiftButton;
    private JButton deleteShiftButton;

    public ShiftSchedulingPanel() {
        setLayout(new BorderLayout());

        // Table for shifts
        shiftTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(shiftTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        addShiftButton = new JButton("Add Shift");
        editShiftButton = new JButton("Edit Shift");
        deleteShiftButton = new JButton("Delete Shift");

        buttonPanel.add(addShiftButton);
        buttonPanel.add(editShiftButton);
        buttonPanel.add(deleteShiftButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Getters for buttons
    public JButton getAddShiftButton() { return addShiftButton; }
    public JButton getEditShiftButton() { return editShiftButton; }
    public JButton getDeleteShiftButton() { return deleteShiftButton; }
    public JTable getShiftTable() { return shiftTable; }
}
