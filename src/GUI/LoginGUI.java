package gui;

import apu_asc.model.Staff;
import apu_asc.utility.DataIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginGUI extends JFrame implements ActionListener{

    private JTextField usernameField;
    private JPasswordField passwordField;
    
    private JButton loginButton;
    private JButton exitButton;
    public LoginGUI() {
        
        setTitle("Automotive Service Centre");
        setSize(450,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        //MAIN PANEL
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,20,8,20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        
        //TITLE
        JLabel titleLabel = new JLabel("AUTOMATIVE SERVICE CENTRE");
        
        titleLabel.setFont(new Font("Arial", Font.BOLD,20));
        
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 2; 
        gbc.insets = new Insets(20, 20, 5, 20);
        
        panel.add(titleLabel, gbc);
        
        
        
        //WELCOME
        JLabel welcomeLabel = new JLabel("Welcome"); 
        welcomeLabel.setFont( new Font("Arial", Font.PLAIN, 15) ); 
        welcomeLabel.setHorizontalAlignment( JLabel.CENTER ); 
        
        gbc.gridx = 0; 
        gbc.gridy = 1; 
        gbc.gridwidth = 2; 
        gbc.insets = new Insets(0, 20, 25, 20); 
        panel.add(welcomeLabel, gbc);
        
        
        
        //USERNAME
        JLabel usernameLabel = new JLabel("Username");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        panel.add(usernameLabel, gbc);
        
        usernameField = new JTextField();

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        panel.add(usernameField, gbc);
        
        //Password
        JLabel passwordLabel =
        new JLabel("Password");

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        panel.add(passwordLabel, gbc);
        
        passwordField = new JPasswordField();

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;

        panel.add(passwordField, gbc);
        
        //LOG IN BUTTON
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 40));
        
        loginButton.setFont( new Font("Arial", Font.BOLD, 14) ); 
        gbc.gridx = 0; 
        gbc.gridy = 6; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.insets = new Insets(5, 20, 10, 20); 
        panel.add(loginButton, gbc);
        
        
        //EXIT BUTTON
        exitButton = new JButton("Exit");
        exitButton.setFont( new Font("Arial", Font.PLAIN, 13) ); 
        gbc.gridx = 0; 
        gbc.gridy = 7; 
        gbc.gridwidth = 2; 
        gbc.insets = new Insets(0, 20, 20, 20); 
        panel.add(exitButton, gbc);
        
        
        //BUTTON LISTENER
        loginButton.addActionListener(this);
        exitButton.addActionListener(this);
        
        
        add(panel);
        setVisible(true);
        

            }
    
    //BUTTON ACTION
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == loginButton){
            String username = usernameField.getText().trim();
            
            String password = new String(passwordField.getPassword());
            
            //CHECK EMPTY INPUT
            if(username.isEmpty() || password.isEmpty()){
                
                JOptionPane.showMessageDialog(this, "Please enter username and password");
                
                return;
                
            }
            
            //FIND STAFF BY USERNAME
            Staff staff = DataIO.checkUsername(username);
            
            //USERNAME NOT FOUND
            if(staff == null){
                JOptionPane.showMessageDialog(this,"Invalid username or password.");
                
                return;
            }
            
            //CHECK PASSWORD
            if(!staff.getPassword().equals(password)){
                
                JOptionPane.showMessageDialog(this,"Invalid username or password.");
                
                return;
                
            }
            
            //LOGIN SUCCESS
            JOptionPane.showMessageDialog(this, "Login successful.");
            
            
            //OPEN GUI BASED ON ROLE
            if(staff.getRole().equals("Manager")){
                new ManagerGUI(staff);
                
            }else if (staff.getRole().equals("CounterStaff")){
                new CounterStaffGUI(staff);
                    
            } else if (staff.getRole().equals("Technician")) {

                new TechnicianGUI(staff);
            }
            
            dispose();
            
        }
        
        else if(e.getSource() == exitButton){
            System.exit(0);
        }
    }
}