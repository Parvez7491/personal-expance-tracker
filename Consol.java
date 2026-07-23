public class Consol{
    public static void main(String[] args) {
        TransactionManager tManager = new TransactionManager();

        tManager.add(new Expense("7-23-2026", "kisu na", 900));
        tManager.exportToFile();
    }
}