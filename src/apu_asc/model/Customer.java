package apu_asc.model;

import utility.DataIO;
import java.util.ArrayList;
import java.time.LocalDate;

public class Customer extends User {

    public Customer(
            String userID,
            String username,
            String password,
            String name,
            String phoneNumber) {

        super(userID, username, password, name, phoneNumber, "CUSTOMER");
    }

    // CHECK WHETHER A USERNAME IS AVAILABLE
    public String checkProfileInput(
            String name, String username, String phone) {

        if (name.trim().isEmpty()
                || username.trim().isEmpty()
                || phone.trim().isEmpty()) {
            return "Name, username and phone number cannot be empty.";
        }

        Customer existingCustomer =
                DataIO.checkCustomerUsername(username.trim());

        if (existingCustomer != null
                && !existingCustomer.getUserID()
                        .equalsIgnoreCase(getUserID())) {
            return "This username is already taken.";
        }

        if (DataIO.checkUsername(username.trim()) != null) {
            return "This username is already taken.";
        }

        return null;
    }

    // UPDATE PROFILE
    public void updateProfile(
            String name, String username,
            String phone, String password) {

        setName(name.trim());
        setUsername(username.trim());
        setPhoneNumber(phone.trim());

        if (!password.trim().isEmpty()) {
            setPassword(password.trim());
        }

        DataIO.write();
    }

    // GET THIS CUSTOMER'S CAR DETAILS
    public String getCarDetails() {

        StringBuilder result = new StringBuilder();
        boolean found = false;

        for (Car car : DataIO.allCars) {
            if (car.getCustomerID().equalsIgnoreCase(
                    getUserID().trim())) {

                found = true;
                result.append("Car ID: ")
                        .append(car.getCarID()).append("\n");
                result.append("Registration Number: ")
                        .append(car.getRegistrationNumber()).append("\n");
                result.append("Brand: ")
                        .append(car.getBrand()).append("\n");
                result.append("Model: ")
                        .append(car.getModel()).append("\n");
                result.append("Year: ")
                        .append(car.getYear()).append("\n");
                result.append("Colour: ")
                        .append(car.getColour()).append("\n\n");
            }
        }

        if (!found) {
            return "No car details found for your account.";
        }

        return result.toString();
    }

    // FIND THIS CUSTOMER'S FIRST CAR
    public Car findCustomerCar() {

        for (Car car : DataIO.allCars) {
            if (car.getCustomerID().equalsIgnoreCase(
                    getUserID().trim())) {
                return car;
            }
        }

        return null;
    }

    // GET ALL CARS BELONGING TO THIS CUSTOMER
    public ArrayList<Car> getCustomerCars() {

        ArrayList<Car> customerCars = new ArrayList<>();

        for (Car car : DataIO.allCars) {
            if (car.getCustomerID().equalsIgnoreCase(
                    getUserID().trim())) {

                customerCars.add(car);
            }
        }

        return customerCars;
    }

    // UPDATE CAR DETAILS
    public String updateCarDetails(
            Car car, String registration, String brand,
            String model, String yearText, String colour) {

        if (car == null
                || !car.getCustomerID().equalsIgnoreCase(getUserID())) {
            return "No car details available to edit.";
        }

        if (registration.trim().isEmpty()
                || brand.trim().isEmpty()
                || model.trim().isEmpty()
                || yearText.trim().isEmpty()
                || colour.trim().isEmpty()) {
            return "Please fill in all car details.";
        }

        int year;

        try {
            year = Integer.parseInt(yearText.trim());
        } catch (NumberFormatException ex) {
            return "Year must be a valid number.";
        }

        if (year < 1886 || year > 2100) {
            return "Please enter a valid car year.";
        }

        for (Car existingCar : DataIO.allCars) {

            if (existingCar != car
                    && existingCar.getRegistrationNumber()
                            .equalsIgnoreCase(registration.trim())) {

                return "This registration number is already in use.";
            }
        }

        car.setRegistrationNumber(registration.trim());
        car.setBrand(brand.trim());
        car.setModel(model.trim());
        car.setYear(year);
        car.setColour(colour.trim());

        DataIO.write();

        return null;
    }

    // GET APPOINTMENT / SERVICE HISTORY
    public String getAppointmentHistory() {

        StringBuilder result = new StringBuilder();
        boolean found = false;

        for (Appointment appointment : DataIO.allAppointments) {
            if (appointment.getCustomerID().equalsIgnoreCase(getUserID())) {

                found = true;

                result.append("Appointment ID: ")
                        .append(appointment.getAppointmentID()).append("\n");
                result.append("Car ID: ")
                        .append(appointment.getCarID()).append("\n");
                result.append("Service Type: ")
                        .append(appointment.getServiceType()).append("\n");
                result.append("Date: ")
                        .append(appointment.getDate()).append("\n");
                result.append("Start Time: ")
                        .append(appointment.getStartTime()).append("\n");
                result.append("End Time: ")
                        .append(appointment.getEndTime()).append("\n");
                result.append("Service Price: RM ")
                        .append(String.format("%.2f",
                                appointment.getServicePrice())).append("\n");
                result.append("Appointment Status: ")
                        .append(appointment.getAppointmentStatus()).append("\n");
                result.append("Payment Status: ")
                        .append(appointment.getPaymentStatus()).append("\n");
                result.append("\n------------------------------\n\n");
            }
        }

        if (!found) {
            return "No appointment or service history found.";
        }

        return result.toString();
    }

    // GET PAYMENT AND RECEIPT DETAILS
    public String getPaymentReceiptDetails() {

        StringBuilder result = new StringBuilder();
        boolean foundPayment = false;
        boolean foundReceipt = false;

        for (Payment payment : DataIO.allPayments) {
            for (Appointment appointment : DataIO.allAppointments) {

                if (payment.getAppointmentID().equalsIgnoreCase(
                        appointment.getAppointmentID())
                        && appointment.getCustomerID()
                                .equalsIgnoreCase(getUserID())) {

                    foundPayment = true;

                    result.append("Payment ID: ")
                            .append(payment.getPaymentID()).append("\n");
                    result.append("Appointment ID: ")
                            .append(payment.getAppointmentID()).append("\n");
                    result.append("Amount: RM ")
                            .append(String.format("%.2f",
                                    payment.getAmount())).append("\n");
                    result.append("Payment Method: ")
                            .append(payment.getPaymentMethod()).append("\n");
                    result.append("Payment Date: ")
                            .append(payment.getPaymentDate()).append("\n");
                    result.append("Counter Staff ID: ")
                            .append(payment.getCounterStaffID()).append("\n");
                    result.append("\n------------------------------\n\n");

                    break;
                }
            }
        }

        if (!foundPayment) {
            result.append("No payment records found.\n\n");
        }

        result.append("RECEIPTS\n\n");

        for (Receipt receipt : DataIO.allReceipts) {
            if (receipt.getCustomerID().equalsIgnoreCase(getUserID())) {

                foundReceipt = true;

                result.append("Receipt ID: ")
                        .append(receipt.getReceiptID()).append("\n");
                result.append("Payment ID: ")
                        .append(receipt.getPaymentID()).append("\n");
                result.append("Appointment ID: ")
                        .append(receipt.getAppointmentID()).append("\n");
                result.append("Amount: RM ")
                        .append(String.format("%.2f",
                                receipt.getAmount())).append("\n");
                result.append("Receipt Date: ")
                        .append(receipt.getReceiptDate()).append("\n");
                result.append("\n------------------------------\n\n");
            }
        }

        if (!foundReceipt) {
            result.append("No receipt records found.\n");
        }

        return result.toString();
    }

    // GET TECHNICIAN FEEDBACK FOR THIS CUSTOMER'S APPOINTMENTS
    public String getTechnicianFeedback() {

        StringBuilder result = new StringBuilder();
        boolean found = false;

        for (TechnicianFeedback feedback : DataIO.allTechnicianFeedback) {
            for (Appointment appointment : DataIO.allAppointments) {

                if (feedback.getAppointmentID().equalsIgnoreCase(
                        appointment.getAppointmentID())
                        && appointment.getCustomerID()
                                .equalsIgnoreCase(getUserID())) {

                    found = true;

                    result.append("Feedback ID: ")
                            .append(feedback.getFeedbackID()).append("\n");
                    result.append("Appointment ID: ")
                            .append(feedback.getAppointmentID()).append("\n");
                    result.append("Technician ID: ")
                            .append(feedback.getTechnicianID()).append("\n");
                    result.append("Feedback: ")
                            .append(feedback.getFeedbackText()).append("\n");
                    result.append("Feedback Date: ")
                            .append(feedback.getFeedbackDate()).append("\n");
                    result.append("\n------------------------------\n\n");

                    break;
                }
            }
        }

        if (!found) {
            return "No technician feedback found for your appointments.";
        }

        return result.toString();
    }

    // GET THIS CUSTOMER'S APPOINTMENT IDs
    public ArrayList<String> getCustomerAppointmentIDs() {

        ArrayList<String> appointmentIDs = new ArrayList<>();

        for (Appointment appointment : DataIO.allAppointments) {
            if (appointment.getCustomerID().equalsIgnoreCase(getUserID())
                    && appointment.getAppointmentStatus()
                            .equalsIgnoreCase("COMPLETED")) {

                boolean alreadySubmitted = false;

                for (CustomerComment comment
                        : DataIO.allCustomerComments) {

                    if (comment.getAppointmentID()
                            .equalsIgnoreCase(
                                    appointment.getAppointmentID())
                            && comment.getCustomerID()
                                    .equalsIgnoreCase(getUserID())) {

                        alreadySubmitted = true;
                        break;
                    }
                }

                if (alreadySubmitted) {
                    continue;
                }

                appointmentIDs.add(appointment.getAppointmentID());
            }
        }

        return appointmentIDs;
    }

    // SUBMIT RATINGS AND COMMENTS
    public String submitComment(
            String appointmentID,
            int technicianRating,
            int counterStaffRating,
            String technicianComment,
            String counterStaffComment) {

        if (appointmentID == null) {
            return "Please select an appointment.";
        }

        boolean validAppointment = false;

        for (Appointment appointment : DataIO.allAppointments) {
            if (appointment.getAppointmentID().equalsIgnoreCase(appointmentID)
                    && appointment.getCustomerID().equalsIgnoreCase(getUserID())
                    && appointment.getAppointmentStatus()
                            .equalsIgnoreCase("COMPLETED")) {

                validAppointment = true;
                break;
            }
        }

        if (!validAppointment) {
            return "Only completed appointments can be rated.";
        }

        for (CustomerComment existingComment
                : DataIO.allCustomerComments) {

            if (existingComment.getAppointmentID()
                    .equalsIgnoreCase(appointmentID)
                    && existingComment.getCustomerID()
                            .equalsIgnoreCase(getUserID())) {

                return "You have already submitted ratings and comments "
                        + "for this appointment.";
            }
        }

        if (technicianRating < 1 || technicianRating > 5
                || counterStaffRating < 1 || counterStaffRating > 5) {
            return "Ratings must be between 1 and 5.";
        }

        if (technicianComment.trim().isEmpty()
                || counterStaffComment.trim().isEmpty()) {
            return "Please enter both comments.";
        }

        int highestNumber = 0;

        for (CustomerComment comment : DataIO.allCustomerComments) {
            if (comment.getCommentID().matches("CC\\d+")) {
                int number = Integer.parseInt(
                        comment.getCommentID().substring(2));

                if (number > highestNumber) {
                    highestNumber = number;
                }
            }
        }

        String commentID = String.format("CC%03d", highestNumber + 1);

        CustomerComment comment = new CustomerComment(
                commentID,
                appointmentID,
                getUserID(),
                technicianRating,
                counterStaffRating,
                technicianComment.trim(),
                counterStaffComment.trim(),
                LocalDate.now().toString()
        );

        DataIO.allCustomerComments.add(comment);
        DataIO.write();

        return null;
    }
}
