public class Nurse {
    private int employeeId;
    private String name;
    private String position;
    private boolean registered;
    private String socialSecurityNumber;

    public Nurse(int employeeId, String name, String position, boolean registered, String socialSecurityNumber) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.registered = registered;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
}