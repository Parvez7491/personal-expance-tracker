import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    final private String filename = "UserTransaction.csv";
    public double balance;
    public double income;
    public double spent;
    public List<Transaction> transactions = new ArrayList<>();

    public TransactionManager() {
        this.loadFromCSV();
        this.recalculateTotals();
    }

    public void add(Transaction t) {
        transactions.add(t);
        updateTotalsWith(t);
        
        try (FileWriter writer = new FileWriter(this.filename, true)) {
            writer.write(t.generateCSV());
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Failed to export: " + e.getMessage());
        }
    }

    public void edit(int index, Transaction updatedTransaction) {
        if (index >= 0 && index < transactions.size()) {
            transactions.set(index, updatedTransaction);
            recalculateTotals();
            rewriteCSV();
        }
    }

    public void delete(int index) {
        if (index >= 0 && index < transactions.size()) {
            transactions.remove(index);
            recalculateTotals();
            rewriteCSV();
        }
    }

    private void rewriteCSV() {
        try (FileWriter writer = new FileWriter(this.filename, false)) {
            for (Transaction t : transactions) {
                writer.write(t.generateCSV());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Failed to rewrite CSV: " + e.getMessage());
        }
    }

    private void recalculateTotals() {
        income = 0;
        spent = 0;
        for (Transaction a : transactions) {
            if (a.calculateImpact() < 0) {
                spent += -a.calculateImpact();
            } else {
                income += a.calculateImpact();
            }
        }
        balance = income - spent;
    }

    private void updateTotalsWith(Transaction t) {
        if (t.calculateImpact() < 0) {
            spent += -t.calculateImpact();
        } else {
            income += t.calculateImpact();
        }
        balance = income - spent;
    }

    private void loadFromCSV() {
        File file = new File(filename);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String raw;
            while ((raw = br.readLine()) != null) {
                if (raw.trim().isEmpty()) continue;
                
                String[] fetch = raw.split(",");
                if (fetch.length >= 5) {
                    String date = fetch[0];
                    String type = fetch[1];
                    String category = fetch[2];
                    String description = fetch[3].replace("\"", "");
                    double amount = Double.parseDouble(fetch[4]);

                    if (type.equals("Expense")) {
                        transactions.add(new Expense(date, description, amount));
                    } else {
                        transactions.add(new Income(date, description, amount));
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Failed to read: " + e.getMessage());
        }
    }
}