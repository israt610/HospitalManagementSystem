import java.sql.*;
public class OnCall {
    private int nurseId;
    private int blockFloor;
    private int blockCode;
    private Timestamp onCallStart;
    private Timestamp onCallEnd;

    public OnCall(int nurseId, int blockFloor, int blockCode, Timestamp onCallStart, Timestamp onCallEnd) {
        this.nurseId = nurseId;
        this.blockFloor = blockFloor;
        this.blockCode = blockCode;
        this.onCallStart = onCallStart;
        this.onCallEnd = onCallEnd;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getBlockFloor() {
        return blockFloor;
    }

    public void setBlockFloor(int blockFloor) {
        this.blockFloor = blockFloor;
    }

    public int getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(int blockCode) {
        this.blockCode = blockCode;
    }

    public Timestamp getOnCallStart() {
        return onCallStart;
    }

    public void setOnCallStart(Timestamp onCallStart) {
        this.onCallStart = onCallStart;
    }

    public Timestamp getOnCallEnd() {
        return onCallEnd;
    }

    public void setOnCallEnd(Timestamp onCallEnd) {
        this.onCallEnd = onCallEnd;
    }
}
