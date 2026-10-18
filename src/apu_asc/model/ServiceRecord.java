package apu_asc.model;

public class ServiceRecord {

    private String appointmentID;
    private String technicianID;
    private String workPerformed;
    private String partsChanged;
    private String carModifications;

    public ServiceRecord(
            String appointmentID,
            String technicianID,
            String workPerformed,
            String partsChanged,
            String carModifications) {

        this.appointmentID = appointmentID;
        this.technicianID = technicianID;
        this.workPerformed = workPerformed;
        this.partsChanged = partsChanged;
        this.carModifications = carModifications;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getTechnicianID() {
        return technicianID;
    }

    public String getWorkPerformed() {
        return workPerformed;
    }

    public String getPartsChanged() {
        return partsChanged;
    }

    public String getCarModifications() {
        return carModifications;
    }
}