abstract class Transaction implements Exportable {
    private String date;
    private String description;
    private double amount;

    Transaction(String d, String des, double a) {
        //logic
        this.date = d;
        this.description = des;
        this.amount = a;

    }

    @Override
    public String generateCSV() {
        return date + "," + getClass().getSimpleName() + ",\"" + description + "\"," + amount; // "basa vara, bua bill"
    }

    // getter
    public double getAmount() {
        return this.amount;
    }

    public String getDate() {
        return this.date;
    }

    public String getDes() {
        return this.description;
    }

    // abstract methods
    abstract double calculateImpact();
}