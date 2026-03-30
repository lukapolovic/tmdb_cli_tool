import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.cli.*;

import java.util.ArrayList;

public class tmdb_app {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("h", "help", false, "show help");
        options.addOption("t", "type", true, "Type selected");

        cliManager cliMan = new cliManager();
        CommandLine cmd = cliMan.parseCli(options, args);

        if(cmd == null) {
            System.out.println("Closing tool...");
            System.exit(0);
        }

        try {
            String choice = cliMan.checkChoice(cmd);
            apiHandler api = new apiHandler();

            String apiResponse = api.sendHttpRequest(choice);

            jsonHandler jHandler = new jsonHandler();
            ArrayList<Movie> movies = jHandler.returnMovies(apiResponse);

            cliMan.printTableView(movies);

        } catch (IllegalArgumentException e) {
            System.err.println(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
