import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.border.*;

public class Test_UI {

    static CardLayout cardLayout = new CardLayout();
    static JPanel cardPanel = new JPanel(cardLayout);
    
    public static void main(String[] args) {
        JFrame window = new JFrame("Welcome to RDS");
        
        TransactionManager t = App_UI.tManager;
        EditTransactions_UI edit_UI = new EditTransactions_UI();


        ArrayList<Card_UI> card_UI = new ArrayList<>();

        int i = 0;
        for (Transaction a : t.transactions) {
            Card_UI cui = new Card_UI(a, i);
            card_UI.add(cui);
            i++;
        }

        JPanel trans = new JPanel();
        trans.setLayout(new BoxLayout(trans, BoxLayout.Y_AXIS));

        for (Card_UI c : card_UI) {
            trans.add(c.getUI(cardLayout, cardPanel, edit_UI));
            trans.add(Box.createVerticalStrut(15));
        }

        cardPanel.add(trans, "card");
        cardPanel.add(edit_UI.getUI(cardLayout, cardPanel), "Edit");

        window.add(cardPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(450, 800);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}