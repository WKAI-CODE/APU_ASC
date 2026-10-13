package apu_asc.model;


public class TechnicianFeedback {
    private String feedbackID;
    private String appointmentID;
    private String technicianID;
    private String feedbackText;
    private String feedbackDate;
    
    public TechnicianFeedback(String feedbackID,
                              String appointmentID,
                              String technicianID,
                              String feedbackText,
                              String feedbackDate){
        
        this.feedbackID = feedbackID;
        this.appointmentID = appointmentID;
        this.technicianID = technicianID;
        this.feedbackText = feedbackText;
        this.feedbackDate = feedbackDate;
    }

    public String getFeedbackID() {
        return feedbackID;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getTechnicianID() {
        return technicianID;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }
    
}
