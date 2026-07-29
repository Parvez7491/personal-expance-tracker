abstract class Transaction implements Exportable {
    private String date;
    private String category;
    private String description;
    private double amount;

    Transaction(String d, String cat, String des, double a) {
        //logic
        this.date = d;
        this.category = cat;
        this.description = des;
        this.amount = a;

    }

    @Override
    public String generateCSV() {
        return date + "," + getClass().getSimpleName() + "," + category + ",\"" + description + "\"," + amount; // "basa vara, bua bill"
    }

    // getter
    public double getAmount() {
        return this.amount;
    }

    public String getDate() {
        return this.date;
    }

    public String getCategory()
    {
        return this.category;
    }

    public String getDes() {
        return this.description;
    }

    // abstract methods
    abstract double calculateImpact();
}