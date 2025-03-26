import java.sql.*;
public class Undergoes {
    private int patientId;
    private int procedureDetails;
    private int stayId;
    private Date date;
    private int physicianId;
    private int assistingNurseId;

    public Undergoes(int patientId, int procedureDetails, int stayId, Date date, int physicianId, int assistingNurseId) {
        this.patientId = patientId;
        this.procedureDetails = procedureDetails;
        this.stayId = stayId;
        this.date = date;
        this.physicianId = physicianId;
        this.assistingNurseId = assistingNurseId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getProcedureDetails() {
        return procedureDetails;
    }

    public void setProcedureDetails(int procedureDetails) {
        this.procedureDetails = procedureDetails;
    }

    public int getStayId() {
        return stayId;
    }

    public void setStayId(int stayId) {
        this.stayId = stayId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public int getAssistingNurseId() {
        return assistingNurseId;
    }

    public void setAssistingNurseId(int assistingNurseId) {
        this.assistingNurseId = assistingNurseId;
    }
}