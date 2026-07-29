public class Expense extends Transaction {
    public Expense(String d, String des, double a) {
        super(d, des, a);
    }

    public double calculateImpact() {
        return -this.getAmount();
    }

}