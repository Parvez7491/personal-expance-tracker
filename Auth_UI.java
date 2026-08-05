import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;

public class Auth_UI {

    private static final String USERS_FILE = "users.csv";
    private static JFrame window;

    public static void main(String[] args) {
        window = new JFrame("MySphere - Authentication");
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        cardPanel.add(createLoginPanel(cardLayout, cardPanel), "LOGIN");
        cardPanel.add(createSignUpPanel(cardLayout, cardPanel), "SIGNUP");

        window.add(cardPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(420, 750); 
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
    }

    public static JPanel createLoginPanel(CardLayout cl, JPanel cp) {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(50, 40, 50, 40));
        main.setBackground(Style.OFF_WHITE);

        JLabel lblSub = new JLabel("Login to");
        lblSub.setFont(Style.NORMAL(16));
        lblSub.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTitle = new JLabel("MySphere");
        lblTitle.setFont(Style.BOLD(36));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        Color fieldBgColor = new Color(194, 217, 210);

        JLabel lblUser = new JLabel("User Name");
        lblUser.setFont(Style.NORMAL(16));
        JTextField txtUser = createStyledTextField(fieldBgColor);
        JLabel errUser = createErrorLabel("Username not found");

        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(Style.NORMAL(16));
        JPasswordField txtPass = createStyledPasswordField(fieldBgColor);
        JLabel errPass = createErrorLabel("Password mismatch");

        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        actionPanel.setBackground(Style.OFF_WHITE);
        actionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        actionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnExit = new JButton("Exit");
        styleActionButton(btnExit, Style.EXPENSE_RED);

        JButton btnLogin = new JButton("Log In");
        styleActionButton(btnLogin, Style.INCOME_GREEN);

        actionPanel.add(btnExit);
        actionPanel.add(btnLogin);

        // "OR" Center Alignment Fix
        JPanel orPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        orPanel.setBackground(Style.OFF_WHITE);
        orPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        orPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel lblOr = new JLabel("OR");
        lblOr.setFont(Style.NORMAL(14));
        orPanel.add(lblOr);

        JButton btnCreate = new JButton("Create New Account");
        styleActionButton(btnCreate, Style.GREEN);

        btnExit.addActionListener(e -> System.exit(0));

        btnCreate.addActionListener(e -> {
            errUser.setVisible(false);
            errPass.setVisible(false);
            txtUser.setText("");
            txtPass.setText("");
            cl.show(cp, "SIGNUP");
        });

        btnLogin.addActionListener(e -> {
            errUser.setVisible(false);
            errPass.setVisible(false);

            String username = txtUser.getText().trim();
            String password = new String(txtPass.getPassword());

            if (username.isEmpty()) {
                errUser.setText("Username cannot be empty");
                errUser.setVisible(true);
                return;
            }

            // authResult 0 নাম্বার ইনডেক্সে স্ট্যাটাস এবং 1 নাম্বারে Full Name রিটার্ন করবে
            String[] authResult = authenticateAndGetName(username, password);
            if (authResult[0].equals("-1")) {
                errUser.setText("Username not found");
                errUser.setVisible(true);
            } else if (authResult[0].equals("0")) {
                errPass.setText("Password mismatch");
                errPass.setVisible(true);
            } else {
                // Login Success!
                String fullName = authResult[1];
                window.dispose(); // লগিন উইন্ডো বন্ধ করা
                App_UI.startApp(username, fullName); // মেইন অ্যাপ ওপেন করা
            }
        });

        main.add(lblSub);
        main.add(lblTitle);
        main.add(Box.createRigidArea(new Dimension(0, 40)));
        main.add(lblUser);
        main.add(txtUser);
        main.add(errUser);
        main.add(Box.createRigidArea(new Dimension(0, 15)));
        main.add(lblPass);
        main.add(txtPass);
        main.add(errPass);
        main.add(Box.createRigidArea(new Dimension(0, 30)));
        main.add(actionPanel);
        main.add(Box.createRigidArea(new Dimension(0, 5)));
        main.add(orPanel); // Centered OR Label
        main.add(Box.createRigidArea(new Dimension(0, 5)));
        main.add(btnCreate);

        return main;
    }

    public static JPanel createSignUpPanel(CardLayout cl, JPanel cp) {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(50, 40, 50, 40));
        main.setBackground(Style.OFF_WHITE);

        JLabel lblSub = new JLabel("Welcome to");
        lblSub.setFont(Style.NORMAL(16));
        lblSub.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTitle = new JLabel("MySphere");
        lblTitle.setFont(Style.BOLD(36));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        Color fieldBgColor = new Color(194, 217, 210);

        JLabel lblName = new JLabel("Full Name");
        lblName.setFont(Style.NORMAL(16));
        JTextField txtName = createStyledTextField(fieldBgColor);
        JLabel errName = createErrorLabel("Name Field can not be blank");

        JLabel lblUser = new JLabel("User Name");
        lblUser.setFont(Style.NORMAL(16));
        JTextField txtUser = createStyledTextField(fieldBgColor);
        JLabel errUser = createErrorLabel("Username already exist");

        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(Style.NORMAL(16));
        JPasswordField txtPass = createStyledPasswordField(fieldBgColor);
        JLabel errPass = createErrorLabel("Password should be atleast 8 char long");

        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        actionPanel.setBackground(Style.OFF_WHITE);
        actionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        actionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnSignIn = new JButton("Sign In Instead");
        styleActionButton(btnSignIn, Style.EXPENSE_RED);

        JButton btnCreate = new JButton("Create Account");
        styleActionButton(btnCreate, Style.INCOME_GREEN);

        actionPanel.add(btnSignIn);
        actionPanel.add(btnCreate);

        btnSignIn.addActionListener(e -> {
            errName.setVisible(false);
            errUser.setVisible(false);
            errPass.setVisible(false);
            txtName.setText("");
            txtUser.setText("");
            txtPass.setText("");
            cl.show(cp, "LOGIN");
        });

        btnCreate.addActionListener(e -> {
            errName.setVisible(false);
            errUser.setVisible(false);
            errPass.setVisible(false);

            String name = txtName.getText().trim();
            String username = txtUser.getText().trim();
            String password = new String(txtPass.getPassword());
            boolean hasError = false;

            if (name.isEmpty()) {
                errName.setVisible(true);
                hasError = true;
            }
            if (username.isEmpty() || checkUserExists(username)) {
                errUser.setText(username.isEmpty() ? "Username cannot be empty" : "Username already exist");
                errUser.setVisible(true);
                hasError = true;
            }
            if (password.length() < 8) {
                errPass.setVisible(true);
                hasError = true;
            }

            if (!hasError) {
                saveUser(name, username, password);
                JOptionPane.showMessageDialog(main, "Account created successfully!\nPlease Log In.", "Success", JOptionPane.INFORMATION_MESSAGE);
                btnSignIn.doClick(); 
            }
        });

        main.add(lblSub);
        main.add(lblTitle);
        main.add(Box.createRigidArea(new Dimension(0, 30)));
        main.add(lblName);
        main.add(txtName);
        main.add(errName);
        main.add(Box.createRigidArea(new Dimension(0, 10)));
        main.add(lblUser);
        main.add(txtUser);
        main.add(errUser);
        main.add(Box.createRigidArea(new Dimension(0, 10)));
        main.add(lblPass);
        main.add(txtPass);
        main.add(errPass);
        main.add(Box.createRigidArea(new Dimension(0, 30)));
        main.add(actionPanel);

        return main;
    }

    private static boolean checkUserExists(String username) {
        File file = new File(USERS_FILE);
        if (!file.exists()) return false;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return false;
    }

    private static String[] authenticateAndGetName(String username, String password) {
        File file = new File(USERS_FILE);
        if (!file.exists()) return new String[]{"-1", ""}; // User not found
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[1].equals(username)) {
                    if (parts[2].equals(password)) {
                        return new String[]{"1", parts[0]}; // Success, returns Full Name
                    } else {
                        return new String[]{"0", ""}; // Wrong password
                    }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return new String[]{"-1", ""}; 
    }

    private static void saveUser(String name, String username, String password) {
        try (FileWriter fw = new FileWriter(USERS_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(name + "," + username + "," + password);
        } catch (IOException e) { e.printStackTrace(); }
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

    private static JPasswordField createStyledPasswordField(Color bgColor) {
        JPasswordField txt = new JPasswordField();
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

    private static JLabel createErrorLabel(String message) {
        JLabel err = new JLabel(message);
        err.setFont(new Font("Poppins", Font.PLAIN, 11));
        err.setForeground(new Color(220, 80, 70)); 
        err.setAlignmentX(Component.LEFT_ALIGNMENT);
        err.setVisible(false); 
        return err;
    }

    private static void styleActionButton(JButton btn, Color bgColor) {
        btn.setFont(Style.NORMAL(16));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btn.setOpaque(true);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
    }
}