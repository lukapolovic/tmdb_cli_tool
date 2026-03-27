import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class apiHandler {
    public String sendHttpRequest(String endpoint) {
        String apiURL = "https://api.themoviedb.org/3/movie/";
        String apiKey = "";
        StringBuilder response = new StringBuilder();

        try {
            apiKey = System.getenv("API_KEY");
            if(apiKey == null) {
                throw new APIKeyMissingException("API_KEY value is null!");
            }

            String fullURL = apiURL + endpoint;
            response = apiHandler.getResponse(fullURL, apiKey);
        } catch(MalformedURLException e) {
            System.err.println("API endpoint is malformed.");
        } catch (APIKeyMissingException | IOException e) {
            System.err.println(e);
        }

        return response.toString();
    }

    public static StringBuilder getResponse(String endpoint, String apiKey) throws IOException{
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Authorization", "Bearer " + apiKey);
        con.setRequestProperty("accept", "application/json");

        int status = con.getResponseCode();

        BufferedReader reader;
        if (status >=200 && status < 300) {
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

        return response;
    }

    class APIKeyMissingException extends Exception {
        public APIKeyMissingException(String message) {
            super(message);
        }
    }
}
