import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.HashMap;
import org.json.JSONObject;

public class CurrencyConverter {
    public static void main(String[] args) throws URISyntaxException, IOException{
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

    private static void sendHttpGETRequest(String fromCode, String toCode, double amount) throws URISyntaxException, IOException{

        URI uri = new URI("https", "v6.exchangerate-api.com", "/v6/936c8ca3cf6e00580fe6a4bc/pair/" + fromCode + "/" + toCode + "/" + amount, null);
        URL url = uri.toURL();

        //https://v6.exchangerate-api.com/v6/936c8ca3cf6e00580fe6a4bc/pair/USD/INR/1

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();

        if(responseCode==HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine=in.readLine())!=null){
                response.append(inputLine);
            }in.close();

            JSONObject obj = new JSONObject(response.toString());

            System.out.println("JSON Response: " + response.toString());

            if (obj.has("conversion_rate")) {
                Double exchangeRate = obj.getDouble("conversion_rate");
                System.out.println("Conversion rate: " + exchangeRate);
                System.out.println(amount + fromCode + " = " + amount / exchangeRate + toCode);
            } else {
                System.out.println("Conversion rate is not available.");
            }
        }else{
            System.out.println("Didn't work.");
        }
        
    }
}
