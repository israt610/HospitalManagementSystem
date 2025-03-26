public class Patient {
    private String social_security_number;
    private String name;
    private String address;
    private String phone;
    private String insuranceId;
    private int primaryCarePhysician;

    public Patient(String social_security_number, String name, String address, String phone, String insuranceId, int primaryCarePhysician) {
        this.social_security_number = social_security_number;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.insuranceId = insuranceId;
        this.primaryCarePhysician = primaryCarePhysician;
    }

    public String getSocial_security_number() {
        return social_security_number;
    }

    public void setSocial_security_number(String social_security_number) {
        this.social_security_number = social_security_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public int getPrimaryCarePhysician() {
        return primaryCarePhysician;
    }

    public void setPrimaryCarePhysician(int primaryCarePhysician) {
        this.primaryCarePhysician = primaryCarePhysician;
    }
}
