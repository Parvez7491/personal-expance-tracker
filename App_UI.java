import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class App_UI {

    public static TransactionManager tManager = new TransactionManager();

    public static void main(String[] args) {
        JFrame window = new JFrame("Expense Tracker");
        window.setLayout(new BorderLayout());

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        JPanel temp = new JPanel();
        temp.setBackground(Color.red);

        cardPanel.add(homePanel(), "HOME");
        cardPanel.add(temp, "RED");

        // buttons

        // adding to main window
        window.add(cardPanel, BorderLayout.CENTER);
        window.add(navbar(cardLayout, cardPanel), BorderLayout.SOUTH);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(450, 800);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static JPanel navbar(CardLayout cl, JPanel cp) {
        JPanel navbar = new JPanel(new GridLayout(1, 0));

        JButton home = Style.modernButton("Home");
        home.setBackground(Style.GREEN);
        home.setOpaque(true);
        home.setForeground(Color.white);
        JButton add = Style.modernButton("Add");
        JButton about = Style.modernButton("About");

        navbar.add(home);
        navbar.add(add);
        navbar.add(about);

        home.addActionListener(e -> {
            home.setBackground(Style.GREEN);
            home.setOpaque(true);
            home.setForeground(Color.white);

            add.setBackground(Color.white);
            add.setOpaque(true);
            add.setForeground(Color.black);

            about.setBackground(Color.white);
            about.setOpaque(true);
            about.setForeground(Color.black);

            cl.show(cp, "HOME");
        });
        add.addActionListener(e -> {
            add.setBackground(Style.GREEN);
            add.setOpaque(true);
            add.setForeground(Color.white);

            home.setBackground(Color.white);
            home.setOpaque(true);
            home.setForeground(Color.black);

            about.setBackground(Color.white);
            about.setOpaque(true);
            about.setForeground(Color.black);

            cl.show(cp, "RED");
        });
        about.addActionListener(e -> {
            about.setBackground(Style.GREEN);
            about.setOpaque(true);
            about.setForeground(Color.white);

            home.setBackground(Color.white);
            home.setOpaque(true);
            home.setForeground(Color.black);

            add.setBackground(Color.white);
            add.setOpaque(true);
            add.setForeground(Color.black);
        });

        return navbar;

    }

    public static JPanel homePanel() {
        JPanel main_panel = new JPanel();
        main_panel.setBorder(new EmptyBorder(30, 20, 30, 20));
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));

        JLabel greeting = new JLabel("GOOD MORNING");
        greeting.setFont(Style.BOLD(24));

        JLabel temp = new JLabel("TEMP");
        temp.setForeground(new Color(1, 1, 1, 0));
        temp.setFont(Style.NORMAL(26));

        JLabel text_recent = new JLabel("Recent Transactions");
        text_recent.setFont(Style.NORMAL(24));

        // adding to main panel
        main_panel.add(greeting);
        main_panel.add(balanceSection());
        main_panel.add(temp);
        main_panel.add(text_recent);

        return main_panel;
    }

    public static JPanel balanceSection() {
        JPanel main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
        main_panel.setBorder(new EmptyBorder(30, 20, 30, 20));
        main_panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 290));
        main_panel.setBackground(Style.GREEN);

        // Total_Balance
        JLabel tb = new JLabel("TOTAL BALANCE");
        tb.setFont(Style.NORMAL(13));
        tb.setForeground(Color.white);

        // Balance
        JLabel balance = new JLabel(String.format("%.2f", tManager.balance));
        balance.setFont(Style.BOLD(64));
        balance.setForeground(Color.white);

        

        // Month name
        JLabel month_name = new JLabel("July 2026");
        month_name.setFont(Style.NORMAL(13));
        month_name.setForeground(Color.white);

        // income and spent
        JPanel statis = new JPanel();
        statis.setLayout(new BoxLayout(statis, BoxLayout.X_AXIS));
        statis.setMaximumSize(new Dimension(Integer.MAX_VALUE, 98));
        statis.setAlignmentX(Component.LEFT_ALIGNMENT);
        statis.setBackground(Style.GREEN);

        statis.add(iAnde("Income", tManager.income));
        JPanel temp = new JPanel();
        temp.setBackground(Style.GREEN);
        statis.add(temp);
        statis.add(iAnde("Spent", tManager.spent));

        // add to main panel
        main_panel.add(tb);
        main_panel.add(balance);
        main_panel.add(month_name);
        main_panel.add(statis);

        return main_panel;
    }

    public static JPanel iAnde(String h, double amount) {
        JPanel income_panel = new JPanel();
        income_panel.setBorder(new EmptyBorder(5, 10, 5, 10));
        income_panel.setLayout(new BoxLayout(income_panel, BoxLayout.Y_AXIS));
        income_panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        income_panel.setBackground(Style.LIGHT_GREEN);

        JLabel income = new JLabel(h);
        income.setFont(Style.NORMAL(13));
        income.setForeground(Color.white);

        JLabel income_amount = new JLabel(String.format("%.2f", amount));
        income_amount.setFont(Style.NORMAL(24));
        income_amount.setForeground(Color.white);

        income_panel.add(income);
        income_panel.add(income_amount);

        return income_panel;
    }
}