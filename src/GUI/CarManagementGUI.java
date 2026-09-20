package GUI;

import apu_asc.model.Car;
import apu_asc.model.CounterStaff;
import apu_asc.model.Customer;
import apu_asc.utility.DataIO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class CarManagementGUI
        implements ActionListener {

    CounterStaff counterStaff;

    JFrame x;

    JLabel title;

    JButton addCar;
    JButton viewCars;
    JButton searchCar;
    JButton updateCar;
    JButton deleteCar;
    JButton back;

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addCar) {

            try {

                String keyword = JOptionPane.showInputDialog(
                        x,
                        "Enter Customer ID, username, "
                        + "name or phone number:"
                );

                if (keyword == null) {
                    return;
                }

                keyword = keyword.trim();

                if (keyword.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Please enter a search keyword!"
                    );

                    return;
                }

                String customerResults = "";
                int numberOfMatches = 0;
                Customer owner = null;

                for (int i = 0;
                        i < DataIO.allCustomers.size();
                        i++) {

                    Customer customer =
                            DataIO.allCustomers.get(i);

                    if (customer.getUserID()
                                .equalsIgnoreCase(keyword)
                            || customer.getUsername()
                                .toLowerCase()
                                .contains(keyword.toLowerCase())
                            || customer.getName()
                                .toLowerCase()
                                .contains(keyword.toLowerCase())
                            || customer.getPhoneNumber()
                                .contains(keyword)) {

                        numberOfMatches++;
                        owner = customer;

                        customerResults +=
                                "Customer ID: "
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

                if (numberOfMatches == 0) {

                    JOptionPane.showMessageDialog(
                            x,
                            "No matching Customer found!"
                    );

                    return;
                }

                if (numberOfMatches > 1) {

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

                    owner = DataIO.checkCustomerID(
                            selectedCustomerID
                    );

                    if (owner == null) {

                        JOptionPane.showMessageDialog(
                                x,
                                "Customer not found!"
                        );

                        return;
                    }
                }

                String customerID = owner.getUserID();

                JTextField customerIDField =
                        new JTextField(owner.getUserID());

                JTextField customerNameField =
                        new JTextField(owner.getName());

                JTextField registrationNumberField =
                        new JTextField(15);

                JTextField brandField =
                        new JTextField(15);

                JTextField modelField =
                        new JTextField(15);

                JTextField yearField =
                        new JTextField(15);

                JTextField colourField =
                        new JTextField(15);

                customerIDField.setEditable(false);
                customerNameField.setEditable(false);

                JPanel addCarPanel =
                        new JPanel(
                                new GridLayout(7, 2, 10, 10)
                        );

                addCarPanel.add(new JLabel("Customer ID:"));
                addCarPanel.add(customerIDField);

                addCarPanel.add(new JLabel("Customer Name:"));
                addCarPanel.add(customerNameField);

                addCarPanel.add(
                        new JLabel("Registration Number:")
                );
                addCarPanel.add(registrationNumberField);

                addCarPanel.add(new JLabel("Brand:"));
                addCarPanel.add(brandField);

                addCarPanel.add(new JLabel("Model:"));
                addCarPanel.add(modelField);

                addCarPanel.add(new JLabel("Year:"));
                addCarPanel.add(yearField);

                addCarPanel.add(new JLabel("Colour:"));
                addCarPanel.add(colourField);

                int result = JOptionPane.showConfirmDialog(
                        x,
                        addCarPanel,
                        "Register New Car",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result != JOptionPane.OK_OPTION) {
                    return;
                }

                String registrationNumber =
                        registrationNumberField
                                .getText()
                                .trim();

                String brand =
                        brandField.getText().trim();

                String model =
                        modelField.getText().trim();

                String yearInput =
                        yearField.getText().trim();

                String colour =
                        colourField.getText().trim();

                if (registrationNumber.isEmpty()
                        || brand.isEmpty()
                        || model.isEmpty()
                        || yearInput.isEmpty()
                        || colour.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Please complete all fields!"
                    );

                    return;
                }

                int year;

                try {

                    year = Integer.parseInt(yearInput);

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Car year must be a valid number!"
                    );

                    return;
                }

                if (year <= 0) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Please enter a valid car year!"
                    );

                    return;
                }

                String carID =
                        counterStaff.generateCarID();

                Car newCar = counterStaff.addCar(
                        carID,
                        customerID,
                        registrationNumber,
                        brand,
                        model,
                        year,
                        colour
                );

                if (newCar == null) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Unable to register the car!"
                            + "\nThe registration number "
                            + "may already exist."
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            x,
                            "Car registered successfully!"
                            + "\n\nCar ID: "
                            + newCar.getCarID()
                            + "\nCustomer ID: "
                            + newCar.getCustomerID()
                            + "\nRegistration Number: "
                            + newCar.getRegistrationNumber()
                            + "\nBrand: "
                            + newCar.getBrand()
                            + "\nModel: "
                            + newCar.getModel()
                            + "\nYear: "
                            + newCar.getYear()
                            + "\nColour: "
                            + newCar.getColour()
                    );
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        x,
                        "Unable to register the car!"
                );
            }
            
        } else if (e.getSource() == viewCars) {

            if (DataIO.allCars.isEmpty()) {

                JOptionPane.showMessageDialog(
                        x,
                        "No car records found!"
                );

            } else {

                String carDetails =
                        "Total Cars: "
                        + DataIO.allCars.size()
                        + "\n\n";

                for (int i = 0;
                        i < DataIO.allCars.size();
                        i++) {

                    Car car =
                            DataIO.allCars.get(i);

                    Customer owner =
                            DataIO.checkCustomerID(
                                    car.getCustomerID()
                            );

                    String ownerName = "Unknown";

                    if (owner != null) {
                        ownerName = owner.getName();
                    }

                    carDetails +=
                            "Car ID: "
                            + car.getCarID()
                            + "\nCustomer ID: "
                            + car.getCustomerID()
                            + "\nCustomer name: "
                            + ownerName
                            + "\nRegistration number: "
                            + car.getRegistrationNumber()
                            + "\nBrand: "
                            + car.getBrand()
                            + "\nModel: "
                            + car.getModel()
                            + "\nYear: "
                            + car.getYear()
                            + "\nColour: "
                            + car.getColour()
                            + "\n------------------------------\n";
                }

                showScrollableResults(
                        "Car Records",
                        carDetails
                );
            }
            
        } else if (e.getSource() == searchCar) {

            try {

                String keyword =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter Car ID, registration number, brand, model, Customer ID or customer name:"
                        );

                if (keyword == null
                        || keyword.trim().isEmpty()) {

                    throw new Exception();
                }

                keyword = keyword.trim();

                String searchResults = "";

                for (int i = 0;
                        i < DataIO.allCars.size();
                        i++) {

                    Car car =
                            DataIO.allCars.get(i);

                    Customer owner =
                            DataIO.checkCustomerID(
                                    car.getCustomerID()
                            );

                    String ownerName = "Unknown";

                    if (owner != null) {
                        ownerName = owner.getName();
                    }

                    if (car.getCarID()
                                .equalsIgnoreCase(keyword)
                            || car.getRegistrationNumber()
                                .toLowerCase()
                                .contains(keyword.toLowerCase())
                            || car.getBrand()
                                .toLowerCase()
                                .contains(keyword.toLowerCase())
                            || car.getModel()
                                .toLowerCase()
                                .contains(keyword.toLowerCase())
                            || car.getCustomerID()
                                .equalsIgnoreCase(keyword)
                            || ownerName
                                .toLowerCase()
                                .contains(keyword.toLowerCase())) {

                        searchResults +=
                                "Car ID: "
                                + car.getCarID()
                                + "\nCustomer ID: "
                                + car.getCustomerID()
                                + "\nCustomer name: "
                                + ownerName
                                + "\nRegistration number: "
                                + car.getRegistrationNumber()
                                + "\nBrand: "
                                + car.getBrand()
                                + "\nModel: "
                                + car.getModel()
                                + "\nYear: "
                                + car.getYear()
                                + "\nColour: "
                                + car.getColour()
                                + "\n------------------------------\n";
                    }
                }

                if (searchResults.length() == 0) {

                    JOptionPane.showMessageDialog(
                            x,
                            "No matching car found!"
                    );

                } else {

                    showScrollableResults(
                            "Matching Cars",
                            searchResults
                    );
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        x,
                        "Invalid input!"
                );
            }
            
        } else if (e.getSource() == updateCar) {

            try {

                String keyword = JOptionPane.showInputDialog(
                        x,
                        "Enter Car ID, registration number, "
                        + "Customer ID or customer name:"
                );

                if (keyword == null) {
                    return;
                }

                keyword = keyword.trim();

                if (keyword.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Please enter a search keyword!"
                    );

                    return;
                }

                String carResults = "";
                int numberOfMatches = 0;
                Car selectedCar = null;

                for (int i = 0;
                        i < DataIO.allCars.size();
                        i++) {

                    Car car = DataIO.allCars.get(i);

                    Customer owner = DataIO.checkCustomerID(
                            car.getCustomerID()
                    );

                    String ownerName = "Unknown";

                    if (owner != null) {
                        ownerName = owner.getName();
                    }

                    if (car.getCarID()
                                .equalsIgnoreCase(keyword)
                            || car.getRegistrationNumber()
                                .toLowerCase()
                                .contains(keyword.toLowerCase())
                            || car.getCustomerID()
                                .equalsIgnoreCase(keyword)
                            || ownerName
                                .toLowerCase()
                                .contains(keyword.toLowerCase())) {

                        numberOfMatches++;
                        selectedCar = car;

                        carResults +=
                                "Car ID: "
                                + car.getCarID()
                                + "\nCustomer ID: "
                                + car.getCustomerID()
                                + "\nCustomer Name: "
                                + ownerName
                                + "\nRegistration Number: "
                                + car.getRegistrationNumber()
                                + "\nBrand: "
                                + car.getBrand()
                                + "\nModel: "
                                + car.getModel()
                                + "\n------------------------------\n";
                    }
                }

                if (numberOfMatches == 0) {

                    JOptionPane.showMessageDialog(
                            x,
                            "No matching car found!"
                    );

                    return;
                }

                if (numberOfMatches > 1) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Matching Cars:\n\n"
                            + carResults
                    );

                    String selectedCarID =
                            JOptionPane.showInputDialog(
                                    x,
                                    "Enter the Car ID "
                                    + "you want to update:"
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

                    selectedCar = DataIO.checkCarID(
                            selectedCarID
                    );

                    if (selectedCar == null) {

                        JOptionPane.showMessageDialog(
                                x,
                                "Car not found!"
                        );

                        return;
                    }
                }

                Customer selectedOwner =
                        DataIO.checkCustomerID(
                                selectedCar.getCustomerID()
                        );

                String selectedOwnerName = "Unknown";

                if (selectedOwner != null) {
                    selectedOwnerName =
                            selectedOwner.getName();
                }

                JTextField carIDField =
                        new JTextField(
                                selectedCar.getCarID()
                        );

                JTextField customerIDField =
                        new JTextField(
                                selectedCar.getCustomerID()
                        );

                JTextField customerNameField =
                        new JTextField(
                                selectedOwnerName
                        );

                JTextField registrationNumberField =
                        new JTextField(
                                selectedCar
                                        .getRegistrationNumber()
                        );

                JTextField brandField =
                        new JTextField(
                                selectedCar.getBrand()
                        );

                JTextField modelField =
                        new JTextField(
                                selectedCar.getModel()
                        );

                JTextField yearField =
                        new JTextField(
                                String.valueOf(
                                        selectedCar.getYear()
                                )
                        );

                JTextField colourField =
                        new JTextField(
                                selectedCar.getColour()
                        );

                carIDField.setEditable(false);
                customerIDField.setEditable(false);
                customerNameField.setEditable(false);

                JPanel updateCarPanel =
                        new JPanel(
                                new GridLayout(8, 2, 10, 10)
                        );

                updateCarPanel.add(new JLabel("Car ID:"));
                updateCarPanel.add(carIDField);

                updateCarPanel.add(
                        new JLabel("Customer ID:")
                );
                updateCarPanel.add(customerIDField);

                updateCarPanel.add(
                        new JLabel("Customer Name:")
                );
                updateCarPanel.add(customerNameField);

                updateCarPanel.add(
                        new JLabel("Registration Number:")
                );
                updateCarPanel.add(
                        registrationNumberField
                );

                updateCarPanel.add(new JLabel("Brand:"));
                updateCarPanel.add(brandField);

                updateCarPanel.add(new JLabel("Model:"));
                updateCarPanel.add(modelField);

                updateCarPanel.add(new JLabel("Year:"));
                updateCarPanel.add(yearField);

                updateCarPanel.add(new JLabel("Colour:"));
                updateCarPanel.add(colourField);

                int result = JOptionPane.showConfirmDialog(
                        x,
                        updateCarPanel,
                        "Update Car",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result != JOptionPane.OK_OPTION) {
                    return;
                }

                String registrationNumber =
                        registrationNumberField
                                .getText()
                                .trim();

                String brand =
                        brandField.getText().trim();

                String model =
                        modelField.getText().trim();

                String yearInput =
                        yearField.getText().trim();

                String colour =
                        colourField.getText().trim();

                if (registrationNumber.isEmpty()
                        || brand.isEmpty()
                        || model.isEmpty()
                        || yearInput.isEmpty()
                        || colour.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Please complete all fields!"
                    );

                    return;
                }

                int year;

                try {

                    year = Integer.parseInt(yearInput);

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Car year must be a valid number!"
                    );

                    return;
                }

                if (year <= 0) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Please enter a valid car year!"
                    );

                    return;
                }

                counterStaff.updateCarDetails(
                        selectedCar,
                        registrationNumber,
                        brand,
                        model,
                        year,
                        colour
                );

                JOptionPane.showMessageDialog(
                        x,
                        "Car updated successfully!"
                        + "\n\nCar ID: "
                        + selectedCar.getCarID()
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        x,
                        "Unable to update the car!"
                );
            }
            
        } else if (e.getSource() == deleteCar) {

            try {

                String keyword =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter Car ID, registration number, "
                                + "Customer ID or customer name:"
                        );

                if (keyword == null
                        || keyword.trim().isEmpty()) {

                    throw new Exception();
                }

                keyword = keyword.trim();

                String carResults = "";
                int numberOfMatches = 0;
                Car selectedCar = null;

                for (int i = 0;
                        i < DataIO.allCars.size();
                        i++) {

                    Car car =
                            DataIO.allCars.get(i);

                    Customer owner =
                            DataIO.checkCustomerID(
                                    car.getCustomerID()
                            );

                    String ownerName = "Unknown";

                    if (owner != null) {
                        ownerName = owner.getName();
                    }

                    if (car.getCarID()
                                .equalsIgnoreCase(keyword)
                            || car.getRegistrationNumber()
                                .toLowerCase()
                                .contains(keyword.toLowerCase())
                            || car.getCustomerID()
                                .equalsIgnoreCase(keyword)
                            || ownerName
                                .toLowerCase()
                                .contains(keyword.toLowerCase())) {

                        numberOfMatches++;

                        selectedCar = car;

                        carResults +=
                                "Car ID: "
                                + car.getCarID()
                                + "\nCustomer ID: "
                                + car.getCustomerID()
                                + "\nCustomer name: "
                                + ownerName
                                + "\nRegistration number: "
                                + car.getRegistrationNumber()
                                + "\nBrand: "
                                + car.getBrand()
                                + "\nModel: "
                                + car.getModel()
                                + "\n------------------------------\n";
                    }
                }

                if (numberOfMatches == 0) {

                    JOptionPane.showMessageDialog(
                            x,
                            "No matching car found!"
                    );

                    return;
                }

                if (numberOfMatches > 1) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Matching Cars:\n\n"
                            + carResults
                    );

                    String selectedCarID =
                            JOptionPane.showInputDialog(
                                    x,
                                    "Enter the Car ID you want to delete:"
                            );

                    if (selectedCarID == null
                            || selectedCarID.trim().isEmpty()) {

                        throw new Exception();
                    }

                    selectedCar = DataIO.checkCarID(
                            selectedCarID.trim()
                    );

                    if (selectedCar == null) {

                        JOptionPane.showMessageDialog(
                                x,
                                "Car not found!"
                        );

                        return;
                    }
                }

                Customer owner =
                        DataIO.checkCustomerID(
                                selectedCar.getCustomerID()
                        );

                String ownerName = "Unknown";

                if (owner != null) {
                    ownerName = owner.getName();
                }

                String confirmation =
                        JOptionPane.showInputDialog(
                                x,
                                "Car ID: "
                                + selectedCar.getCarID()
                                + "\nCustomer ID: "
                                + selectedCar.getCustomerID()
                                + "\nCustomer name: "
                                + ownerName
                                + "\nRegistration number: "
                                + selectedCar.getRegistrationNumber()
                                + "\nBrand: "
                                + selectedCar.getBrand()
                                + "\nModel: "
                                + selectedCar.getModel()
                                + "\n\nType YES to delete this car:"
                        );

                if (confirmation == null
                        || !confirmation.equalsIgnoreCase("YES")) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Deletion cancelled."
                    );

                } else {

                    Car deletedCar =
                            counterStaff.deleteCar(
                                    selectedCar.getCarID()
                            );

                    if (deletedCar == null) {

                        JOptionPane.showMessageDialog(
                                x,
                                "This car cannot be deleted"
                                + "\nbecause it has appointment history."
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                x,
                                "Car deleted successfully!"
                        );
                    }
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

    public CarManagementGUI(
            CounterStaff counterStaff) {

        this.counterStaff = counterStaff;

        x = new JFrame();

        x.setTitle("Car Management");
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
                "Car Management"
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
                        "Select a Car Function"
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
                new GridLayout(2, 1, 10, 10)
        );

        buttonArea.setBackground(Color.white);

        // First row
        JPanel firstRow = new JPanel();

        firstRow.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        10
                )
        );

        firstRow.setBackground(Color.white);

        addCar = new JButton("Add Car");
        viewCars = new JButton("View Cars");
        searchCar = new JButton("Search Car");

        // Second row
        JPanel secondRow = new JPanel();

        secondRow.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        10
                )
        );

        secondRow.setBackground(Color.white);

        updateCar = new JButton("Update Car");
        deleteCar = new JButton("Delete Car");

        Font buttonFont = new Font(
                "SansSerif",
                Font.BOLD,
                14
        );

        addCar.setFont(buttonFont);
        viewCars.setFont(buttonFont);
        searchCar.setFont(buttonFont);
        updateCar.setFont(buttonFont);
        deleteCar.setFont(buttonFont);

        addCar.setVerticalAlignment(
                JButton.CENTER
        );

        viewCars.setVerticalAlignment(
                JButton.CENTER
        );

        searchCar.setVerticalAlignment(
                JButton.CENTER
        );

        updateCar.setVerticalAlignment(
                JButton.CENTER
        );

        deleteCar.setVerticalAlignment(
                JButton.CENTER
        );

        firstRow.add(addCar);
        firstRow.add(viewCars);
        firstRow.add(searchCar);

        secondRow.add(updateCar);
        secondRow.add(deleteCar);

        buttonArea.add(firstRow);
        buttonArea.add(secondRow);

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
        addCar.addActionListener(this);
        viewCars.addActionListener(this);
        searchCar.addActionListener(this);
        updateCar.addActionListener(this);
        deleteCar.addActionListener(this);
        back.addActionListener(this);

        // Add all sections
        x.add("North", headerPanel);
        x.add("Center", mainPanel);
        x.add("South", footerPanel);

        x.setVisible(true);
    }
}
