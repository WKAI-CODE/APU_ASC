package gui;

import apu_asc.model.Staff;
import apu_asc.model.Appointment;
import apu_asc.model.TechnicianFeedback;
import utility.DataIO;
import apu_asc.model.ServiceRecord;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TechnicianGUI extends JFrame {

    private Staff technician;
    private String userID;
    private String username;
    private String name;
    
    public TechnicianGUI(Staff technician) {

    this.technician = technician;

    this.userID = technician.getUserID();
    this.username = technician.getUsername();
    this.name = technician.getName();

    setTitle("APU Automotive Service Centre - Technician");

    setSize(700, 500);

    setLocationRelativeTo(null);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        JButton feedbackButton =
                new JButton("Give Feedback");

        JButton customerButton =
                new JButton("View Customer Information");

        JButton historyButton =
                new JButton("Service History");
        
        JButton profileButton =
                new JButton("Edit Technician Profile");

        JButton logoutButton =
                new JButton("Logout");

        buttonPanel.add(appointmentButton);
        buttonPanel.add(serviceButton);

        buttonPanel.add(feedbackButton);

        buttonPanel.add(customerButton);
        buttonPanel.add(historyButton);

        buttonPanel.add(profileButton);
        buttonPanel.add(logoutButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // ======================================
        // BUTTON ACTIONS
        // ======================================

        appointmentButton.addActionListener(e -> viewAppointments());

        serviceButton.addActionListener(e -> performService());

        feedbackButton.addActionListener(e -> giveFeedback());

        customerButton.addActionListener(e -> viewCustomerInformation());

        historyButton.addActionListener(e -> viewServiceHistory());
        
        profileButton.addActionListener(e -> editProfile());

        logoutButton.addActionListener(e -> logout());
    }

private void editProfile() {

    boolean editing = true;

    while (editing) {

        String profileInformation =
                "TECHNICIAN PROFILE\n"
                + "============================\n\n"
                + "Technician ID: " + technician.getUserID() + "\n"
                + "Username: " + technician.getUsername() + "\n"
                + "Name: " + technician.getName() + "\n"
                + "Phone Number: " + technician.getPhoneNumber() + "\n"
                + "Age: " + technician.getAge() + "\n"
                + "Identity Number: " + technician.getIdentityNumber() + "\n"
                + "Email: " + technician.getEmail() + "\n"
                + "Address: " + technician.getAddress() + "\n"
                + "Role: " + technician.getRole();

        String[] options = {
            "Edit Username",
            "Edit Name",
            "Edit Phone Number",
            "Edit Age",
            "Edit Identity Number",
            "Edit Email",
            "Edit Address"
        };

        String choice = (String) JOptionPane.showInputDialog(
                this,
                profileInformation + "\n\nSelect what you want to edit:",
                "Edit Technician Profile",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == null || choice.equals("Back")) {
            editing = false;
            continue;
        }

        switch (choice) {

            case "Edit Username":
                editUsername();
                break;

            case "Edit Name":
                editName();
                break;

            case "Edit Phone Number":
                editPhoneNumber();
                break;

            case "Edit Age":
                editAge();
                break;

            case "Edit Identity Number":
                editIdentityNumber();
                break;

            case "Edit Email":
                editEmail();
                break;

            case "Edit Address":
                editAddress();
                break;
        }
    }
}

private void editUsername() {

    String newUsername = JOptionPane.showInputDialog(
            this,
            "Enter new username:",
            technician.getUsername()
    );

    if (newUsername == null) {
        return;
    }

    newUsername = newUsername.trim();

    if (newUsername.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Username cannot be empty.",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    //look for duplicated name
    Staff existingStaff = DataIO.checkUsername(newUsername);

    if (existingStaff != null
            && !existingStaff.getUserID().equalsIgnoreCase(technician.getUserID())) {

        JOptionPane.showMessageDialog(
                this,
                "Username already exists. Please choose another username.",
                "Username Taken",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    technician.setUsername(newUsername);
    username = newUsername;

    DataIO.write();

    JOptionPane.showMessageDialog(
            this,
            "Username updated successfully!",
            "Profile Updated",
            JOptionPane.INFORMATION_MESSAGE
    );
}

private void editName() {

    String newName = JOptionPane.showInputDialog(
            this,
            "Enter new name:",
            technician.getName()
    );

    if (newName == null) {
        return;
    }

    newName = newName.trim();

    if (newName.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Name cannot be empty.",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    technician.setName(newName);
    name = newName;

    DataIO.write();

    JOptionPane.showMessageDialog(
            this,
            "Name updated successfully!",
            "Profile Updated",
            JOptionPane.INFORMATION_MESSAGE
    );
}

private void editPhoneNumber() {

    String newPhoneNumber = JOptionPane.showInputDialog(
            this,
            "Enter new phone number:",
            technician.getPhoneNumber()
    );

    if (newPhoneNumber == null) {
        return;
    }

    newPhoneNumber = newPhoneNumber.trim();

    if (newPhoneNumber.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Phone number cannot be empty.",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    technician.setPhoneNumber(newPhoneNumber);

    DataIO.write();

    JOptionPane.showMessageDialog(
            this,
            "Phone number updated successfully!",
            "Profile Updated",
            JOptionPane.INFORMATION_MESSAGE
    );
}

private void editAge() {

    String ageInput = JOptionPane.showInputDialog(
            this,
            "Enter new age:",
            technician.getAge()
    );

    if (ageInput == null) {
        return;
    }

    try {

        int newAge = Integer.parseInt(ageInput.trim());

        if (newAge <= 0 || newAge > 120) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid age.",
                    "Invalid Age",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        technician.setAge(newAge);

        DataIO.write();

        JOptionPane.showMessageDialog(
                this,
                "Age updated successfully!",
                "Profile Updated",
                JOptionPane.INFORMATION_MESSAGE
        );

    } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(
                this,
                "Age must be a number.",
                "Invalid Age",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

private void editIdentityNumber() {

    String newIdentityNumber = JOptionPane.showInputDialog(
            this,
            "Enter new identity number:",
            technician.getIdentityNumber()
    );

    if (newIdentityNumber == null) {
        return;
    }

    newIdentityNumber = newIdentityNumber.trim();

    if (newIdentityNumber.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Identity number cannot be empty.",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    technician.setIdentityNumber(newIdentityNumber);

    DataIO.write();

    JOptionPane.showMessageDialog(
            this,
            "Identity number updated successfully!",
            "Profile Updated",
            JOptionPane.INFORMATION_MESSAGE
    );
}

private void editEmail() {

    String newEmail = JOptionPane.showInputDialog(
            this,
            "Enter new email:",
            technician.getEmail()
    );

    if (newEmail == null) {
        return;
    }

    newEmail = newEmail.trim();

    if (newEmail.isEmpty() || !newEmail.contains("@")) {
        JOptionPane.showMessageDialog(
                this,
                "Please enter a valid email address.",
                "Invalid Email",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    technician.setEmail(newEmail);

    DataIO.write();

    JOptionPane.showMessageDialog(
            this,
            "Email updated successfully!",
            "Profile Updated",
            JOptionPane.INFORMATION_MESSAGE
    );
}

private void editAddress() {

    String newAddress = JOptionPane.showInputDialog(
            this,
            "Enter new address:",
            technician.getAddress()
    );

    if (newAddress == null) {
        return;
    }

    newAddress = newAddress.trim();

    if (newAddress.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Address cannot be empty.",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    technician.setAddress(newAddress);

    DataIO.write();

    JOptionPane.showMessageDialog(
            this,
            "Address updated successfully!",
            "Profile Updated",
            JOptionPane.INFORMATION_MESSAGE
    );
}



private void viewCustomerInformation() {

    ArrayList<Appointment> assignedAppointments =
            new ArrayList<Appointment>();

    //find appointments assigned to this technician
    for (int i = 0; i < DataIO.allAppointments.size(); i++) {

        Appointment appointment = DataIO.allAppointments.get(i);

        if (appointment.getTechnicianID().equalsIgnoreCase(userID)) {
            assignedAppointments.add(appointment);
        }
    }

    if (assignedAppointments.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "You currently have no assigned appointments.",
                "Customer Information",
                JOptionPane.INFORMATION_MESSAGE
        );
        return;
    }

   
    String[] options = new String[assignedAppointments.size()];

    for (int i = 0; i < assignedAppointments.size(); i++) {

        Appointment appointment = assignedAppointments.get(i);

        options[i] =
                appointment.getAppointmentID()
                + " | Customer: "
                + appointment.getCustomerID()
                + " | Car: "
                + appointment.getCarID()
                + " | "
                + appointment.getDate();
    }

    String selected = (String) JOptionPane.showInputDialog(
            this,
            "Select an appointment:",
            "Customer Information",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
    );

    if (selected == null) {
        return;
    }

    
    Appointment selectedAppointment = null;

    for (int i = 0; i < assignedAppointments.size(); i++) {

        Appointment appointment = assignedAppointments.get(i);

        if (selected.startsWith(appointment.getAppointmentID())) {
            selectedAppointment = appointment;
            break;
        }
    }

    if (selectedAppointment != null) {
        showCustomerInformation(selectedAppointment);
    }
}

private void showCustomerInformation(Appointment appointment) {

    String customerID = appointment.getCustomerID();
    String carID = appointment.getCarID();

    StringBuilder information = new StringBuilder();

    information.append("CUSTOMER INFORMATION\n");
    information.append("============================\n\n");

    information.append("Customer ID: ")
            .append(customerID)
            .append("\n");

    information.append("Car ID: ")
            .append(carID)
            .append("\n\n");

    information.append("APPOINTMENT INFORMATION\n");
    information.append("============================\n\n");

    information.append("Appointment ID: ")
            .append(appointment.getAppointmentID())
            .append("\n");

    information.append("Service Type: ")
            .append(appointment.getServiceType())
            .append("\n");

    information.append("Date: ")
            .append(appointment.getDate())
            .append("\n");

    information.append("Time: ")
            .append(appointment.getStartTime())
            .append(" - ")
            .append(appointment.getEndTime())
            .append("\n");

    information.append("Appointment Status: ")
            .append(appointment.getAppointmentStatus())
            .append("\n");

    information.append("Payment Status: ")
            .append(appointment.getPaymentStatus())
            .append("\n");

    JOptionPane.showMessageDialog(
            this,
            information.toString(),
            "Customer & Appointment Information",
            JOptionPane.INFORMATION_MESSAGE
    );
}
    
private void giveFeedback() {

    java.util.List<Appointment> completedAppointments =
            new java.util.ArrayList<>();

    //Find completed appointments belonging to this technician
    for (Appointment appointment : DataIO.allAppointments) {

        if (appointment.getTechnicianID()
                .equalsIgnoreCase(userID)
                && appointment.getAppointmentStatus()
                        .equalsIgnoreCase("COMPLETED")) {

            completedAppointments.add(appointment);
        }
    }

    if (completedAppointments.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "You have no completed appointments available for feedback.",
                "Give Feedback",
                JOptionPane.INFORMATION_MESSAGE
        );

        return;
    }

    //appointment list
    String[] appointmentOptions =
            new String[completedAppointments.size()];

    for (int i = 0;
         i < completedAppointments.size();
         i++) {

        Appointment appointment =
                completedAppointments.get(i);

        appointmentOptions[i] =
                appointment.getAppointmentID()
                + " | "
                + appointment.getDate()
                + " | "
                + appointment.getServiceType();
    }

    String selected =
            (String) JOptionPane.showInputDialog(
                    this,
                    "Select a completed appointment:",
                    "Give Feedback",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    appointmentOptions,
                    appointmentOptions[0]
            );

    if (selected == null) {
        return;
    }

    int selectedIndex = -1;

    for (int i = 0;
         i < appointmentOptions.length;
         i++) {

        if (appointmentOptions[i].equals(selected)) {
            selectedIndex = i;
            break;
        }
    }

    if (selectedIndex == -1) {
        return;
    }

    Appointment appointment =
            completedAppointments.get(selectedIndex);

    enterFeedback(appointment);
}

private void enterFeedback(Appointment appointment) {

    JTextArea feedbackArea =
            new JTextArea(5, 40);

    JTextArea recommendationArea =
            new JTextArea(5, 40);

    feedbackArea.setLineWrap(true);
    feedbackArea.setWrapStyleWord(true);

    recommendationArea.setLineWrap(true);
    recommendationArea.setWrapStyleWord(true);

    JPanel panel = new JPanel();

    panel.setLayout(
            new BoxLayout(panel, BoxLayout.Y_AXIS)
    );

    panel.add(
            new JLabel("Feedback:")
    );

    panel.add(
            new JScrollPane(feedbackArea)
    );

    panel.add(
            Box.createVerticalStrut(10)
    );

    panel.add(
            new JLabel("Recommendations:")
    );

    panel.add(
            new JScrollPane(recommendationArea)
    );

    int result = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Technician Feedback - "
                    + appointment.getAppointmentID(),
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
    );

    if (result != JOptionPane.OK_OPTION) {
        return;
    }

    String feedback =
            feedbackArea.getText().trim();

    String recommendation =
            recommendationArea.getText().trim();

    if (feedback.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter feedback.",
                "Input Required",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    if (recommendation.isEmpty()) {
        recommendation = "None";
    }

    // Combine feedback and recommendation
    String feedbackText =
            feedback
            + "|"
            + recommendation;

    // Generate a simple Feedback ID
    String feedbackID =
            "TF" + (DataIO.allTechnicianFeedback.size() + 1);

    // Get current date
    String feedbackDate =
            java.time.LocalDate.now().toString();

    TechnicianFeedback technicianFeedback =
            new TechnicianFeedback(
                    feedbackID,
                    appointment.getAppointmentID(),
                    userID,
                    feedbackText,
                    feedbackDate
            );

    DataIO.allTechnicianFeedback.add(
            technicianFeedback
    );

    DataIO.write();

    JOptionPane.showMessageDialog(
            this,
            "Feedback and recommendations saved successfully.",
            "Feedback Saved",
            JOptionPane.INFORMATION_MESSAGE
    );
}

private void viewServiceHistory() {

    StringBuilder result = new StringBuilder();

    result.append("SERVICE HISTORY\n");
    result.append("================================\n\n");

    boolean found = false;

    for (ServiceRecord record : DataIO.allServiceRecords) {

        if (record.getTechnicianID()
                .equalsIgnoreCase(userID)) {

            found = true;

            result.append("Appointment ID: ")
                    .append(record.getAppointmentID())
                    .append("\n");

            result.append("Technician ID: ")
                    .append(record.getTechnicianID())
                    .append("\n\n");

            result.append("Work Performed:\n")
                    .append(record.getWorkPerformed())
                    .append("\n\n");

            result.append("Parts Changed:\n")
                    .append(record.getPartsChanged())
                    .append("\n\n");

            result.append("Car Modifications:\n")
                    .append(record.getCarModifications())
                    .append("\n");

            result.append("--------------------------------\n\n");
        }
    }

    if (!found) {

        result.append(
                "No completed service records found."
        );
    }

    JTextArea textArea =
            new JTextArea(result.toString());

    textArea.setEditable(false);

    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);

    textArea.setFont(
            new Font("Monospaced", Font.PLAIN, 14)
    );

    JScrollPane scrollPane =
            new JScrollPane(textArea);

    scrollPane.setPreferredSize(
            new Dimension(650, 450)
    );

    JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Service History",
            JOptionPane.INFORMATION_MESSAGE
    );
}

private void viewAppointments() {

    java.util.List<Appointment> assignedAppointments =
            new java.util.ArrayList<>();

    for (Appointment appointment : DataIO.allAppointments) {

        if (appointment.getTechnicianID()
                .equalsIgnoreCase(userID)) {

            assignedAppointments.add(appointment);
        }
    }

    if (assignedAppointments.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "No appointments are currently assigned to you.",
                "My Assigned Appointments",
                JOptionPane.INFORMATION_MESSAGE
        );

        return;
    }

    String[] appointmentOptions =
            new String[assignedAppointments.size()];

    for (int i = 0; i < assignedAppointments.size(); i++) {

        Appointment appointment =
                assignedAppointments.get(i);

        appointmentOptions[i] =
                appointment.getAppointmentID()
                + " | "
                + appointment.getDate()
                + " | "
                + appointment.getServiceType()
                + " | "
                + appointment.getAppointmentStatus();
    }

    String selected = (String) JOptionPane.showInputDialog(
            this,
            "Select an appointment:",
            "My Assigned Appointments",
            JOptionPane.PLAIN_MESSAGE,
            null,
            appointmentOptions,
            appointmentOptions[0]
    );

    if (selected == null) {
        return;
    }

    int selectedIndex = -1;

    for (int i = 0; i < appointmentOptions.length; i++) {

        if (appointmentOptions[i].equals(selected)) {
            selectedIndex = i;
            break;
        }
    }

    if (selectedIndex == -1) {
        return;
    }

    Appointment appointment =
            assignedAppointments.get(selectedIndex);

    showAppointmentDetails(appointment);
}
    
private void showAppointmentDetails(Appointment appointment) {

    StringBuilder details = new StringBuilder();

    details.append("APPOINTMENT DETAILS\n");
    details.append("==============================\n\n");

    details.append("Appointment ID: ")
            .append(appointment.getAppointmentID())
            .append("\n");

    details.append("Customer ID: ")
            .append(appointment.getCustomerID())
            .append("\n");

    details.append("Car ID: ")
            .append(appointment.getCarID())
            .append("\n");

    details.append("Technician ID: ")
            .append(appointment.getTechnicianID())
            .append("\n");

    details.append("Service Type: ")
            .append(appointment.getServiceType())
            .append("\n");

    details.append("Date: ")
            .append(appointment.getDate())
            .append("\n");

    details.append("Start Time: ")
            .append(appointment.getStartTime())
            .append("\n");

    details.append("End Time: ")
            .append(appointment.getEndTime())
            .append("\n");

    details.append("Service Price: RM ")
            .append(String.format(
                    "%.2f",
                    appointment.getServicePrice()
            ))
            .append("\n");

    details.append("Appointment Status: ")
            .append(appointment.getAppointmentStatus())
            .append("\n");

    details.append("Payment Status: ")
            .append(appointment.getPaymentStatus())
            .append("\n");

    JTextArea textArea =
            new JTextArea(details.toString());

    textArea.setEditable(false);

    textArea.setFont(
            new Font("Monospaced", Font.PLAIN, 14)
    );

    JScrollPane scrollPane =
            new JScrollPane(textArea);

    scrollPane.setPreferredSize(
            new Dimension(600, 400)
    );

    JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Appointment Details",
            JOptionPane.INFORMATION_MESSAGE
    );
}
    
private void performService() {

    java.util.List<Appointment> assignedAppointments =
            new java.util.ArrayList<>();

    for (Appointment appointment : DataIO.allAppointments) {

        if (appointment.getTechnicianID()
                .equalsIgnoreCase(userID)
                && appointment.getAppointmentStatus()
                        .equalsIgnoreCase("BOOKED")) {

            assignedAppointments.add(appointment);
        }
    }

    if (assignedAppointments.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "You have no booked appointments to perform.",
                "Perform Service",
                JOptionPane.INFORMATION_MESSAGE
        );

        return;
    }

    String[] appointmentOptions =
            new String[assignedAppointments.size()];

    for (int i = 0; i < assignedAppointments.size(); i++) {

        Appointment appointment =
                assignedAppointments.get(i);

        appointmentOptions[i] =
                appointment.getAppointmentID()
                + " | "
                + appointment.getDate()
                + " | "
                + appointment.getServiceType();
    }

    String selected = (String) JOptionPane.showInputDialog(
            this,
            "Select the appointment to perform:",
            "Perform Service",
            JOptionPane.PLAIN_MESSAGE,
            null,
            appointmentOptions,
            appointmentOptions[0]
    );

    if (selected == null) {
        return;
    }

    int selectedIndex = -1;

    for (int i = 0; i < appointmentOptions.length; i++) {

        if (appointmentOptions[i].equals(selected)) {
            selectedIndex = i;
            break;
        }
    }

    if (selectedIndex == -1) {
        return;
    }

    Appointment appointment =
            assignedAppointments.get(selectedIndex);

    recordServiceDetails(appointment);
}
    
private void recordServiceDetails(Appointment appointment) {

    JTextArea workPerformedArea =
            new JTextArea(5, 40);

    JTextArea partsChangedArea =
            new JTextArea(5, 40);

    JTextArea modificationsArea =
            new JTextArea(5, 40);

    workPerformedArea.setLineWrap(true);
    workPerformedArea.setWrapStyleWord(true);

    partsChangedArea.setLineWrap(true);
    partsChangedArea.setWrapStyleWord(true);

    modificationsArea.setLineWrap(true);
    modificationsArea.setWrapStyleWord(true);

    JPanel panel = new JPanel();

    panel.setLayout(
            new BoxLayout(panel, BoxLayout.Y_AXIS)
    );

    panel.add(
            new JLabel("Work Performed:")
    );

    panel.add(
            new JScrollPane(workPerformedArea)
    );

    panel.add(
            Box.createVerticalStrut(10)
    );

    panel.add(
            new JLabel("Parts Changed:")
    );

    panel.add(
            new JScrollPane(partsChangedArea)
    );

    panel.add(
            Box.createVerticalStrut(10)
    );

    panel.add(
            new JLabel("Car Modification Details:")
    );

    panel.add(
            new JScrollPane(modificationsArea)
    );

    int result = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Complete Service - "
                    + appointment.getAppointmentID(),
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
    );

    if (result != JOptionPane.OK_OPTION) {
        return;
    }

    String workPerformed =
            workPerformedArea.getText().trim();

    String partsChanged =
            partsChangedArea.getText().trim();

    String modifications =
            modificationsArea.getText().trim();

    if (workPerformed.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the work performed.",
                "Input Required",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    if (partsChanged.isEmpty()) {
        partsChanged = "None";
    }

    if (modifications.isEmpty()) {
        modifications = "None";
    }

    ServiceRecord record =
            new ServiceRecord(
                    appointment.getAppointmentID(),
                    userID,
                    workPerformed,
                    partsChanged,
                    modifications
            );

    DataIO.allServiceRecords.add(record);

    //Update appointment status
    appointment.setAppointmentStatus("COMPLETED");

    DataIO.write();

    JOptionPane.showMessageDialog(
            this,
            "Service completed successfully!\n\n"
            + "Appointment ID: "
            + appointment.getAppointmentID()
            + "\n"
            + "Status: COMPLETED",
            "Service Completed",
            JOptionPane.INFORMATION_MESSAGE
    );
}
    

    
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