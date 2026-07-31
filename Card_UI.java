import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Card_UI{
    Transaction t;
    int index;

    Card_UI(Transaction t, int i){
        this.t = t;
        this.index = i;
    }

    JPanel getUI(CardLayout cl, JPanel cp, EditTransactions_UI editor){
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        main.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        main.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel date_des = new JPanel();
        date_des.setLayout(new BoxLayout(date_des, BoxLayout.Y_AXIS));
        
        JLabel date = new JLabel(t.getDate());
        date.setFont(Style.NORMAL(12));
        date.setForeground(Color.white);
        JLabel des = new JLabel(t.getDes());
        des.setFont(Style.NORMAL(18));
        des.setForeground(Color.white);
        
        date_des.add(des);
        date_des.add(date);
        date_des.setOpaque(false);
        
        
        JLabel amount = new JLabel(String.format("%.2f", t.getAmount()));
        amount.setFont(Style.NORMAL(20));
        amount.setForeground(Color.white);
        amount.setOpaque(false);

        main.add(date_des);
        main.add(Box.createHorizontalGlue());
        main.add(amount);

        if (t.getClass().getSimpleName().equals("Income")) {
            main.setBackground(Style.INCOME_GREEN);
            main.setOpaque(true);
        } else {
            main.setBackground(Style.EXPENSE_RED);
            main.setOpaque(true);
        }

        main.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                editor.loadTransaction(t, index);
                cl.show(cp, "Edit");
            }
        });

        return main;
    }
}