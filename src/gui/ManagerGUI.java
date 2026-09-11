package gui;

import apu_asc.model.ManagerFunction;
import apu_asc.model.Staff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManagerGUI extends JFrame implements ActionListener {

    private ManagerFunction managerFunction;
    private Staff staff;
    
    private JPanel mainPanel;
    private JPanel contentPanel;
    
    private JButton addStaffButton;
    private JButton viewStaffButton;
    private JButton updateStaffButton;
    private JButton deleteStaffButton;
    private JButton servicePriceButton;
    private JButton feedbackButton;
    private JButton commentsButton;
    private JButton reportsButton;
    private JButton passwordButton;
    private JButton logoutButton;
    
    public ManagerGUI(Staff staff){
        
        this.staff = staff;
        managerFunction = new ManagerFunction();
        
        setTitle("Automotive Service Centre - Manager");
        setSize(1000,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        
        //main panel
        mainPanel = new JPanel(new BorderLayout());
        
        
        //TOP PANEL
        JPanel topPanel = new JPanel();
        
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("AUTOMOTIVE SERVICE CENTRE");
        
        titleLabel.setFont(new Font("Arial", Font.BOLD,24));
        
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel welcomeLabel = new JLabel(
                            "Welcome, " 
                            + staff.getName()
                            + " (Manager)");
        
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        topPanel.add(Box.createVerticalStrut(15));
        
        topPanel.add(titleLabel);
        
        topPanel.add(Box.createVerticalStrut(5));
        
        topPanel.add(welcomeLabel);
        
        topPanel.add(Box.createVerticalStrut(15));
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        
        //LEFT MENU
        JPanel menuPanel = new JPanel();
        
        menuPanel.setLayout(new GridLayout(10,1,5,5));
        
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        addStaffButton = new JButton("Add Staff");
        
        viewStaffButton = new JButton("View Staff");

        updateStaffButton = new JButton("Update Staff");

        deleteStaffButton = new JButton("Delete Staff");

        servicePriceButton = new JButton("Set Service Price");

        feedbackButton = new JButton("View Technician Feedback");

        commentsButton = new JButton("View Customer Comments");

        reportsButton = new JButton("Generate Reports");

        passwordButton = new JButton("Change Password");

        logoutButton = new JButton("Logout");

        menuPanel.add(addStaffButton);
        menuPanel.add(viewStaffButton);
        menuPanel.add(updateStaffButton);
        menuPanel.add(deleteStaffButton);
        menuPanel.add(servicePriceButton);
        menuPanel.add(feedbackButton);
        menuPanel.add(commentsButton);
        menuPanel.add(reportsButton);
        menuPanel.add(passwordButton);
        menuPanel.add(logoutButton);

        addStaffButton.addActionListener(this);
        viewStaffButton.addActionListener(this);
        updateStaffButton.addActionListener(this);
        deleteStaffButton.addActionListener(this);
        servicePriceButton.addActionListener(this);
        feedbackButton.addActionListener(this);
        commentsButton.addActionListener(this);
        reportsButton.addActionListener(this);
        passwordButton.addActionListener(this);
        logoutButton.addActionListener(this);
        
        mainPanel.add(menuPanel, BorderLayout.WEST);
        
        
        //CENTER CONTENT
        contentPanel = new JPanel(new BorderLayout());
        
        JLabel dashboardLabel = new JLabel("Manager Dashboard");
        
        dashboardLabel.setFont(new Font("Arial", Font.BOLD , 22));
        
        dashboardLabel.setHorizontalAlignment(JLabel.CENTER);
        
        JTextArea welcomeText = new JTextArea();
        
        welcomeText.setText(
                "\nWelcome to the Manager Dashboard.\n\n"
                + "Please select an option from the menu."
        );
        
        welcomeText.setFont(new Font("Arial", Font.PLAIN, 16));
        
        welcomeText.setEditable(false);
        
        welcomeText.setBackground(contentPanel.getBackground());
        
        contentPanel.add(dashboardLabel, BorderLayout.NORTH);
        
        contentPanel.add(welcomeText , BorderLayout.CENTER);
        
        mainPanel.add(contentPanel , BorderLayout.CENTER);
        
        add(mainPanel);
        
        setVisible(true);
    }
        
        
        //BUTTON ACTION
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == addStaffButton) {

            addStaff();

        } else if (e.getSource() == viewStaffButton) {

            viewStaff();

        } else if (e.getSource() == updateStaffButton) {

            updateStaff();

        } else if (e.getSource() == deleteStaffButton) {

            deleteStaff();

        } else if (e.getSource() == servicePriceButton) {

            setServicePrice();

        } else if (e.getSource() == feedbackButton) {

            viewFeedback();

        } else if (e.getSource() == commentsButton) {

            viewComments();

        } else if (e.getSource() == reportsButton) {

            generateReports();

        } else if (e.getSource() == passwordButton) {

            changePassword();

        } else if (e.getSource() == logoutButton) {

            logout();
        }
    }
        
        //ADD STAFF
        private void addStaff(){
            
            while(true){
                String[] roles = {
                    "Manager",
                    "CounterStaff",
                    "Technician"
                
                };
                
                String role = 
                            (String) JOptionPane.showInputDialog(
                            this,
                            "Select Staff Role:",
                            "Add Staff",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            roles,
                            roles[0]);
                
                if (role == null){
                    return;
                }
                
                String name = JOptionPane.showInputDialog(this,"Enter Name:");
                
                if(name == null){
                    return;
                }
                
                String username =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Username:");

                if (username == null) {
                    return;
                }
                
                String phoneNumber = JOptionPane.showInputDialog(this, "Enter Phone Number:");
                
                if(phoneNumber == null){
                    return;              
                }
                
                String ageText =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Age:");

            if (ageText == null) {
                return;
            }

            String identityNumber =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Identity Number:");

            if (identityNumber == null) {
                return;
            }

            String email =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Email:");

            if (email == null) {
                return;
            }

            String address =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Address:");

            if (address == null) {
                return;
            }
            
            
            //EMPTY FIELD VALIDATION
            if (name.trim().isEmpty()
                    || username.trim().isEmpty()
                    || phoneNumber.trim().isEmpty()
                    || ageText.trim().isEmpty()
                    || identityNumber.trim().isEmpty()
                    || email.trim().isEmpty()
                    || address.trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "All fields are required.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);

                continue;
            }
            
            
            //PHONE VALIATION
            if (!phoneNumber.matches(
                    "^(01)[0-9]{8,9}$")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid phone number.\n"
                        + "Example: 0123456789",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);

                continue;
            }
            
            //IDENTITY NUMBER VALIDATION
            if (!identityNumber.matches(
                    "^[0-9]{6}-[0-9]{2}-[0-9]{4}$")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid identity number.\n"
                        + "Example: 010101-01-1234",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);

                continue;
            }
            
            //EMAIL VALIDATION
            if (!email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid email format.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);

                continue;
            }

            int age;

            try {

                age = Integer.parseInt(
                        ageText.trim());

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Age must be a number.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);

                continue;
            }
            
            
            //AGE VALIDATION
            if (age < 18 || age > 65) {

                JOptionPane.showMessageDialog(
                        this,
                        "Age must be between 18 and 65.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);

                continue;
            }
            
            //USERNAME VALIDATION
            if (managerFunction.findUsername(
                    username.trim()) != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Username already exists.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);

                continue;
            }
            
            
            //GENERATE USER ID
            String userID = managerFunction.generateUserID(role);
            
            Staff newStaff = managerFunction.addStaff(
                            role,
                            userID,
                            username.trim(),
                            name.trim(),
                            phoneNumber.trim(),
                            age,
                            identityNumber.trim(),
                            email.trim(),
                            address.trim()
            );
            
            if (newStaff == null){
                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add staff.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                
                continue;
            }
            
            JOptionPane.showMessageDialog(
                    this,
                    "Staff added successfully!\n\n"
                    +"User ID: "
                    + newStaff.getUserID()
                    + "\nUsername: "
                    + newStaff.getUsername()
                    + "\nDefault Password: "
                    + newStaff.getPassword()
            );
            
            break;
            }
        
        }
        
        
        //VIEW STAFF
            
        }

   
