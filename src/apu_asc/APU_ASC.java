/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apu_asc;
import apu_asc.model.CounterStaff;
import apu_asc.model.Customer;
import apu_asc.model.Car;
import apu_asc.model.Appointment;
import apu_asc.model.Payment;
import apu_asc.model.Receipt;

public class APU_ASC {

    public static void main(String[] args) {
        System.out.println("APU Automotive Service Centre");
        
        CounterStaff staff1 = new CounterStaff(
            "C5001",
            "counter01",
            "pass123",
            "Ali",
            "0123456789",
            25,
            "IC001",
            "ali@email.com",
            "Kuala Lumpur"
        );
    
        System.out.println("Counter Staff name: " + staff1.getName());
        
        Customer customer1 = staff1.createCustomer(
                "C001",
                "customer01",
                "pass123",
                "Aisha",
                "0198765432"
        );
        
        Customer[] customers = new Customer[5];
        customers[0] = customer1;

        Customer foundCustomer =
                staff1.findCustomer(customers, "C001");

        if (foundCustomer != null) {
            System.out.println("Customer found");
            System.out.println("Customer ID: "
                    + foundCustomer.getUserID());
            System.out.println("Name: "
                    + foundCustomer.getName());
            System.out.println("Phone: "
                    + foundCustomer.getPhoneNumber());
        } else {
            System.out.println("Customer not found");
        }
        
        Customer missingCustomer =
        staff1.findCustomer(customers, "C999");

        if (missingCustomer == null) {
            System.out.println("C999 does not exist");
}
            
        System.out.println("Created customer: " + customer1.getName());
        
        Car car1 = staff1.registerCar(
                "CAR001",
                "C001",
                "VAB1234",
                "Toyota",
                "Vios",
                2022,
                "White"
        );
        
        System.out.println("Registered car: " + car1.getBrand() + " " + car1.getModel());
        
        System.out.println("Car owner ID: " + "car1.getCustomerID()");
        
        staff1.updateCarDetails(
                car1,
                "VAB5678",
                "Toyota",
                "Vios",
                2022,
                "Black"
        );
        
        System.out.println("Updated registration: " + car1.getRegistrationNumber());
        
        System.out.println("Updated colour: " + car1.getColour());
        
        Appointment appointment1 = staff1.bookAppointment(
                "A001",
                "C001",
                "CAR001",
                "T001",
                "CS001",
                "MINOR",
                "2026-08-18",
                "09:00",
                "10:00",
                50.00
                
        );
        
        System.out.println("Appointment ID: " + appointment1.getAppointmentID());
        
        System.out.println("Service type: " + appointment1.getServiceType());
        
        System.out.println("Appointment status: " + appointment1.getAppointmentStatus());
        
        System.out.println("Payment status: " + appointment1.getPaymentStatus());
    
        boolean available1 = staff1.isTechnicianAvailable(
                appointment1,
                "T001",
                "2026-08-18",
                "09:30",
                "10:30"
        );
        
        System.out.println("Available for overlapping time: " + available1);
        
        boolean available2 = staff1.isTechnicianAvailable(
                appointment1,
                "T001",
                "2026-08-18",
                "10:00",
                "11:00"
        );
        
        System.out.println("Available after appointment: " + available2);
        
        Appointment appointment2 = staff1.bookAppointment(
                "A002",
                "C001",
                "CAR001",
                "T001",
                "CS001",
                "MINOR",
                "2026-08-18",
                "11:00",
                "12:00",
                50.00
        );
        
        Appointment[] appointments = new Appointment[3];
        
        appointments[0] = appointment1;
        appointments[1] = appointment2;
        
        boolean available3 = staff1.isTechnicianAvailableForAll(
                appointments,
                "T001",
                "2026-08-18",
                "10:30",
                "11:30"
        );
        
        System.out.println("Available from 10:30 to 11:30: " + available3);
        
        boolean available4 = staff1.isTechnicianAvailableForAll(
                appointments,
                "T001",
                "2026-08-18",
                "10:00",
                "11:00"
        );
        
        System.out.println("Available from 10:00 to 11:00: " + available4);
        
        Appointment appointment3 = staff1.bookAppointmentWithValidation(
                    appointments,
                    "A003",
                    "C001",
                    "CAR001",
                    "T001",
                    "CS001",
                    "MINOR",
                    "2026-08-18",
                    "10:00",
                    "11:00",
                    50.00
        );
        
        if (appointment3 != null) {
            appointments[2] = appointment3;
            
            System.out.println("Appointment A003 booked successfully.");
                    
        } else {
            System.out.println("Appointment A003 cannot be booked.");
        }
        
        boolean validDate1 = staff1.isValidBookingDate("2026-08-18");
        boolean validDate2 = staff1.isValidBookingDate("2026-08-16");
        boolean validDate3 = staff1.isValidBookingDate("2026-09-10");
        
        System.out.println("August 18: " + validDate1);
        System.out.println("August 16: " + validDate2);
        System.out.println("September 10: " + validDate3);
        
        boolean workingTime1 = staff1.isWithinWorkingHours("09:00", "10:00");
        
        boolean workingTime2 = staff1.isWithinWorkingHours("17:00", "19:00");
        
        boolean workingTime3 = staff1.isWithinWorkingHours("12:00", "11:00");
        
        System.out.println("09:00-10:00: " + workingTime1);
        System.out.println("17:00-19:00: " + workingTime2);
        System.out.println("12:00-11:00: " + workingTime3);
        
        appointment1.setAppointmentStatus("COMPLETED");
        
        Payment payment1 = staff1.collectPayment(
                appointment1,
                "P001",
                "CASH",
                "2026-08-18",
                "CS001"
        );
        
        if (payment1 != null) {
            
            System.out.println("Payment collected: RM" + payment1.getAmount());
            System.out.println("Payment status: " + appointment1.getPaymentStatus());
            
        } else {
            
            System.out.println("Payment cannot be collected.");
        }
        
        Receipt receipt1 = staff1.generateReceipt(
                payment1,
                appointment1,
                "R001",
                "C001",
                "2026-08-18");
        
        if(receipt1 != null) {
            System.out.println("Receipt generated successfully");
            System.out.println("Receipt ID: " + receipt1.getReceiptID());
            System.out.println("Payment ID: " + receipt1.getPaymentID());
            System.out.println("Appointment ID: " + receipt1.getAppointmentID());
            System.out.println("Customer ID: " + receipt1.getCustomerID());
            System.out.println("Amount: RM " + receipt1.getAmount());
            System.out.println("Receipt date: " + receipt1.getReceiptDate());
            
        } else {
            System.out.println("Receipt cannot be generated");
        }
        
        System.out.println("Before update:");
        System.out.println("Username: " + customer1.getUsername());
        System.out.println("Name: " + customer1.getName());
        System.out.println("Phone: " + customer1.getPhoneNumber());
        
        staff1.updateCustomerDetails(
                customer1,
                "aisha_new",
                "new123",
                "Aisha Tan",
                "0123456789");
        
        System.out.println("After update:");
        System.out.println("Username: " + customer1.getUsername());
        System.out.println("Name: " + customer1.getName());
        System.out.println("Phone: " + customer1.getPhoneNumber());
        
        Customer customer2 = staff1.createCustomer(
                "C002",
                "testcustomer",
                "test123",
                "Test Customer",
                "0111111111");
        
        customers[1] = customer2;
        
        boolean deleted = staff1.deleteCustomer(customers,"C002");
        
        if (deleted) {
            System.out.println("Customer C002 deleted successfully");
        } else {
            System.out.println("Customer C002 was not found");
        }
        
        Customer deletedCustomer = staff1.findCustomer(customers, "C002");
        
        if (deletedCustomer == null) {
            System.out.println("C002 no longer exists in the array");
        }
        
        Car[] cars = new Car[5];
        cars[0] = car1;
        
        Car foundCar = staff1.findCar(cars,"CAR001");
        
        if (foundCar != null) {
            System.out.println("Car found");
            System.out.println("Car ID: " + foundCar.getCarID());
            System.out.println("Registration number: " + foundCar.getRegistrationNumber());
            System.out.println("Brand: " + foundCar.getBrand());
            System.out.println("Model: " + foundCar.getModel());
            
        } else {
            System.out.println("Car not found");
        }
        
        Car car2 = staff1.registerCar(
        "CAR002",
        "C001",
        "ABC1234",
        "Honda",
        "City",
        2021,
        "Blue");

        cars[1] = car2;

        boolean carDeleted = staff1.deleteCar(cars, "CAR002");

        if (carDeleted) {
            System.out.println("Car CAR002 deleted successfully");
        } else {
            System.out.println("Car CAR002 was not found");
        }
        
        Car deletedCar = staff1.findCar(cars, "CAR002");

        if (deletedCar == null) {
            System.out.println("CAR002 no longer exists in the array");
        }
        
        staff1.editProfile(
                "counter_new",
                "newPassword123",
                "Ali Ahmad",
                "0122222222",
                25,
                "ali@apu.edu.my",
                "Kuala Lumpur");
        
        System.out.println("Counter Staff profile updated");
        System.out.println("User ID: " + staff1.getUserID());
        System.out.println("Username: " + staff1.getUsername());
        System.out.println("Name: " + staff1.getName());
        System.out.println("Phone: " + staff1.getPhoneNumber());
        System.out.println("Age: " + staff1.getAge());
        System.out.println("Email: " + staff1.getEmail());
        System.out.println("Address: " + staff1.getAddress());
        
    }
    
}