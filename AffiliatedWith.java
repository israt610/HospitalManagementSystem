public class AffiliatedWith {
    private int physicianId;
    private int departmentId;
    private boolean primaryAffiliation;

    public AffiliatedWith(int physicianId, int departmentId, boolean primaryAffiliation) {
        this.physicianId = physicianId;
        this.departmentId = departmentId;
        this.primaryAffiliation = primaryAffiliation;
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public boolean isPrimaryAffiliation() {
        return primaryAffiliation;
    }

    public void setPrimaryAffiliation(boolean primaryAffiliation) {
        this.primaryAffiliation = primaryAffiliation;
    }
}
