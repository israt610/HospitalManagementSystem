import java.sql.*;
public class Stay {
    private int stayId;
    private int patientId;
    private int roomId;
    private Timestamp startTime;
    private Timestamp endTime;

    public Stay(int stayId, int patientId, int roomId, Timestamp startTime, Timestamp endTime) {
        this.stayId = stayId;
        this.patientId = patientId;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStayId() {
        return stayId;
    }

    public void setStayId(int stayId) {
        this.stayId = stayId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
}