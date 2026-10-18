package GUI;

import apu_asc.model.Customer;
import apu_asc.model.Car;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerGUI extends JFrame implements ActionListener {

    private Customer customer;

    private JButton carButton;
    private JButton appointmentButton;
    private JButton paymentButton;
    private JButton feedbackButton;
    private JButton commentButton;
    private JButton profileButton;
    private JButton logoutButton;

    private JLabel welcomeLabel;

    public CustomerGUI(Customer customer) {

        this.customer = customer;

        setTitle("Customer Dashboard");
        setSize(700, 400);
        setLocation(400, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header section
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(
                new BorderLayout(10, 10)
        );
        headerPanel.setBackground(Color.blue);

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(
                new FlowLayout(FlowLayout.LEFT, 15, 15)
        );
        welcomePanel.setBackground(Color.blue);

        welcomeLabel = new JLabel(
                "Welcome, " + customer.getName()
        );
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );
        welcomePanel.add(welcomeLabel);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(
                new FlowLayout(FlowLayout.CENTER, 15, 15)
        );
        titlePanel.setBackground(Color.blue);

        JLabel titleLabel =
                new JLabel("Customer Dashboard");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(
                new Font("SansSerif", Font.BOLD, 20)
        );
        titlePanel.add(titleLabel);

        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(
                new FlowLayout(FlowLayout.RIGHT, 15, 10)
        );
        profilePanel.setBackground(Color.blue);

        profileButton =
                new JButton("   Edit Profile   ");
        profileButton.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );
        profileButton.addActionListener(this);
        profilePanel.add(profileButton);

        headerPanel.add("West", welcomePanel);
        headerPanel.add("Center", titlePanel);
        headerPanel.add("East", profilePanel);

        // Main function section
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.white);

        JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(
                new FlowLayout(FlowLayout.CENTER, 10, 10)
        );
        instructionPanel.setBackground(Color.white);

        JLabel instructionLabel =
                new JLabel("Select a Function");
        instructionLabel.setFont(
                new Font("SansSerif", Font.BOLD, 16)
        );
        instructionPanel.add(instructionLabel);

        JPanel functionArea = new JPanel();
        functionArea.setLayout(
                new FlowLayout(FlowLayout.CENTER, 20, 5)
        );
        functionArea.setBackground(Color.white);

        JPanel functionPanel = new JPanel();
        functionPanel.setLayout(
                new GridLayout(3, 1, 5, 15)
        );
        functionPanel.setBackground(Color.white);

        carButton =
                new JButton("View Car Details");
        appointmentButton =
                new JButton("Appointment / Service History");
        paymentButton =
                new JButton("Payments and Receipts");
        feedbackButton =
                new JButton("Technician Feedback");
        commentButton =
                new JButton("Rate / Comment");

        Font buttonFont = new Font(
                "SansSerif", Font.BOLD, 14
        );

        JButton[] functionButtons = {
            carButton,
            appointmentButton,
            paymentButton,
            feedbackButton,
            commentButton
        };

        for (int i = 0;
                i < functionButtons.length;
                i++) {

            functionButtons[i].setFont(buttonFont);
            functionButtons[i].setVerticalAlignment(
                    JButton.CENTER
            );
            functionButtons[i].setHorizontalAlignment(
                    JButton.CENTER
            );
        }

        JPanel firstRow = new JPanel();
        firstRow.setLayout(
                new GridLayout(1, 2, 20, 0)
        );
        firstRow.setBackground(Color.white);

        firstRow.add(carButton);
        firstRow.add(appointmentButton);

        JPanel secondRow = new JPanel();
        secondRow.setLayout(
                new GridLayout(1, 2, 20, 0)
        );
        secondRow.setBackground(Color.white);

        secondRow.add(paymentButton);
        secondRow.add(feedbackButton);

        JPanel thirdRow = new JPanel();
        thirdRow.setLayout(
                new FlowLayout(FlowLayout.CENTER, 10, 0)
        );
        thirdRow.setBackground(Color.white);
        thirdRow.add(commentButton);

        functionPanel.add(firstRow);
        functionPanel.add(secondRow);
        functionPanel.add(thirdRow);
        functionArea.add(functionPanel);

        carButton.addActionListener(this);
        appointmentButton.addActionListener(this);
        paymentButton.addActionListener(this);
        feedbackButton.addActionListener(this);
        commentButton.addActionListener(this);

        mainPanel.add("North", instructionPanel);
        mainPanel.add("Center", functionArea);

        // Bottom section
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(
                new GridLayout(2, 1, 5, 5)
        );
        bottomPanel.setBackground(Color.white);

        JPanel logoutPanel = new JPanel();
        logoutPanel.setLayout(
                new FlowLayout(FlowLayout.CENTER, 10, 5)
        );
        logoutPanel.setBackground(Color.white);

        logoutButton =
                new JButton("     Logout     ");
        logoutButton.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );
        logoutButton.setVerticalAlignment(JButton.CENTER);
        logoutButton.setHorizontalAlignment(JButton.CENTER);
        logoutButton.addActionListener(this);
        logoutPanel.add(logoutButton);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(
                new FlowLayout(FlowLayout.CENTER, 10, 4)
        );
        footerPanel.setBackground(Color.white);

        JLabel footerLabel = new JLabel(
                "Automotive Service Centre Management System"
        );
        footerPanel.add(footerLabel);

        bottomPanel.add(logoutPanel);
        bottomPanel.add(footerPanel);

        add("North", headerPanel);
        add("Center", mainPanel);
        add("South", bottomPanel);

        setVisible(true);
    }

    // =========================================
    // BUTTON ACTIONS
    // =========================================

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == profileButton) {

            editProfile();

        } else if (source == carButton) {

            showCarDetails();

        } else if (source == appointmentButton) {

            showAppointmentHistory();

        } else if (source == paymentButton) {

            showPaymentReceipts();

        } else if (source == feedbackButton) {

            showTechnicianFeedback();

        } else if (source == commentButton) {

            showRateComment();

        } else if (source == logoutButton) {

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new MainGUI();
            }
        }
    }

    // =========================================
    // 1. CAR DETAILS
    // =========================================

    private void showCarDetails() {

        JFrame frame = new JFrame("My Car Details");
        frame.setSize(450, 400);
        frame.setLocationRelativeTo(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(
                customer.getCarDetails()
        );
        textArea.setEditable(false);
        textArea.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        textArea.setMargin(new Insets(10, 10, 10, 10));

        JButton editButton = new JButton("Edit Car Details");

        editButton.addActionListener(e -> {

            ArrayList<Car> customerCars =
                    customer.getCustomerCars();

            if (customerCars.isEmpty()) {
                JOptionPane.showMessageDialog(
                        frame,
                        "No car details available to edit."
                );
                return;
            }

            Car selectedCar;

            if (customerCars.size() == 1) {

                selectedCar = customerCars.get(0);

            } else {

                String[] carOptions =
                        new String[customerCars.size()];

                for (int i = 0;
                        i < customerCars.size();
                        i++) {

                    Car car = customerCars.get(i);

                    carOptions[i] =
                            car.getCarID()
                            + " | "
                            + car.getRegistrationNumber()
                            + " | "
                            + car.getBrand()
                            + " "
                            + car.getModel();
                }

                String selectedOption =
                        (String) JOptionPane.showInputDialog(
                                frame,
                                "Select the car you want to edit:",
                                "Select Car",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                carOptions,
                                carOptions[0]
                        );

                if (selectedOption == null) {
                    return;
                }

                int selectedIndex = -1;

                for (int i = 0;
                        i < carOptions.length;
                        i++) {

                    if (carOptions[i].equals(selectedOption)) {
                        selectedIndex = i;
                        break;
                    }
                }

                if (selectedIndex == -1) {
                    return;
                }

                selectedCar = customerCars.get(selectedIndex);
            }

            editCarDetails(
                    frame,
                    selectedCar,
                    textArea
            );
        });

        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(editButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void editCarDetails(
            JFrame frame,
            Car car,
            JTextArea textArea) {

        JTextField registrationField =
                new JTextField(car.getRegistrationNumber());

        JTextField brandField =
                new JTextField(car.getBrand());

        JTextField modelField =
                new JTextField(car.getModel());

        JTextField yearField =
                new JTextField(String.valueOf(car.getYear()));

        JTextField colourField =
                new JTextField(car.getColour());

        JPanel panel = new JPanel(
                new GridLayout(5, 2, 5, 5)
        );

        panel.add(new JLabel("Registration Number:"));
        panel.add(registrationField);

        panel.add(new JLabel("Brand:"));
        panel.add(brandField);

        panel.add(new JLabel("Model:"));
        panel.add(modelField);

        panel.add(new JLabel("Year:"));
        panel.add(yearField);

        panel.add(new JLabel("Colour:"));
        panel.add(colourField);

        int result = JOptionPane.showConfirmDialog(
                frame,
                panel,
                "Edit Car Details",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String error = customer.updateCarDetails(
                car,
                registrationField.getText(),
                brandField.getText(),
                modelField.getText(),
                yearField.getText(),
                colourField.getText()
        );

        if (error != null) {

            JOptionPane.showMessageDialog(frame, error);

        } else {

            textArea.setText(customer.getCarDetails());

            JOptionPane.showMessageDialog(
                    frame,
                    "Car details updated successfully!"
            );
        }
    }

    // =========================================
    // 2. APPOINTMENT / SERVICE HISTORY
    // =========================================

    private void showAppointmentHistory() {

        JFrame frame = new JFrame(
                "Appointment / Service History"
        );

        frame.setSize(500, 450);
        frame.setLocationRelativeTo(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea(
                customer.getAppointmentHistory()
        );

        textArea.setEditable(false);
        textArea.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        textArea.setMargin(new Insets(10, 10, 10, 10));

        frame.add(new JScrollPane(textArea));
        frame.setVisible(true);
    }

    // =========================================
    // 3. PAYMENTS AND RECEIPTS
    // =========================================

    private void showPaymentReceipts() {

        JFrame frame = new JFrame("Payments and Receipts");

        frame.setSize(550, 500);
        frame.setLocationRelativeTo(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea(
                customer.getPaymentReceiptDetails()
        );

        textArea.setEditable(false);
        textArea.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        textArea.setMargin(new Insets(10, 10, 10, 10));

        frame.add(new JScrollPane(textArea));
        frame.setVisible(true);
    }

    // =========================================
    // 4. TECHNICIAN FEEDBACK
    // =========================================

    private void showTechnicianFeedback() {

        JFrame frame = new JFrame("Technician Feedback");

        frame.setSize(500, 450);
        frame.setLocationRelativeTo(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea(
                customer.getTechnicianFeedback()
        );

        textArea.setEditable(false);
        textArea.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        textArea.setMargin(new Insets(10, 10, 10, 10));

        frame.add(new JScrollPane(textArea));
        frame.setVisible(true);
    }

    // =========================================
    // 5. RATE AND COMMENT
    // =========================================

    private void showRateComment() {

        JFrame frame = new JFrame("Rate / Comment");

        frame.setSize(550, 600);
        frame.setLocationRelativeTo(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(
                new GridLayout(0, 1, 5, 5)
        );
        panel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        panel.add(new JLabel("Select Appointment:"));

        JComboBox<String> appointmentBox = new JComboBox<>();

        for (String appointmentID
                : customer.getCustomerAppointmentIDs()) {

            appointmentBox.addItem(appointmentID);
        }

        panel.add(appointmentBox);

        panel.add(new JLabel("Technician Rating (1-5):"));

        JComboBox<Integer> technicianRatingBox =
                new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});

        panel.add(technicianRatingBox);

        panel.add(new JLabel("Technician Comment:"));

        JTextArea technicianCommentArea = new JTextArea(3, 20);
        technicianCommentArea.setLineWrap(true);
        technicianCommentArea.setWrapStyleWord(true);

        panel.add(new JScrollPane(technicianCommentArea));

        panel.add(new JLabel("Counter Staff Rating (1-5):"));

        JComboBox<Integer> counterStaffRatingBox =
                new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});

        panel.add(counterStaffRatingBox);

        panel.add(new JLabel("Counter Staff Comment:"));

        JTextArea counterStaffCommentArea = new JTextArea(3, 20);
        counterStaffCommentArea.setLineWrap(true);
        counterStaffCommentArea.setWrapStyleWord(true);

        panel.add(new JScrollPane(counterStaffCommentArea));

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {

            String appointmentID =
                    (String) appointmentBox.getSelectedItem();

            int technicianRating =
                    (Integer) technicianRatingBox.getSelectedItem();

            int counterStaffRating =
                    (Integer) counterStaffRatingBox.getSelectedItem();

            String error = customer.submitComment(
                    appointmentID,
                    technicianRating,
                    counterStaffRating,
                    technicianCommentArea.getText(),
                    counterStaffCommentArea.getText()
            );

            if (error != null) {

                JOptionPane.showMessageDialog(frame, error);

            } else {

                JOptionPane.showMessageDialog(
                        frame,
                        "Your ratings and comments have been submitted!"
                );

                frame.dispose();
            }
        });

        panel.add(submitButton);

        if (appointmentBox.getItemCount() == 0) {
            submitButton.setEnabled(false);

            JOptionPane.showMessageDialog(
                    this,
                    "No completed appointments are available "
                    + "for rating, or all completed "
                    + "appointments have already been rated."
            );
        }

        frame.add(new JScrollPane(panel), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // =========================================
    // EDIT PROFILE
    // =========================================

    private void editProfile() {

        JTextField nameField =
                new JTextField(customer.getName());

        JTextField usernameField =
                new JTextField(customer.getUsername());

        JTextField phoneField =
                new JTextField(customer.getPhoneNumber());

        JPasswordField passwordField =
                new JPasswordField();

        JPanel panel = new JPanel(
                new GridLayout(4, 2, 5, 5)
        );

        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);

        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneField);

        panel.add(new JLabel(
                "New Password (leave blank to keep current):"
        ));
        panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Profile",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String password =
                new String(passwordField.getPassword());

        String error = customer.checkProfileInput(
                nameField.getText(),
                usernameField.getText(),
                phoneField.getText()
        );

        if (error != null) {
            JOptionPane.showMessageDialog(this, error);
            return;
        }

        customer.updateProfile(
                nameField.getText(),
                usernameField.getText(),
                phoneField.getText(),
                password
        );

        welcomeLabel.setText(
                "Welcome, " + customer.getName()
        );

        JOptionPane.showMessageDialog(
                this,
                "Profile updated successfully!"
        );
    }
}
