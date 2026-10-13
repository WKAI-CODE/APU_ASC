package apu_asc.model;

public class Payment {

    private String paymentID;
    private String appointmentID;
    private double amount;
    private String paymentMethod;
    private String paymentDate;
    private String counterStaffID;

    // Constructor
    public Payment(
            String paymentID,
            String appointmentID,
            double amount,
            String paymentMethod,
            String paymentDate,
            String counterStaffID) {

        this.paymentID = paymentID;
        this.appointmentID = appointmentID;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.counterStaffID = counterStaffID;
    }

    // Getters
    public String getPaymentID() {
        return paymentID;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getCounterStaffID() {
        return counterStaffID;
    }
}

