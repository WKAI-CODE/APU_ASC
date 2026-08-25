/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apu_asc.model;
import java.util.Calendar;
import java.util.GregorianCalendar;
import apu_asc.utility.FileHandler;
import java.util.Scanner;

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
        
        if (car != null) {
            car.setRegistrationNumber(registrationNumber);
            car.setBrand(brand);
            car.setModel(model);
            car.setYear(year);
            car.setColour(colour);
            
            updateCarInFile(car);
        }
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
        
        boolean validBookingDate = isValidBookingDate(date);
        
        if (!validBookingDate) {
            return null;
        }
        
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
        
        servicePrice = getServicePriceFromFile(serviceType);
        
        if (servicePrice < 0) {
            System.out.println("Failed: service price not found");
            return null;
        }
        
        boolean customerExists = isUserWithRoleInFile(customerID, "CUSTOMER");
        
        if (!customerExists) {
            System.out.println("Failed: Customer does not exist");
            return null;
        }
        
        boolean correctCarOwner = isCarOwnedByCustomerInFile(carID, customerID);
        
        if (!correctCarOwner) {
            System.out.println("Failed: Car does not belong to the Customer");
            return null;
        }
        
        boolean technicianExists = isUserWithRoleInFile(technicianID, "TECHNICIAN");
        
        if (!technicianExists) {
            System.out.println("Failed: Technician does not exist");
            return null;
        }
        
        boolean appointmentExistsInFile = FileHandler.recordExists("data/appointments.txt",appointmentID);
        
        if (appointmentExistsInFile) {
            return null;
        }
        
        for (int i = 0; i < appointments.length; i++) {
            
            if (appointments[i] != null && appointments[i].getAppointmentID().compareTo(appointmentID) == 0){
                return null;
            }
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
        
        Appointment newAppointment =
                bookAppointment(
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
        
        for (int i = 0; i < appointments.length; i++) {
            
            if (appointments[i] == null) {
                appointments[i] = newAppointment;
                saveAppointmentToFile(newAppointment);
                return newAppointment;
            }
        }

        return null;
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
                    
        if (appointment == null) {
            return null;
        }
        
        boolean completed = appointment.getAppointmentStatus().compareTo("COMPLETED") == 0;
        
        boolean unpaid = appointment.getPaymentStatus().compareTo("UNPAID") == 0;
        
        if (!completed || !unpaid) {
            return null;
        }
        
        if (FileHandler.recordExists("data/payments.txt",paymentID)) {
                return null;
        }
        

            
        Payment payment = new Payment(
                paymentID,
                appointment.getAppointmentID(),
                appointment.getServicePrice(),
                paymentMethod,
                paymentDate,
                counterStaffID);

        appointment.setPaymentStatus("PAID");
        updateAppointmentInFile(appointment);
        savePaymentToFile(payment);

        return payment;
        }
    
    public Receipt generateReceipt(
            Payment payment,
            Appointment appointment,
            String receiptID,
            String customerID,
            String receiptDate) {

        if (payment == null || appointment == null) {
            return null;
        }
        
        boolean paid = appointment.getPaymentStatus().compareTo("PAID") == 0;
 
        if (!paid) {
            return null;
        }
        
        boolean correctAppointment = payment.getAppointmentID().compareTo(appointment.getAppointmentID()) == 0;
        
        if (!correctAppointment) {
            return null;
        }
        
        boolean correctCustomer = appointment.getCustomerID().compareTo(customerID) == 0;
        
        if (!correctCustomer) {
            return null;
        }
        
        if (FileHandler.recordExists("data/receipts.txt", receiptID)) {
            return null;
        }
            
        Receipt receipt = new Receipt(
                receiptID,
                payment.getPaymentID(),
                appointment.getAppointmentID(),
                customerID,
                payment.getAmount(),
                receiptDate);

        saveReceiptToFile(receipt);

        return receipt;
    }
    
    public void updateCustomerDetails(
            Customer customer,
            String username,
            String password,
            String name,
            String phoneNumber) {
        
        if (customer != null) {
            customer.setUsername(username);
            customer.setPassword(password);
            customer.setName(name);
            customer.setPhoneNumber(phoneNumber);

            updateCustomerInFile(customer);
        }
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
                FileHandler.deleteRecord("data/users.txt", customerID);
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
                FileHandler.deleteRecord("data/cars.txt",carID);
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
        updateCounterStaffUserFile();
        updateCounterStaffDetailsFile();
    }
    
    public void saveCustomerToFile(Customer customer) {
        
        String customerData = 
                customer.getUserID() + "|" 
                + customer.getUsername() + "|"
                + customer.getPassword() + "|"
                + customer.getName() + "|"
                + customer.getPhoneNumber()+"|"
                + customer.getRole();
        
        FileHandler.appendFile("data/users.txt", customerData);
        System.out.println("Saving customer: " + customerData);                              
    }
    
    public Customer addCustomer(
            Customer[] customers,
            String userID,
            String username,
            String password,
            String name,
            String phoneNumber) {
        
        Customer existingCustomer = findCustomer(customers, userID);
        
        boolean customerExistsFile = FileHandler.recordExists("data/users.txt", userID);
        
        if (existingCustomer != null || customerExistsFile) {
            return null;
        }
        
        for (int i = 0; i < customers.length; i++) {
            
            if (customers[i] == null) {
                
                Customer newCustomer = createCustomer(
                        userID,
                        username,
                        password,
                        name,
                        phoneNumber);
                
                customers[i] = newCustomer;
                saveCustomerToFile(newCustomer);
                
                return newCustomer;
            }
        }
        return null;
    }
    
    public void saveCarToFile(Car car) {
        
        String carData =
                car.getCarID() + "|"
                + car.getCustomerID() + "|"
                + car.getRegistrationNumber() + "|"
                + car.getBrand() + "|"
                + car.getModel() + "|"
                + car.getYear() + "|"
                + car.getColour();
        
        FileHandler.appendFile(
                "data/cars.txt",
                carData);
    }
    
    public Car addCar(
            Car[] cars,
            Customer[] customers,
            String carID,
            String customerID,
            String registrationNumber,
            String brand,
            String model,
            int year,
            String colour) {
        
        Customer owner = findCustomer(customers, customerID);
        
        boolean ownerExistsInFile = isUserWithRoleInFile(customerID,"CUSTOMER");
        
        if (owner == null && !ownerExistsInFile) {
            return null;
        }
        
        Car existingCar = findCar(cars, carID);
        
        boolean carExistsInFile = FileHandler.recordExists("data/cars.txt", carID);
        
        if (existingCar != null || carExistsInFile) {
            return null;
        }
        
        for (int i = 0; i < cars.length; i++) {
            
            if(cars[i] == null) {
                
                Car newCar = registerCar(
                        carID,
                        customerID,
                        registrationNumber,
                        brand,
                        model,
                        year,
                        colour);
                
                cars[i] = newCar;
                saveCarToFile(newCar);
                
                return newCar;
            }
        }
        return null;
    }
    
    public void saveAppointmentToFile(
            Appointment appointment) {
        
        String appointmentData =
                appointment.getAppointmentID() + "|"
                + appointment.getCustomerID() + "|"
                + appointment.getCarID() + "|"
                + appointment.getTechnicianID() + "|"
                + appointment.getCounterStaffID() + "|"
                + appointment.getServiceType() + "|"
                + appointment.getDate() + "|"
                + appointment.getStartTime() + "|"
                + appointment.getEndTime() + "|"
                + appointment.getServicePrice() + "|"
                + appointment.getAppointmentStatus() + "|"
                + appointment.getPaymentStatus();
        
        FileHandler.appendFile("data/appointments.txt", appointmentData);
    }
    
    public void savePaymentToFile(Payment payment) {
        
        String paymentData =
                payment.getPaymentID() + "|"
                + payment.getAppointmentID() + "|"
                + payment.getAmount() + "|"
                + payment.getPaymentMethod() + "|"
                + payment.getPaymentDate() + "|"
                + payment.getCounterStaffID();
        
        FileHandler.appendFile("data/payments.txt", paymentData);
    }
    
    public void saveReceiptToFile(Receipt receipt) {
        
        String receiptData =
                receipt.getReceiptID() + "|"
                + receipt.getPaymentID() + "|"
                + receipt.getAppointmentID() + "|"
                + receipt.getCustomerID() + "|"
                + receipt.getAmount() + "|"
                + receipt.getReceiptDate();
        
        FileHandler.appendFile("data/receipts.txt", receiptData);
    }
    
    public boolean updateCustomerInFile(Customer customer) {
        
        String customerData = 
                customer.getUserID() + "|" +
                customer.getUsername() + "|" +
                customer.getPassword() + "|" +
                customer.getName() + "|" +
                customer.getPhoneNumber() + "|" +
                customer.getRole();
        
        return FileHandler.updateRecord("data/users.txt", customer.getUserID(), customerData);
    }
    
    public boolean updateCarInFile(Car car) {
        
        String carData = 
                car.getCarID() + "|" +
                car.getCustomerID() + "|" +
                car.getRegistrationNumber() + "|" +
                car.getBrand() + "|" +
                car.getModel() + "|" +
                car.getYear() + "|" +
                car.getColour();
        
        return FileHandler.updateRecord("data/cars.txt", car.getCarID(), carData);
    }
    
        public boolean updateAppointmentInFile(
                Appointment appointment) {

            String appointmentData =
                    appointment.getAppointmentID() + "|"
                    + appointment.getCustomerID() + "|"
                    + appointment.getCarID() + "|"
                    + appointment.getTechnicianID() + "|"
                    + appointment.getCounterStaffID() + "|"
                    + appointment.getServiceType() + "|"
                    + appointment.getDate() + "|"
                    + appointment.getStartTime() + "|"
                    + appointment.getEndTime() + "|"
                    + appointment.getServicePrice() + "|"
                    + appointment.getAppointmentStatus() + "|"
                    + appointment.getPaymentStatus();

            return FileHandler.updateRecord("data/appointments.txt", appointment.getAppointmentID(),appointmentData);
        }
        
        public Customer[] loadCustomersFromFile() {
            
            String[] lines = FileHandler.readFileToArray("data/users.txt");
            
            Customer[] customers = new Customer[lines.length + 10];
            
            int position = 0;
            
            for (int i = 1; i < lines.length; i++) {
                
                Scanner lineScanner = new Scanner(lines[i]);
                
                lineScanner.useDelimiter("\\|");
                
                String userID = lineScanner.next();
                String username = lineScanner.next();
                String password = lineScanner.next();
                String name = lineScanner.next();
                String phoneNumber = lineScanner.next();
                String role = lineScanner.next();
                
                if (role.compareTo("CUSTOMER") == 0) {
                    
                    customers[position] =
                            createCustomer(
                                    userID,
                                    username,
                                    password,
                                    name,
                                    phoneNumber);
                    position++;
                }
                lineScanner.close();
            }
            return customers;
        }
        
        public Car[] loadCarsFromFile() {
            
            String[] lines = FileHandler.readFileToArray("data/cars.txt");
            
            Car[] cars = new Car[lines.length + 10];
            
            int position = 0;
            
            for (int i = 1; i < lines.length; i++) {
                
                Scanner lineScanner = new Scanner(lines[i]);
                lineScanner.useDelimiter("\\|");
                
                String carID = lineScanner.next();
                String customerID = lineScanner.next();
                String registrationNumber = lineScanner.next();
                String brand = lineScanner.next();
                String model = lineScanner.next();
                
                int year = Integer.parseInt(lineScanner.next());
                
                String colour = lineScanner.next();
                
                cars[position] = registerCar(
                        carID,
                        customerID,
                        registrationNumber,
                        brand,
                        model,
                        year,
                        colour);
                
                position++;
                
                lineScanner.close();
            }
            return cars;
        }
        
        public Appointment[] loadAppointmentsFromFile() {
            
            String[] lines = FileHandler.readFileToArray("data/appointments.txt");
            
            Appointment[] appointments = new Appointment[lines.length + 10];
            
            int position = 0;
            
            for (int i = 1; i < lines.length; i++) {
                
                Scanner lineScanner = new Scanner(lines[i]);
                lineScanner.useDelimiter("\\|");
                
                String appointmentID = lineScanner.next();
                String customerID = lineScanner.next();
                String carID = lineScanner.next();
                String technicianID = lineScanner.next();
                String counterStaffID = lineScanner.next();
                String serviceType = lineScanner.next();
                String date = lineScanner.next();
                String startTime = lineScanner.next();
                String endTime = lineScanner.next();
                double servicePrice = Double.parseDouble(lineScanner.next());
                String appointmentStatus = lineScanner.next();
                String paymentStatus = lineScanner.next();
                
                appointments[position] = 
                        new Appointment(
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
                                appointmentStatus,
                                paymentStatus);
                
                position++;
                
                lineScanner.close();
            }
            return appointments;
        }
        
        public double getServicePriceFromFile(
                String serviceType) {
            
            String[] lines = FileHandler.readFileToArray("data/service_prices.txt");
            
            for (int i = 1; i < lines.length; i++) {
                
                Scanner lineScanner = new Scanner(lines[i]);
                
                lineScanner.useDelimiter("\\|");
                
                String fileServiceType = lineScanner.next();
                String durationHours = lineScanner.next();
                double price = Double.parseDouble(lineScanner.next());
                
                lineScanner.close();
                
                if (fileServiceType.compareTo(serviceType) == 0) {
                    
                    return price;
                }
            }
            
            return -1;
        }
        
        public boolean isUserWithRoleInFile(
                String userID,
                String requiredRole) {
            
            String[] lines = FileHandler.readFileToArray("data/users.txt");
            
            for (int i = 1; i < lines.length; i++) {
                
                Scanner lineScanner = new Scanner(lines[i]);
                
                lineScanner.useDelimiter("\\|");
                
                String fileUserID = lineScanner.next();
                String username = lineScanner.next();
                String password = lineScanner.next();
                String name = lineScanner.next();
                String phoneNumber = lineScanner.next();
                String role = lineScanner.next();
                
                lineScanner.close();
                
                if (fileUserID.compareTo(userID) == 0 && role.compareTo(requiredRole) == 0){
                    
                    return true;
                }
            }
            return false;
        }
        
        public boolean isCarOwnedByCustomerInFile(
                String carID,
                String customerID) {
            
            String[] lines = FileHandler.readFileToArray("data/cars.txt");
            
            for (int i = 1; i < lines.length; i++) {
                
                Scanner lineScanner = new Scanner(lines[i]);
                
                lineScanner.useDelimiter("\\|");
                
                String fileCarID = lineScanner.next();
                String fileCustomerID = lineScanner.next();
                
                lineScanner.close();
                
                if (fileCarID.compareTo(carID) == 0 && fileCustomerID.compareTo(customerID) == 0) {
                    return true;
                }
            }
            return false;
        }
        
        public boolean updateCounterStaffUserFile() {
            
            String userData =
                    getUserID() + "|" +
                    getUsername() + "|" +
                    getPassword() + "|" +
                    getName() + "|" +
                    getPhoneNumber() + "|" +
                    getRole();
            
            if (FileHandler.recordExists("data/users.txt",getUserID())) {
                
                return FileHandler.updateRecord("data/users.txt", getUserID(), userData);
            }
            
            FileHandler.appendFile("data/users.txt", userData);
            
            return true;
        }
        
        public boolean updateCounterStaffDetailsFile() {
            
            String staffData =
                    getUserID() + "|" +
                    getAge() + "|" +
                    getIdentityNumber() + "|" +
                    getEmail() + "|" +
                    getAddress();
            
            if (FileHandler.recordExists("data/staff.txt",getUserID())) {
                
                return FileHandler.updateRecord("data/staff.txt", getUserID(),staffData);    
            }
            
            FileHandler.appendFile("data/staff.txt", staffData);
            
            return true;
        }
}