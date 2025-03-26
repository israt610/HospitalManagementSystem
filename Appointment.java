import java.sql.*;
public class Appointment {
    private int appointmentId;
    private int patientId;
    private int prepNurseId;
    private int physicianId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String examinationRoom;

    public Appointment(int appointmentId, int patientId, int prepNurseId, int physicianId, Timestamp startTime, Timestamp endTime, String examinationRoom) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.prepNurseId = prepNurseId;
        this.physicianId = physicianId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.examinationRoom = examinationRoom;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPrepNurseId() {
        return prepNurseId;
    }

    public void setPrepNurseId(int prepNurseId) {
        this.prepNurseId = prepNurseId;
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getExaminationRoom() {
        return examinationRoom;
    }

    public void setExaminationRoom(String examinationRoom) {
        this.examinationRoom = examinationRoom;
    }
}
