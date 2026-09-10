package gui;

import javax.swing.*;
import java.awt.*;

public class CounterStaffGUI extends JFrame {

    private String userID;
    private String username;
    private String name;

    public CounterStaffGUI(
            String userID,
            String username,
            String name) {

        this.userID = userID;
        this.username = username;
        this.name = name;

        setTitle(
                "APU Automotive Service Centre - Counter Staff"
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
                        "COUNTER STAFF MENU",
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
                                5,
                                2,
                                10,
                                10
                        )
                );

        JButton appointmentButton =
                new JButton("Manage Appointments");

        JButton customerButton =
                new JButton("Customer Information");

        JButton carButton =
                new JButton("Manage Cars");

        JButton paymentButton =
                new JButton("Process Payment");

        JButton receiptButton =
                new JButton("View Receipts");

        JButton feedbackButton =
                new JButton("Customer Comments");

        JButton viewButton =
                new JButton("View Records");

        JButton logoutButton =
                new JButton("Logout");

        buttonPanel.add(appointmentButton);
        buttonPanel.add(customerButton);

        buttonPanel.add(carButton);
        buttonPanel.add(paymentButton);

        buttonPanel.add(receiptButton);
        buttonPanel.add(feedbackButton);

        buttonPanel.add(viewButton);
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
                    "Appointment function will be connected here."
            );

        });

        customerButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer function will be connected here."
            );

        });

        carButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Car function will be connected here."
            );

        });

        paymentButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Payment function will be connected here."
            );

        });

        receiptButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Receipt function will be connected here."
            );

        });

        feedbackButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer comment function will be connected here."
            );

        });

        viewButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "View records function will be connected here."
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