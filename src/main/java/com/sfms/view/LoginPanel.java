package com.sfms.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Login panel for user authentication.
 */
public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private LoginListener loginListener;

    public LoginPanel() {
        initializeComponents();
        setupLayout();
        setupListeners();
    }

    private void initializeComponents() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
    }

    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);
    }

    private void setupListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginListener != null) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    loginListener.onLoginAttempt(username, password);
                }
            }
        });
    }

    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }

    public interface LoginListener {
        void onLoginAttempt(String username, String password);
    }
}
