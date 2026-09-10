package gui;

import apu_asc.model.ManagerFunction;
import apu_asc.model.Staff;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManagerGUI extends JFrame {

    private final ManagerFunction managerFunction;

    private String userID;
    private String username;
    private String name;

    public ManagerGUI(String userID, String username, String name) {

        this.userID = userID;
        this.username = username;
        this.name = name;

        managerFunction = new ManagerFunction();

        setTitle("APU Automotive Service Centre - Manager");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createGUI();

        setVisible(true);
    }

    // ============================================================
    // MAIN GUI
    // ============================================================

    private void createGUI() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JLabel title = new JLabel(
                "APU AUTOMOTIVE SERVICE CENTRE - MANAGER",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel welcome = new JLabel(
                "Welcome, " + name + " (" + username + ")",
                SwingConstants.CENTER
        );

        welcome.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(title);
        topPanel.add(welcome);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel =
                new JPanel(new GridLayout(4, 2, 10, 10));

        JButton addStaffButton =
                new JButton("Add Staff");

        JButton viewStaffButton =
                new JButton("View Staff");

        JButton updateStaffButton =
                new JButton("Update Staff");

        JButton deleteStaffButton =
                new JButton("Delete Staff");

        JButton servicePriceButton =
                new JButton("Set Service Price");

        JButton feedbackButton =
                new JButton("View Feedback");

        JButton reportButton =
                new JButton("Generate Reports");

        JButton passwordButton =
                new JButton("Change Password");

        buttonPanel.add(addStaffButton);
        buttonPanel.add(viewStaffButton);

        buttonPanel.add(updateStaffButton);
        buttonPanel.add(deleteStaffButton);

        buttonPanel.add(servicePriceButton);
        buttonPanel.add(feedbackButton);

        buttonPanel.add(reportButton);
        buttonPanel.add(passwordButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        JButton logoutButton =
                new JButton("Logout");

        JButton exitButton =
                new JButton("Exit");

        bottomPanel.add(logoutButton);
        bottomPanel.add(exitButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // BUTTON ACTIONS

        addStaffButton.addActionListener(e -> addStaff());

        viewStaffButton.addActionListener(e -> viewStaff());

        updateStaffButton.addActionListener(e -> updateStaff());

        deleteStaffButton.addActionListener(e -> deleteStaff());

        servicePriceButton.addActionListener(e -> setServicePrice());

        feedbackButton.addActionListener(e -> viewFeedback());

        reportButton.addActionListener(e -> generateReports());

        passwordButton.addActionListener(e -> changePassword());

        logoutButton.addActionListener(e -> logout());

        exitButton.addActionListener(e -> System.exit(0));
    }

    // ============================================================
    // 1. ADD STAFF
    // ============================================================

    private void addStaff() {

        // User ID is NOT entered manually anymore
        // It will be generated automatically.

        JPanel panel = new JPanel(
                new GridLayout(8, 2, 5, 5)
        );

        JComboBox<String> roleBox =
                new JComboBox<>(
                        new String[]{
                            "Manager",
                            "CounterStaff",
                            "Technician"
                        }
                );

        JTextField usernameField =
                new JTextField();

        JTextField nameField =
                new JTextField();

        JTextField phoneField =
                new JTextField();

        JTextField ageField =
                new JTextField();

        JTextField identityField =
                new JTextField();

        JTextField emailField =
                new JTextField();

        JTextField addressField =
                new JTextField();

        panel.add(new JLabel("Role:"));
        panel.add(roleBox);

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneField);

        panel.add(new JLabel("Age:"));
        panel.add(ageField);

        panel.add(new JLabel("Identity Number:"));
        panel.add(identityField);

        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        panel.add(new JLabel("Address:"));
        panel.add(addressField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add Staff",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        // ========================================================
        // GET VALUES
        // ========================================================

        String role =
                roleBox.getSelectedItem().toString();

        String username =
                usernameField.getText().trim();

        String staffName =
                nameField.getText().trim();

        String phone =
                phoneField.getText().trim();

        String ageText =
                ageField.getText().trim();

        String identity =
                identityField.getText().trim();

        String email =
                emailField.getText().trim();

        String address =
                addressField.getText().trim();

        // ========================================================
        // CHECK EMPTY
        // ========================================================

        if (username.isEmpty()
                || staffName.isEmpty()
                || phone.isEmpty()
                || ageText.isEmpty()
                || identity.isEmpty()
                || email.isEmpty()
                || address.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ========================================================
        // CHECK AGE
        // ========================================================

        int age;

        try {

            age = Integer.parseInt(ageText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Age must be a number.",
                    "Invalid Age",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (age <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Age must be greater than 0.",
                    "Invalid Age",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // ========================================================
        // CHECK DUPLICATE USERNAME
        // ========================================================

        if (managerFunction.usernameExists(username)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Username already exists!\n"
                    + "Please use another username.",
                    "Duplicate Username",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ========================================================
        // AUTO GENERATE USER ID
        // ========================================================

        String userID =
                managerFunction.generateUserID(role);

        if (userID.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid staff role.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // ========================================================
        // ADD STAFF
        // ========================================================

        boolean success =
                managerFunction.addStaff(
                        role,
                        userID,
                        username,
                        staffName,
                        phone,
                        age,
                        identity,
                        email,
                        address
                );

        // ========================================================
        // RESULT
        // ========================================================

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Staff added successfully!\n\n"
                    + "User ID: " + userID + "\n"
                    + "Username: " + username + "\n"
                    + "Role: " + role + "\n\n"
                    + "Default Password:\n"
                    + username + userID,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to add staff.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ============================================================
    // 2. VIEW STAFF
    // ============================================================

    private void viewStaff() {

        ArrayList<Staff> staffList =
                managerFunction.getAllStaff();

        if (staffList.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No staff found.",
                    "View Staff",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        StringBuilder output =
                new StringBuilder();

        output.append(
                "================ STAFF LIST ================\n\n"
        );

        for (Staff staff : staffList) {

            output.append(
                    "--------------------------------------------\n"
            );

            output.append("User ID          : ")
                    .append(staff.getUserID())
                    .append("\n");

            output.append("Username         : ")
                    .append(staff.getUsername())
                    .append("\n");

            output.append("Name             : ")
                    .append(staff.getName())
                    .append("\n");

            output.append("Role             : ")
                    .append(staff.getRole())
                    .append("\n");

            output.append("Phone Number     : ")
                    .append(staff.getPhoneNumber())
                    .append("\n");

            output.append("Age              : ")
                    .append(staff.getAge())
                    .append("\n");

            output.append("Identity Number  : ")
                    .append(staff.getIdentityNumber())
                    .append("\n");

            output.append("Email            : ")
                    .append(staff.getEmail())
                    .append("\n");

            output.append("Address          : ")
                    .append(staff.getAddress())
                    .append("\n\n");
        }

        JTextArea textArea =
                new JTextArea(output.toString());

        textArea.setEditable(false);

        textArea.setFont(
                new Font("Monospaced", Font.PLAIN, 13)
        );

        JScrollPane scrollPane =
                new JScrollPane(textArea);

        scrollPane.setPreferredSize(
                new Dimension(650, 400)
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "View Staff",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ============================================================
    // 3. UPDATE STAFF
    // ============================================================

    private void updateStaff() {

        String userID =
                JOptionPane.showInputDialog(
                        this,
                        "Enter User ID to update:"
                );

        if (userID == null
                || userID.trim().isEmpty()) {

            return;
        }

        userID =
                userID.trim().toUpperCase();

        String[] fields = {
            "Username",
            "Name",
            "Phone Number",
            "Age",
            "Identity Number",
            "Email",
            "Address"
        };

        String field =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select information to update:",
                        "Update Staff",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        fields,
                        fields[0]
                );

        if (field == null) {
            return;
        }

        String newValue =
                JOptionPane.showInputDialog(
                        this,
                        "Enter new " + field + ":"
                );

        if (newValue == null
                || newValue.trim().isEmpty()) {

            return;
        }

        newValue =
                newValue.trim();

        // Username cannot duplicate

        if (field.equals("Username")
                && managerFunction.usernameExists(newValue)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Username already exists!",
                    "Duplicate Username",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        boolean success =
                managerFunction.updateStaff(
                        userID,
                        field,
                        newValue
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    field + " updated successfully!"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Update failed.\n"
                    + "Please check the User ID and input.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ============================================================
    // 4. DELETE STAFF
    // ============================================================

    private void deleteStaff() {

        String userID =
                JOptionPane.showInputDialog(
                        this,
                        "Enter User ID to delete:"
                );

        if (userID == null
                || userID.trim().isEmpty()) {

            return;
        }

        userID =
                userID.trim().toUpperCase();

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete staff:\n"
                        + userID + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        boolean success =
                managerFunction.deleteStaff(userID);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Staff deleted successfully!"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Staff not found.",
                    "Delete Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ============================================================
    // 5. SET SERVICE PRICE
    // ============================================================

    private void setServicePrice() {

        String minorText =
                JOptionPane.showInputDialog(
                        this,
                        "Enter MINOR service price:"
                );

        if (minorText == null) {
            return;
        }

        String majorText =
                JOptionPane.showInputDialog(
                        this,
                        "Enter MAJOR service price:"
                );

        if (majorText == null) {
            return;
        }

        double minorPrice;
        double majorPrice;

        try {

            minorPrice =
                    Double.parseDouble(minorText);

            majorPrice =
                    Double.parseDouble(majorText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Price must be a valid number.",
                    "Invalid Price",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (minorPrice < 0
                || majorPrice < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Price cannot be negative.",
                    "Invalid Price",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        boolean success =
                managerFunction.setServicePrice(
                        minorPrice,
                        majorPrice
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Service prices updated successfully!\n\n"
                    + "MINOR : RM "
                    + String.format("%.2f", minorPrice)
                    + "\n"
                    + "MAJOR : RM "
                    + String.format("%.2f", majorPrice)
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update service prices."
            );
        }
    }

    // ============================================================
    // 6. VIEW FEEDBACK
    // ============================================================

    private void viewFeedback() {

        ArrayList<String> technicianFeedback =
                managerFunction.getTechnicianFeedback();

        ArrayList<String> customerComments =
                managerFunction.getCustomerComments();

        StringBuilder output =
                new StringBuilder();

        output.append(
                "========== TECHNICIAN FEEDBACK ==========\n\n"
        );

        if (technicianFeedback.isEmpty()) {

            output.append(
                    "No technician feedback found.\n"
            );

        } else {

            for (String line : technicianFeedback) {

                output.append(line)
                        .append("\n");
            }
        }

        output.append(
                "\n========== CUSTOMER COMMENTS ==========\n\n"
        );

        if (customerComments.isEmpty()) {

            output.append(
                    "No customer comments found.\n"
            );

        } else {

            for (String line : customerComments) {

                output.append(line)
                        .append("\n");
            }
        }

        JTextArea textArea =
                new JTextArea(output.toString());

        textArea.setEditable(false);

        textArea.setFont(
                new Font("Monospaced", Font.PLAIN, 13)
        );

        JScrollPane scrollPane =
                new JScrollPane(textArea);

        scrollPane.setPreferredSize(
                new Dimension(650, 400)
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "Feedback and Comments",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ============================================================
    // 7. GENERATE REPORTS
    // ============================================================

    private void generateReports() {

        String[] reports = {
            "Appointment Status Report",
            "Service Analysis Report",
            "Revenue Report",
            "Technician Performance Report",
            "Exit"
        };

        while (true) {

            String choice =
                    (String) JOptionPane.showInputDialog(
                            this,
                            "Select a report:",
                            "Generate Reports",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            reports,
                            reports[0]
                    );

            if (choice == null
                    || choice.equals("Exit")) {

                break;
            }

            String report = "";

            switch (choice) {

                case "Appointment Status Report":

                    report =
                            managerFunction
                                    .appointmentStatusReport();

                    break;

                case "Service Analysis Report":

                    report =
                            managerFunction
                                    .serviceAnalysisReport();

                    break;

                case "Revenue Report":

                    report =
                            managerFunction
                                    .revenueReport();

                    break;

                case "Technician Performance Report":

                    report =
                            managerFunction
                                    .technicianPerformanceReport();

                    break;
            }

            JTextArea textArea =
                    new JTextArea(report);

            textArea.setEditable(false);

            textArea.setFont(
                    new Font("Monospaced", Font.PLAIN, 13)
            );

            JScrollPane scrollPane =
                    new JScrollPane(textArea);

            scrollPane.setPreferredSize(
                    new Dimension(600, 400)
            );

            JOptionPane.showMessageDialog(
                    this,
                    scrollPane,
                    choice,
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // ============================================================
    // 8. CHANGE OWN PASSWORD
    // ============================================================

    private void changePassword() {

        JPasswordField currentPassword =
                new JPasswordField();

        JPasswordField newPassword =
                new JPasswordField();

        JPasswordField confirmPassword =
                new JPasswordField();

        JPanel panel =
                new JPanel(
                        new GridLayout(3, 2, 5, 5)
                );

        panel.add(
                new JLabel("Current Password:")
        );

        panel.add(currentPassword);

        panel.add(
                new JLabel("New Password:")
        );

        panel.add(newPassword);

        panel.add(
                new JLabel("Confirm Password:")
        );

        panel.add(confirmPassword);

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Change Password",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String current =
                new String(
                        currentPassword.getPassword()
                );

        String newPass =
                new String(
                        newPassword.getPassword()
                );

        String confirm =
                new String(
                        confirmPassword.getPassword()
                );

        if (current.isEmpty()
                || newPass.isEmpty()
                || confirm.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields."
            );

            return;
        }

        if (!newPass.equals(confirm)) {

            JOptionPane.showMessageDialog(
                    this,
                    "New passwords do not match!",
                    "Password Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (current.equals(newPass)) {

            JOptionPane.showMessageDialog(
                    this,
                    "New password must be different "
                    + "from current password."
            );

            return;
        }

        boolean success =
                managerFunction.changePassword(
                        userID,
                        current,
                        newPass
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password changed successfully!"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Current password is incorrect.",
                    "Password Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ============================================================
    // 9. LOGOUT
    // ============================================================

    private void logout() {

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirm == JOptionPane.YES_OPTION) {

            dispose();

            new LoginGUI();
        }
    }
}