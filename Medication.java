public class Medication {
    private String medicationCode;
    private String name;
    private String brand;
    private String description;

    public Medication(String medicationCode, String name, String brand, String description) {
        this.medicationCode = medicationCode;
        this.name = name;
        this.brand = brand;
        this.description = description;
    }

    public String getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(String medicationCode) {
        this.medicationCode = medicationCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}