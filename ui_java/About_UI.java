package ui_java;
import raw_java.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import javax.imageio.ImageIO;

public class About_UI {
    public static JPanel getPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Style.OFF_WHITE);

        // Green header band
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(Style.GREEN);
        header.setBorder(new EmptyBorder(30, 20, 30, 20));
        header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel title = new JLabel("ABOUT");
        title.setFont(Style.BOLD(28));
        title.setForeground(Color.WHITE);
        
        JLabel subtitle = new JLabel("Expense Tracker App");
        subtitle.setFont(Style.NORMAL(14));
        subtitle.setForeground(Color.WHITE);
        
        header.add(title);
        header.add(Box.createVerticalStrut(4));
        header.add(subtitle);

        // Description paragraph
        JPanel descPanel = new JPanel();
        descPanel.setLayout(new BoxLayout(descPanel, BoxLayout.Y_AXIS));
        descPanel.setBackground(Style.OFF_WHITE);
        descPanel.setBorder(new EmptyBorder(20, 20, 10, 20));
        descPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel descTitle = new JLabel("About This App");
        descTitle.setFont(Style.BOLD(16));
        descTitle.setForeground(Style.GREEN);
        descTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextArea descText = new JTextArea(
            "My Sphere is a personal finance management application " +
            "designed to help users monitor their income and expenses with ease. " +
            "It allows you to add, edit, and review transactions, providing a " +
            "clear overview of your financial balance at a glance. Built with " +
            "Java Swing for a clean and intuitive desktop experience."
        );
        descText.setFont(Style.NORMAL(13));
        descText.setForeground(Color.DARK_GRAY);
        descText.setBackground(Style.OFF_WHITE);
        descText.setLineWrap(true);
        descText.setWrapStyleWord(true);
        descText.setEditable(false);
        descText.setFocusable(false);
        descText.setAlignmentX(Component.LEFT_ALIGNMENT);
        descText.setMaximumSize(new Dimension(410, Integer.MAX_VALUE));
        
        descPanel.add(descTitle);
        descPanel.add(Box.createVerticalStrut(8));
        descPanel.add(descText);
        
        // Divider
        JSeparator sep1 = new JSeparator();
        sep1.setForeground(Style.FIELD_COLOR);
        sep1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep1.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Developed By section 
        JPanel devSection = new JPanel();
        devSection.setLayout(new BoxLayout(devSection, BoxLayout.Y_AXIS));
        devSection.setBackground(Style.OFF_WHITE);
        devSection.setBorder(new EmptyBorder(15, 20, 10, 20));
        devSection.setAlignmentX(Component.LEFT_ALIGNMENT);
       
        JLabel devTitle = new JLabel("Developed By");
        devTitle.setFont(Style.BOLD(16));
        devTitle.setForeground(Style.GREEN);
        devTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        devSection.add(devTitle);
        devSection.add(Box.createVerticalStrut(12));
        
        // our infos
        devSection.add(developerCard(
            "Md Parvez Mosharraf Bhuiyan",
            "213.png",
            "https://github.com/Parvez7491"
        ));
        devSection.add(Box.createVerticalStrut(10));
        
        devSection.add(developerCard(
            "Rejoan Hasan Mugdho",
            "41826.png",
            "https://github.com/rhmugdho5"
        ));
        devSection.add(Box.createVerticalStrut(10));
        
        devSection.add(developerCard(
            "Tanzim Mohammad Enayetullah",
            "IMG_20260226_230737.jpg",
            "https://github.com/suptanzim"
        ));
        
        // Assemble 
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Style.OFF_WHITE);
        content.add(header);
        content.add(descPanel);
        content.add(sep1);
        content.add(devSection);
        
        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);
        mainPanel.add(scrollPane);
        return mainPanel;
    }

    
    private static JPanel developerCard(String name, String photoFile, String githubUrl) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.X_AXIS));
        card.setBackground(Style.OFF_WHITE);
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        JLabel photoLabel = new JLabel(loadCircularPhoto(photoFile, name, 60));
        photoLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(Style.OFF_WHITE);
        info.setBorder(new EmptyBorder(0, 14, 0, 0));
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(Style.BOLD(15));
        nameLabel.setForeground(Color.DARK_GRAY);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel linkLabel = new JLabel("<html><u>" + githubUrl + "</u></html>");
        linkLabel.setFont(Style.NORMAL(12));
        linkLabel.setForeground(Style.GREEN);
        linkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        linkLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(githubUrl));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Could not open link:\n" + githubUrl,
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        info.add(nameLabel);
        info.add(Box.createVerticalStrut(3));
        info.add(linkLabel);
        card.add(photoLabel);
        card.add(info);
        card.add(Box.createHorizontalGlue());
        return card;
    }
    // ── Load image as circular icon; fall back to coloured initial avatar ──
    private static ImageIcon loadCircularPhoto(String filename, String name, int size) {
        BufferedImage result = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = result.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(new Ellipse2D.Float(0, 0, size, size));
        File f = new File(filename);
        boolean loaded = false;
        if (f.exists()) {
            try {
                BufferedImage src = ImageIO.read(f);
                g2.drawImage(src, 0, 0, size, size, null);
                loaded = true;
            } catch (Exception ignored) {}
        }
        if (!loaded) {
            g2.setColor(Style.LIGHT_GREEN);
            g2.fillOval(0, 0, size, size);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Poppins", Font.BOLD, size / 2));
            String initial = name.isEmpty() ? "?" : String.valueOf(name.charAt(0)).toUpperCase();
            FontMetrics fm = g2.getFontMetrics();
            int x = (size - fm.stringWidth(initial)) / 2;
            int y = (size - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(initial, x, y);
        }
        g2.dispose();
        return new ImageIcon(result);
    }
}