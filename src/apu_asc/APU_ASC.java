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

        System.out.println(
                "APU Automotive Service Centre");

        CounterStaff staff1 =
                new CounterStaff(
                        "CS001",
                        "counter_new",
                        "newPassword123",
                        "Ali Ahmad",
                        "0122222222",
                        25,
                        "IC001",
                        "ali@apu.edu.my",
                        "Kuala Lumpur");

        Customer[] customers =
                staff1.loadCustomersFromFile();

        Car[] cars =
                staff1.loadCarsFromFile();

        Appointment[] appointments =
                staff1.loadAppointmentsFromFile();

        int customerCount = 0;
        int carCount = 0;
        int appointmentCount = 0;

        for (int i = 0; i < customers.length; i++) {
            if (customers[i] != null) {
                customerCount++;
            }
        }

        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null) {
                carCount++;
            }
        }

        for (int i = 0;
                i < appointments.length;
                i++) {

            if (appointments[i] != null) {
                appointmentCount++;
            }
        }

        System.out.println(
                "Customers loaded: " + customerCount);

        System.out.println(
                "Cars loaded: " + carCount);

        System.out.println(
                "Appointments loaded: "
                + appointmentCount);
    }