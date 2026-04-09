package src.com.polovski.tmdbcli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ApiHandler {
    public String sendHttpRequest(String endpoint) throws APIException {
        String apiURL = "https://api.themoviedb.org/3/movie/";
        String apiKey = System.getenv("API_KEY");
        if(apiKey == null) {
            throw new APIKeyMissingException("API_KEY value is null!", null);
        }
        StringBuilder response;

        try {
            String fullURL = apiURL + endpoint;
            response = ApiHandler.getResponse(fullURL, apiKey);
        } catch(MalformedURLException e) {
            throw new APIException("The API URL is Malformed", e);
        } catch (IOException e) {
            throw new APIException("API request failed", e);
        }
        
        return response.toString();
    }

    public static StringBuilder getResponse(String endpoint, String apiKey) throws IOException, ApiConnectionException {
        HttpURLConnection con = getHttpURLConnection(endpoint, apiKey);

        int status = con.getResponseCode();

        BufferedReader reader;
        if (status >=200 && status < 300) {
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            throw new ApiConnectionException("HTTP error:" + status, null);
        }

        String line;
        StringBuilder response = new StringBuilder();

        while((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response;
    }

    private static HttpURLConnection getHttpURLConnection(String endpoint, String apiKey) throws MalformedURLException, ApiConnectionException {
        URL url = new URL(endpoint);
        HttpURLConnection con = null;

        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("accept", "application/json");
        } catch(IOException e) {
            throw new ApiConnectionException("Failed to reach TMDB API", e);
        } finally {
            if(con != null) {
                con.disconnect();
            }
        }
        return con;
    }

    public static class APIException extends Exception {
        public APIException(String message, Exception cause) {
            super(message, cause);
        }
    }
    
    static class APIKeyMissingException extends APIException {
        public APIKeyMissingException(String message, Exception cause) {

            super(message, cause);
        }
    }

    static class ApiConnectionException extends APIException {
        public ApiConnectionException(String message, Exception cause) {
            super(message, cause);
        }
    }
}
