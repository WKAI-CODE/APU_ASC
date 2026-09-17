/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import apu_asc.model.CounterStaff;
import apu_asc.model.Appointment;
import apu_asc.model.Car;
import apu_asc.model.Customer;
import apu_asc.model.Staff;
import apu_asc.utility.DataIO;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class AppointmentManagementGUI
        implements ActionListener {

    CounterStaff counterStaff;

    JFrame x;

    Label title;

    Button bookAppointment;
    Button viewAppointments;
    Button searchAppointment;
    Button back;

    public AppointmentManagementGUI(
            CounterStaff counterStaff) {

        this.counterStaff = counterStaff;

        x = new JFrame();

        x.setTitle("Appointment Management");
        x.setSize(500, 200);
        x.setLocation(550, 300);
        x.setLayout(new FlowLayout());

        title = new Label(
                "Appointment Management",
                Label.CENTER
        );

        bookAppointment =
                new Button("Book Appointment");

        viewAppointments =
                new Button("View Appointments");

        searchAppointment =
                new Button("Search Appointment");

        back = new Button("Back");

        bookAppointment.addActionListener(this);
        viewAppointments.addActionListener(this);
        searchAppointment.addActionListener(this);
        back.addActionListener(this);

        x.add(title);
        x.add(bookAppointment);
        x.add(viewAppointments);
        x.add(searchAppointment);
        x.add(back);

        x.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bookAppointment) {

            try {

                // Search Customer
                String customerKeyword =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter Customer ID, username, "
                                + "name or phone number:"
                        );

                if (customerKeyword == null
                        || customerKeyword.trim().isEmpty()) {

                    throw new Exception();
                }

                customerKeyword =
                        customerKeyword.trim();

                String customerResults = "";
                int customerMatches = 0;
                Customer selectedCustomer = null;

                for (int i = 0;
                        i < DataIO.allCustomers.size();
                        i++) {

                    Customer customer =
                            DataIO.allCustomers.get(i);

                    if (customer.getUserID()
                                .equalsIgnoreCase(
                                        customerKeyword
                                )
                            || customer.getUsername()
                                .toLowerCase()
                                .contains(
                                        customerKeyword.toLowerCase()
                                )
                            || customer.getName()
                                .toLowerCase()
                                .contains(
                                        customerKeyword.toLowerCase()
                                )
                            || customer.getPhoneNumber()
                                .contains(customerKeyword)) {

                        customerMatches++;
                        selectedCustomer = customer;

                        customerResults +=
                                "Customer ID: "
                                + customer.getUserID()
                                + "\nUsername: "
                                + customer.getUsername()
                                + "\nName: "
                                + customer.getName()
                                + "\nPhone: "
                                + customer.getPhoneNumber()
                                + "\n------------------------------\n";
                    }
                }

                if (customerMatches == 0) {

                    JOptionPane.showMessageDialog(
                            x,
                            "No matching Customer found!"
                    );

                    return;
                }

                if (customerMatches > 1) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Matching Customers:\n\n"
                            + customerResults
                    );

                    String selectedCustomerID =
                            JOptionPane.showInputDialog(
                                    x,
                                    "Enter the Customer ID "
                                    + "you want to select:"
                            );

                    if (selectedCustomerID == null
                            || selectedCustomerID
                                    .trim()
                                    .isEmpty()) {

                        throw new Exception();
                    }

                    selectedCustomer =
                            DataIO.checkCustomerID(
                                    selectedCustomerID.trim()
                            );

                    if (selectedCustomer == null) {

                        JOptionPane.showMessageDialog(
                                x,
                                "Customer not found!"
                        );

                        return;
                    }
                }

                String customerID =
                        selectedCustomer.getUserID();

                // Find the Customer's cars
                String carResults = "";
                int carMatches = 0;
                Car selectedCar = null;

                for (int i = 0;
                        i < DataIO.allCars.size();
                        i++) {

                    Car car =
                            DataIO.allCars.get(i);

                    if (car.getCustomerID()
                            .equalsIgnoreCase(customerID)) {

                        carMatches++;
                        selectedCar = car;

                        carResults +=
                                "Car ID: "
                                + car.getCarID()
                                + "\nRegistration number: "
                                + car.getRegistrationNumber()
                                + "\nBrand: "
                                + car.getBrand()
                                + "\nModel: "
                                + car.getModel()
                                + "\n------------------------------\n";
                    }
                }

                if (carMatches == 0) {

                    JOptionPane.showMessageDialog(x,"This Customer does not have a registered car." + "\nPlease register the car first."
                    );

                    return;
                }

                if (carMatches > 1) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Customer's Cars:\n\n"
                            + carResults
                    );

                    String selectedCarID =
                            JOptionPane.showInputDialog(
                                    x,
                                    "Enter the Car ID "
                                    + "you want to select:"
                            );

                    if (selectedCarID == null
                            || selectedCarID.trim().isEmpty()) {

                        throw new Exception();
                    }

                    selectedCar =
                            DataIO.checkCarID(
                                    selectedCarID.trim()
                            );

                    if (selectedCar == null
                            || !selectedCar.getCustomerID()
                                    .equalsIgnoreCase(customerID)) {

                        JOptionPane.showMessageDialog(
                                x,
                                "The selected car does not belong "
                                + "to this Customer!"
                        );

                        return;
                    }
                }

                String carID =
                        selectedCar.getCarID();

                JOptionPane.showMessageDialog(
                        x,
                        "Selected Customer and Car"
                        + "\n\nCustomer ID: "
                        + selectedCustomer.getUserID()
                        + "\nCustomer name: "
                        + selectedCustomer.getName()
                        + "\nCar ID: "
                        + selectedCar.getCarID()
                        + "\nRegistration number: "
                        + selectedCar.getRegistrationNumber()
                        + "\nCar: "
                        + selectedCar.getBrand()
                        + " "
                        + selectedCar.getModel()
                );

                // Select service type
                String serviceType =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter service type:"
                                + "\nMINOR = 1 hour"
                                + "\nMAJOR = 3 hours"
                        );

                if (serviceType == null
                        || serviceType.trim().isEmpty()) {

                    throw new Exception();
                }

                serviceType =
                        serviceType.trim().toUpperCase();

                if (!serviceType.equals("MINOR")
                        && !serviceType.equals("MAJOR")) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Service type must be "
                            + "MINOR or MAJOR!"
                    );

                    return;
                }

                double servicePrice;

                if (serviceType.equals("MINOR")) {

                    servicePrice = DataIO.minorPrice;

                } else {

                    servicePrice = DataIO.majorPrice;
                }

                if (servicePrice <= 0) {

                    JOptionPane.showMessageDialog(
                            x,
                            "The Manager has not set "
                            + "the service price yet!"
                    );

                    return;
                }

                // Enter booking date
                String date =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter appointment date:"
                                + "\nFormat: yyyy-MM-dd"
                                + "\nBooking is allowed from tomorrow "
                                + "until the next 14 days."
                        );

                if (date == null
                        || date.trim().isEmpty()) {

                    throw new Exception();
                }

                date = date.trim();

                if (counterStaff.getValidBookingDate(
                        date) == null) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Invalid booking date!"
                            + "\nThe date must be from tomorrow "
                            + "until the next 14 days."
                    );

                    return;
                }

                // Enter start time
                String startTime =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter the start time:"
                                + "\nFormat: HH:mm"
                                + "\nWorking hours: 09:00 to 18:00"
                        );

                if (startTime == null
                        || startTime.trim().isEmpty()) {

                    throw new Exception();
                }

                startTime = startTime.trim();

                String endTime =
                        counterStaff.calculateEndTime(
                                serviceType,
                                startTime
                        );

                if (endTime == null) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Invalid start time!"
                            + "\nPlease use HH:mm."
                    );

                    return;
                }

                String workingHoursError =
                        counterStaff.getWorkingHoursError(
                                startTime,
                                endTime
                        );

                if (workingHoursError != null) {

                    JOptionPane.showMessageDialog(
                            x,
                            workingHoursError
                    );

                    return;
                }

                JOptionPane.showMessageDialog(
                        x,
                        "Appointment Time"
                        + "\n\nService type: "
                        + serviceType
                        + "\nStart time: "
                        + startTime
                        + "\nEnd time: "
                        + endTime
                        + "\nService price: RM "
                        + String.format(
                                "%.2f",
                                servicePrice
                        )
                );

                // Find available Technicians
                String technicianResults = "";
                int technicianMatches = 0;
                Staff selectedTechnician = null;

                for (int i = 0;
                        i < DataIO.allStaff.size();
                        i++) {

                    Staff staff =
                            DataIO.allStaff.get(i);

                    if ("TECHNICIAN".equalsIgnoreCase(
                            staff.getRole())) {

                        Appointment conflict =
                                counterStaff
                                        .findConflictingAppointment(
                                                staff.getUserID(),
                                                date,
                                                startTime,
                                                endTime
                                        );

                        if (conflict == null) {

                            technicianMatches++;
                            selectedTechnician = staff;

                            technicianResults +=
                                    "Technician ID: "
                                    + staff.getUserID()
                                    + "\nName: "
                                    + staff.getName()
                                    + "\n------------------------------\n";
                        }
                    }
                }

                if (technicianMatches == 0) {

                    JOptionPane.showMessageDialog(
                            x,
                            "No Technician is available "
                            + "at the selected time!"
                    );

                    return;
                }

                if (technicianMatches > 1) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Available Technicians:\n\n"
                            + technicianResults
                    );

                    String selectedTechnicianID =
                            JOptionPane.showInputDialog(
                                    x,
                                    "Enter the Technician ID "
                                    + "you want to assign:"
                            );

                    if (selectedTechnicianID == null
                            || selectedTechnicianID
                                    .trim()
                                    .isEmpty()) {

                        throw new Exception();
                    }

                    selectedTechnician =
                            DataIO.checkUserID(
                                    selectedTechnicianID.trim()
                            );

                    if (selectedTechnician == null
                            || !"TECHNICIAN"
                                    .equalsIgnoreCase(
                                            selectedTechnician
                                                    .getRole()
                                    )) {

                        JOptionPane.showMessageDialog(
                                x,
                                "Invalid Technician!"
                        );

                        return;
                    }

                    Appointment conflict =
                            counterStaff
                                    .findConflictingAppointment(
                                            selectedTechnician
                                                    .getUserID(),
                                            date,
                                            startTime,
                                            endTime
                                    );

                    if (conflict != null) {

                        JOptionPane.showMessageDialog(
                                x,
                                "The selected Technician "
                                + "is not available!"
                        );

                        return;
                    }
                }

                String technicianID =
                        selectedTechnician.getUserID();

                String appointmentID =
                        counterStaff.generateAppointmentID();

                Appointment newAppointment =
                        counterStaff
                                .bookAppointmentWithValidation(
                                        appointmentID,
                                        customerID,
                                        carID,
                                        technicianID,
                                        serviceType,
                                        date,
                                        startTime,
                                        endTime
                                );

                if (newAppointment == null) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Unable to create the appointment!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            x,
                            "Appointment created successfully!"
                            + "\n\nAppointment ID: "
                            + newAppointment.getAppointmentID()
                            + "\nCustomer ID: "
                            + newAppointment.getCustomerID()
                            + "\nCar ID: "
                            + newAppointment.getCarID()
                            + "\nTechnician ID: "
                            + newAppointment.getTechnicianID()
                            + "\nService type: "
                            + newAppointment.getServiceType()
                            + "\nDate: "
                            + newAppointment.getDate()
                            + "\nTime: "
                            + newAppointment.getStartTime()
                            + " - "
                            + newAppointment.getEndTime()
                            + "\nPrice: RM "
                            + String.format(
                                    "%.2f",
                                    newAppointment
                                            .getServicePrice()
                            )
                            + "\nStatus: "
                            + newAppointment
                                    .getAppointmentStatus()
                            + "\nPayment: "
                            + newAppointment
                                    .getPaymentStatus()
                    );
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        x,
                        "Invalid input!"
                );
            }
            
        } else if (e.getSource() == viewAppointments) {

            if (DataIO.allAppointments.isEmpty()) {

                JOptionPane.showMessageDialog(
                        x,
                        "No appointment records found!"
                );

            } else {

                String appointmentDetails =
                        "Total Appointments: "
                        + DataIO.allAppointments.size()
                        + "\n\n";

                for (int i = 0;
                        i < DataIO.allAppointments.size();
                        i++) {

                    Appointment appointment =
                            DataIO.allAppointments.get(i);

                    Customer customer =
                            DataIO.checkCustomerID(
                                    appointment.getCustomerID()
                            );

                    Car car =
                            DataIO.checkCarID(
                                    appointment.getCarID()
                            );

                    Staff technician =
                            DataIO.checkUserID(
                                    appointment.getTechnicianID()
                            );

                    String customerName = "Unknown";
                    String registrationNumber = "Unknown";
                    String technicianName = "Unknown";

                    if (customer != null) {
                        customerName = customer.getName();
                    }

                    if (car != null) {
                        registrationNumber =
                                car.getRegistrationNumber();
                    }

                    if (technician != null) {
                        technicianName =
                                technician.getName();
                    }

                    appointmentDetails +=
                            "Appointment ID: "
                            + appointment.getAppointmentID()
                            + "\nCustomer ID: "
                            + appointment.getCustomerID()
                            + "\nCustomer name: "
                            + customerName
                            + "\nCar ID: "
                            + appointment.getCarID()
                            + "\nRegistration number: "
                            + registrationNumber
                            + "\nTechnician ID: "
                            + appointment.getTechnicianID()
                            + "\nTechnician name: "
                            + technicianName
                            + "\nService type: "
                            + appointment.getServiceType()
                            + "\nDate: "
                            + appointment.getDate()
                            + "\nTime: "
                            + appointment.getStartTime()
                            + " - "
                            + appointment.getEndTime()
                            + "\nService price: RM "
                            + String.format(
                                    "%.2f",
                                    appointment.getServicePrice()
                            )
                            + "\nAppointment status: "
                            + appointment.getAppointmentStatus()
                            + "\nPayment status: "
                            + appointment.getPaymentStatus()
                            + "\n------------------------------\n";
                }

                JOptionPane.showMessageDialog(
                        x,
                        appointmentDetails
                );
            }
            
        } else if (e.getSource() == searchAppointment) {

            try {

                String keyword =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter Appointment ID, Customer, car, "
                                + "Technician, date, service or status:"
                        );

                if (keyword == null
                        || keyword.trim().isEmpty()) {

                    throw new Exception();
                }

                keyword = keyword.trim();

                String lowerKeyword =
                        keyword.toLowerCase();

                String searchResults = "";

                for (int i = 0;
                        i < DataIO.allAppointments.size();
                        i++) {

                    Appointment appointment =
                            DataIO.allAppointments.get(i);

                    Customer customer =
                            DataIO.checkCustomerID(
                                    appointment.getCustomerID()
                            );

                    Car car =
                            DataIO.checkCarID(
                                    appointment.getCarID()
                            );

                    Staff technician =
                            DataIO.checkUserID(
                                    appointment.getTechnicianID()
                            );

                    String customerName = "Unknown";
                    String registrationNumber = "Unknown";
                    String technicianName = "Unknown";

                    if (customer != null) {
                        customerName = customer.getName();
                    }

                    if (car != null) {
                        registrationNumber =
                                car.getRegistrationNumber();
                    }

                    if (technician != null) {
                        technicianName =
                                technician.getName();
                    }

                    if (appointment.getAppointmentID()
                                .equalsIgnoreCase(keyword)
                            || appointment.getCustomerID()
                                .equalsIgnoreCase(keyword)
                            || customerName
                                .toLowerCase()
                                .contains(lowerKeyword)
                            || appointment.getCarID()
                                .equalsIgnoreCase(keyword)
                            || registrationNumber
                                .toLowerCase()
                                .contains(lowerKeyword)
                            || appointment.getTechnicianID()
                                .equalsIgnoreCase(keyword)
                            || technicianName
                                .toLowerCase()
                                .contains(lowerKeyword)
                            || appointment.getServiceType()
                                .equalsIgnoreCase(keyword)
                            || appointment.getDate()
                                .contains(keyword)
                            || appointment.getAppointmentStatus()
                                .equalsIgnoreCase(keyword)
                            || appointment.getPaymentStatus()
                                .equalsIgnoreCase(keyword)) {

                        searchResults +=
                                "Appointment ID: "
                                + appointment.getAppointmentID()
                                + "\nCustomer ID: "
                                + appointment.getCustomerID()
                                + "\nCustomer name: "
                                + customerName
                                + "\nCar ID: "
                                + appointment.getCarID()
                                + "\nRegistration number: "
                                + registrationNumber
                                + "\nTechnician ID: "
                                + appointment.getTechnicianID()
                                + "\nTechnician name: "
                                + technicianName
                                + "\nService type: "
                                + appointment.getServiceType()
                                + "\nDate: "
                                + appointment.getDate()
                                + "\nTime: "
                                + appointment.getStartTime()
                                + " - "
                                + appointment.getEndTime()
                                + "\nService price: RM "
                                + String.format(
                                        "%.2f",
                                        appointment.getServicePrice()
                                )
                                + "\nAppointment status: "
                                + appointment.getAppointmentStatus()
                                + "\nPayment status: "
                                + appointment.getPaymentStatus()
                                + "\n------------------------------\n";
                    }
                }

                if (searchResults.length() == 0) {

                    JOptionPane.showMessageDialog(
                            x,
                            "No matching appointment found!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            x,
                            "Matching Appointments:\n\n"
                            + searchResults
                    );
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        x,
                        "Invalid input!"
                );
            }

        } else if (e.getSource() == back) {

            CounterStaffDashboardGUI counterStaffDashboardGUI = new CounterStaffDashboardGUI(
                    counterStaff
            );

            x.setVisible(false);
        }
    }
}