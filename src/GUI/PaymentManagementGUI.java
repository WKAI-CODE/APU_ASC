package GUI;

import apu_asc.model.CounterStaff;
import apu_asc.model.Appointment;
import apu_asc.model.Car;
import apu_asc.model.Customer;
import apu_asc.model.Payment;
import apu_asc.model.Receipt;
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

public class PaymentManagementGUI
        implements ActionListener {

    CounterStaff counterStaff;

    JFrame x;

    JLabel title;

    JButton collectPayment;
    JButton viewPayments;
    JButton viewReceipts;
    JButton back;
    
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == collectPayment) {

            try {

                String eligibleAppointments = "";
                int eligibleCount = 0;

                // Find appointments that can be paid
                for (int i = 0;
                        i < DataIO.allAppointments.size();
                        i++) {

                    Appointment appointment =
                            DataIO.allAppointments.get(i);

                    if ("COMPLETED".equalsIgnoreCase(
                                appointment.getAppointmentStatus())
                            && "UNPAID".equalsIgnoreCase(
                                appointment.getPaymentStatus())) {

                        eligibleCount++;

                        Customer customer =
                                DataIO.checkCustomerID(
                                        appointment.getCustomerID()
                                );

                        Car car =
                                DataIO.checkCarID(
                                        appointment.getCarID()
                                );

                        String customerName = "Unknown";
                        String registrationNumber = "Unknown";

                        if (customer != null) {
                            customerName = customer.getName();
                        }

                        if (car != null) {
                            registrationNumber =
                                    car.getRegistrationNumber();
                        }

                        eligibleAppointments +=
                                "Appointment ID: "
                                + appointment.getAppointmentID()
                                + "\nCustomer ID: "
                                + appointment.getCustomerID()
                                + "\nCustomer name: "
                                + customerName
                                + "\nCar registration: "
                                + registrationNumber
                                + "\nService type: "
                                + appointment.getServiceType()
                                + "\nAmount: RM "
                                + String.format(
                                        "%.2f",
                                        appointment.getServicePrice()
                                )
                                + "\n------------------------------\n";
                    }
                }

                if (eligibleCount == 0) {

                    JOptionPane.showMessageDialog(
                            x,
                            "There are no completed and "
                            + "unpaid appointments."
                    );

                    return;
                }

                JOptionPane.showMessageDialog(
                        x,
                        "Appointments Ready for Payment:\n\n"
                        + eligibleAppointments
                );

                String appointmentID =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter the Appointment ID "
                                + "for payment:"
                        );

                if (appointmentID == null
                        || appointmentID.trim().isEmpty()) {

                    throw new Exception();
                }

                appointmentID = appointmentID.trim();

                Appointment selectedAppointment =
                        DataIO.checkAppointmentID(
                                appointmentID
                        );

                if (selectedAppointment == null) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Appointment not found!"
                    );

                    return;
                }

                if (!"COMPLETED".equalsIgnoreCase(
                        selectedAppointment
                                .getAppointmentStatus())) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Payment can only be collected "
                            + "after the appointment is completed!"
                    );

                    return;
                }

                if (!"UNPAID".equalsIgnoreCase(
                        selectedAppointment
                                .getPaymentStatus())) {

                    JOptionPane.showMessageDialog(
                            x,
                            "This appointment has already been paid!"
                    );

                    return;
                }

                Customer customer =
                        DataIO.checkCustomerID(
                                selectedAppointment
                                        .getCustomerID()
                        );

                String customerName = "Unknown";

                if (customer != null) {
                    customerName = customer.getName();
                }

                String paymentMethod =
                        JOptionPane.showInputDialog(
                                x,
                                "Enter payment method:"
                                + "\nCASH"
                                + "\nCARD"
                                + "\nONLINE"
                        );

                if (paymentMethod == null
                        || paymentMethod.trim().isEmpty()) {

                    throw new Exception();
                }

                paymentMethod =
                        paymentMethod.trim().toUpperCase();

                if (!paymentMethod.equals("CASH")
                        && !paymentMethod.equals("CARD")
                        && !paymentMethod.equals("ONLINE")) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Payment method must be "
                            + "CASH, CARD or ONLINE!"
                    );

                    return;
                }

                String confirmation =
                        JOptionPane.showInputDialog(
                                x,
                                "Confirm Payment"
                                + "\n\nAppointment ID: "
                                + selectedAppointment
                                        .getAppointmentID()
                                + "\nCustomer: "
                                + customerName
                                + "\nAmount: RM "
                                + String.format(
                                        "%.2f",
                                        selectedAppointment
                                                .getServicePrice()
                                )
                                + "\nPayment method: "
                                + paymentMethod
                                + "\n\nType YES to confirm:"
                        );

                if (confirmation == null
                        || !confirmation
                                .equalsIgnoreCase("YES")) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Payment cancelled."
                    );

                    return;
                }

                String paymentID =
                        counterStaff.generatePaymentID();

                String paymentDate =
                        counterStaff.getCurrentDate();

                Payment newPayment =
                        counterStaff.collectPayment(
                                appointmentID,
                                paymentID,
                                paymentMethod,
                                paymentDate
                        );

                if (newPayment == null) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Unable to collect payment!"
                    );

                    return;
                }

                // Automatically generate the receipt
                String receiptID =
                        counterStaff.generateReceiptID();

                Receipt newReceipt =
                        counterStaff.generateReceipt(
                                newPayment.getPaymentID(),
                                receiptID,
                                selectedAppointment
                                        .getCustomerID(),
                                paymentDate
                        );

                if (newReceipt == null) {

                    JOptionPane.showMessageDialog(
                            x,
                            "Payment collected successfully,"
                            + "\nbut the receipt could not "
                            + "be generated."
                            + "\n\nPayment ID: "
                            + newPayment.getPaymentID()
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            x,
                            "Payment collected successfully!"
                            + "\n\nRECEIPT"
                            + "\n------------------------------"
                            + "\nReceipt ID: "
                            + newReceipt.getReceiptID()
                            + "\nPayment ID: "
                            + newReceipt.getPaymentID()
                            + "\nAppointment ID: "
                            + newReceipt.getAppointmentID()
                            + "\nCustomer ID: "
                            + newReceipt.getCustomerID()
                            + "\nCustomer name: "
                            + customerName
                            + "\nAmount: RM "
                            + String.format(
                                    "%.2f",
                                    newReceipt.getAmount()
                            )
                            + "\nPayment method: "
                            + newPayment.getPaymentMethod()
                            + "\nReceipt date: "
                            + newReceipt.getReceiptDate()
                            + "\n------------------------------"
                    );
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        x,
                        "Invalid input!"
                );
            }
            
        } else if (e.getSource() == viewPayments) {

            if (DataIO.allPayments.isEmpty()) {

                JOptionPane.showMessageDialog(
                        x,
                        "No payment records found!"
                );

            } else {

                String paymentDetails =
                        "Total Payments: "
                        + DataIO.allPayments.size()
                        + "\n\n";

                for (int i = 0;
                        i < DataIO.allPayments.size();
                        i++) {

                    Payment payment =
                            DataIO.allPayments.get(i);

                    Appointment appointment =
                            DataIO.checkAppointmentID(
                                    payment.getAppointmentID()
                            );

                    String customerID = "Unknown";
                    String customerName = "Unknown";
                    String serviceType = "Unknown";

                    if (appointment != null) {

                        customerID =
                                appointment.getCustomerID();

                        serviceType =
                                appointment.getServiceType();

                        Customer customer =
                                DataIO.checkCustomerID(
                                        customerID
                                );

                        if (customer != null) {
                            customerName =
                                    customer.getName();
                        }
                    }

                    paymentDetails +=
                            "Payment ID: "
                            + payment.getPaymentID()
                            + "\nAppointment ID: "
                            + payment.getAppointmentID()
                            + "\nCustomer ID: "
                            + customerID
                            + "\nCustomer name: "
                            + customerName
                            + "\nService type: "
                            + serviceType
                            + "\nAmount: RM "
                            + String.format(
                                    "%.2f",
                                    payment.getAmount()
                            )
                            + "\nPayment method: "
                            + payment.getPaymentMethod()
                            + "\nPayment date: "
                            + payment.getPaymentDate()
                            + "\nCollected by Counter Staff: "
                            + payment.getCounterStaffID()
                            + "\n------------------------------\n";
                }

                showScrollableResults(
                        "Payment Records",
                        paymentDetails
                );
            }
            
        } else if (e.getSource() == viewReceipts) {

            if (DataIO.allReceipts.isEmpty()) {
                JOptionPane.showMessageDialog(x, "No receipt records found!");
            } else {
                String receiptDetails =
                        "Total Receipts: " + DataIO.allReceipts.size() + "\n\n";

                for (int i = 0; i < DataIO.allReceipts.size(); i++) {
                    Receipt receipt = DataIO.allReceipts.get(i);

                    Payment payment =
                            DataIO.checkPaymentID(receipt.getPaymentID());

                    Customer customer =
                            DataIO.checkCustomerID(receipt.getCustomerID());

                    String customerName = "Unknown";
                    String paymentMethod = "Unknown";

                    if (customer != null) {
                        customerName = customer.getName();
                    }

                    if (payment != null) {
                        paymentMethod = payment.getPaymentMethod();
                    }

                    receiptDetails +=
                            "Receipt ID: " + receipt.getReceiptID()
                            + "\nPayment ID: " + receipt.getPaymentID()
                            + "\nAppointment ID: " + receipt.getAppointmentID()
                            + "\nCustomer ID: " + receipt.getCustomerID()
                            + "\nCustomer name: " + customerName
                            + "\nAmount: RM "
                            + String.format("%.2f", receipt.getAmount())
                            + "\nPayment method: " + paymentMethod
                            + "\nReceipt date: " + receipt.getReceiptDate()
                            + "\n------------------------------\n";
                }

                showScrollableResults(
                        "Receipt Records",
                        receiptDetails
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

    public PaymentManagementGUI(CounterStaff counterStaff) {

        this.counterStaff = counterStaff;

        x = new JFrame();

        x.setTitle("Payment Management");
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
                "Payment Management"
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
                        "Select a Payment Function"
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

        collectPayment =
                new JButton("Collect Payment");

        viewPayments =
                new JButton("View Payments");

        viewReceipts =
                new JButton("View Receipts");

        Font buttonFont = new Font(
                "SansSerif",
                Font.BOLD,
                14
        );

        collectPayment.setFont(buttonFont);
        viewPayments.setFont(buttonFont);
        viewReceipts.setFont(buttonFont);

        collectPayment.setVerticalAlignment(
                JButton.CENTER
        );

        viewPayments.setVerticalAlignment(
                JButton.CENTER
        );

        viewReceipts.setVerticalAlignment(
                JButton.CENTER
        );

        firstRow.add(collectPayment);
        firstRow.add(viewPayments);
        firstRow.add(viewReceipts);

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
        collectPayment.addActionListener(this);
        viewPayments.addActionListener(this);
        viewReceipts.addActionListener(this);
        back.addActionListener(this);

        // Add all sections
        x.add("North", headerPanel);
        x.add("Center", mainPanel);
        x.add("South", footerPanel);

        x.setVisible(true);

    }
}
