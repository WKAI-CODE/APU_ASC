/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import apu_asc.model.CounterStaff;
import apu_asc.model.Customer;
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
        }

        } else if (e.getSource() == viewCustomers) {

            // View Customers GUI will be opened here later

        } else if (e.getSource() == updateCustomer) {

            // Update Customer GUI will be opened here later

        } else if (e.getSource() == deleteCustomer) {

            // Delete Customer GUI will be opened here later

        } else if (e.getSource() == back) {

            new CounterStaffDashboardGUI(counterStaff);
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
        updateCustomer = new Button("Update Customer");
        deleteCustomer = new Button("Delete Customer");
        back = new Button("Back");

        addCustomer.addActionListener(this);
        viewCustomers.addActionListener(this);
        updateCustomer.addActionListener(this);
        deleteCustomer.addActionListener(this);
        back.addActionListener(this);

        x.add(title);
        x.add(addCustomer);
        x.add(viewCustomers);
        x.add(updateCustomer);
        x.add(deleteCustomer);
        x.add(back);

        x.setVisible(true);
    }
}