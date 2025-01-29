import java.util.Scanner;
import java.util.HashMap;

public class CurrencyConverter {
    public static void main(String[] args) {
        HashMap<Integer, String> currencyCodes = new HashMap<Integer,String>();

        // adding currency codes
        currencyCodes.put(1,"USD");
        currencyCodes.put(2,"INR");
        currencyCodes.put(3,"EURO");
        currencyCodes.put(4,"YEN");
        currencyCodes.put(5,"CAD");
        currencyCodes.put(4,"HKD");

        String FromCode, ToCode;
        double amount;

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to currency converter!");
    }
}
