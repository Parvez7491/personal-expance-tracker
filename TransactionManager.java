import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    final private String filename = "UserTransaction.csv";
    private List<Transaction> transactions = new ArrayList<>();

    public void add(Transaction t) {
        transactions.add(t);
    }

    public void exportToFile() {
        try (FileWriter writer = new FileWriter(this.filename)) {
            for (Transaction t : transactions) {
                writer.write(t.generateCSV());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Failed to export: " + e.getMessage());
        }
    }
}