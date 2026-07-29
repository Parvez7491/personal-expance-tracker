import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;

public class AddTransactionPanel {
    
    private static String selectedType = "Expense";

    public static JPanel getPanel(CardLayout cl, JPanel cp) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 20, 30, 20));

        JPanel typePanel new JPanel(new GridLayout(1, 2, 10, 0));
        JButton btnExpense = Style.modernButton("Expense");
        JButton btnIncome = Style.modernButton("Income");

        btnExpense.setBackground(Style.EXPENSE_RED);
        btnExpense.setForeground(Color.WHITE);
        btnExpense.setOpaque(true);

        btnIncome.setBackground(Color.WHITE);
        btnIncome.setForeground(Color.BLACK);
        btnIncome.setOpaque(true);

        typePanel.add(btnExpense);
        typePanel.add(btnIncome);
        typePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JLabel lblAmount = new JLabel("HOW MUCH?");
        lblAmount.setFont(Style.NORMAL(14));
        lblAmount.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField txtAmount = new JTextField();
        txtAmount.setFont(Style.BOLD(36));
        txtAmount.setHorizontalAlignment(JTextField.CENTER);
        txtAmount.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel lblDesc = new JLabel("DESCRIPTION");
        lblDesc.setFont(Style.NORMAL(14));
        JTextField txtDesc = new JTextField();
        txtDesc.setFont(Style.NORMAL(14));
        txtDesc.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JButton btnSave = Style.modernButton("Save Transaction");
        btnSave.setBackground(Style.GREEN);
        btnSave.setForeground(Color.WHITE);
        btnSave.setOpaque(true);

        btnExpense.addActionListener(e -> {
            selectedType = "Expense";
            btnExpense.setBackground(Style.EXPENSE_RED);
            btnExpense.setForeground(Color.WHITE);
            btnIncome.setBackground(Color.WHITE);
            btnIncome.setForeground(Color.BLACK);
        });

        btnIncome.addActionListener(e ->{
            selectedType = "Income";
            btnIncome.setBackground(Style.INCOME_GREEN);
            btnIncome.setForeground(Color.WHITE);
            btnExpense.setBackground(Color.WHITE);
            btnExpense.setForeground(Color.BLACK);
        });

        btnSave.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(txtAmount.getText());
                String desc = txtDesc.getText();
                if(desc.isEmpty()) desc = "Unknown";

                String date = LocalDate.now().toString();

                Transaction t;
                if(selectedType.equals("Expense")) {
                    t = new Expense(date, desc, amount);
                }
                else {
                    t = new Income(date, desc, amount);
                }

                App_UI.tManager.add(t);
                JOptionPane.showMessageDialog(mainPanel, "Transaction Saved Successfully!");

                App_UI.refreshUI();
                cl.show(cp, "HOME");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.add(typePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(lblAmount);
        mainPanel.add(txtAmount);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(lblDesc);
        mainPanel.add(txtDesc);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(btnSave);

        return mainPanel;
    }

}
