package com.sfms;

import com.sfms.controller.MainController;
import com.sfms.view.SFMSMainFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Main application class for the Security Force Management System.
 */
public class SFMSApplication {
    public static void main(String[] args) {
        // Set up the look and feel
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Launch the application on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Create the main controller
        MainController mainController = new MainController();

        // Get the main frame from the controller
        SFMSMainFrame mainFrame = mainController.getMainFrame();

        // Show the main frame
        mainFrame.setVisible(true);
    }
}
