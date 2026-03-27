import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonHandler {
    public void printResults(String input) throws JsonProcessingException {
        JsonNode movies = jsonHandler.extractResults(input);

        for(JsonNode movie : movies) {
            String title = movie.get("title").asText();
            double rating = movie.get("vote_average").asDouble();
            String releaseDate = movie.get("release_date").asText();

            System.out.println(title + " (" + releaseDate + ") - Rating: " + rating);
        }
    }

    public static JsonNode extractResults(String input) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(input);
        return jsonNode.get("results");
    }
}
