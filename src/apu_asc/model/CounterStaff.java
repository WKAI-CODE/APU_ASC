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
        
        boolean withinWorkingHours = isWithinWorkingHours(startTime, endTime);
        
        if (!withinWorkingHours) {
            return null;
        }
        
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
    
    public boolean isWithinWorkingHours(
            String startTime,
            String endTime) {
        
        int openingTime = convertTimeToMinutes("09:00");
        int closingTime = convertTimeToMinutes("18:00");
        int appointmentStart = convertTimeToMinutes(startTime);
        int appointmentEnd = convertTimeToMinutes(endTime);
        
        if (appointmentStart >= openingTime && appointmentEnd <= closingTime && appointmentStart < appointmentEnd){
            
            return true;
            
        } else {
            return false;
        }
    }
    
    public Payment collectPayment(
            Appointment appointment,
            String paymentID,
            String paymentMethod,
            String paymentDate,
            String counterStaffID) {
        
        boolean completed = appointment.getAppointmentStatus().compareTo("COMPLETED") == 0;
        boolean unpaid = appointment.getPaymentStatus().compareTo("UNPAID") == 0;
        
        if (completed && unpaid) {
            
            Payment payment = new Payment(
                    paymentID,
                    appointment.getAppointmentID(),
                    appointment.getServicePrice(),
                    paymentMethod,
                    paymentDate,
                    counterStaffID);
            
            appointment.setPaymentStatus("PAID");
            
            return payment;
        }
        return null;
    }
    
    public Receipt generateReceipt(
            Payment payment,
            Appointment appointment,
            String receiptID,
            String customerID,
            String receiptDate) {
        
        boolean paymentExists = payment != null;
        
        boolean paid = appointment.getPaymentStatus().compareTo("PAID") == 0;
        
        if (paymentExists && paid) {
            
            Receipt receipt = new Receipt(
                    receiptID,
                    payment.getPaymentID(),
                    appointment.getAppointmentID(),
                    customerID,
                    payment.getAmount(),
                    receiptDate);
            
            return receipt;
        }
        return null;
    }
    
    public void updateCustomerDetails(
            Customer customer,
            String username,
            String password,
            String name,
            String phoneNumber) {
        
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
    }
    
    public Customer findCustomer(
            Customer[] customers,
            String customerID) {
        
        for (int i = 0; i < customers.length; i++) {
        
            if (customers[i] != null && customers[i].getUserID().compareTo(customerID) == 0) {
                
                return customers[i];
            }
        }
        return null;
    }
    
    public boolean deleteCustomer(
            Customer[] customers,
            String customerID) {
        
        for (int i = 0; i < customers.length; i++) {
            
            if (customers[i] != null && customers[i].getUserID().compareTo(customerID) == 0) {
                
                customers[i] = null;
                return true;
            }
        }
        return false;
    }
    
    public Car findCar(
            Car[] cars,
            String carID) {
        
        for (int i = 0; i < cars.length; i++) {
            
            if (cars[i] != null && cars[i].getCarID().compareTo(carID) == 0) {
                
                return cars[i];
            }
        } 
        return null;
    }
    
    public boolean deleteCar(
            Car[] cars,
            String carID) {
        
        for (int i = 0; i < cars.length; i++) {
            
            if (cars[i] != null && cars[i].getCarID().compareTo(carID) == 0) {
                
                cars[i] = null;
                return true;
            }
        }
        return false;
    }
    
    public void editProfile(
            String username,
            String password,
            String name,
            String phoneNumber,
            int age,
            String email,
            String address) {
        
        setUsername(username);
        setPassword(password);
        setName(name);
        setPhoneNumber(phoneNumber);
        setAge(age);
        setEmail(email);
        setAddress(address);
    }
}
 