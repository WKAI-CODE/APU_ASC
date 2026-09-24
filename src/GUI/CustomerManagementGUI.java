package GUI;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CustomerManagementGUI
        implements ActionListener {

    CounterStaff counterStaff;

    JFrame x;

    JLabel title;

    JButton addCustomer;
    JButton viewCustomers;
    JButton searchCustomer;
    JButton updateCustomer;
    JButton deleteCustomer;
    JButton back;

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addCustomer) {

            JTextField nameField =
                    new JTextField(15);

            JTextField phoneNumberField =
                    new JTextField(15);

            JPanel addCustomerPanel =
                    new JPanel(
                            new GridLayout(
                                    2,
                                    2,
                                    10,
                                    10
                            )
                    );

            addCustomerPanel.add(
                    new JLabel("Customer Name:")
            );

            addCustomerPanel.add(nameField);

            addCustomerPanel.add(
                    new JLabel("Phone Number:")
            );

            addCustomerPanel.add(
                    phoneNumberField
            );

            int result =
                    JOptionPane.showConfirmDialog(
                            x,
                            addCustomerPanel,
                            "Add New Customer",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE
                    );

            if (result
                    == JOptionPane.OK_OPTION) {

                String name =
                        nameField.getText().trim();

                String phoneNumber =
                        phoneNumberField
                                .getText()
                                .trim();

                if (name.isEmpty()
                        || phoneNumber.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Please complete all fields!"
                    );

                } else if (!phoneNumber.matches(
                        "^(01)[0-9]{8,9}$")) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Invalid phone number.\n"
                            + "Example: 0123456789"
                    );

                } else {

                    String userID =
                            counterStaff
                                    .generateCustomerID();

                    String username = userID;

                    String password =
                            userID + "@123";

                    Customer newCustomer =
                            counterStaff.addCustomer(
                                    userID,
                                    username,
                                    password,
                                    name,
                                    phoneNumber
                            );

                    if (newCustomer == null) {

                        JOptionPane.showMessageDialog(
                                x,
                                "Unable to create "
                                + "Customer account!"
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                x,
                                "Customer added successfully!"
                                + "\n\nCustomer ID: "
                                + userID
                                + "\nDefault username: "
                                + username
                                + "\nDefault password: "
                                + password
                        );
                    }
                }
            }

        } else if (e.getSource()
                == viewCustomers) {

            if (DataIO.allCustomers.isEmpty()) {

                JOptionPane.showMessageDialog(
                        x,
                        "No Customer records found!"
                );

            } else {

                String customerDetails =
                        "Total Customers: "
                        + DataIO.allCustomers.size()
                        + "\n\n";

                for (int i = 0;
                        i < DataIO.allCustomers.size();
                        i++) {

                    Customer customer =
                            DataIO.allCustomers.get(i);

                    customerDetails +=
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

                showScrollableResults(
                        "Customer Records",
                        customerDetails
                );
            }

        } else if (e.getSource()
                == searchCustomer) {

            try {

                String keyword =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter Customer ID, "
                                + "username, name or "
                                + "phone number:"
                        );

                if (keyword == null
                        || keyword.trim().isEmpty()) {

                    throw new Exception();
                }

                keyword = keyword.trim();

                String searchResults = "";

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

                        searchResults +=
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

                if (searchResults.length() == 0) {

                    JOptionPane.showMessageDialog(
                            x,
                            "No matching Customer found!"
                    );

                } else {

                    showScrollableResults(
                            "Matching Customers",
                            searchResults
                    );
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        x,
                        "Invalid input!"
                );
            }

        } else if (e.getSource()
                == updateCustomer) {

            String customerID =
                    JOptionPane.showInputDialog(
                            x,
                            "Enter Customer ID:"
                    );

            if (customerID == null) {
                return;
            }

            customerID = customerID.trim();

            if (customerID.isEmpty()) {

                JOptionPane.showMessageDialog(
                        x,
                        "Please enter a Customer ID!"
                );

                return;
            }

            Customer customer =
                    DataIO.checkCustomerID(
                            customerID
                    );

            if (customer == null) {

                JOptionPane.showMessageDialog(
                        x,
                        "Customer not found!"
                );

            } else {

                JTextField customerIDField =
                        new JTextField(
                                customer.getUserID()
                        );

                JTextField usernameField =
                        new JTextField(
                                customer.getUsername()
                        );

                JTextField nameField =
                        new JTextField(
                                customer.getName()
                        );

                JTextField phoneNumberField =
                        new JTextField(
                                customer.getPhoneNumber()
                        );

                customerIDField.setEditable(false);

                JPanel updateCustomerPanel =
                        new JPanel(
                                new GridLayout(
                                        4,
                                        2,
                                        10,
                                        10
                                )
                        );

                updateCustomerPanel.add(
                        new JLabel("Customer ID:")
                );

                updateCustomerPanel.add(
                        customerIDField
                );

                updateCustomerPanel.add(
                        new JLabel("Username:")
                );

                updateCustomerPanel.add(
                        usernameField
                );

                updateCustomerPanel.add(
                        new JLabel("Customer Name:")
                );

                updateCustomerPanel.add(nameField);

                updateCustomerPanel.add(
                        new JLabel("Phone Number:")
                );

                updateCustomerPanel.add(
                        phoneNumberField
                );

                int result =
                        JOptionPane.showConfirmDialog(
                                x,
                                updateCustomerPanel,
                                "Update Customer",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE
                        );

                if (result
                        == JOptionPane.OK_OPTION) {

                    String newUsername =
                            usernameField
                                    .getText()
                                    .trim();

                    String newName =
                            nameField
                                    .getText()
                                    .trim();

                    String newPhoneNumber =
                            phoneNumberField
                                    .getText()
                                    .trim();

                    if (newUsername.isEmpty()
                            || newName.isEmpty()
                            || newPhoneNumber.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                x,
                                "Please complete all fields!"
                        );

                    } else if (!newPhoneNumber.matches(
                            "^(01)[0-9]{8,9}$")) {

                        JOptionPane.showMessageDialog(
                                x,
                                "Invalid phone number.\n"
                                + "Example: 0123456789"
                        );

                    } else {

                        Customer updatedCustomer =
                                counterStaff
                                        .updateCustomerDetails(
                                                customer,
                                                newUsername,
                                                customer
                                                        .getPassword(),
                                                newName,
                                                newPhoneNumber
                                        );

                        if (updatedCustomer == null) {

                            JOptionPane.showMessageDialog(
                                    x,
                                    "Username is already "
                                    + "being used!"
                            );

                        } else {

                            JOptionPane.showMessageDialog(
                                    x,
                                    "Customer updated "
                                    + "successfully!"
                            );
                        }
                    }
                }
            }

        } else if (e.getSource()
                == deleteCustomer) {

            String customerID =
                    JOptionPane.showInputDialog(
                            x,
                            "Enter Customer ID:"
                    );

            if (customerID == null) {
                return;
            }

            customerID = customerID.trim();

            if (customerID.isEmpty()) {

                JOptionPane.showMessageDialog(
                        x,
                        "Please enter a Customer ID!"
                );

                return;
            }

            Customer customer =
                    DataIO.checkCustomerID(
                            customerID
                    );

            if (customer == null) {

                JOptionPane.showMessageDialog(
                        x,
                        "Customer not found!"
                );

            } else {

                JPanel deleteCustomerPanel =
                        new JPanel(
                                new GridLayout(
                                        4,
                                        2,
                                        10,
                                        10
                                )
                        );

                deleteCustomerPanel.add(
                        new JLabel("Customer ID:")
                );

                deleteCustomerPanel.add(
                        new JLabel(
                                customer.getUserID()
                        )
                );

                deleteCustomerPanel.add(
                        new JLabel("Username:")
                );

                deleteCustomerPanel.add(
                        new JLabel(
                                customer.getUsername()
                        )
                );

                deleteCustomerPanel.add(
                        new JLabel("Customer Name:")
                );

                deleteCustomerPanel.add(
                        new JLabel(
                                customer.getName()
                        )
                );

                deleteCustomerPanel.add(
                        new JLabel("Phone Number:")
                );

                deleteCustomerPanel.add(
                        new JLabel(
                                customer.getPhoneNumber()
                        )
                );

                int confirmation =
                        JOptionPane.showConfirmDialog(
                                x,
                                deleteCustomerPanel,
                                "Confirm Customer Deletion",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE
                        );

                if (confirmation
                        == JOptionPane.YES_OPTION) {

                    Customer deletedCustomer =
                            counterStaff.deleteCustomer(
                                    customer.getUserID()
                            );

                    if (deletedCustomer == null) {

                        JOptionPane.showMessageDialog(
                                x,
                                "This Customer cannot "
                                + "be deleted because the "
                                + "Customer still owns a car."
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                x,
                                "Customer deleted successfully!"
                        );
                    }
                }
            }

        } else if (e.getSource() == back) {

            new CounterStaffDashboardGUI(
                    counterStaff
            );

            x.setVisible(false);
        }
    }

    private void showScrollableResults(
            String windowTitle,
            String results) {

        JTextArea resultArea =
                new JTextArea(
                        results,
                        18,
                        45
                );

        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setCaretPosition(0);

        JScrollPane scrollPane =
                new JScrollPane(resultArea);

        JOptionPane.showMessageDialog(
                x,
                scrollPane,
                windowTitle,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public CustomerManagementGUI(
            CounterStaff counterStaff) {

        this.counterStaff = counterStaff;

        x = new JFrame();

        x.setTitle("Customer Management");
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
                "Customer Management"
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
                        "Select a Customer Function"
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

        addCustomer =
                new JButton("Add Customer");

        viewCustomers =
                new JButton("View Customers");

        searchCustomer =
                new JButton("Search Customer");

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

        updateCustomer =
                new JButton("Update Customer");

        deleteCustomer =
                new JButton("Delete Customer");

        Font buttonFont = new Font(
                "SansSerif",
                Font.BOLD,
                14
        );

        addCustomer.setFont(buttonFont);
        viewCustomers.setFont(buttonFont);
        searchCustomer.setFont(buttonFont);
        updateCustomer.setFont(buttonFont);
        deleteCustomer.setFont(buttonFont);

        addCustomer.setVerticalAlignment(
                JButton.CENTER
        );

        viewCustomers.setVerticalAlignment(
                JButton.CENTER
        );

        searchCustomer.setVerticalAlignment(
                JButton.CENTER
        );

        updateCustomer.setVerticalAlignment(
                JButton.CENTER
        );

        deleteCustomer.setVerticalAlignment(
                JButton.CENTER
        );

        firstRow.add(addCustomer);
        firstRow.add(viewCustomers);
        firstRow.add(searchCustomer);

        secondRow.add(updateCustomer);
        secondRow.add(deleteCustomer);

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
        addCustomer.addActionListener(this);
        viewCustomers.addActionListener(this);
        searchCustomer.addActionListener(this);
        updateCustomer.addActionListener(this);
        deleteCustomer.addActionListener(this);
        back.addActionListener(this);

        // Add all sections
        x.add("North", headerPanel);
        x.add("Center", mainPanel);
        x.add("South", footerPanel);

        x.setVisible(true);
    }
}
