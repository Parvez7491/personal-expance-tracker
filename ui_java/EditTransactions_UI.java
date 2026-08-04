package ui_java;
import raw_java.*;

import java.awt.*;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EditTransactions_UI {
    Transaction t;
    int index;

    String selectedType;
    JLabel selectType = new JLabel("Select Type");
    JPanel type = new JPanel(); // 2 buttons
    JButton btnExpense = Style.modernButton("Expense");
    JButton btnIncome = Style.modernButton("Income");

    InputField date = new InputField("Date");
    InputField des = new InputField("Description");
    InputField amount = new InputField("Amount");

    JPanel cancelAndSave = new JPanel();
    JButton btnCancle = Style.modernButton("Cancle"),
            btnSave = Style.modernButton("Save"),
            btnDelete = Style.modernButton("Delete Transaction");

    void loadTransaction(Transaction tr, int index) {
        this.t = tr;
        this.index = index;

        date.inputText.setText(t.getDate());
        des.inputText.setText(t.getDes());
        amount.inputText.setText(String.format("%.2f", t.getAmount()));

        if (t.getClass().getSimpleName().equals("Income")) {
            selectedType = "Income";
            btnIncome.setBackground(Style.GREEN);
            btnIncome.setForeground(Color.white);
            btnExpense.setBackground(Color.white);
            btnExpense.setForeground(Color.black);
        } else {
            selectedType = "Expense";
            btnIncome.setBackground(Color.white);
            btnIncome.setForeground(Color.black);
            btnExpense.setBackground(Style.GREEN);
            btnExpense.setForeground(Color.white);
        }
    }

    EditTransactions_UI() {
        btnIncome.addActionListener(e -> {
            selectedType = "Income";
            btnIncome.setBackground(Style.GREEN);
            btnIncome.setForeground(Color.white);

            btnExpense.setBackground(Color.white);
            btnExpense.setForeground(Color.black);
        });

        btnExpense.addActionListener(e -> {
            selectedType = "Expense";
            btnIncome.setBackground(Color.white);
            btnIncome.setForeground(Color.black);

            btnExpense.setBackground(Style.GREEN);
            btnExpense.setForeground(Color.white);
        });

        btnSave.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amount.inputText.getText());
                String desc = des.inputText.getText();
                if (desc.isEmpty())
                    desc = "Unknown";

                String dat = date.inputText.getText().trim();
                if (dat.isEmpty())
                    dat = LocalDate.now().toString();

                try {
                    LocalDate.parse(dat);
                } catch (java.time.format.DateTimeParseException dtpe) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Date! Please use YYYY-MM-DD format (e.g., " + LocalDate.now().toString() + ").",
                            "Date Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (selectedType.equals("Expense")) {
                    App_UI.tManager.edit(index, new Expense(dat, desc, amt));
                } else {
                    App_UI.tManager.edit(index, new Income(dat, desc, amt));
                }

                App_UI.refreshUI();
                App_UI.switchToHome();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount!", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancle.addActionListener(e -> {
            App_UI.switchToHome();
        });

        btnDelete.addActionListener(e -> {
            App_UI.tManager.delete(index);
            App_UI.refreshUI();
            App_UI.switchToHome();
        });
    }

    JPanel getUI(CardLayout cl, JPanel cp) {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(10, 20, 10, 20));

        // select type
        selectType.setFont(Style.NORMAL(20));
        type.setLayout(new GridLayout(1, 2));
        type.add(btnExpense);
        type.add(btnIncome);
        type.setAlignmentX(Component.LEFT_ALIGNMENT);
        type.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        type.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));

        

        // cancle and save
        cancelAndSave.setLayout(new GridLayout(1, 2));
        cancelAndSave.setAlignmentX(Component.LEFT_ALIGNMENT);
        cancelAndSave.add(btnCancle);
        cancelAndSave.add(btnSave);
        cancelAndSave.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
        cancelAndSave.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        // save
        btnSave.setBackground(Style.INCOME_GREEN);
        btnSave.setForeground(Color.white);

        // cancle
        

        // delete
        btnDelete.setBackground(Style.EXPENSE_RED);
        btnDelete.setForeground(Color.white);
        btnDelete.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        

        // adding to main
        int gap = 25;
        main.add(selectType);
        main.add(type);
        main.add(Box.createVerticalStrut(gap));

        main.add(date.getUI());
        main.add(Box.createVerticalStrut(gap));

        main.add(des.getUI());
        main.add(Box.createVerticalStrut(gap));

        main.add(amount.getUI());
        main.add(Box.createVerticalStrut(gap));

        main.add(cancelAndSave);
        main.add(Box.createVerticalStrut(gap - 7));

        main.add(btnDelete);

        return main;
    }

}

class InputField {
    JLabel text = new JLabel();
    JTextField inputText = new JTextField();

    InputField(String title) {
        text.setText(title);
        text.setFont(Style.NORMAL(20));
        text.setAlignmentX(Component.LEFT_ALIGNMENT);

        inputText.setBackground(Style.FIELD_COLOR);
        inputText.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputText.setOpaque(true);
        inputText.setBorder(null);
        inputText.setFont(Style.NORMAL(20));
        inputText.setBorder(new EmptyBorder(10, 20, 10, 20));
        inputText.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
        inputText.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
    }

    JPanel getUI() {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(text);
        main.add(inputText);

        main.setAlignmentX(Component.LEFT_ALIGNMENT);
        return main;
    }

}