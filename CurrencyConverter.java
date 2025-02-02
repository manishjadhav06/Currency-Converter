import java.net.URL;
import java.text.DecimalFormat;
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
    public static void main(String[] args) throws URISyntaxException, IOException {

        Boolean rep = true;

        do {
            HashMap<Integer, String> currencyCodes = new HashMap<Integer, String>();

            // adding currency codes
            currencyCodes.put(1, "USD");
            currencyCodes.put(2, "INR");
            currencyCodes.put(3, "EURO");
            currencyCodes.put(4, "JPY");
            currencyCodes.put(5, "CAD");
            currencyCodes.put(6, "HKD");

            String fromCode, toCode;
            double amount;

            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to currency converter!\n");
            System.out.println("Visit this link to know the desired Currency Code: https://www.exchangerate-api.com/docs/supported-currencies \n");
            System.out.println("Type the currency you want to convert from: eg. USD");
            fromCode = sc.next();
            System.out.println("Select the currency you want to convert into:");
            toCode = sc.next();

            System.out.println("Enter the amount:");
            amount = sc.nextFloat();

            sendHttpGETRequest(fromCode, toCode, amount);

            System.out.println("Would you like to continue? (y/n)");

            if (sc.next().equals("n")) {
                rep = false;
            } else {
                rep = true;
            }

        } while (rep);

        System.out.println("Thanks for using the converter!");
    }

    private static void sendHttpGETRequest(String fromCode, String toCode, double amount)
            throws URISyntaxException, IOException {

        DecimalFormat f = new DecimalFormat("00.00");

        URI uri = new URI("https", "v6.exchangerate-api.com",
                "/v6/936c8ca3cf6e00580fe6a4bc/pair/" + fromCode + "/" + toCode + "/" + amount, null);
        URL url = uri.toURL();

        // https://v6.exchangerate-api.com/v6/936c8ca3cf6e00580fe6a4bc/pair/INR/USD/1

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject obj = new JSONObject(response.toString());

            if (obj.has("conversion_rate")) {
                Double exchangedRate = obj.getDouble("conversion_result");
                System.out.println();
                System.out.println(amount + " " + fromCode + " = " + f.format(exchangedRate) + " " + toCode);
            } else {
                System.out.println("Conversion rate is not available.");
            }
        } else {
            System.out.println("Didn't work.");
        }

    }
}
