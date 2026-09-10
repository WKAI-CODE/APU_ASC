package utility;


import apu_asc.model.Appointment;
import apu_asc.model.CustomerComment;
import apu_asc.model.Payment;
import apu_asc.model.Staff;
import apu_asc.model.TechnicianFeedback;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataIO {
    private static final String USER_FILE = "src/data/users.txt";
    private static final String STAFF_FILE = "src/data/staff.txt";
    private static final String SERVICE_PRICE_FILE = "src/data/service_prices.txt";
    private static final String APPOINTMENT_FILE = "src/data/appointments.txt";
    private static final String PAYMENT_FILE = "src/data/payments.txt";
    private static final String TECHNICIAN_FEEDBACK_FILE = "src/data/technician_feedback.txt";
    private static final String CUSTOMER_COMMENT_FILE = "src/data/customer_comments.txt";
    
    //Array list
    public static ArrayList<Staff> allStaff = new ArrayList<Staff>();
    public static ArrayList<Appointment> allAppointments = new ArrayList<Appointment>();
    public static ArrayList<Payment> allPayments = new ArrayList<Payment>();
    public static ArrayList<TechnicianFeedback> allTechnicianFeedback = new ArrayList<TechnicianFeedback>();
    public static ArrayList<CustomerComment> allCustomerComments = new ArrayList<CustomerComment>();
    
    
    public static double minorPrice = 0.0;
    public static double majorPrice = 0.0;
    
    
    //WRITE FILE
    public static void write(){
        writeUsers();
        writeStaff();
        writeAppointments();
        writePayments();
        writeServicePrices();
        writeTechnicianFeedback();
        writeCustomerComments();
        
    }
    
    //READ
    public static void read() {
        readUsers();
        readStaff();
        readAppointments();
        readPayments();
        readServicePrices();
        readTechnicianFeedback();
        readCustomerComments();
    }
    
    
    //USERS
    private static void writeUsers(){
        try{
            PrintWriter writer = new PrintWriter(USER_FILE);
            
            for(int i = 0; i< allStaff.size(); i++){
                Staff staff = allStaff.get(i);
                
                writer.println(
                        staff.getUserID()
                        + "|" + staff.getUsername()
                        + "|" + staff.getPassword()
                        + "|" + staff.getName()
                        + "|" + staff.getPhoneNumber()
                        + "|" + staff.getRole()
                );
            }
            
            writer.close();
        }catch(Exception ex){
            System.out.println("Error in writting users.txt");
        }
    }
    
    private static void readUsers(){
        try{
            Scanner scanner = new Scanner(new File(USER_FILE));
            
            while(scanner.hasNextLine()){
                
                String line = scanner.nextLine();
                
                if(line.trim().isEmpty()){
                    continue;
                }
                
                String[] data = line.split("\\|" , -1);
                if(data.length >= 6){
                    String userID = data[0];
                    String username = data[1];
                    String password = data[2];
                    String name = data[3];
                    String phoneNumber = data[4];
                    String role = data[5];
                    
                    Staff staff = new Staff(
                            userID,
                            username,
                            password,
                            name,
                            phoneNumber,
                            role,
                            0,
                            "",
                            "",
                            ""
                    );
                    allStaff.add(staff);
                }
            }
            
            scanner.close();
            
        }catch(Exception ex){
            System.out.println("Error in reading users.txt ");
        }
    }
    
    
    //STAFF
    private static void writeStaff() {

        try {

            PrintWriter writer = new PrintWriter(STAFF_FILE);

            for (int i = 0; i < allStaff.size(); i++) {

                Staff staff = allStaff.get(i);

                writer.println(
                        staff.getUserID()
                        + "|" + staff.getAge()
                        + "|" + staff.getIdentityNumber()
                        + "|" + staff.getEmail()
                        + "|" + staff.getAddress()
                );
            }

            writer.close();

        } catch (Exception ex) {

            System.out.println("Error in writing staff.txt");
        }
    }


    private static void readStaff() {

        try {

            Scanner scanner = new Scanner(new File(STAFF_FILE));

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length >= 5) {

                    Staff staff = checkUserID(data[0]);

                    if (staff != null) {

                        staff.setAge(Integer.parseInt(data[1]));
                        staff.setIdentityNumber(data[2]);
                        staff.setEmail(data[3]);
                        staff.setAddress(data[4]);
                    }
                }
            }

            scanner.close();

        } catch (Exception ex) {

            System.out.println("Error in reading staff.txt");
        }
    }
    
    //APPOINTMENTS
    private static void writeAppointments() {

        try {

            PrintWriter writer = new PrintWriter(APPOINTMENT_FILE);

            for (int i = 0; i < allAppointments.size(); i++) {

                Appointment appointment = allAppointments.get(i);

                writer.println(
                        appointment.getAppointmentID()
                        + "|" + appointment.getCustomerID()
                        + "|" + appointment.getCarID()
                        + "|" + appointment.getTechnicianID()
                        + "|" + appointment.getCounterStaffID()
                        + "|" + appointment.getServiceType()
                        + "|" + appointment.getDate()
                        + "|" + appointment.getStartTime()
                        + "|" + appointment.getEndTime()
                        + "|" + appointment.getServicePrice()
                        + "|" + appointment.getAppointmentStatus()
                        + "|" + appointment.getPaymentStatus()
                );
            }

            writer.close();

        } catch (Exception ex) {

            System.out.println("Error in writing appointments.txt");
        }
    }


    private static void readAppointments() {

        try {

            Scanner scanner =
                    new Scanner(new File(APPOINTMENT_FILE));

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length >= 12) {

                    Appointment appointment = new Appointment(
                            data[0],
                            data[1],
                            data[2],
                            data[3],
                            data[4],
                            data[5],
                            data[6],
                            data[7],
                            data[8],
                            Double.parseDouble(data[9]),
                            data[10],
                            data[11]
                    );

                    allAppointments.add(appointment);
                }
            }

            scanner.close();

        } catch (Exception ex) {

            System.out.println("Error in reading appointments.txt");
        }
    }
    
    //PAYMENTS
    private static void writePayments() {

        try {

            PrintWriter writer = new PrintWriter(PAYMENT_FILE);

            for (int i = 0; i < allPayments.size(); i++) {

                Payment payment = allPayments.get(i);

                writer.println(
                        payment.getPaymentID()
                        + "|" + payment.getAppointmentID()
                        + "|" + payment.getAmount()
                        + "|" + payment.getPaymentMethod()
                        + "|" + payment.getPaymentDate()
                        + "|" + payment.getCounterStaffID()
                );
            }

            writer.close();

        } catch (Exception ex) {

            System.out.println("Error in writing payments.txt");
        }
    }


    private static void readPayments() {

        try {

            Scanner scanner =
                    new Scanner(new File(PAYMENT_FILE));

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length >= 6) {

                    Payment payment = new Payment(
                            data[0],
                            data[1],
                            Double.parseDouble(data[2]),
                            data[3],
                            data[4],
                            data[5]
                    );

                    allPayments.add(payment);
                }
            }

            scanner.close();

        } catch (Exception ex) {

            System.out.println("Error in reading payments.txt");
        }
    }
    
    //SERVICE PRICES
    private static void writeServicePrices() {

        try {

            PrintWriter writer =
                    new PrintWriter(SERVICE_PRICE_FILE);

            writer.println("MINOR|1|" + minorPrice);
            writer.println("MAJOR|3|" + majorPrice);

            writer.close();

        } catch (Exception ex) {

            System.out.println("Error in writing service_prices.txt");
        }
    }


    private static void readServicePrices() {

        try {

            Scanner scanner =
                    new Scanner(new File(SERVICE_PRICE_FILE));

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length >= 3) {

                    double price =
                            Double.parseDouble(data[2]);

                    if (data[0].equalsIgnoreCase("MINOR")) {

                        minorPrice = price;

                    } else if (data[0].equalsIgnoreCase("MAJOR")) {

                        majorPrice = price;
                    }
                }
            }

            scanner.close();

        } catch (Exception ex) {

            System.out.println(
                    "Error in reading service_prices.txt"
            );
        }
    }
    
    
    //TECHNICIAN FEEDBACK
    private static void writeTechnicianFeedback() {

        try {

            PrintWriter writer =
                    new PrintWriter(TECHNICIAN_FEEDBACK_FILE);

            for (int i = 0;
                 i < allTechnicianFeedback.size();
                 i++) {

                TechnicianFeedback feedback =
                        allTechnicianFeedback.get(i);

                writer.println(
                        feedback.getFeedbackID()
                        + "|" + feedback.getAppointmentID()
                        + "|" + feedback.getTechnicianID()
                        + "|" + feedback.getFeedbackText()
                        + "|" + feedback.getFeedbackDate()
                );
            }

            writer.close();

        } catch (Exception ex) {

            System.out.println(
                    "Error in writing technician_feedback.txt"
            );
        }
    }


    private static void readTechnicianFeedback() {

        try {

            Scanner scanner =
                    new Scanner(
                            new File(TECHNICIAN_FEEDBACK_FILE)
                    );

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length >= 5) {

                    TechnicianFeedback feedback =
                            new TechnicianFeedback(
                                    data[0],
                                    data[1],
                                    data[2],
                                    data[3],
                                    data[4]
                            );

                    allTechnicianFeedback.add(feedback);
                }
            }

            scanner.close();

        } catch (Exception ex) {

            System.out.println(
                    "Error in reading technician_feedback.txt"
            );
        }
    }
    
    
    //CUSTOMER COMMENTS
    private static void writeCustomerComments() {

        try {

            PrintWriter writer =
                    new PrintWriter(CUSTOMER_COMMENT_FILE);

            for (int i = 0;
                 i < allCustomerComments.size();
                 i++) {

                CustomerComment comment =
                        allCustomerComments.get(i);

                writer.println(
                        comment.getCommentID()
                        + "|" + comment.getAppointmentID()
                        + "|" + comment.getCustomerID()
                        + "|" + comment.getTechnicianRating()
                        + "|" + comment.getCounterStaffRating()
                        + "|" + comment.getTechnicianComment()
                        + "|" + comment.getCounterStaffComment()
                        + "|" + comment.getCommentDate()
                );
            }

            writer.close();

        } catch (Exception ex) {

            System.out.println(
                    "Error in writing customer_comments.txt"
            );
        }
    }


    private static void readCustomerComments() {

        try {

            Scanner scanner =
                    new Scanner(
                            new File(CUSTOMER_COMMENT_FILE)
                    );

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length >= 8) {

                    CustomerComment comment =
                            new CustomerComment(
                                    data[0],
                                    data[1],
                                    data[2],
                                    Integer.parseInt(data[3]),
                                    Integer.parseInt(data[4]),
                                    data[5],
                                    data[6],
                                    data[7]
                            );

                    allCustomerComments.add(comment);
                }
            }

            scanner.close();

        } catch (Exception ex) {

            System.out.println(
                    "Error in reading customer_comments.txt"
            );
        }
    }
    
    //CHECK USERNAME
    public static Staff checkUsername(String username){
        for (int i = 0; i < allStaff.size(); i++){
            Staff staff = allStaff.get(i);
            if(staff.getUsername().equalsIgnoreCase(username)){
                return staff;
            }
        }
        return null;
    }
    
    
    //CHECK USERID
    public static Staff checkUserID(String userID) {

        for (int i = 0; i < allStaff.size(); i++) {

            Staff staff = allStaff.get(i);

            if (staff.getUserID()
                    .equalsIgnoreCase(userID)) {

                return staff;
            }
        }

        return null;
    }
}
