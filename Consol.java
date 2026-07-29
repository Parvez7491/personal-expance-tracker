import java.util.*;

public class Consol{
    public static void main(String[] args) {
        TransactionManager tManager = new TransactionManager();
        System.out.println(tManager.balance);
        
        for(Transaction a : tManager.transactions){
            System.out.println(a.generateCSV());
        }
        
    }
}