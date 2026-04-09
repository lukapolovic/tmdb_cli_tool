import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.cli.*;
import src.com.polovski.tmdbcli.*;

import java.util.ArrayList;

public class tmdb_app {
    public static void main(String[] args) throws ApiHandler.APIException {
        Options options = new Options();

        options.addOption("h", "help", false, "show help");
        options.addOption("t", "type", true, "Type selected");

        CliManager cliMan = new CliManager();
        CommandLine cmd = cliMan.parseCli(options, args);

        if(cmd == null) {
            System.out.println("Closing tool...");
            System.exit(0);
        }

        try {
            String choice = cliMan.checkChoice(cmd);
            ApiHandler api = new ApiHandler();

            String apiResponse = api.sendHttpRequest(choice);

            JsonHandler jHandler = new JsonHandler();
            ArrayList<Movie> movies = jHandler.returnMovies(apiResponse);

            cliMan.startGui();

        } catch (IllegalArgumentException e) {
            System.err.println(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (ApiHandler.APIException e) {
            throw new ApiHandler.APIException("An API exception occurred", e);
        }
    }
}
