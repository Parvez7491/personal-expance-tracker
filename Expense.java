public class Expense extends Transaction {
    public Expense(String d, String cat, String des, double a) {
        super(d, cat, des, a);
    }

    public double calculateImpact() {
        return -this.getAmount();
    }

}