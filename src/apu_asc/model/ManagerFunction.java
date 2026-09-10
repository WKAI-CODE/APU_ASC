package apu_asc.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import utility.DataIO;

public class ManagerFunction{
    
    private final DecimalFormat df = new DecimalFormat("0.00");
    
    //1.ADD STAFF
    public Staff addStaff(
            String role,
            String userID,
            String username,
            String name,
            String phoneNumber,
            int age,
            String identityNumber,
            String email,
            String address){
        
        userID = userID.toUpperCase().trim();
        username = username.trim();
        
        // CHECK DUPLICATE USER ID
        if (findStaff(userID) != null) {
            return null;
        }

        // CHECK DUPLICATE USERNAME
        if (findUsername(username) != null) {
            return null;
        }
        
        // DEFAULT PASSWORD
        // password = username + userID
        String password = username + userID;
        
        //CREATE STAFF OBJECT
        Staff staff;
        
        if(role.equals("Manager")){
            staff = new Manager(
                    userID,
                    username,
                    password,
                    name,
                    phoneNumber,
                    age,
                    identityNumber,
                    email,
                    address
            );
            
        }else if(role.equals("CounterStaff")){
            staff = new CounterStaff(
                    userID,
                    username,
                    password,
                    name,
                    phoneNumber,
                    age,
                    identityNumber,
                    email,
                    address
            );
        }else if (role.equals("Technician")){
            staff = new Technician(
                    userID,
                    username,
                    password,
                    name,
                    phoneNumber,
                    age,
                    identityNumber,
                    email,
                    address
            );

        }else{
            return null;
        }
        
        DataIO.allStaff.add(staff);
        DataIO.write();
        return staff;
    }
    
    

        
        
        //FIND STAFF BY USER ID
        public Staff findStaff(String userID){
            for(Staff staff : DataIO.allStaff){
                if(staff.getUserID().equalsIgnoreCase(userID)){
                    return staff;
                }
            }
            return null;
        }
        
        
        //FIND STAFF BY USERNAME
        public Staff findUsername(String username){
            for (Staff staff: DataIO.allStaff){
                if (staff.getUsername().equalsIgnoreCase(username)){
                    return staff;
                }
            }
            return null;
                
        }
        
        
        //AUTO GENERATE USERID
        public String generateUserID(String role) {

            String prefix;

            if (role.equals("Manager")) {

                prefix = "M";

            } else if (role.equals("CounterStaff")) {

                prefix = "C";

            } else if (role.equals("Technician")) {

                prefix = "T";

            } else {

                return "";
            }
            
            int number = 1;
            
            while(true){
                String userID = prefix + String.format("%03d", number);
                
                if(findStaff(userID) == null){
                    return userID;
                }
                
                number ++;
            }
        }
        
        
    //2 VIEW STAFF
    public ArrayList<Staff> getAllStaff(){
        return DataIO.allStaff;
    }
    
    
    //3.UPDATE STAFF
    public Staff updateStaff(String userID, String field, String newValue){
        
        Staff staff = findStaff(userID);
        
        if(staff == null){
            return null;
        }
        
        if(field.equals("Username")){
            staff.setUsername(newValue);
            
        }else if (field.equals("Name")){
            staff.setName(newValue);
            
        }else if(field.equals("Phone Number")){
            staff.setPhoneNumber(newValue);
            
        }else if(field.equals("Age")){
            
            try{
                int age = Integer.parseInt(newValue);
                
                if(age <= 0){
                    return null;
                }
                
                staff.setAge(age);
            }catch (NumberFormatException e){
                return null;
                
            }
            
            
        }else if(field.equals("Identity Number")){
            staff.setIdentityNumber(newValue);
            
        }else if(field.equals("Email")){
            staff.setEmail(newValue);
            
        }else if(field.equals("Address")){
            staff.setAddress(newValue);
            
        }else{
            return null;
            
        }
        
        DataIO.write();
        return staff;
    
    }
    
    
    
    //DELETE STAFF
    public Staff deleteStaff(String userID){
        Staff staff = findStaff(userID);
        
        if(staff == null){
            return null;
        }
        
        DataIO.allStaff.remove(staff);
        
        DataIO.write();
        return staff;
        
    }
    
    
    //SET SERVICE PRICE
    public String setServicePrice(double minorPrice, double majorPrice){
        if(minorPrice < 0 || majorPrice < 0){
            return "Invalid Price. ";
        }
        
        DataIO.minorPrice = minorPrice;
        DataIO.majorPrice = majorPrice;
        
        DataIO.write();
        
        return "Service prices updated succesfully";
        
    }
    
    
    
    //GET TECHNICIAN FEEDBACK
    public ArrayList<String> getTechnicianFeedback(){
        ArrayList<String> result = new ArrayList<String>();
        
        for(TechnicianFeedback feedback : DataIO.allTechnicianFeedback){
            
            String line = 
                    feedback.getTechnicianID() + "|"
                    + feedback.getAppointmentID() + "|"
                    + feedback.getTechnicianID() + "|"
                    + feedback.getFeedbackText() + "|"
                    + feedback.getFeedbackDate();
            
            result.add(line);
        }
        
        return result;
    }
    
    
    //GET CUSTOMER COMMENTS
    public ArrayList<String> getCustomerComments(){
        ArrayList<String> result = new ArrayList<String>();
        
        for(CustomerComment comment: DataIO.allCustomerComments) {
            String line = 
                    comment.getCommentID() + "|"
                    + comment.getAppointmentID() + "|"
                    + comment.getCustomerID() + "|"
                    + comment.getTechnicianRating() + "|"
                    + comment.getCounterStaffRating() + "|"
                    + comment.getTechnicianComment() + "|"
                    + comment.getCounterStaffComment() + "|"
                    + comment.getCommentDate();
            
            result.add(line);
                              
            
        }
        
        return result;
    }
    
    
    //APPOINTMENT STATUS REPORT
    public String appointmentStatusReport() {

        int booked = 0;
        int completed = 0;
        int cancelled = 0;

        for (Appointment appointment :
                DataIO.allAppointments) {

            String status =
                    appointment.getAppointmentStatus();

            if (status.equals("BOOKED")) {
                booked++;
            } else if (status.equals("COMPLETED")) {
                completed++;
            } else if (status.equals("CANCELLED")) {
                cancelled++;
            }
        }

        int total = booked + completed + cancelled;

        double completionRate = 0;
        double cancellationRate = 0;

        if (total > 0) {

            completionRate =
                    completed * 100.0 / total;

            cancellationRate =
                    cancelled * 100.0 / total;
        }

        return "===== APPOINTMENT STATUS SUMMARY =====\n\n"
                + "Total Appointments : " + total + "\n"
                + "BOOKED             : " + booked + "\n"
                + "COMPLETED          : " + completed + "\n"
                + "CANCELLED          : " + cancelled + "\n\n"
                + "Completion Rate    : "
                + df.format(completionRate) + "%\n"
                + "Cancellation Rate  : "
                + df.format(cancellationRate) + "%";
    }
    
    
    //SERVICE ANALYSIS REPORT
    public String serviceAnalysisReport(){
        
        int minor = 0;
        int major = 0;
        
        for(Appointment appointment : DataIO.allAppointments){
            
            String serviceType = appointment.getServiceType();
            
            if(serviceType.equals("MINOR")){
                minor ++;
            }else if(serviceType.equals("MAJOR")){
                major ++;
            }
        
        }
        
        int total = major + major;
        
        double minorPercentage = 0;
        double majorPercentage = 0;
        
        if(total > 0){
            minorPercentage = minor * 100.0 /total;
            
            majorPercentage = major * 100.0 / total;
            
        }
        return "===== SERVICE ANALYSIS =====\n\n"
                + "MINOR Services    : " + minor + "\n"
                + "MAJOR Services    : " + major + "\n"
                + "Total Services    : " + total + "\n\n"
                + "MINOR Percentage  : "
                + df.format(minorPercentage) + "%\n"
                + "MAJOR Percentage  : "
                + df.format(majorPercentage) + "%";
    }
    
    
    //REVENUE REPORT
    public String revenueReport() {

        double paidRevenue = 0;
        double unpaidAmount = 0;

        int paidCount = 0;
        int unpaidCount = 0;

        for (Appointment appointment :
                DataIO.allAppointments) {

            String appointmentID =
                    appointment.getAppointmentID();

            String paymentStatus =
                    appointment.getPaymentStatus();

            double amount = 0;

            for (Payment payment :
                    DataIO.allPayments) {

                if (payment.getAppointmentID()
                        .equals(appointmentID)) {

                    amount = payment.getAmount();
                    break;
                }
            }

            if (paymentStatus.equals("PAID")) {

                paidRevenue += amount;
                paidCount++;

            } else if (paymentStatus.equals("UNPAID")) {

                unpaidAmount =
                        unpaidAmount
                        + appointment.getServicePrice();

                unpaidCount++;
            }
        }

        return "===== REVENUE REPORT =====\n\n"
                + "Paid Revenue        : RM "
                + df.format(paidRevenue) + "\n"
                + "Unpaid Amount       : RM "
                + df.format(unpaidAmount) + "\n\n"
                + "Paid Appointments   : "
                + paidCount + "\n"
                + "Unpaid Appointments : "
                + unpaidCount;
    }
    
    
    //TECHNICIAN PERFORMANCE REPORT
    public String technicianPerformanceReport() {

        StringBuilder output =
                new StringBuilder();

        output.append(
                "===== TECHNICIAN PERFORMANCE =====\n\n");

        boolean technicianFound = false;

        for (Staff staff : DataIO.allStaff) {

            if (!staff.getRole()
                    .equals("Technician")) {

                continue;
            }

            technicianFound = true;

            String technicianID =
                    staff.getUserID();

            String technicianName =
                    staff.getName();

            int assigned = 0;
            int completed = 0;

            for (Appointment appointment :
                    DataIO.allAppointments) {

                if (appointment.getTechnicianID()
                        .equals(technicianID)) {

                    assigned++;

                    if (appointment
                            .getAppointmentStatus()
                            .equals("COMPLETED")) {

                        completed++;
                    }
                }
            }

            double completionRate = 0;

            if (assigned > 0) {

                completionRate =
                        completed * 100.0 / assigned;
            }

            output.append(
                    "------------------------------------\n");

            output.append(
                    "Technician ID          : ")
                    .append(technicianID)
                    .append("\n");

            output.append(
                    "Technician Name        : ")
                    .append(technicianName)
                    .append("\n");

            output.append(
                    "Assigned Appointments  : ")
                    .append(assigned)
                    .append("\n");

            output.append(
                    "Completed Appointments : ")
                    .append(completed)
                    .append("\n");

            output.append(
                    "Completion Rate        : ")
                    .append(df.format(completionRate))
                    .append("%\n\n");
        }

        if (!technicianFound) {
            output.append("No technicians found.");
        }

        return output.toString();
    }
    
    
    
    //CHANGE PASSWORD
    public String changePassword(
            String userID,
            String currentPassword,
            String newPassword) {

        Staff staff = findStaff(userID);

        if (staff == null) {
            return "Staff not found.";
        }

        if (!staff.getPassword()
                .equals(currentPassword)) {

            return "Current password is incorrect.";
        }

        if (newPassword == null
                || newPassword.trim().isEmpty()) {

            return "New password cannot be empty.";
        }

        staff.setPassword(newPassword);

        DataIO.write();

        return "Password changed successfully.";
    }
}