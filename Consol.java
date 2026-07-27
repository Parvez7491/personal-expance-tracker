import java.util.*;

public class Consol{
    public static void main(String[] args) {
        TransactionManager tManager = new TransactionManager();

        //tManager.add(new Expense("24 tarikh", "Ice-cream-2", 90));
        
        

        for(Transaction a : tManager.transactions){
            System.out.println(a.generateCSV());
        }
        System.out.println(tManager.balance);
    }
}