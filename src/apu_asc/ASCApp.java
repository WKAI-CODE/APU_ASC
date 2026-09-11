/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apu_asc;

import apu_asc.model.Appointment;
import apu_asc.model.Car;
import apu_asc.model.CounterStaff;
import apu_asc.model.Customer;

public class ASCApp {

    public static void main(String[] args) {

        System.out.println("APU Automotive Service Centre");

        // Temporary Counter Staff object used before the login GUI is added.
        CounterStaff staff1 = new CounterStaff(
                "CS001",
                "counter_new",
                "newPassword123",
                "Ali Ahmad",
                "0122222222",
                25,
                "IC001",
                "ali@apu.edu.my",
                "Kuala Lumpur");

        // Load the records from the text files into memory.
        Customer[] customers = staff1.loadCustomersFromFile();
        Car[] cars = staff1.loadCarsFromFile();
        Appointment[] appointments = staff1.loadAppointmentsFromFile();

        int customerCount = countCustomers(customers);
        int carCount = countCars(cars);
        int appointmentCount = countAppointments(appointments);

        System.out.println("Customers loaded: " + customerCount);
        System.out.println("Cars loaded: " + carCount);
        System.out.println("Appointments loaded: " + appointmentCount);
    }

    private static int countCustomers(Customer[] customers) {

        int count = 0;

        for (int i = 0; i < customers.length; i++) {
            if (customers[i] != null) {
                count++;
            }
        }

        return count;
    }

    private static int countCars(Car[] cars) {

        int count = 0;

        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null) {
                count++;
            }
        }

        return count;
    }

    private static int countAppointments(Appointment[] appointments) {

        int count = 0;

        for (int i = 0; i < appointments.length; i++) {
            if (appointments[i] != null) {
                count++;
            }
        }

        return count;
    }
}
