package ui_java;
import raw_java.*;

import javax.swing.*;
import java.awt.*;

public class Style{
    //fonts
    public static Font BOLD(int n){
        return new Font("Poppins", Font.BOLD, n);
    }
    
    public static Font NORMAL(int n){
        return new Font("Poppins", Font.PLAIN, n);
    }
    

    //colors
    public static final Color GREEN = Color.decode("#44867C");
    public static final Color LIGHT_GREEN = Color.decode("#5E978E");
    public static final Color OFF_WHITE = Color.decode("#F2F2F2");
    public static final Color INCOME_GREEN = Color.decode("#55AC68");
    public static final Color EXPENSE_RED = Color.decode("#D65846");
    public static final Color FIELD_COLOR = new Color(194, 217, 210);


    //buttons
    public static JButton modernButton(String s){
        JButton btn = new JButton(s);

        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.setPreferredSize(new Dimension(Integer.MAX_VALUE, 65));
        btn.setFont(NORMAL(18));

        return btn;
    }
}