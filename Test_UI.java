import javax.swing.*;
import java.awt.*;

public class Test_UI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("CardLayout Tutorial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        // -------------------------------------------------------------
        // STEP 1: Create the CardLayout manager & Container Panel
        // -------------------------------------------------------------
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // -------------------------------------------------------------
        // STEP 2: Create individual screens (The "Cards")
        // -------------------------------------------------------------
        
        // --- Screen 1: Red Page ---
        JPanel redPage = new JPanel(new GridBagLayout());
        redPage.setBackground(new Color(0xE74C3C));
        JLabel redLabel = new JLabel("RED SCREEN (Page 1)");
        redLabel.setForeground(Color.WHITE);
        redLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        redPage.add(redLabel);

        // --- Screen 2: Blue Page ---
        JPanel bluePage = new JPanel(new GridBagLayout());
        bluePage.setBackground(new Color(0x3498DB));
        JLabel blueLabel = new JLabel("BLUE SCREEN (Page 2)");
        blueLabel.setForeground(Color.WHITE);
        blueLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        bluePage.add(blueLabel);

        // --- Screen 3: Green Page ---
        JPanel greenPage = new JPanel(new GridBagLayout());
        greenPage.setBackground(new Color(0x2ECC71));
        JLabel greenLabel = new JLabel("GREEN SCREEN (Page 3)");
        greenLabel.setForeground(Color.WHITE);
        greenLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        greenPage.add(greenLabel);

        // -------------------------------------------------------------
        // STEP 3: Add Cards to Container with Unique "String Keys"
        // (Think of keys like Variant Names in Figma)
        // -------------------------------------------------------------
        cardPanel.add(redPage, "RED");
        cardPanel.add(bluePage, "BLUE");
        cardPanel.add(greenPage, "GREEN");

        // -------------------------------------------------------------
        // STEP 4: Create Navigation Controls (Buttons)
        // -------------------------------------------------------------
        JPanel controlPanel = new JPanel();

        JButton btnRed = new JButton("Red");
        JButton btnBlue = new JButton("Blue");
        JButton btnGreen = new JButton("Green");
        JButton btnNext = new JButton("Next >");

        // STEP 5: Switch screens using cardLayout.show() or cardLayout.next()
        btnRed.addActionListener(e -> cardLayout.show(cardPanel, "RED"));
        btnBlue.addActionListener(e -> cardLayout.show(cardPanel, "BLUE"));
        btnGreen.addActionListener(e -> cardLayout.show(cardPanel, "GREEN"));
        
        // cardLayout also has built-in methods like next() and previous()
        btnNext.addActionListener(e -> cardLayout.next(cardPanel));

        controlPanel.add(btnRed);
        controlPanel.add(btnBlue);
        controlPanel.add(btnGreen);
        controlPanel.add(btnNext);

        // -------------------------------------------------------------
        // STEP 6: Assemble Window
        // -------------------------------------------------------------
        frame.setLayout(new BorderLayout());
        frame.add(cardPanel, BorderLayout.CENTER);   // Cards take main viewport
        frame.add(controlPanel, BorderLayout.SOUTH); // Buttons pinned to bottom

        frame.setVisible(true);
    }
}