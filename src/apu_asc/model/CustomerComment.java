package apu_asc.model;


public class CustomerComment {
    private String commentID;
    private String appointmentID;
    private String customerID;
    private int technicianRating;
    private int counterStaffRating;
    private String technicianComment;
    private String counterStaffComment;
    private String commentDate;
    
    public CustomerComment(String commentID,
                           String appointmentID,
                           String customerID,
                           int technicianRating,
                           int counterStaffRating,
                           String technicianComment,
                           String counterStaffComment,
                           String commentDate) {

        this.commentID = commentID;
        this.appointmentID = appointmentID;
        this.customerID = customerID;
        this.technicianRating = technicianRating;
        this.counterStaffRating = counterStaffRating;
        this.technicianComment = technicianComment;
        this.counterStaffComment = counterStaffComment;
        this.commentDate = commentDate;
    }

    public String getCommentID() {
        return commentID;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public int getTechnicianRating() {
        return technicianRating;
    }

    public int getCounterStaffRating() {
        return counterStaffRating;
    }

    public String getTechnicianComment() {
        return technicianComment;
    }

    public String getCounterStaffComment() {
        return counterStaffComment;
    }

    public String getCommentDate() {
        return commentDate;
    }
    
            
}
