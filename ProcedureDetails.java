class ProcedureDetails {
    private String procedure_code;
    private String name;
    private double cost;

    public ProcedureDetails(String procedure_code, String name, double cost) {
        this.procedure_code = procedure_code;
        this.name = name;
        this.cost = cost;
    }

    public String getProcedureCode() {
        return procedure_code;
    }

    public void setProcedure_code(String procedure_code) {
        this.procedure_code = procedure_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}