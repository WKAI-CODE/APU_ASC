package GUI;

import apu_asc.model.CounterStaff;
import apu_asc.model.Appointment;
import apu_asc.model.Car;
import apu_asc.model.Customer;
import apu_asc.model.Staff;
import apu_asc.utility.DataIO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AppointmentManagementGUI
        implements ActionListener {

    CounterStaff counterStaff;

    JFrame x;

    JLabel title;

    JButton bookAppointment;
    JButton viewAppointments;
    JButton searchAppointment;
    JButton back;

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bookAppointment) {

            try {

                // Search for Customer
                String customerKeyword =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter Customer ID, username, "
                                + "name or phone number:"
                        );

                if (customerKeyword == null) {
                    return;
                }

                customerKeyword = customerKeyword.trim();

                if (customerKeyword.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Please enter a search keyword!"
                    );

                    return;
                }

                String customerResults = "";
                int customerMatches = 0;
                Customer selectedCustomer = null;

                for (int i = 0;
                        i < DataIO.allCustomers.size();
                        i++) {

                    Customer customer =
                            DataIO.allCustomers.get(i);

                    if (customer.getUserID()
                                .equalsIgnoreCase(customerKeyword)
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

                    if (selectedCustomerID == null) {
                        return;
                    }

                    selectedCustomerID =
                            selectedCustomerID.trim();

                    if (selectedCustomerID.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                x,
                                "Please enter a Customer ID!"
                        );

                        return;
                    }

                    selectedCustomer =
                            DataIO.checkCustomerID(
                                    selectedCustomerID
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

                    Car car = DataIO.allCars.get(i);

                    if (car.getCustomerID()
                            .equalsIgnoreCase(customerID)) {

                        carMatches++;
                        selectedCar = car;

                        carResults +=
                                "Car ID: "
                                + car.getCarID()
                                + "\nRegistration Number: "
                                + car.getRegistrationNumber()
                                + "\nBrand: "
                                + car.getBrand()
                                + "\nModel: "
                                + car.getModel()
                                + "\n------------------------------\n";
                    }
                }

                if (carMatches == 0) {

                    JOptionPane.showMessageDialog(
                            x,
                            "This Customer does not have "
                            + "a registered car."
                            + "\nPlease register the car first."
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

                    if (selectedCarID == null) {
                        return;
                    }

                    selectedCarID = selectedCarID.trim();

                    if (selectedCarID.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                x,
                                "Please enter a Car ID!"
                        );

                        return;
                    }

                    selectedCar =
                            DataIO.checkCarID(selectedCarID);

                    if (selectedCar == null
                            || !selectedCar.getCustomerID()
                                    .equalsIgnoreCase(customerID)) {

                        JOptionPane.showMessageDialog(
                                x,
                                "The selected car does not "
                                + "belong to this Customer!"
                        );

                        return;
                    }
                }

                String carID = selectedCar.getCarID();

                // Read-only Customer and Car fields
                JTextField customerIDField =
                        new JTextField(
                                selectedCustomer.getUserID()
                        );

                JTextField customerNameField =
                        new JTextField(
                                selectedCustomer.getName()
                        );

                JTextField carIDField =
                        new JTextField(
                                selectedCar.getCarID()
                        );

                JTextField registrationNumberField =
                        new JTextField(
                                selectedCar.getRegistrationNumber()
                        );

                JTextField carDescriptionField =
                        new JTextField(
                                selectedCar.getBrand()
                                + " "
                                + selectedCar.getModel()
                        );

                customerIDField.setEditable(false);
                customerNameField.setEditable(false);
                carIDField.setEditable(false);
                registrationNumberField.setEditable(false);
                carDescriptionField.setEditable(false);

                // Service dropdown
                String[] serviceOptions = {
                    "MINOR - 1 hour",
                    "MAJOR - 3 hours"
                };

                JComboBox<String> serviceTypeBox =
                        new JComboBox<>(serviceOptions);

                JTextField dateField =
                        new JTextField(15);

                JTextField startTimeField =
                        new JTextField(15);

                JPanel appointmentPanel =
                        new JPanel(
                                new GridLayout(8, 2, 10, 10)
                        );

                appointmentPanel.add(
                        new JLabel("Customer ID:")
                );
                appointmentPanel.add(customerIDField);

                appointmentPanel.add(
                        new JLabel("Customer Name:")
                );
                appointmentPanel.add(customerNameField);

                appointmentPanel.add(
                        new JLabel("Car ID:")
                );
                appointmentPanel.add(carIDField);

                appointmentPanel.add(
                        new JLabel("Registration Number:")
                );
                appointmentPanel.add(
                        registrationNumberField
                );

                appointmentPanel.add(
                        new JLabel("Car:")
                );
                appointmentPanel.add(carDescriptionField);

                appointmentPanel.add(
                        new JLabel("Service Type:")
                );
                appointmentPanel.add(serviceTypeBox);

                appointmentPanel.add(
                        new JLabel("Date (yyyy-MM-dd):")
                );
                appointmentPanel.add(dateField);

                appointmentPanel.add(
                        new JLabel("Start Time (HH:mm):")
                );
                appointmentPanel.add(startTimeField);

                int result = JOptionPane.showConfirmDialog(
                        x,
                        appointmentPanel,
                        "Book Appointment",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result != JOptionPane.OK_OPTION) {
                    return;
                }

                String serviceType;

                if (serviceTypeBox.getSelectedIndex() == 0) {
                    serviceType = "MINOR";
                } else {
                    serviceType = "MAJOR";
                }

                String date =
                        dateField.getText().trim();

                String startTime =
                        startTimeField.getText().trim();

                if (date.isEmpty()
                        || startTime.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Please complete all fields!"
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

                if (counterStaff.getValidBookingDate(date)
                        == null) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Invalid booking date!"
                            + "\nThe date must be from tomorrow "
                            + "until the next 14 days."
                    );

                    return;
                }

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
                        + "\n\nService Type: "
                        + serviceType
                        + "\nStart Time: "
                        + startTime
                        + "\nEnd Time: "
                        + endTime
                        + "\nService Price: RM "
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

                    if (selectedTechnicianID == null) {
                        return;
                    }

                    selectedTechnicianID =
                            selectedTechnicianID.trim();

                    if (selectedTechnicianID.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                x,
                                "Please enter a Technician ID!"
                        );

                        return;
                    }

                    selectedTechnician =
                            DataIO.checkUserID(
                                    selectedTechnicianID
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
                            + newAppointment
                                    .getAppointmentID()
                            + "\nCustomer ID: "
                            + newAppointment
                                    .getCustomerID()
                            + "\nCar ID: "
                            + newAppointment.getCarID()
                            + "\nTechnician ID: "
                            + newAppointment
                                    .getTechnicianID()
                            + "\nService Type: "
                            + newAppointment
                                    .getServiceType()
                            + "\nDate: "
                            + newAppointment.getDate()
                            + "\nTime: "
                            + newAppointment
                                    .getStartTime()
                            + " - "
                            + newAppointment
                                    .getEndTime()
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
                        "Unable to create the appointment!"
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

                showScrollableResults(
                        "Appointment Records",
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

                    showScrollableResults(
                            "Matching Appointments",
                            searchResults
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

    private void showScrollableResults(
            String windowTitle,
            String results) {

        JTextArea resultArea = new JTextArea(
                results,
                18,
                45
        );

        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(
                resultArea
        );

        JOptionPane.showMessageDialog(
                x,
                scrollPane,
                windowTitle,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public AppointmentManagementGUI(
            CounterStaff counterStaff) {

        this.counterStaff = counterStaff;

        x = new JFrame();

        x.setTitle("Appointment Management");
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
                "Appointment Management"
        );

        title.setForeground(Color.white);

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
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
                        "Select an Appointment Function"
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

        bookAppointment =
                new JButton("Book Appointment");

        viewAppointments =
                new JButton("View Appointments");

        searchAppointment =
                new JButton("Search Appointment");

        Font buttonFont = new Font(
                "SansSerif",
                Font.BOLD,
                14
        );

        bookAppointment.setFont(buttonFont);
        viewAppointments.setFont(buttonFont);
        searchAppointment.setFont(buttonFont);

        bookAppointment.setVerticalAlignment(
                JButton.CENTER
        );

        viewAppointments.setVerticalAlignment(
                JButton.CENTER
        );

        searchAppointment.setVerticalAlignment(
                JButton.CENTER
        );

        firstRow.add(bookAppointment);
        firstRow.add(viewAppointments);
        firstRow.add(searchAppointment);

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
        bookAppointment.addActionListener(this);
        viewAppointments.addActionListener(this);
        searchAppointment.addActionListener(this);
        back.addActionListener(this);

        // Add all sections
        x.add("North", headerPanel);
        x.add("Center", mainPanel);
        x.add("South", footerPanel);

        x.setVisible(true);
    }
}
