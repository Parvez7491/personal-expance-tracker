package ui_java;

import raw_java.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class App_UI {

    public static TransactionManager tManager;
    public static String loggedInUsername = "";
    public static String loggedInFullName = "";

    public static CardLayout cardLayout;
    public static JPanel cardPanel;
    public static JFrame window;

    static EditTransactions_UI editor = new EditTransactions_UI();

    public static JButton btnNavHome, btnNavAdd, btnNavAbout;

    public static void main(String[] args) {
        startApp("guest", "Guest User");
    }

    public static void startApp(String username, String fullName) {
        loggedInUsername = username;
        loggedInFullName = fullName;
        tManager = new TransactionManager(username);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        window = new JFrame("My Sphere");
        window.setLayout(new BorderLayout());

        cardPanel.add(homePanel(), "HOME");
        cardPanel.add(AddTransactionPanel.getPanel(cardLayout, cardPanel), "ADD_TX");
        cardPanel.add(About_UI.getPanel(), "ABOUT");
        cardPanel.add(editor.getUI(cardLayout, cardPanel), "Edit");

        window.add(cardPanel, BorderLayout.CENTER);
        window.add(navbar(cardLayout, cardPanel), BorderLayout.SOUTH);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(450, 800);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void refreshUI() {
        cardPanel.removeAll();
        cardPanel.add(homePanel(), "HOME");
        cardPanel.add(AddTransactionPanel.getPanel(cardLayout, cardPanel), "ADD_TX");
        cardPanel.add(About_UI.getPanel(), "ABOUT");
        cardPanel.add(editor.getUI(cardLayout, cardPanel), "Edit");
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public static void switchToHome() {
        cardLayout.show(cardPanel, "HOME");

        btnNavHome.setBackground(Style.GREEN);
        btnNavHome.setForeground(Color.white);

        btnNavAdd.setBackground(Color.white);
        btnNavAdd.setForeground(Color.black);

        btnNavAbout.setBackground(Color.white);
        btnNavAbout.setForeground(Color.black);
    }

    public static JPanel navbar(CardLayout cl, JPanel cp) {
        JPanel navbar = new JPanel(new GridLayout(1, 0));

        btnNavHome = Style.modernButton("Home");
        btnNavHome.setBackground(Style.GREEN);
        btnNavHome.setOpaque(true);
        btnNavHome.setForeground(Color.white);

        btnNavAdd = Style.modernButton("Add");
        btnNavAbout = Style.modernButton("About");

        navbar.add(btnNavHome);
        navbar.add(btnNavAdd);
        navbar.add(btnNavAbout);

        btnNavHome.addActionListener(e -> switchToHome());

        btnNavAdd.addActionListener(e -> {
            btnNavAdd.setBackground(Style.GREEN);
            btnNavAdd.setOpaque(true);
            btnNavAdd.setForeground(Color.white);

            btnNavHome.setBackground(Color.white);
            btnNavHome.setOpaque(true);
            btnNavHome.setForeground(Color.black);

            btnNavAbout.setBackground(Color.white);
            btnNavAbout.setOpaque(true);
            btnNavAbout.setForeground(Color.black);

            cl.show(cp, "ADD_TX");
        });

        btnNavAbout.addActionListener(e -> {
            btnNavAbout.setBackground(Style.GREEN);
            btnNavAbout.setOpaque(true);
            btnNavAbout.setForeground(Color.white);

            btnNavHome.setBackground(Color.white);
            btnNavHome.setOpaque(true);
            btnNavHome.setForeground(Color.black);

            btnNavAdd.setBackground(Color.white);
            btnNavAdd.setOpaque(true);
            btnNavAdd.setForeground(Color.black);

            cl.show(cp, "ABOUT");
        });

        return navbar;
    }

    public static JPanel homePanel() {
        JPanel main_panel = new JPanel();
        main_panel.setBorder(new EmptyBorder(30, 20, 0, 20));
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));

        // header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        // Time Logic
        int hour = LocalTime.now().getHour();
        String greetingText = "GOOD MORNING";
        if (hour >= 12 && hour < 18) {
            greetingText = "GOOD AFTERNOON";
        } else if (hour >= 18) {
            greetingText = "GOOD EVENING";
        }

        JLabel greeting = new JLabel(greetingText);
        greeting.setFont(Style.BOLD(20));

        JLabel nameLabel = new JLabel(loggedInFullName);
        nameLabel.setFont(Style.NORMAL(16));
        nameLabel.setForeground(Color.DARK_GRAY);

        textPanel.add(greeting);
        textPanel.add(nameLabel);

        // Logout Button
        JButton btnLogout = new JButton("Log Out");
        btnLogout.setBackground(Style.EXPENSE_RED);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(Style.NORMAL(12));
        btnLogout.setFocusPainted(false);
        btnLogout.setBorder(new EmptyBorder(5, 15, 5, 15));
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Log out action
        btnLogout.addActionListener(e -> {
            window.dispose();
            Auth_UI.main(new String[] {});
        });

        headerPanel.add(textPanel, BorderLayout.WEST);
        headerPanel.add(btnLogout, BorderLayout.EAST);

        JLabel text_recent = new JLabel("Recent Transactions");
        text_recent.setFont(Style.NORMAL(24));

        main_panel.add(headerPanel);
        main_panel.add(Box.createRigidArea(new Dimension(0, 20)));
        main_panel.add(balanceSection());
        main_panel.add(Box.createRigidArea(new Dimension(0, 20)));
        main_panel.add(text_recent);
        main_panel.add(recentTransactions());

        return main_panel;
    }

    public static JPanel balanceSection() {
        JPanel main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
        main_panel.setBorder(new EmptyBorder(30, 20, 30, 20));
        main_panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 290));
        main_panel.setBackground(Style.GREEN);

        JLabel tb = new JLabel("TOTAL BALANCE");
        tb.setFont(Style.NORMAL(13));
        tb.setForeground(Color.white);

        JLabel balance = new JLabel(String.format("%.2f", tManager.balance));
        balance.setFont(Style.BOLD(64));
        balance.setForeground(Color.white);

        JLabel month_name = new JLabel("July 2026");
        month_name.setFont(Style.NORMAL(13));
        month_name.setForeground(Color.white);

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

    public static JScrollPane recentTransactions() {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        ArrayList<Card_UI> card_ui = new ArrayList<>();

        int i = 0;
        for (Transaction a : tManager.transactions) {
            Card_UI c_ui = new Card_UI(a, i);
            card_ui.add(c_ui);
            i++;
        }

        for (Card_UI c : card_ui) {
            main.add(c.getUI(cardLayout, cardPanel, editor));
            main.add(Box.createVerticalStrut(15));
        }

        JScrollPane scrollPane = new JScrollPane(main);

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);

        return scrollPane;
    }
}