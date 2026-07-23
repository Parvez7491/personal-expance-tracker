public class Consol{
    public static void main(String[] args) {
        TransactionManager tManager = new TransactionManager();

        tManager.add(new Expense("7-23-2026", "ice cream", 90));
        tManager.add(new Income("7-23-2026", "design submit", 1000));
        tManager.exportToFile();
    }
}