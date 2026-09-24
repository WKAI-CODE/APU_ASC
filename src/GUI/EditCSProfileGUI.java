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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EditCSProfileGUI implements ActionListener {

    CounterStaff counterStaff;

    JFrame x;
    JLabel title;
    JButton viewProfile;
    JButton editProfile;
    JButton back;

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == viewProfile) {

            String profileDetails =
                    "User ID: " + counterStaff.getUserID()
                    + "\nUsername: " + counterStaff.getUsername()
                    + "\nName: " + counterStaff.getName()
                    + "\nPhone number: " + counterStaff.getPhoneNumber()
                    + "\nAge: " + counterStaff.getAge()
                    + "\nIdentity number: " + counterStaff.getIdentityNumber()
                    + "\nEmail: " + counterStaff.getEmail()
                    + "\nAddress: " + counterStaff.getAddress()
                    + "\nRole: " + counterStaff.getRole();

            JOptionPane.showMessageDialog(x, profileDetails);

        } else if (e.getSource() == editProfile) {

            JTextField staffIDField =
                    new JTextField(
                            counterStaff.getUserID()
                    );

            JTextField usernameField =
                    new JTextField(
                            counterStaff.getUsername()
                    );

            JTextField passwordField =
                    new JTextField(
                            counterStaff.getPassword()
                    );

            JTextField nameField =
                    new JTextField(
                            counterStaff.getName()
                    );

            JTextField phoneNumberField =
                    new JTextField(
                            counterStaff.getPhoneNumber()
                    );

            JTextField ageField =
                    new JTextField(
                            String.valueOf(
                                    counterStaff.getAge()
                            )
                    );

            JTextField identityNumberField =
                    new JTextField(
                            counterStaff.getIdentityNumber()
                    );

            JTextField emailField =
                    new JTextField(
                            counterStaff.getEmail()
                    );

            JTextField addressField =
                    new JTextField(
                            counterStaff.getAddress()
                    );

            // Staff ID cannot be changed
            staffIDField.setEditable(false);

            JPanel editProfilePanel =
                    new JPanel(
                            new GridLayout(9, 2, 10, 10)
                    );

            editProfilePanel.add(
                    new JLabel("Staff ID:")
            );
            editProfilePanel.add(staffIDField);

            editProfilePanel.add(
                    new JLabel("Username:")
            );
            editProfilePanel.add(usernameField);

            editProfilePanel.add(
                    new JLabel("Password:")
            );
            editProfilePanel.add(passwordField);

            editProfilePanel.add(
                    new JLabel("Name:")
            );
            editProfilePanel.add(nameField);

            editProfilePanel.add(
                    new JLabel("Phone Number:")
            );
            editProfilePanel.add(phoneNumberField);

            editProfilePanel.add(
                    new JLabel("Age:")
            );
            editProfilePanel.add(ageField);

            editProfilePanel.add(
                    new JLabel("Identity Number:")
            );
            editProfilePanel.add(identityNumberField);

            editProfilePanel.add(
                    new JLabel("Email:")
            );
            editProfilePanel.add(emailField);

            editProfilePanel.add(
                    new JLabel("Address:")
            );
            editProfilePanel.add(addressField);

            int result = JOptionPane.showConfirmDialog(
                    x,
                    editProfilePanel,
                    "Edit Profile",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result != JOptionPane.OK_OPTION) {
                return;
            }

            String username =
                    usernameField.getText().trim();

            String password =
                    passwordField.getText().trim();

            String name =
                    nameField.getText().trim();

            String phoneNumber =
                    phoneNumberField.getText().trim();

            String ageText =
                    ageField.getText().trim();

            String identityNumber =
                    identityNumberField.getText().trim();

            String email =
                    emailField.getText().trim();

            String address =
                    addressField.getText().trim();

            if (username.isEmpty()
                    || password.isEmpty()
                    || name.isEmpty()
                    || phoneNumber.isEmpty()
                    || ageText.isEmpty()
                    || identityNumber.isEmpty()
                    || email.isEmpty()
                    || address.isEmpty()) {

                JOptionPane.showMessageDialog(
                        x,
                        "All fields must be completed!"
                );

                return;
            }
            
            // Phone number validation
            if (!phoneNumber.matches(
                    "^(01)[0-9]{8,9}$")) {

                JOptionPane.showMessageDialog(
                        x,
                        "Invalid phone number.\n"
                        + "Example: 0123456789"
                );

                return;
            }

            // Identity number validation
            if (!identityNumber.matches(
                    "^[0-9]{6}-[0-9]{2}-[0-9]{4}$")) {

                JOptionPane.showMessageDialog(
                        x,
                        "Invalid identity number.\n"
                        + "Example: 010101-01-1234"
                );

                return;
            }

            // Email validation
            if (!email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                JOptionPane.showMessageDialog(
                        x,
                        "Invalid email format."
                );

                return;
            }

            int age;

            try {

                age = Integer.parseInt(ageText);

            } catch (NumberFormatException exception) {

                JOptionPane.showMessageDialog(
                        x,
                        "Age must contain numbers only!"
                );

                return;
            }

            if (age <= 0) {

                JOptionPane.showMessageDialog(
                        x,
                        "Age must be greater than zero!"
                );

                return;
            }

            Staff updatedStaff =
                    counterStaff.editProfile(
                            username,
                            password,
                            name,
                            phoneNumber,
                            age,
                            identityNumber,
                            email,
                            address
                    );

            if (updatedStaff == null) {

                JOptionPane.showMessageDialog(
                        x,
                        "Profile could not be updated."
                        + "\nThe username may already "
                        + "be in use."
                );

            } else {

                JOptionPane.showMessageDialog(
                        x,
                        "Profile updated successfully!"
                );
            }

        } else if (e.getSource() == back) {

            new CounterStaffDashboardGUI(counterStaff);
            x.setVisible(false);
        }
    }

    public EditCSProfileGUI(CounterStaff counterStaff) {

        this.counterStaff = counterStaff;

        x = new JFrame();

        x.setTitle("Counter Staff Profile");
        x.setSize(650, 350);
        x.setLocation(430, 220);

        x.setLayout(
                new BorderLayout(10, 10)
        );

        // Header section
        JPanel headerPanel = new JPanel();

        headerPanel.setLayout(
                new GridLayout(1, 3)
        );

        headerPanel.setBackground(Color.blue);

        // Back button on the left
        JPanel backPanel = new JPanel();

        backPanel.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        15,
                        10
                )
        );

        backPanel.setBackground(Color.blue);

        back = new JButton("   Back   ");

        back.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        backPanel.add(back);

        // Title in the centre
        JPanel titlePanel = new JPanel();

        titlePanel.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        10,
                        15
                )
        );

        titlePanel.setBackground(Color.blue);

        title = new JLabel(
                "Counter Staff Profile"
        );

        title.setForeground(Color.white);

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        titlePanel.add(title);

        // Empty right panel keeps title centred
        JPanel rightPanel = new JPanel();

        rightPanel.setBackground(Color.blue);

        headerPanel.add(backPanel);
        headerPanel.add(titlePanel);
        headerPanel.add(rightPanel);

        // Main section
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
                new JLabel(
                        "Select a Profile Function"
                );

        instruction.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        instructionPanel.add(instruction);

        // Space around buttons
        JPanel functionArea = new JPanel();

        functionArea.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        20,
                        15
                )
        );

        functionArea.setBackground(Color.white);

        JPanel buttonArea = new JPanel();

        buttonArea.setLayout(
                new GridLayout(1, 1, 10, 10)
        );

        buttonArea.setBackground(Color.white);

        // Function button row
        JPanel firstRow = new JPanel();

        firstRow.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        10
                )
        );

        firstRow.setBackground(Color.white);

        viewProfile = new JButton("View Profile");
        editProfile = new JButton("Edit Profile");

        Font buttonFont = new Font(
                "SansSerif",
                Font.BOLD,
                14
        );

        viewProfile.setFont(buttonFont);
        editProfile.setFont(buttonFont);

        viewProfile.setVerticalAlignment(
                JButton.CENTER
        );

        editProfile.setVerticalAlignment(
                JButton.CENTER
        );

        firstRow.add(viewProfile);
        firstRow.add(editProfile);

        buttonArea.add(firstRow);

        functionArea.add(buttonArea);

        mainPanel.add(
                "North",
                instructionPanel
        );

        mainPanel.add(
                "Center",
                functionArea
        );

        // Footer
        JPanel footerPanel = new JPanel();

        footerPanel.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        10,
                        10
                )
        );

        footerPanel.setBackground(Color.white);

        JLabel footer = new JLabel(
                "Automative Service Centre Management System"
        );

        footerPanel.add(footer);

        // Button listeners
        viewProfile.addActionListener(this);
        editProfile.addActionListener(this);
        back.addActionListener(this);

        // Add all sections
        x.add("North", headerPanel);
        x.add("Center", mainPanel);
        x.add("South", footerPanel);

        x.setVisible(true);
    }
}