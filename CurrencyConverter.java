import java.net.URL;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.HashMap;

public class CurrencyConverter {
    public static void main(String[] args) throws MalformedURLException, URISyntaxException{
        HashMap<Integer, String> currencyCodes = new HashMap<Integer,String>();

        // adding currency codes
        currencyCodes.put(1,"USD");
        currencyCodes.put(2,"INR");
        currencyCodes.put(3,"EURO");
        currencyCodes.put(4,"YEN");
        currencyCodes.put(5,"CAD");
        currencyCodes.put(6,"HKD");

        String fromCode, toCode;
        double amount;

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to currency converter!");
        System.out.println("Select the currency you want to convert from:");
        System.out.println("1:USD \t 2:INR \t 3:EURO \t 4:YEN \t 5:CAD \t 6:HKD");
        fromCode = sc.next();
        System.out.println("Select the currency you want to convert into:");
        System.out.println("1:USD \t 2:INR \t 3:EURO \t 4:YEN \t 5:CAD \t 6:HKD");
        toCode = sc.next();

        System.out.println("Enter the amount:");
        amount =sc.nextFloat();

        sendHttpGETRequest(fromCode, toCode, amount);

        System.out.println("Thanks for using the converter!");
    }

    private static void sendHttpGETRequest(String fromCode, String toCode, double amount) throws MalformedURLException, URISyntaxException{
        // String GET_URL ="https://v6.exchangerate-api.com/v6/936c8ca3cf6e00580fe6a4bc/pair/"+fromCode+"/"+toCode+"/"+amount;
        // URL url= new URL("https://v6.exchangerate-api.com/v6/936c8ca3cf6e00580fe6a4bc/pair/"+fromCode+"/"+toCode+"/"+amount);
        // https://v6.exchangerate-api.com/v6/936c8ca3cf6e00580fe6a4bc/pair/INR/USD/100

        URI uri = new URI("https", "v6.exchangerate-api.com", "/v6/936c8ca3cf6e00580fe6a4bc/pair/" + fromCode + "/" + toCode + "/" + amount, null);
        URL url = uri.toURL();
    }
}
