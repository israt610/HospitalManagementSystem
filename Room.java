public class Room {
    private int room_number;
    private String roomtype;
    private String blockfloor;
    private String blockcode;
    private boolean unavailable;

    public Room(int room_number, String roomtype, String blockfloor, String blockcode, boolean unavailable) {
        this.room_number = room_number;
        this.roomtype = roomtype;
        this.blockfloor = blockfloor;
        this.blockcode = blockcode;
        this.unavailable = unavailable;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getBlockfloor() {
        return blockfloor;
    }

    public void setBlockfloor(String blockfloor) {
        this.blockfloor = blockfloor;
    }

    public String getBlockcode() {
        return blockcode;
    }

    public void setBlockcode(String blockcode) {
        this.blockcode = blockcode;
    }

    public boolean isUnavailable() {
        return unavailable;
    }

    public void setUnavailable(boolean unavailable) {
        this.unavailable = unavailable;
    }
}