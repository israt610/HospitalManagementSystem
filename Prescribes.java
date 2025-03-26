import java.sql.*;
public class Prescribes {
    private int physicianId;
    private int patientId;
    private String medicationCode;
    private Date prescriptionDate;
    private int appointmentId;
    private String dose;

    public Prescribes(int physicianId, int patientId, String medicationCode, Date prescriptionDate, int appointmentId, String dose) {
        this.physicianId = physicianId;
        this.patientId = patientId;
        this.medicationCode = medicationCode;
        this.prescriptionDate = prescriptionDate;
        this.appointmentId = appointmentId;
        this.dose = dose;
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(String medicationCode) {
        this.medicationCode = medicationCode;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }
}
