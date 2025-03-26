public class Department {
    private int departmentId;
    private String name;
    private String head;

    public Department(int departmentId, String name, String head) {
        this.departmentId = departmentId;
        this.name = name;
        this.head = head;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}