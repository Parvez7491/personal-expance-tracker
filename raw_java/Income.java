package raw_java;

public class Income extends Transaction {
    public Income(String date, String des, double amount) {
        super(date, des, amount);
    }

    public double calculateImpact() {
        return this.getAmount();
    }

}