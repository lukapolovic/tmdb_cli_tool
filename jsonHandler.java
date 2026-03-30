import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class jsonHandler {
    public ArrayList<Movie> returnMovies(String input) throws JsonProcessingException {
        JsonNode movies = jsonHandler.extractResults(input);
        ArrayList<Movie> listOfMovies = new ArrayList<>(10);

        for(int i = 0; i < 10; i++) {
            JsonNode jNode = movies.get(i);
            Movie newMovie = jsonHandler.extractAndCreateMovie(jNode);
            listOfMovies.add(newMovie);
        }

        return listOfMovies;
    }

    public static Movie extractAndCreateMovie(JsonNode jNode) {
        long id = jNode.get("id").asInt();
        boolean isAdultRated = jNode.get("adult").asBoolean();
        String originalLanguage = jNode.get("original_language").asText();
        String title = jNode.get("title").asText();
        String overview = jNode.get("overview").asText();
        double popularity = jNode.get("popularity").asDouble();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate releaseDate = LocalDate.parse(jNode.get("release_date").asText(), formatter);

        int rating = jNode.get("vote_average").asInt();
        int ratingVotes = jNode.get("vote_count").asInt();

        Movie movie = new Movie(ratingVotes, rating, releaseDate, popularity, overview, title, originalLanguage, isAdultRated, id);

        return movie;
    }

    public static JsonNode extractResults(String input) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(input);
        return jsonNode.get("results");
    }
}
