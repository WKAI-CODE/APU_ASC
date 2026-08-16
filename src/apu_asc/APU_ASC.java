/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apu_asc;
import apu_asc.model.CounterStaff;
import apu_asc.model.Customer;
import apu_asc.model.Car;
import apu_asc.model.Appointment;

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
        
    }
    
}