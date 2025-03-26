public class Physician {
    private int employee_id;
    private String name;
    private String position;
    private String social_security_number;

    public Physician(int employee_id, String name, String position, String social_security_number) {
        this.employee_id = employee_id;
        this.name = name;
        this.position = position;
        this.social_security_number = social_security_number;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
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

    public String getSocial_security_number() {
        return social_security_number;
    }

    public void setSocial_security_number(String social_security_number) {
        this.social_security_number = social_security_number;
    }
}
