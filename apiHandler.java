import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class apiHandler {
    public void sendHttpRequest(String endpoint) {
        String apiURL = "https://api.themoviedb.org/3/movie/";
        String apiKey = System.getenv("API_KEY");

        if(apiKey == null) {
            System.out.println("API_KEY value is null!");
        }

        try {
            URL url = new URL(apiURL + endpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("accept", "application/json");

            int status = con.getResponseCode();

            BufferedReader reader;
            if(status >= 200 && status < 300) {
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String line;
            StringBuilder response = new StringBuilder();

            while((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("Response code: " + status);
            System.out.println(response);
        } catch(MalformedURLException e) {
            System.err.println("API endpoint is malformed.");
        } catch (IOException e) {
            throw new RuntimeException("Timed out during connection opening.");
        }
    }
}
