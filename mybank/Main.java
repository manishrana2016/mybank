package mybank;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args)throws SQLException, ClassNotFoundException, InterruptedException{
        Bank bank = new Bank();
        bank.start();
    }
}
