/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apu_asc.model;

public class Receipt {
    
    private String receiptID;
    private String paymentID;
    private String appointmentID;
    private String customerID;
    private double amount;
    private String receiptDate;

    public Receipt(
            String receiptID, 
            String paymentID, 
            String appointmentID, 
            String customerID, 
            double amount, 
            String receiptDate) {
        
        this.receiptID = receiptID;
        this.paymentID = paymentID;
        this.appointmentID = appointmentID;
        this.customerID = customerID;
        this.amount = amount;
        this.receiptDate = receiptDate;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public double getAmount() {
        return amount;
    }

    public String getReceiptDate() {
        return receiptDate;
    }
    
    
    
}
