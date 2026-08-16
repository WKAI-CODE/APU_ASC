/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apu_asc.model;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CounterStaff extends Staff {
    
    public CounterStaff(
            String userID,
            String username,
            String password,
            String name,
            String phoneNumber,
            int age,
            String identityNumber,
            String email,
            String address) {
        
        super(userID, username, password, name, phoneNumber, "COUNTER_STAFF", age, identityNumber, email, address);
    }
    
    public Customer createCustomer(
            String userID,
            String username,
            String password,
            String name,
            String phoneNumber) {
        
        Customer customer = new Customer(
                userID,
                username,
                password,
                name,
                phoneNumber);
        
        return customer;
    }
    
    public Car registerCar(
            String carID,
            String customerID,
            String registrationNumber,
            String brand,
            String model,
            int year,
            String colour) {
        
        Car car = new Car(
                carID,
                customerID,
                registrationNumber,
                brand,
                model,
                year,
                colour);
        
        return car;
    }
     
    public void updateCarDetails(
            Car car,
            String registrationNumber,
            String brand,
            String model,
            int year,
            String colour) {
        
        car.setRegistrationNumber(registrationNumber);
        car.setBrand(brand);
        car.setModel(model);
        car.setYear(year);
        car.setColour(colour);
    }
    
    public Appointment bookAppointment(
            String appointmentID,
            String customerID,
            String carID,
            String technicianID,
            String counterStaffID,
            String serviceType,
            String date,
            String startTime,
            String endTime,
            double servicePrice) {
        
        Appointment appointment = new Appointment(
            appointmentID,
            customerID,
            carID,
            technicianID,
            counterStaffID,
            serviceType,
            date,
            startTime,
            endTime,
            servicePrice,
            "BOOKED",
            "UNPAID");
        
        return appointment;
    }
    
    public boolean isValidServiceDuration(
            String serviceType,
            String startTime,
            String endTime) {
        
        int startHour = Integer.parseInt(startTime.substring(0,2));
        int startMinute = Integer.parseInt(startTime.substring(3,5));
        
        int endHour = Integer.parseInt(endTime.substring(0,2));
        int endMinute = Integer.parseInt(endTime.substring(3,5));
        
        int startTotalMinutes = (startHour * 60) + startMinute;
        int endTotalMinutes = (endHour * 60) + endMinute;
        
        int durationMinutes = endTotalMinutes - startTotalMinutes;
        
        if (serviceType.compareTo("MINOR") == 0 && durationMinutes == 60) {
            
            return true;
              
        } else if (serviceType.compareTo("MAJOR") == 0 && durationMinutes == 180) {
            
            return true;
            
            
        } else {
            return false;
        }
    }
    
    private int convertTimeToMinutes(String time) {
        
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));
        
        return (hour * 60) + minute;
    }
    
    public boolean isTechnicianAvailable(
            Appointment existingAppointment,
            String technicianID,
            String date,
            String startTime,
            String endTime) {
        
        boolean sameTechnician = existingAppointment.getTechnicianID().compareTo(technicianID) == 0;
        
        boolean sameDate = existingAppointment.getDate().compareTo(date) == 0;
        
        boolean isCancelled = existingAppointment.getAppointmentStatus().compareTo("CANCELLED") == 0;
        
        if (sameTechnician && sameDate && !isCancelled){
            
            int existingStart = convertTimeToMinutes(existingAppointment.getStartTime());
        
            int existingEnd = convertTimeToMinutes(existingAppointment.getEndTime());
            
            int newStart = convertTimeToMinutes(startTime);
            int newEnd = convertTimeToMinutes(endTime);
            
            boolean overlaps = newStart < existingEnd && newEnd > existingStart;
            
            if (overlaps) {
                return false;
            }           
        }
        
        return true;
    }
    
    public boolean isTechnicianAvailableForAll(
            Appointment[] appointments,
            String technicianID,
            String date,
            String startTime,
            String endTime) {
        
        for (int i = 0; i < appointments.length; i++) {
            
            if (appointments[i] != null) {
                
                boolean available = 
                        isTechnicianAvailable(
                                appointments[i],
                                technicianID,
                                date,
                                startTime,
                                endTime);
                
                if (!available) {
                    return false;
                }
                
            }
        }
        
        return true;
    }
    
    public Appointment bookAppointmentWithValidation(
            Appointment[] appointments,
            String appointmentID,
            String customerID,
            String carID,
            String technicianID,
            String counterStaffID,
            String serviceType,
            String date,
            String startTime,
            String endTime,
            double servicePrice) {
        
        boolean validDuration = isValidServiceDuration(
                serviceType,
                startTime,
                endTime);
        
        if (!validDuration) {
            return null;
        }
        
        boolean technicianAvailable = isTechnicianAvailableForAll(
                appointments,
                technicianID,
                date,
                startTime,
                endTime);
        
        if (!technicianAvailable) {
            return null;
        }
        
        return bookAppointment(
                appointmentID,
                customerID,
                carID,
                technicianID,
                counterStaffID,
                serviceType,
                date,
                startTime,
                endTime,
                servicePrice);
    }
    
    public boolean isValidBookingDate(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7)) -1;
        int day = Integer.parseInt(date.substring(8, 10));
        
        GregorianCalendar bookingDate = new GregorianCalendar(year, month, day);
        
        boolean correctDate = bookingDate.get(Calendar.YEAR) == year && bookingDate.get(Calendar.MONTH) == month && bookingDate.get(Calendar.DATE) == day;
        
        if (!correctDate) {
            return false;
        }
        
        GregorianCalendar currentDate = new GregorianCalendar();
        
        int currentYear = currentDate.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH);
        int currentDay = currentDate.get(Calendar.DATE);
        
        GregorianCalendar tomorrow = new GregorianCalendar(
                    currentYear,
                    currentMonth,
                    currentDay);
        
        tomorrow.add(Calendar.DATE, 1);
        
        GregorianCalendar lastBookingDate = new GregorianCalendar(
                    currentYear,
                    currentMonth,
                    currentDay);
        
        lastBookingDate.add(Calendar.DATE, 14);
        
        boolean beforeTomorrow = tomorrow.after(bookingDate);
        
        boolean afterLastBookingDate = bookingDate.after(lastBookingDate);
        
        if (beforeTomorrow || afterLastBookingDate) {
            return false;
        }
        
        return true;
        
    }
    
}
