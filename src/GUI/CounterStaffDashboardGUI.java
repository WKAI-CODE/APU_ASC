package GUI;

import apu_asc.model.CounterStaff;
import apu_asc.model.Staff;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CounterStaffDashboardGUI
        implements ActionListener {

    CounterStaff counterStaff;

    JFrame x;

    JLabel welcome;
    JLabel title;
    JLabel footer;

    JButton manageCustomers;
    JButton manageCars;
    JButton manageAppointments;
    JButton payments;
    JButton editProfile;
    JButton logout;

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == manageCustomers) {

            new CustomerManagementGUI(counterStaff);
            x.setVisible(false);

        } else if (e.getSource() == manageCars) {

            new CarManagementGUI(counterStaff);
            x.setVisible(false);

        } else if (e.getSource()
                == manageAppointments) {

            new AppointmentManagementGUI(
                    counterStaff
            );

            x.setVisible(false);

        } else if (e.getSource() == payments) {

            new PaymentManagementGUI(
                    counterStaff
            );

            x.setVisible(false);

        } else if (e.getSource() == editProfile) {

            new EditCSProfileGUI(counterStaff);
            x.setVisible(false);

        } else if (e.getSource() == logout) {
            
            int choice = JOptionPane.showConfirmDialog(x,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );
            
            if(choice == JOptionPane.YES_OPTION){
                

            LoginGUI loginPage = new LoginGUI();

            loginPage.setVisible(true);
            x.setVisible(false);
            }
        }
    }

    public CounterStaffDashboardGUI(Staff staff) {

        counterStaff = (CounterStaff) staff;

        x = new JFrame();

        x.setTitle("Counter Staff Dashboard");
        x.setSize(700, 400);
        x.setLocation(400, 180);

        x.setLayout(
                new BorderLayout(10, 10)
        );

        // Header section
        JPanel headerPanel = new JPanel();

        headerPanel.setLayout(
                new BorderLayout(10, 10)
        );

        headerPanel.setBackground(Color.blue);

        // Welcome message on the left
        JPanel welcomePanel = new JPanel();

        welcomePanel.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        15,
                        15
                )
        );

        welcomePanel.setBackground(Color.blue);

        welcome = new JLabel(
                "Welcome, "
                + counterStaff.getName()
        );

        welcome.setForeground(Color.white);

        welcome.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        welcomePanel.add(welcome);

        // Dashboard title in the centre
        JPanel titlePanel = new JPanel();

        titlePanel.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        15
                )
        );

        titlePanel.setBackground(Color.blue);

        title = new JLabel(
                "Counter Staff Dashboard"
        );

        title.setForeground(Color.white);

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        titlePanel.add(title);

        // Edit Profile button in the top-right
        JPanel profilePanel = new JPanel();

        profilePanel.setLayout(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        15,
                        10
                )
        );

        profilePanel.setBackground(Color.blue);

        editProfile =
                new JButton("   Edit Profile   ");

        editProfile.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        editProfile.addActionListener(this);

        profilePanel.add(editProfile);

        headerPanel.add(
                "West",
                welcomePanel
        );

        headerPanel.add(
                "Center",
                titlePanel
        );

        headerPanel.add(
                "East",
                profilePanel
        );

        // Main function section
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(
                new BorderLayout()
        );

        mainPanel.setBackground(Color.white);

        // Instruction
        JPanel instructionPanel = new JPanel();

        instructionPanel.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        10,
                        15
                )
        );

        instructionPanel.setBackground(Color.white);

        JLabel instruction =
                new JLabel("Select a Function");

        instruction.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        instructionPanel.add(instruction);

        // Area around the four buttons
        JPanel functionArea = new JPanel();

        functionArea.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        30,
                        15
                )
        );

        functionArea.setBackground(Color.white);

        // Four function buttons
        JPanel functionPanel = new JPanel();

        functionPanel.setLayout(
                new GridLayout(
                        2,
                        2,
                        20,
                        20
                )
        );

        functionPanel.setBackground(Color.white);

        manageCustomers =
                new JButton("Manage Customers");

        manageCars =
                new JButton("Manage Cars");

        manageAppointments =
                new JButton("Manage Appointments");

        payments =
                new JButton("Payments and Receipts");

        Font buttonFont = new Font(
                "SansSerif",
                Font.BOLD,
                14
        );

        manageCustomers.setFont(buttonFont);
        manageCars.setFont(buttonFont);
        manageAppointments.setFont(buttonFont);
        payments.setFont(buttonFont);

        // Centre the text inside the buttons
        manageCustomers.setVerticalAlignment(
                JButton.CENTER
        );

        manageCars.setVerticalAlignment(
                JButton.CENTER
        );

        manageAppointments.setVerticalAlignment(
                JButton.CENTER
        );

        payments.setVerticalAlignment(
                JButton.CENTER
        );

        manageCustomers.setHorizontalAlignment(
                JButton.CENTER
        );

        manageCars.setHorizontalAlignment(
                JButton.CENTER
        );

        manageAppointments.setHorizontalAlignment(
                JButton.CENTER
        );

        payments.setHorizontalAlignment(
                JButton.CENTER
        );

        manageCustomers.addActionListener(this);
        manageCars.addActionListener(this);
        manageAppointments.addActionListener(this);
        payments.addActionListener(this);

        functionPanel.add(manageCustomers);
        functionPanel.add(manageCars);
        functionPanel.add(manageAppointments);
        functionPanel.add(payments);

        functionArea.add(functionPanel);

        mainPanel.add(
                "North",
                instructionPanel
        );

        mainPanel.add(
                "Center",
                functionArea
        );

        // Bottom section
        JPanel bottomPanel = new JPanel();

        bottomPanel.setLayout(
                new GridLayout(2, 1, 5, 5)
        );

        bottomPanel.setBackground(Color.white);

        // Logout button
        JPanel logoutPanel = new JPanel();

        logoutPanel.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        10,
                        10
                )
        );

        logoutPanel.setBackground(Color.white);

        logout = new JButton(
                "     Logout     "
        );

        logout.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        logout.setVerticalAlignment(
                JButton.CENTER
        );

        logout.setHorizontalAlignment(
                JButton.CENTER
        );

        logout.addActionListener(this);

        logoutPanel.add(logout);

        // Footer
        JPanel footerPanel = new JPanel();

        footerPanel.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        10,
                        8
                )
        );

        footerPanel.setBackground(Color.white);

        footer = new JLabel(
                "Automotive Service Centre Management System"
        );

        footerPanel.add(footer);

        bottomPanel.add(logoutPanel);
        bottomPanel.add(footerPanel);

        // Add all dashboard sections
        x.add("North", headerPanel);
        x.add("Center", mainPanel);
        x.add("South", bottomPanel);

        x.setVisible(true);
    }
}