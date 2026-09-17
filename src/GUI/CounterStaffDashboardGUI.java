/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import apu_asc.model.CounterStaff;
import apu_asc.model.Staff;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class CounterStaffDashboardGUI implements ActionListener {

    CounterStaff counterStaff;

    JFrame x;

    Label title;

    Button manageCustomers;
    Button manageCars;
    Button manageAppointments;
    Button payments;
    Button editProfile;
    Button logout;

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == manageCustomers) {

            CustomerManagementGUI customerManagementGUI = new CustomerManagementGUI(counterStaff);
            x.setVisible(false);

        } else if (e.getSource() == logout) {

            LoginGUI loginPage = new LoginGUI();

            loginPage.setVisible(true);
            x.setVisible(false);
        }
    }

    public CounterStaffDashboardGUI(Staff staff) {

        counterStaff = (CounterStaff) staff;

        x = new JFrame();

        x.setTitle("Counter Staff Dashboard");
        x.setSize(550, 250);
        x.setLocation(500, 250);
        x.setLayout(new FlowLayout());

        title = new Label(
                "Welcome, " + counterStaff.getName(),
                Label.CENTER
        );

        manageCustomers = new Button("Manage Customers");
        manageCars = new Button("Manage Cars");
        manageAppointments = new Button("Manage Appointments");
        payments = new Button("Payments and Receipts");
        editProfile = new Button("Edit Profile");
        logout = new Button("Logout");

        manageCustomers.addActionListener(this);
        manageCars.addActionListener(this);
        manageAppointments.addActionListener(this);
        payments.addActionListener(this);
        editProfile.addActionListener(this);
        logout.addActionListener(this);

        x.add(title);
        x.add(manageCustomers);
        x.add(manageCars);
        x.add(manageAppointments);
        x.add(payments);
        x.add(editProfile);
        x.add(logout);

        x.setVisible(true);
    }
}