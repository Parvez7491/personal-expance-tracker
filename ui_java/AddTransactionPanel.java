package ui_java;
import raw_java.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;

public class AddTransactionPanel {

    private static String selectedType = "Expense";

    public static JPanel getPanel(CardLayout cl, JPanel cp) {
        
        selectedType = "Expense";
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 20, 30, 20));
        mainPanel.setBackground(Style.OFF_WHITE);

        JPanel typePanel = new JPanel(new GridLayout(1, 2, 10, 0));
        typePanel.setBackground(Style.OFF_WHITE);
        typePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        typePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnExpense = new JButton("Expense");
        JButton btnIncome = new JButton("Income");
        
        styleToggleButton(btnExpense, true);
        styleToggleButton(btnIncome, false);

        typePanel.add(btnExpense);
        typePanel.add(btnIncome);

        Color fieldBgColor = new Color(194, 217, 210);

        JLabel lblDate = new JLabel("Date");
        lblDate.setFont(Style.NORMAL(16));
        lblDate.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField txtDate = createStyledTextField(fieldBgColor);
        txtDate.setText(LocalDate.now().toString());

        JLabel lblDesc = new JLabel("Description");
        lblDesc.setFont(Style.NORMAL(16));
        lblDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField txtDesc = createStyledTextField(fieldBgColor);

        JLabel lblAmount = new JLabel("Amount");
        lblAmount.setFont(Style.NORMAL(16));
        lblAmount.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField txtAmount = createStyledTextField(fieldBgColor);

        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        actionPanel.setBackground(Style.OFF_WHITE);
        actionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        actionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnCancel = new JButton("Cancel");
        styleActionButton(btnCancel, Style.EXPENSE_RED);

        JButton btnSave = new JButton("Save");
        styleActionButton(btnSave, Style.INCOME_GREEN);

        actionPanel.add(btnCancel);
        actionPanel.add(btnSave);

        btnExpense.addActionListener(e -> {
            selectedType = "Expense";
            styleToggleButton(btnExpense, true);
            styleToggleButton(btnIncome, false);
        });

        btnIncome.addActionListener(e -> {
            selectedType = "Income";
            styleToggleButton(btnIncome, true);
            styleToggleButton(btnExpense, false);
        });

        btnCancel.addActionListener(e -> {
            txtAmount.setText("");
            txtDesc.setText("");
            txtDate.setText(LocalDate.now().toString());
            
            App_UI.switchToHome(); 
        });

        btnSave.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(txtAmount.getText());
                String desc = txtDesc.getText();
                if (desc.isEmpty()) desc = "Unknown";
                
                String date = txtDate.getText().trim();
                if (date.isEmpty()) date = LocalDate.now().toString();

                try {
                    LocalDate.parse(date); 
                } catch (java.time.format.DateTimeParseException dtpe) {
                    JOptionPane.showMessageDialog(mainPanel, 
                        "Invalid Date! Please use YYYY-MM-DD format (e.g., " + LocalDate.now().toString() + ").", 
                        "Date Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Transaction t;
                if (selectedType.equals("Expense")) {
                    t = new Expense(date, desc, amount);
                } else {
                    t = new Income(date, desc, amount);
                }

                App_UI.tManager.add(t);
                
                txtAmount.setText("");
                txtDesc.setText("");
                txtDate.setText(LocalDate.now().toString());
                
                App_UI.refreshUI();
                App_UI.switchToHome(); 

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Please enter a valid numeric amount!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.add(typePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(lblDate);
        mainPanel.add(txtDate);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(lblDesc);
        mainPanel.add(txtDesc);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(lblAmount);
        mainPanel.add(txtAmount);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(actionPanel);

        return mainPanel;
    }
    
    private static void styleToggleButton(JButton btn, boolean isSelected) {
        btn.setFont(Style.NORMAL(16));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (isSelected) {
            btn.setBackground(Style.GREEN); 
            btn.setForeground(Color.WHITE);
            btn.setBorder(BorderFactory.createEmptyBorder());
        } else {
            btn.setBackground(Color.WHITE);
            btn.setForeground(Color.BLACK);
            btn.setBorder(BorderFactory.createLineBorder(Style.GREEN, 1));
        }
        btn.setOpaque(true);
    }

    private static void styleActionButton(JButton btn, Color bgColor) {
        btn.setFont(Style.NORMAL(18));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setOpaque(true);
    }

    private static JTextField createStyledTextField(Color bgColor) {
        JTextField txt = new JTextField();
        txt.setFont(Style.NORMAL(16));
        txt.setBackground(bgColor);
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txt.setAlignmentX(Component.LEFT_ALIGNMENT);
        return txt;
    }
}