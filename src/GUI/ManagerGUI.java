package GUI;

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
        
        setTitle("Manager Dashboard");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        // Header section
        JPanel headerPanel = new JPanel(
                new GridLayout(1, 3)
        );

        headerPanel.setBackground(Color.blue);

        JPanel welcomePanel = new JPanel(
                new FlowLayout(
                        FlowLayout.LEFT,
                        15,
                        20
                )
        );

        welcomePanel.setBackground(Color.blue);

        JLabel welcomeLabel = new JLabel(
                "Welcome, " + staff.getName()
        );

        welcomeLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        welcomeLabel.setForeground(Color.white);
        welcomePanel.add(welcomeLabel);

        JPanel titlePanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        5,
                        15
                )
        );

        titlePanel.setBackground(Color.blue);

        JLabel titleLabel = new JLabel(
                "Manager Dashboard"
        );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        titleLabel.setForeground(Color.white);
        titlePanel.add(titleLabel);

        JPanel passwordPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        15,
                        13
                )
        );

        passwordPanel.setBackground(Color.blue);

        passwordButton = new JButton(
                "Change Password"
        );

        passwordButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        passwordPanel.add(passwordButton);

        headerPanel.add(welcomePanel);
        headerPanel.add(titlePanel);
        headerPanel.add(passwordPanel);

        // Main function section
        contentPanel = new JPanel(
                new BorderLayout(0, 25)
        );

        contentPanel.setBackground(Color.white);

        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        50,
                        10,
                        50
                )
        );

        JLabel selectLabel = new JLabel(
                "Select a Function",
                JLabel.CENTER
        );

        selectLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        contentPanel.add(
                selectLabel,
                BorderLayout.NORTH
        );

        JPanel buttonPanel = new JPanel(
                new GridLayout(4, 2, 25, 15)
        );

        buttonPanel.setBackground(Color.white);

        buttonPanel.setPreferredSize(
                new Dimension(620, 230)
        );
        
        addStaffButton = new JButton("Add Staff");
        
        viewStaffButton = new JButton("View Staff");

        updateStaffButton = new JButton("Update Staff");

        deleteStaffButton = new JButton("Delete Staff");

        servicePriceButton = new JButton("Set Service Price");

        feedbackButton = new JButton("View Technician Feedback");

        commentsButton = new JButton("View Customer Comments");

        reportsButton = new JButton("Generate Reports");

        logoutButton = new JButton("Logout");

        Font buttonFont = new Font(
                "SansSerif",
                Font.BOLD,
                14
        );

        addStaffButton.setFont(buttonFont);
        viewStaffButton.setFont(buttonFont);
        updateStaffButton.setFont(buttonFont);
        deleteStaffButton.setFont(buttonFont);
        servicePriceButton.setFont(buttonFont);
        feedbackButton.setFont(buttonFont);
        commentsButton.setFont(buttonFont);
        reportsButton.setFont(buttonFont);
        logoutButton.setFont(buttonFont);

        buttonPanel.add(addStaffButton);
        buttonPanel.add(viewStaffButton);
        buttonPanel.add(updateStaffButton);
        buttonPanel.add(deleteStaffButton);
        buttonPanel.add(servicePriceButton);
        buttonPanel.add(feedbackButton);
        buttonPanel.add(commentsButton);
        buttonPanel.add(reportsButton);

        JPanel buttonArea = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        0,
                        5
                )
        );

        buttonArea.setBackground(Color.white);
        buttonArea.add(buttonPanel);

        contentPanel.add(
                buttonArea,
                BorderLayout.CENTER
        );

        // Footer section
        JPanel footerPanel = new JPanel(
                new GridLayout(2, 1, 0, 5)
        );

        footerPanel.setBackground(Color.white);

        footerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        15,
                        10
                )
        );

        JPanel logoutPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        logoutPanel.setBackground(Color.white);
        logoutPanel.add(logoutButton);

        JLabel footerLabel = new JLabel(
                "Automobile Service Centre Management System",
                JLabel.CENTER
        );

        footerLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        footerPanel.add(logoutPanel);
        footerPanel.add(footerLabel);

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

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                footerPanel,
                BorderLayout.SOUTH
        );

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
        private void viewStaff(){
            
            ArrayList<Staff> staffList = managerFunction.getAllStaff();
            
            if(staffList.isEmpty()){
                JOptionPane.showMessageDialog(
                        this,
                        "No staff found."
                );
                
                return;
                
            }
            
            String output = "===== STAFF LIST =====\n\n";
            
            for(Staff staff : staffList){
                
                output =
                    output
                    + "User ID                : "
                    + staff.getUserID()
                    + "\n";

                output =
                        output
                        + "Username           : "
                        + staff.getUsername()
                        + "\n";

                output =
                        output
                        + "Name                  : "
                        + staff.getName()
                        + "\n";

                output =
                        output
                        + "Phone Number   : "
                        + staff.getPhoneNumber()
                        + "\n";

                output =
                        output
                        + "Role                    : "
                        + staff.getRole()
                        + "\n";

                output =
                        output
                        + "Age                     : "
                        + staff.getAge()
                        + "\n";

                output =
                        output
                        + "Identity Number  : "
                        + staff.getIdentityNumber()
                        + "\n";

                output =
                        output
                        + "Email                  : "
                        + staff.getEmail()
                        + "\n";

                output =
                        output
                        + "Address              : "
                        + staff.getAddress()
                        + "\n";

                output =
                        output
                        + "--------------------------------\n";
            }
            
            JTextArea textArea = new JTextArea(output);
            
            textArea.setEditable(false);
            
            JScrollPane scrollPane = new JScrollPane(textArea);
            
            scrollPane.setPreferredSize(new Dimension(650,450));
            
            JOptionPane.showMessageDialog(
                    this,
                    scrollPane,
                    "Staff List",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        
        
        
        //UPDATE STAFF
        private void updateStaff(){
            
            String userID = JOptionPane.showInputDialog(
                    this,
                    "Enter user ID to update:");
            
            if(userID == null || userID.trim().isEmpty()){
                return;
            }
            
            Staff staff = managerFunction.findStaff(userID.trim());
            
            if(staff == null){
                JOptionPane.showMessageDialog(
                        this,
                        "Staff not found,",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                
                return;
            }
            
            String[] fields = {
                "Username",
                "Name",
                "Phone number",
                "Age",
                "Identity number",
                "Email",
                "Address"
            };
             
            
            String field = 
                    (String) JOptionPane.showInputDialog(
                            this,
                            "Select field to update",
                            "Update staff",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            fields,
                            fields[0]
                            
                    );
            
            if(field == null){
                return;
            }
            
            String newValue = JOptionPane.showInputDialog(
                    this,
                    "Enter new " + field + ":"
            );
            
            if(newValue == null || newValue.trim().isEmpty()) {
                return;
            }
            
            //USERNAME CHECK
            if(field.equals("Username")){
                Staff existing = managerFunction.findUsername(
                        newValue.trim()
                );
                
            if(existing!= null 
                    && !existing.getUserID()
                            .equalsIgnoreCase(
                                    staff.getUserID())){
                
                JOptionPane.showMessageDialog(
                        this,
                        "Username already exists.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
                
                return;
                               
            }
            }
            
            //PHONE CHECK
            if (field.equals("Phone number")
                && !newValue.matches(
                        "^(01)[0-9]{8,9}$")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid phone number.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }
            
        
        //IDENTITY CHECK
        if(field.equals("Identity number") 
                && !newValue.matches("^[0-9]{6}-[0-9]{2}-[0-9]{4}$")){
            
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid identity number.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);

            return;
        
        }
        
        //EMAIL CHECK
        if(field.equals("Email")
                && !newValue.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
            
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid email format.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);

            return;
            
        }
        
        if(field.equals("Age")){
            
            try{
                
                int age = Integer.parseInt(newValue.trim());
                
                if(age < 18 || age > 65){
                    
                    JOptionPane.showMessageDialog(
                            this,
                            "Age must be between 18 and 65.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE
                    );
                    
                    return;
                    
                }
                
            }catch(NumberFormatException e){
                
                JOptionPane.showMessageDialog(
                        this, 
                        "Age must be a number", 
                        "Invalid Input", 
                        JOptionPane.ERROR_MESSAGE);
                
            return;
                
            }
            
        }
        
        Staff updatedStaff = managerFunction.updateStaff(
                userID.trim(),
                field,
                newValue.trim()
        );
        
        if(updatedStaff == null){
            
            JOptionPane.showMessageDialog(
                    this,
                    "Failed to update staff.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            
            );
            
            return;
            
        }
        
        JOptionPane.showMessageDialog(this, "Staff updated successfully.");

        }
        
        
        
        //DELETE STAFF
        private void deleteStaff(){
            
            String userID = JOptionPane.showInputDialog(
                    this,
                    "Enter User ID to delete:"
            );
            
            if (userID == null || userID.trim().isEmpty()){
                return;
            }
            
            Staff staff = managerFunction.findStaff(userID.trim());
            
            if(staff == null){
                
                JOptionPane.showMessageDialog(
                        this,
                        "Staff not found",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                        
                        return;
                
            }
            
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete "
                    + staff.getName()
                    + " (" + staff.getUserID() + ")?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );
            
            
            if(choice == JOptionPane.YES_OPTION){
                
                Staff deletedStaff = managerFunction.deleteStaff(userID.trim());
                
                if(deletedStaff != null){
                    
                    JOptionPane.showMessageDialog(
                            this,
                            "Staff deleted successfully"
                    
                    );
                }else{
                    
                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to delete staff",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    
                }
            
            }
                    
        }
        
        
        //SET SERVICE PRICE
        private void setServicePrice(){
            
            String minorText = JOptionPane.showInputDialog(
                    this,
                    "Enter Minor Service Price:"
            );
            
            if(minorText == null){
                return;
            }
            
            String majorText = JOptionPane.showInputDialog(
                    this,
                    "Enter Major Service Price:"
            );
            
            if(majorText == null){
                return;
            }
            
            
            try{
                
                double minorPrice = Double.parseDouble(minorText.trim());
                
                double majorPrice = Double.parseDouble(majorText.trim());
                
                String result = managerFunction.setServicePrice(
                        minorPrice,
                        majorPrice
                );
                
                JOptionPane.showMessageDialog(
                        this,
                        result);
                
            }catch(NumberFormatException e){
                
                JOptionPane.showMessageDialog(
                        this, 
                        "Please enter valid prices.",
                        "Invalid input",
                        JOptionPane.ERROR_MESSAGE
                        
                                );
                                        
            }
            
            
        }
        
        
        //VIEW TECHNICIAN FEEDBACK
        private void viewFeedback(){
            
            ArrayList<String> feedbackList = managerFunction.getTechnicianFeedback();
            
            if(feedbackList.isEmpty()){
                
                JOptionPane.showMessageDialog(
                        this
                        , "No technician feedback found.");
                
                return;
                
            }
            
            String output = "===== TECHNICIAN FEEDBACK =====\n\n";
            
            for(String feedback : feedbackList){
                
                output = 
                        output
                        +feedback
                        + "\n"
                        + "--------------------------------\n";
            }
            
            JTextArea textArea = new JTextArea(output);
            
            textArea.setEditable(false);
            
            JScrollPane scrollPane = new JScrollPane(textArea);
            
            scrollPane.setPreferredSize(new Dimension(700,450));
            
            JOptionPane.showMessageDialog(
                    this, 
                    scrollPane, 
                    "Technician feedback", 
                    JOptionPane.INFORMATION_MESSAGE);
            
        }
        
        
        //VIEW CUSTOMER COMMENTS
        private void viewComments(){
            
            ArrayList<String> commentList = managerFunction.getCustomerComments();
            
            if(commentList.isEmpty()){
                JOptionPane.showMessageDialog(
                        this,
                        "No customer comments found."
                );
                
                return;    
                
            }
            
            String output =  "===== CUSTOMER COMMENTS =====\n\n";
            
            for(String comment :commentList){
                
                output = 
                        output
                        + comment
                        + "\n"
                        + "--------------------------------\n";
            }
            
            JTextArea textArea = new JTextArea(output);
            
            textArea.setEditable(false);
            
            JScrollPane scrollPane = new JScrollPane(textArea);
            
            scrollPane.setPreferredSize(new Dimension(750,450));
            
            JOptionPane.showMessageDialog(
                    this, 
                    scrollPane, 
                    "Customer Comments", 
                    JOptionPane.INFORMATION_MESSAGE);
            
        }      
        
        
        //GENERATE REPORT
        private void generateReports(){
        
            String[] reports = {
                "Appointment Status",
                "Service Analysis",
                "Revenue Report",
                "Technician Performance",
                "Monthly Appointment Analysis"
            };
            
            String selectedReport = (String) JOptionPane.showInputDialog(
                    this,
                    "Select Report:",
                    "Generate Report",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    reports,
                    reports[0]
            );
            
            if(selectedReport == null){
                return;
            }
            
            String result = "";
            
            //APPOINTMENT STATUS
            if(selectedReport.equals("Appointment Status")){
                
                result = managerFunction.appointmentStatusReport();
                
                
            //SERVICE ANALYSIS    
            }else if(selectedReport.equals("Service Analysis")){
                
                result = managerFunction.serviceAnalysisReport();
                
                
            //REVENUE REPORT
            }else if(selectedReport.equals("Revenue Report")){
                
                result = managerFunction.revenueReport();
                
            
            //TECHNICIAN PERFORMANCE
            }else if(selectedReport.equals("Technician Performance")){
                
                result = managerFunction.technicianPerformanceReport();
              
                
            //MONTHLY APPOINTMENT
            }else if(selectedReport.equals("Monthly Appointment Analysis")){
                
                String yearText = JOptionPane.showInputDialog(
                        this,
                        "Enter year:"
                );
                
                if(yearText == null || yearText.trim().isEmpty()){
                    return;
                }
                
                try{
                    
                    int selectedYear = Integer.parseInt(yearText.trim());
                    
                    result = managerFunction.monthlyAppointmentAnalysis(selectedYear);
                    
                }catch(NumberFormatException e){
                    
                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter a valid year.",
                            "Invalid year",
                            JOptionPane.ERROR_MESSAGE
                    );
                    
                    return;
                    
                }
            }
            
            //DISPLAY REPORT
            JTextArea textArea = new JTextArea(result);
            
            textArea.setEditable(false);
            
            textArea.setFont(new Font(
                    "Monospaced",
                    Font.PLAIN,
                    14
            ));
            
            JScrollPane scrollPane = new JScrollPane(textArea);
            
            scrollPane.setPreferredSize(new Dimension(650,450));
            
            JOptionPane.showMessageDialog(
                    this,
                    scrollPane,
                    selectedReport,
                    JOptionPane.INFORMATION_MESSAGE
                    
            );
        
        }
        
        
        //CHANGE PASSWORD
        private void changePassword(){
            
            String currentPassword = JOptionPane.showInputDialog(
                    this,
                    "Enter current password"
            );
            
            if(currentPassword == null){
                return;
            }
            
            String newPassword = JOptionPane.showInputDialog(
                    this,
                    "Enter new password:"
            );
            
            if(newPassword == null){
                return;
            }
            
            String result = managerFunction.changePassword(
                    staff.getUserID(),
                    currentPassword,
                    newPassword
            );
            
            JOptionPane.showMessageDialog(
                    this,
                    result
            );
            
        }
        
        //LOG OUT 
        private void logout(){
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );
            
            if(choice == JOptionPane.YES_OPTION){
                
                dispose();
                
                new LoginGUI();
                        
            }
        }
        
        }