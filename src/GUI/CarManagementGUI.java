package GUI;

import apu_asc.model.Car;
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

public class CarManagementGUI
        implements ActionListener {

    CounterStaff counterStaff;

    JFrame x;

    Label title;

    Button addCar;
    Button viewCars;
    Button searchCar;
    Button updateCar;
    Button deleteCar;
    Button back;

    public CarManagementGUI(
            CounterStaff counterStaff) {

        this.counterStaff = counterStaff;

        x = new JFrame();

        x.setTitle("Car Management");
        x.setSize(500, 200);
        x.setLocation(550, 300);
        x.setLayout(new FlowLayout());

        title = new Label(
                "Car Management",
                Label.CENTER
        );

        addCar = new Button("Add Car");
        viewCars = new Button("View Cars");
        searchCar = new Button("Search Car");
        updateCar = new Button("Update Car");
        deleteCar = new Button("Delete Car");
        back = new Button("Back");

        addCar.addActionListener(this);
        viewCars.addActionListener(this);
        searchCar.addActionListener(this);
        updateCar.addActionListener(this);
        deleteCar.addActionListener(this);
        back.addActionListener(this);

        x.add(title);
        x.add(addCar);
        x.add(viewCars);
        x.add(searchCar);
        x.add(updateCar);
        x.add(deleteCar);
        x.add(back);

        x.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addCar) {

            try {

                // Search for the Customer
                String keyword =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter Customer ID, username, "
                                + "name or phone number:"
                        );

                if (keyword == null
                        || keyword.trim().isEmpty()) {

                    throw new Exception();
                }

                keyword = keyword.trim();

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
                                .contains(
                                        keyword.toLowerCase()
                                )
                            || customer.getName()
                                .toLowerCase()
                                .contains(
                                        keyword.toLowerCase()
                                )
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

                // No matching Customer
                if (numberOfMatches == 0) {

                    JOptionPane.showMessageDialog(
                            x,
                            "No matching Customer found!"
                    );

                    return;
                }

                // Several matching Customers
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

                    if (selectedCustomerID == null
                            || selectedCustomerID
                                    .trim()
                                    .isEmpty()) {

                        throw new Exception();
                    }

                    owner = DataIO.checkCustomerID(
                            selectedCustomerID.trim()
                    );

                    if (owner == null) {

                        JOptionPane.showMessageDialog(
                                x,
                                "Customer not found!"
                        );

                        return;
                    }
                }

                String customerID =
                        owner.getUserID();

                JOptionPane.showMessageDialog(
                        x,
                        "Selected Customer"
                        + "\n\nCustomer ID: "
                        + owner.getUserID()
                        + "\nName: "
                        + owner.getName()
                        + "\nPhone Number: "
                        + owner.getPhoneNumber()
                );

                // Enter the car information
                String registrationNumber =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter car registration number:"
                        );

                String brand =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter car brand:"
                        );

                String model =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter car model:"
                        );

                String yearInput =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter car year:"
                        );

                String colour =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter car colour:"
                        );

                // Check whether any input is empty
                if (registrationNumber == null
                        || registrationNumber
                                .trim()
                                .isEmpty()
                        || brand == null
                        || brand.trim().isEmpty()
                        || model == null
                        || model.trim().isEmpty()
                        || yearInput == null
                        || yearInput.trim().isEmpty()
                        || colour == null
                        || colour.trim().isEmpty()) {

                    throw new Exception();
                }

                // Convert the year to an integer
                int year = Integer.parseInt(
                        yearInput.trim()
                );

                if (year <= 0) {
                    throw new Exception();
                }

                // Automatically generate the Car ID
                String carID =
                        counterStaff.generateCarID();

                // Create and save the new car
                Car newCar = counterStaff.addCar(
                        carID,
                        customerID,
                        registrationNumber.trim(),
                        brand.trim(),
                        model.trim(),
                        year,
                        colour.trim()
                );

                if (newCar == null) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Unable to register the car!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            x,
                            "Car registered successfully!"
                            + "\n\nCar ID: "
                            + newCar.getCarID()
                            + "\nCustomer ID: "
                            + newCar.getCustomerID()
                            + "\nRegistration number: "
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
                        "Invalid input!"
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

                JOptionPane.showMessageDialog(
                        x,
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

                    JOptionPane.showMessageDialog(
                            x,
                            "Matching Cars:\n\n"
                            + searchResults
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
                                    "Enter the Car ID you want to update:"
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

                JOptionPane.showMessageDialog(
                        x,
                        "Current Car Details"
                        + "\n\nCar ID: "
                        + selectedCar.getCarID()
                        + "\nCustomer ID: "
                        + selectedCar.getCustomerID()
                        + "\nRegistration number: "
                        + selectedCar.getRegistrationNumber()
                        + "\nBrand: "
                        + selectedCar.getBrand()
                        + "\nModel: "
                        + selectedCar.getModel()
                        + "\nYear: "
                        + selectedCar.getYear()
                        + "\nColour: "
                        + selectedCar.getColour()
                );

                String registrationNumber =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter new registration number:",
                                selectedCar.getRegistrationNumber()
                        );

                String brand =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter new brand:",
                                selectedCar.getBrand()
                        );

                String model =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter new model:",
                                selectedCar.getModel()
                        );

                String yearInput =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter new year:",
                                String.valueOf(
                                        selectedCar.getYear()
                                )
                        );

                String colour =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter new colour:",
                                selectedCar.getColour()
                        );

                if (registrationNumber == null
                        || registrationNumber.trim().isEmpty()
                        || brand == null
                        || brand.trim().isEmpty()
                        || model == null
                        || model.trim().isEmpty()
                        || yearInput == null
                        || yearInput.trim().isEmpty()
                        || colour == null
                        || colour.trim().isEmpty()) {

                    throw new Exception();
                }

                int year = Integer.parseInt(
                        yearInput.trim()
                );

                if (year <= 0) {
                    throw new Exception();
                }

                counterStaff.updateCarDetails(
                        selectedCar,
                        registrationNumber.trim(),
                        brand.trim(),
                        model.trim(),
                        year,
                        colour.trim()
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
                        "Invalid input!"
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
}