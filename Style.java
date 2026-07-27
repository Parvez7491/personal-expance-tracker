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
}