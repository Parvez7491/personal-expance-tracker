import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class Test_UI {

    public static void main(String[] args) {
        JFrame window = new JFrame("Welcome to RDS");

        // 1. Initialize CardLayout and parent panel
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // 2. Add panels with unique String keys
        cardPanel.add(signIN(cardLayout, cardPanel), "SIGN_IN");
        cardPanel.add(signUP(cardLayout, cardPanel), "SIGN_UP");

        window.add(cardPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 450);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    // --- SIGN UP PANEL ---
    public static JPanel signUP(CardLayout cardLayout, JPanel cardPanel) {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel header = new JLabel("Create Account");
        header.setFont(new Font("SansSerif", Font.BOLD, 18));

        JLabel lname = new JLabel("Enter your name");
        JTextField tfname = new JTextField();
        tfname.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel lpass = new JLabel("Set Password");
        JPasswordField tfpass = new JPasswordField();
        tfpass.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JButton submitBtn = moderButton("Sign Up");

        // Navigation Button to switch back to Sign In
        JButton switchToSignInBtn = moderButton("Already have an account? Sign In");
        switchToSignInBtn.addActionListener(e -> cardLayout.show(cardPanel, "SIGN_IN"));

        // Add components with spacing
        main.add(header);
        main.add(Box.createRigidArea(new Dimension(0, 15)));
        main.add(lname);
        main.add(tfname);
        main.add(Box.createRigidArea(new Dimension(0, 10)));
        main.add(lpass);
        main.add(tfpass);
        main.add(Box.createRigidArea(new Dimension(0, 15)));
        main.add(submitBtn);
        main.add(Box.createRigidArea(new Dimension(0, 10)));
        main.add(switchToSignInBtn);

        return main;
    }

    // --- SIGN IN PANEL ---
    public static JPanel signIN(CardLayout cardLayout, JPanel cardPanel) {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel header = new JLabel("Sign In");
        header.setFont(new Font("SansSerif", Font.BOLD, 18));

        JLabel lname = new JLabel("Username");
        JTextField tfname = new JTextField();
        tfname.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel lpass = new JLabel("Password");
        JPasswordField tfpass = new JPasswordField();
        tfpass.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JButton submitBtn = moderButton("Sign In");
        submitBtn.setFont(Style.NORMAL(12));
        


        // Navigation Button to switch to Sign Up
        JButton switchToSignUpBtn = moderButton("Don't have an account? Sign Up");
        switchToSignUpBtn.addActionListener(e -> cardLayout.show(cardPanel, "SIGN_UP"));

        // Add components with spacing
        main.add(header);
        main.add(Box.createRigidArea(new Dimension(0, 15)));
        main.add(lname);
        main.add(tfname);
        main.add(Box.createRigidArea(new Dimension(0, 10)));
        main.add(lpass);
        main.add(tfpass);
        main.add(Box.createRigidArea(new Dimension(0, 15)));
        main.add(submitBtn);
        main.add(Box.createRigidArea(new Dimension(0, 10)));
        main.add(switchToSignUpBtn);

        return main;
    }

    public static JButton moderButton(String s){
        JButton btn = new JButton(s);
        btn.setFocusPainted(false);                             
        btn.setBackground(new Color(99, 102, 241));             
        btn.setForeground(Color.WHITE);                         
        btn.setOpaque(true);                                    
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));         
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));           
        btn.setFont(new Font("DialogInput", Font.BOLD, 14));
        return btn;
    }
}