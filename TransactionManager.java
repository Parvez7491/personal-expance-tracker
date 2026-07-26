import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    final private String filename = "UserTransaction.csv";
    private List<Transaction> transactions = new ArrayList<>();

    public TransactionManager() {
        this.loadFromCSV();
    }

    public void add(Transaction t) {
        transactions.add(t);
        try (FileWriter writer = new FileWriter(this.filename, true)) {
            writer.write(t.generateCSV());
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Failed to export: " + e.getMessage());
        }

    }

    private void loadFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String raw;
            while((raw = br.readLine()) != null){
                String fetch[] = raw.split(",");
                if (fetch[1] == "Expense") {
                    transactions.add(new Expense(fetch[0], fetch[2], Double.parseDouble(fetch[3])));
                } else {
                    transactions.add(new Income(fetch[0], fetch[2], Double.parseDouble(fetch[3])));
                }
            }

        } catch (IOException e) {
            System.out.println("Failed to read: " + e.getMessage());
        }
    }

    public List<Transaction> geTransactions() {
        return transactions;
    }

}