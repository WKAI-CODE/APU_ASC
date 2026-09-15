/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apu_asc.model;
import java.util.Calendar;
import java.util.GregorianCalendar;
import apu_asc.utility.DataIO;

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
        
        super(userID, username, password, name, phoneNumber, "CounterStaff", age, identityNumber, email, address);
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
        
        if (car != null) {
            car.setRegistrationNumber(registrationNumber);
            car.setBrand(brand);
            car.setModel(model);
            car.setYear(year);
            car.setColour(colour);
            
            DataIO.write();
        }
    }
    
    private Appointment bookAppointment(
            String appointmentID,
            String customerID,
            String carID,
            String technicianID,
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
            getUserID(),
            serviceType,
            date,
            startTime,
            endTime,
            servicePrice,
            "BOOKED",
            "UNPAID");
        
        return appointment;
    }
    
    public String getServiceDurationError(
            String serviceType,
            String startTime,
            String endTime) {

        if (serviceType == null) {
            return "Service type cannot be empty.";
        }

        int startTotalMinutes = convertTimeToMinutes(startTime);
        int endTotalMinutes = convertTimeToMinutes(endTime);

        if (startTotalMinutes == -1 || endTotalMinutes == -1) {
            return "Invalid time format. Please use HH:mm.";
        }

        int durationMinutes = endTotalMinutes - startTotalMinutes;

        if (serviceType.equalsIgnoreCase("MINOR")) {

            if (durationMinutes != 60) {return "Minor service must be exactly 1 hour.";
            }

            return null;
        }

        if (serviceType.equalsIgnoreCase("MAJOR")) {

            if (durationMinutes != 180) {return "Major service must be exactly 3 hours.";
            }

            return null;
        }

        return "Service type must be MINOR or MAJOR.";
    }

    private int convertTimeToMinutes(String time) {

        try {
            if (time == null || time.length() != 5 || time.charAt(2) != ':'){

                return -1;
            }

            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(3, 5));

            if (hour < 0 || hour > 23 || minute < 0 || minute > 59){

                return -1;
            }

            return (hour * 60) + minute;

        } catch (Exception e) {

            return -1;
        }
        }
    
    public Appointment findConflictingAppointment(
            String technicianID,
            String date,
            String startTime,
            String endTime) {
        
        int newStart = convertTimeToMinutes(startTime);
        int newEnd = convertTimeToMinutes(endTime);
        
        for (int i = 0; i < DataIO.allAppointments.size(); i++) {
            
            Appointment appointment = DataIO.allAppointments.get(i);
            
            if (appointment.getTechnicianID().equalsIgnoreCase(technicianID) && appointment.getDate().equals(date) && !"CANCELLED".equalsIgnoreCase(appointment.getAppointmentStatus())) {
                
                int existingStart = convertTimeToMinutes(appointment.getStartTime());
                
                int existingEnd = convertTimeToMinutes(appointment.getEndTime());
                
                if (newStart < existingEnd && newEnd > existingStart) {
                    return appointment;
                }
            }
        }
        return null;
    }
    
    public Appointment bookAppointmentWithValidation(
            String appointmentID,
            String customerID,
            String carID,
            String technicianID,
            String serviceType,
            String date,
            String startTime,
            String endTime) {
        
        GregorianCalendar validBookingDate = getValidBookingDate(date);
        
        if (validBookingDate == null) {
            System.out.println("Failed: Booking date must be from tomorrow until the next 14 days.");
            return null;
        }
        
        String workingHoursError = getWorkingHoursError(startTime, endTime);
        
        if (workingHoursError != null) {
            System.out.println("Failed: " + workingHoursError);
            return null;
        }
        
        String serviceDurationError = getServiceDurationError(
                        serviceType,
                        startTime,
                        endTime);

        if (serviceDurationError != null) {
            System.out.println("Failed: " + serviceDurationError);
            return null;
        }
        
        double servicePrice;
        
        if ("MINOR".equalsIgnoreCase(serviceType)) {
            
            servicePrice = DataIO.minorPrice;
            
        } else if ("MAJOR".equalsIgnoreCase(serviceType)) {
            
            servicePrice = DataIO.majorPrice;
            
        } else {
            
            System.out.println("Failed: Invalid service type.");
            return null;
        }
        
        if (servicePrice <= 0) {
            
            System.out.println("Failed: Service price has not been set.");
            return null;
        }
        
        Customer customer = DataIO.checkCustomerID(customerID);
        
        if (customer == null) {
            System.out.println("Failed: Customer does not exist.");
            return null;
        }
        
        Staff technician = DataIO.checkUserID(technicianID);
        
        if (technician == null) {
            System.out.print("Failed: Technician does not exist.");
            return null;
        }
        
        if (!"TECHNICIAN".equalsIgnoreCase(technician.getRole())) {
            
            System.out.println("Failed: Selected staff is not a Technician.");
            return null;
        }
        
        Car car = DataIO.checkCarID(carID);
        
        if (car == null) {
            System.out.println("Failed: Car does not exist.");
            return null;
        }
        
        if (!car.getCustomerID().equalsIgnoreCase(customerID)) {
            System.out.println("Failed: Car does not belong to the Customer.");
            return null;
        }
        
        Appointment existingAppointment = DataIO.checkAppointmentID(appointmentID);
        
        if (existingAppointment != null) {
            System.out.println("Failed: Appointment ID already exists.");
            return null;
        }

        Appointment conflictingAppointment = findConflictingAppointment(
                        technicianID,
                        date,
                        startTime,
                        endTime);
        
        if (conflictingAppointment != null) {
            System.out.println("Failed: Technician already has another appointment.");
            return null;
        }
        
        Appointment newAppointment =
                bookAppointment(
                        appointmentID,
                        customerID,
                        carID,
                        technicianID,
                        serviceType,
                        date,
                        startTime,
                        endTime,
                        servicePrice);
        
        DataIO.allAppointments.add(newAppointment);
        DataIO.write();
        
        return newAppointment;
    }
    
    public GregorianCalendar getValidBookingDate(String date) {
        
        try {
            if (date == null || date.length() != 10 || date.charAt(4) != '-' || date.charAt(7) != '-') {
                return null;
            }
            
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(5, 7)) - 1;
            int day = Integer.parseInt(date.substring(8, 10));
            
            GregorianCalendar bookingDate = new GregorianCalendar(year, month, day);
            
            if (bookingDate.get(Calendar.YEAR) != year || bookingDate.get(Calendar.MONTH) != month || bookingDate.get(Calendar.DATE) != day) {
                return null;
            }
            
            GregorianCalendar currentDate = new GregorianCalendar();
            
            GregorianCalendar tomorrow = new GregorianCalendar(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
            
            tomorrow.add(Calendar.DATE, 1);
            
            GregorianCalendar lastBookingDate = new GregorianCalendar(
                        currentDate.get(Calendar.YEAR),
                       currentDate.get(Calendar.MONTH),
                   currentDate.get(Calendar.DATE));
            
            lastBookingDate.add(Calendar.DATE, 14);
            
            if (tomorrow.after(bookingDate) || bookingDate.after(lastBookingDate)) {
                return null;
            }
            
            return bookingDate;
            
        } catch (Exception ex) {
            return null;
        }
    }
    
    
    public String getWorkingHoursError(
            String startTime,
            String endTime) {
        
        int openingTime = convertTimeToMinutes("09:00");
        int closingTime = convertTimeToMinutes("18:00");
        
        int appointmentStart = convertTimeToMinutes(startTime);
        int appointmentEnd = convertTimeToMinutes(endTime);
        
        if (appointmentStart == -1  || appointmentEnd == -1) {
            return "Invalid time format. Please use HH:mm";
        }
        
        if (appointmentStart >= appointmentEnd) {
            return "The end time must be later than the start time.";
        }
        
        if (appointmentStart < openingTime || appointmentEnd > closingTime) {
            return "Appointment must be between 09:00 and 18:00";
        }
        
        return null;
    }
    
        public Payment collectPayment(
            String appointmentID,
            String paymentID,
            String paymentMethod,
            String paymentDate) {

        Appointment appointment = DataIO.checkAppointmentID(appointmentID);

        if (appointment == null) {
            System.out.println("Failed: Appointment does not exist.");
            return null;
        }

        if (!"COMPLETED".equalsIgnoreCase(appointment.getAppointmentStatus())) {

            System.out.println("Failed: Payment can only be collected after the appointment is completed.");
            return null;
        }

        if (!"UNPAID".equalsIgnoreCase(appointment.getPaymentStatus())) {

            System.out.println("Failed: This appointment has already been paid.");
            return null;
        }

        Payment existingPayment = DataIO.checkPaymentID(paymentID);

        if (existingPayment != null) {
            System.out.println("Failed: Payment ID already exists.");
            return null;
        }

        Payment newPayment =
                new Payment(
                        paymentID,
                        appointmentID,
                        appointment.getServicePrice(),
                        paymentMethod,
                        paymentDate,
                        getUserID());

        appointment.setPaymentStatus("PAID");

        DataIO.allPayments.add(newPayment);
        DataIO.write();

        return newPayment;
    }
        
        public Receipt findReceiptByPaymentID(String paymentID) {

        for (int i = 0; i < DataIO.allReceipts.size(); i++) {

            Receipt receipt = DataIO.allReceipts.get(i);

            if (receipt.getPaymentID().equalsIgnoreCase(paymentID)) {
                return receipt;
            }
        }

        return null;
    }
    
    public Receipt generateReceipt(
            String paymentID,
            String receiptID,
            String customerID,
            String receiptDate) {

        Payment payment =
                DataIO.checkPaymentID(paymentID);

        if (payment == null) {
            System.out.println("Failed: Payment does not exist.");
            return null;
        }

        Appointment appointment =
                DataIO.checkAppointmentID(
                        payment.getAppointmentID());

        if (appointment == null) {
            System.out.println("Failed: Appointment does not exist.");
            return null;
        }

        if (!"PAID".equalsIgnoreCase(
                appointment.getPaymentStatus())) {

            System.out.println("Failed: Receipt can only be generated after payment.");
            return null;
        }

        if (!appointment.getCustomerID()
                .equalsIgnoreCase(customerID)) {

            System.out.println("Failed: The appointment does not belong to this customer.");
            return null;
        }

        Receipt existingReceipt =
                DataIO.checkReceiptID(receiptID);

        if (existingReceipt != null) {
            System.out.println("Failed: Receipt ID already exists.");
            return null;
        }
        
        Receipt receiptForPayment =
                findReceiptByPaymentID(paymentID);

        if (receiptForPayment != null) {
            System.out.println("Failed: A receipt has already been generated for this payment.");
            return null;
        }

        Receipt newReceipt =
                new Receipt(
                        receiptID,
                        paymentID,
                        appointment.getAppointmentID(),
                        customerID,
                        payment.getAmount(),
                        receiptDate);

        DataIO.allReceipts.add(newReceipt);
        DataIO.write();

        return newReceipt;
    }
    
    public Customer updateCustomerDetails(
            Customer customer,
            String username,
            String password,
            String name,
            String phoneNumber) {

        if (customer == null) {
            System.out.println("Failed: Customer does not exist.");
            return null;
        }

        Customer customerWithSameUsername =
                DataIO.checkCustomerUsername(username);

        Staff staffWithSameUsername =
                DataIO.checkUsername(username);

        if (customerWithSameUsername != null
                && !customerWithSameUsername.getUserID()
                        .equalsIgnoreCase(customer.getUserID())) {

            System.out.println("Failed: Username is already used by another customer.");
            return null;
        }

        if (staffWithSameUsername != null) {
            System.out.println("Failed: Username is already used by a staff member.");
            return null;
        }

        customer.setUsername(username); 
        customer.setPassword(password);
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);

        DataIO.write();

        return customer;
    }
    
    public Customer deleteCustomer(String customerID){
            
        Customer customer = DataIO.checkCustomerID(customerID);
        
        if (customer == null) {
            return null;
        }
        
        for (int i = 0; i < DataIO.allCars.size(); i++) {
            
            Car car = DataIO.allCars.get(i);
            
            if (car.getCustomerID().equalsIgnoreCase(customerID)) {
                
                System.out.println("Customer cannot be deleted because they still own a car.");
                
                return null;
            }
        }
        
        DataIO.allCustomers.remove(customer);
        
        DataIO.write();
        
        return customer;
    }
    
    public Car deleteCar(String carID){
        
        Car car = DataIO.checkCarID(carID);
        
        if (car == null) {
            return null;
        }
        
        for (int i = 0; i < DataIO.allAppointments.size(); i++) {
            
            Appointment appointment = DataIO.allAppointments.get(i);
            
            if (appointment.getCarID().equalsIgnoreCase(carID)) {
                
                System.out.println("Car cannot be deleted because it has appointment history.");
                
                return null;
            }
        }
        
        DataIO.allCars.remove(car);
        
        DataIO.write();
        
        return car;
    }
    
    public Staff editProfile(
            String username,
            String password,
            String name,
            String phoneNumber,
            int age,
            String email,
            String address) {

        Staff savedCounterStaff =
                DataIO.checkUserID(getUserID());

        if (savedCounterStaff == null) {
            System.out.println("Failed: Counter Staff account does not exist.");
            return null;
        }

        Staff staffWithSameUsername =
                DataIO.checkUsername(username);

        Customer customerWithSameUsername =
                DataIO.checkCustomerUsername(username);

        if (staffWithSameUsername != null
                && !staffWithSameUsername.getUserID()
                        .equalsIgnoreCase(getUserID())) {

            System.out.println("Failed: Username is already used by another staff member.");
            return null;
        }

        if (customerWithSameUsername != null) {
            System.out.println("Failed: Username is already used by a customer.");
            return null;
        }

        savedCounterStaff.setUsername(username);
        savedCounterStaff.setPassword(password);
        savedCounterStaff.setName(name);
        savedCounterStaff.setPhoneNumber(phoneNumber);
        savedCounterStaff.setAge(age);
        savedCounterStaff.setEmail(email);
        savedCounterStaff.setAddress(address);

        setUsername(username);
        setPassword(password);
        setName(name);
        setPhoneNumber(phoneNumber);
        setAge(age);
        setEmail(email);
        setAddress(address);

        DataIO.write();

        return savedCounterStaff;
    }
    
    public String generateCustomerID() {
        
        int number = DataIO.allCustomers.size() + 1;
        
        String customerID = String.format("C%03d", number);
        
        while (DataIO.checkCustomerID(customerID) != null 
                || DataIO.checkUserID(customerID) != null 
                || DataIO.checkCustomerUsername(customerID) != null
                || DataIO.checkUsername(customerID) != null) {
            
            number ++;
            
            customerID = String.format("C%03d", number);
        }
        
        return customerID;
    }
    
    public Customer addCustomer(
            String userID,
            String username,
            String password,
            String name,
            String phoneNumber) {
        
        Customer customerWithSameID = DataIO.checkCustomerID(userID);
        
        Staff staffWithSameID = DataIO.checkUserID(userID);
        
        Customer customerWithSameUsername = DataIO.checkCustomerUsername(username);
        
        Staff staffWithSameUsername = DataIO.checkUsername(username);
        
        if (customerWithSameID != null || staffWithSameID != null || customerWithSameUsername != null || staffWithSameUsername != null) {
            
            return null;
        }       
        
        Customer newCustomer = createCustomer(
                userID,
                username,
                password,
                name,
                phoneNumber);

        DataIO.allCustomers.add(newCustomer);
        
        DataIO.write();
        
        return newCustomer;
    }
    
    public Car addCar(
            String carID,
            String customerID,
            String registrationNumber,
            String brand,
            String model,
            int year,
            String colour) {
        
        Customer owner = DataIO.checkCustomerID(customerID);
        
        if (owner == null) {
            return null;
        }
        
        Car existingCar = DataIO.checkCarID(carID);
        
        if (existingCar != null) {
            return null;
        }

        Car newCar = registerCar(
                carID,
                customerID,
                registrationNumber,
                brand,
                model,
                year,
                colour);

        DataIO.allCars.add(newCar);
        
        DataIO.write();

        return newCar;
    }
}