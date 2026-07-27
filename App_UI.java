import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class App_UI{
    static Color GREEN = Color.decode("#44867C");
    public static void main(String[] args) {
        JFrame window1 = new JFrame("Personal Tracker");

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(30, 20, 00, 20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel quick_info = new JPanel();
        quick_info.setBorder(new EmptyBorder(30, 20, 30, 20));
        quick_info.setLayout(new BoxLayout(quick_info, BoxLayout.Y_AXIS));
        quick_info.setBackground(GREEN);
        quick_info.setMaximumSize(new Dimension(410, 300));
        
        JLabel ltotal_balance = new JLabel("TOTAL BALANCE");
        ltotal_balance.setFont(new Font("Poppins", Font.PLAIN, 13));
        ltotal_balance.setForeground(Color.white);
        quick_info.add(ltotal_balance);

        TransactionManager tManager = new TransactionManager();
        double balance = tManager.balance;
        JLabel lshow_balance = new JLabel(String.format("%.2f", balance));
        lshow_balance.setFont(new Font("Poppins", Font.PLAIN, 64));
        lshow_balance.setForeground(Color.white);
        quick_info.add(lshow_balance);

        JLabel greeting = new JLabel("Good Morning");
        greeting.setFont(new Font("Poppins", Font.BOLD, 32));

        panel.add(greeting);
        panel.add(quick_info);

        window1.add(panel);
        window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window1.setSize(450, 800);
        window1.setLocationRelativeTo(null);
        window1.setVisible(true);

    }
}