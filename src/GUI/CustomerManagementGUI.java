/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import apu_asc.model.CounterStaff;
import apu_asc.model.Customer;
import apu_asc.utility.DataIO;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CustomerManagementGUI implements ActionListener {

    CounterStaff counterStaff;

    JFrame x;

    Label title;

    Button addCustomer;
    Button viewCustomers;
    Button searchCustomer;
    Button updateCustomer;
    Button deleteCustomer;
    Button back;

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addCustomer) {

            try {
                String name = JOptionPane.showInputDialog(x, "Enter Customer name: ");
                
                if (name == null || name.trim().isEmpty()) {
                    throw new Exception();
                }
                
                String phoneNumber = JOptionPane.showInputDialog(x, "Enter phone number: ");
                
                if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
                    throw new Exception();
                }
                
                name = name.trim();
                phoneNumber = phoneNumber.trim();
                
                String userID = counterStaff.generateCustomerID();
                
                String username = userID;
                String password = userID + "@123";
                
                Customer newCustomer = counterStaff.addCustomer(
                        userID,
                        username,
                        password,
                        name,
                        phoneNumber
                );
                
                if (newCustomer == null) {
                    
                    JOptionPane.showMessageDialog(x, "Unable to create Customer account!");
                    
                } else {
                    
                    JOptionPane.showMessageDialog(x, "Customer added successfully!"
                                                                + "\n\nCustomer ID: " + userID
                                                                + "\n\nDefault username: " + username
                                                                + "\n\nDefault password: " + password);
                    
                }
            } catch (Exception ex) {
                
                JOptionPane.showMessageDialog(x, "Invalid input!");
            }

        } else if (e.getSource() == viewCustomers) {

            if (DataIO.allCustomers.isEmpty()) {
               
               JOptionPane.showMessageDialog(x, "No Customer records found!");
               
            } else {
               
               String customerDetails = "Total Customers: " + DataIO.allCustomers.size() + "\n\n";
               
               for (int i = 0; i < DataIO.allCustomers.size(); i++) {
                   
                   Customer customer = DataIO.allCustomers.get(i);
                   
                   customerDetails += "Customer ID: " + customer.getUserID() + "\nUsername: " + customer.getUsername()
                           + "\nName: " + customer.getName() + "\nPhone Number: " + customer.getPhoneNumber() + "\n------------------------------\n";
               }
               
               JOptionPane.showMessageDialog(x, customerDetails);
            }
            
        } else if (e.getSource() == searchCustomer) {
            
            try {
                
                String keyword = JOptionPane.showInputDialog(x, "Enter Customer ID, username, name or phone number:");
                
                if (keyword == null || keyword.trim().isEmpty()) {
                    
                    throw new Exception();
                }
                
                keyword = keyword.trim();
                
                String searchResults = "";
                
                for (int i = 0; i < DataIO.allCustomers.size(); i++) {
                    
                    Customer customer = DataIO.allCustomers.get(i);
                    
                    if (customer.getUserID().equalsIgnoreCase(keyword) 
                            || customer.getUsername().toLowerCase().contains(keyword.toLowerCase())
                            || customer.getName().toLowerCase().contains(keyword.toLowerCase())
                            || customer.getPhoneNumber().contains(keyword)) {
                        
                        searchResults += "Customer ID: "
                                + customer.getUserID()
                                + "\nUsername: "
                                + customer.getUsername()
                                + "\nName: "
                                + customer.getName()
                                + "\nPhone Number: "
                                + customer.getPhoneNumber()
                                + "\n------------------------------\n";
                    }
                }
                
                if (searchResults.length() == 0) {
                    
                    JOptionPane.showMessageDialog(x, "No matching Customer found!");
                    
                } else {
                    
                    JOptionPane.showMessageDialog(x, "Matching Customers:\n\n" + searchResults);
                    
                } 
                
            } catch (Exception ex) {
                        
                JOptionPane.showMessageDialog(x, "Invalid input!");

            }           

        } else if (e.getSource() == updateCustomer) {

            try {
                
                String customerID = JOptionPane.showInputDialog(x, "Enter Customer ID: ");
                
                if (customerID == null || customerID.trim().isEmpty()) {
                    
                    throw new Exception();
                }
                
                Customer customer = DataIO.checkCustomerID(customerID.trim());
                
                if (customer == null) {
                    
                    JOptionPane.showMessageDialog(x, "Customer not found!");
                    
                } else {
                    
                    JOptionPane.showMessageDialog(x, "Current Customer Details" + "\n\nCustomerID: " + customer.getUserID()
                                + "\nUsername: " + customer.getUsername() + "\nName: " + customer.getName() + "\nPhone Number: " + customer.getPhoneNumber());
                
                    String username = JOptionPane.showInputDialog(x, "Enter new username: ");
                    String name = JOptionPane.showInputDialog(x, "Enter new name: ");
                    String phoneNumber = JOptionPane.showInputDialog(x, "Enter new phone number: ");
                    
                    if (username == null || username.trim().isEmpty() || name == null
                            || name.trim().isEmpty() || phoneNumber == null || phoneNumber.trim().isEmpty()) {
                        
                        throw new Exception();
                    }
                    
                    Customer updatedCustomer = counterStaff.updateCustomerDetails(customer, username.trim(), customer.getPassword(), name.trim(), phoneNumber.trim());
                    
                    if (updatedCustomer == null) {
                        
                        JOptionPane.showMessageDialog(x, "Username is already being used!");
                        
                    } else {
                        
                        JOptionPane.showMessageDialog(x, "Customer updated successfully!");
                    }
                }
            } catch (Exception ex) {
                
                JOptionPane.showMessageDialog(x, "Invalid input!");
            }
                
        } else if (e.getSource() == deleteCustomer) {

            try {
                
                String customerID = JOptionPane.showInputDialog(x, "Enter Customer ID:");
                
                if (customerID == null || customerID.trim().isEmpty()) {
                    
                    throw new Exception();
                }
                
                Customer customer = DataIO.checkCustomerID(customerID.trim());
                
                if (customer == null) {
                    
                    JOptionPane.showMessageDialog(x, "Customer not found!");
                    
                } else {
                    
                    String confirmation = JOptionPane.showInputDialog(x, "Customer ID: " + customer.getUserID()
                                    + "\nName: " + customer.getName() + "\nPhone Number: " + customer.getPhoneNumber() + "\n\nType YES to delete this Customer:");
                    
                    if (confirmation == null || !confirmation.equalsIgnoreCase("YES")) {
                        
                        JOptionPane.showMessageDialog(x, "Deletion cancelled.");
                        
                    } else {
                        
                        Customer deletedCustomer = counterStaff.deleteCustomer(customer.getUserID());
                        
                        if (deletedCustomer == null) {
                            
                            JOptionPane.showMessageDialog(x, "This Customer cannot be deleted because the Customer still own a car.");
                            
                        } else {
                            
                            JOptionPane.showMessageDialog(x, "Customer deleted successfully!");
                            
                        }
                    }
                }
                
            } catch (Exception ex) {
                
                JOptionPane.showMessageDialog(x, "Invalid input!");
            }

        } else if (e.getSource() == back) {

            CounterStaffDashboardGUI counterStaffDashboardGUI = new CounterStaffDashboardGUI(counterStaff);
            x.setVisible(false);
        }
    }

    public CustomerManagementGUI(CounterStaff counterStaff) {

        this.counterStaff = counterStaff;

        x = new JFrame();

        x.setTitle("Customer Management");
        x.setSize(450, 200);
        x.setLocation(550, 300);
        x.setLayout(new FlowLayout());

        title = new Label("Customer Management", Label.CENTER);

        addCustomer = new Button("Add Customer");
        viewCustomers = new Button("View Customers");
        searchCustomer = new Button("Search Customer");
        updateCustomer = new Button("Update Customer");
        deleteCustomer = new Button("Delete Customer");
        back = new Button("Back");

        addCustomer.addActionListener(this);
        viewCustomers.addActionListener(this);
        searchCustomer.addActionListener(this);
        updateCustomer.addActionListener(this);
        deleteCustomer.addActionListener(this);
        back.addActionListener(this);

        x.add(title);
        x.add(addCustomer);
        x.add(viewCustomers);
        x.add(searchCustomer);
        x.add(updateCustomer);
        x.add(deleteCustomer);
        x.add(back);

        x.setVisible(true);
    }
}