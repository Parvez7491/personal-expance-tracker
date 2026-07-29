public class Income extends Transaction {
    public Income(String date, String cat, String des, double amount) {
        super(date, cat, des, amount);
    }

    public double calculateImpact() {
        return this.getAmount();
    }

}