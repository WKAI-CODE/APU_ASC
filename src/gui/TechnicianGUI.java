package gui;

import javax.swing.*;
import java.awt.*;

public class TechnicianGUI extends JFrame {

    private String userID;
    private String username;
    private String name;

    public TechnicianGUI(
            String userID,
            String username,
            String name) {

        this.userID = userID;
        this.username = username;
        this.name = name;

        setTitle(
                "APU Automotive Service Centre - Technician"
        );

        setSize(700, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        createGUI();

        setVisible(true);
    }

    private void createGUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        // ======================================
        // HEADER
        // ======================================

        JPanel headerPanel =
                new JPanel(new GridLayout(3, 1));

        JLabel titleLabel =
                new JLabel(
                        "TECHNICIAN MENU",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        JLabel welcomeLabel =
                new JLabel(
                        "Welcome, " + name,
                        SwingConstants.CENTER
                );

        JLabel userLabel =
                new JLabel(
                        "User ID: " + userID
                        + "    Username: " + username,
                        SwingConstants.CENTER
                );

        headerPanel.add(titleLabel);
        headerPanel.add(welcomeLabel);
        headerPanel.add(userLabel);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // ======================================
        // BUTTONS
        // ======================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                10,
                                10
                        )
                );

        JButton appointmentButton =
                new JButton("View Appointments");

        JButton serviceButton =
                new JButton("Perform Service");

        JButton statusButton =
                new JButton("Update Service Status");

        JButton feedbackButton =
                new JButton("Give Feedback");

        JButton customerButton =
                new JButton("View Customer Information");

        JButton historyButton =
                new JButton("Service History");

        JButton logoutButton =
                new JButton("Logout");

        buttonPanel.add(appointmentButton);
        buttonPanel.add(serviceButton);

        buttonPanel.add(statusButton);
        buttonPanel.add(feedbackButton);

        buttonPanel.add(customerButton);
        buttonPanel.add(historyButton);

        buttonPanel.add(logoutButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // ======================================
        // BUTTON ACTIONS
        // ======================================

        appointmentButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "View appointment function will be connected here."
            );

        });

        serviceButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Perform service function will be connected here."
            );

        });

        statusButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Update service status function will be connected here."
            );

        });

        feedbackButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Technician feedback function will be connected here."
            );

        });

        customerButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer information function will be connected here."
            );

        });

        historyButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Service history function will be connected here."
            );

        });

        logoutButton.addActionListener(e -> logout());
    }

    // ==========================================
    // LOGOUT
    // ==========================================

    private void logout() {

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirm == JOptionPane.YES_OPTION) {

            new LoginGUI();

            dispose();
        }
    }
}