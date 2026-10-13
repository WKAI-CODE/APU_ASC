package apu_asc.model;

public class Appointment {
    
    private String appointmentID;
    private String customerID;
    private String carID;
    private String technicianID;
    private String counterStaffID;
    private String serviceType;
    private String date;
    private String startTime;
    private String endTime;
    private double servicePrice;
    private String appointmentStatus;
    private String paymentStatus;

    public Appointment(
            String appointmentID, 
            String customerID, 
            String carID, 
            String technicianID, 
            String counterStaffID, 
            String serviceType, 
            String date, 
            String startTime, 
            String endTime, 
            double servicePrice, 
            String appointmentStatus, 
            String paymentStatus) {
        
        
        this.appointmentID = appointmentID;
        this.customerID = customerID;
        this.carID = carID;
        this.technicianID = technicianID;
        this.counterStaffID = counterStaffID;
        this.serviceType = serviceType;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.servicePrice = servicePrice;
        this.appointmentStatus = appointmentStatus;
        this.paymentStatus = paymentStatus;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCarID() {
        return carID;
    }

    public String getTechnicianID() {
        return technicianID;
    }

    public String getCounterStaffID() {
        return counterStaffID;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
    

    public void setAppointmentStatus(String appointmentStatus) {
        
        this.appointmentStatus = appointmentStatus;
    }
    
    public void setPaymentStatus(String paymentStatus) {
        
        this.paymentStatus = paymentStatus;
    }
    
}

