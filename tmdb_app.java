import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.apache.commons.cli.*;
import src.com.polovski.tmdbcli.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class tmdb_app {
    public static void main(String[] args) throws Exception {
        Options options = new Options();
        options.addOption("h", "help", false, "Show help");
        options.addOption("t", "type", true, "Type selected (now_playing, popular, top_rated, upcoming)");
        options.addOption("g", "gui", false, "Open GUI tool");
        options.addOption("p", "pages", true, "Pagination limit");

        CommandLine cmd = new DefaultParser().parse(options, args);

        if (cmd.hasOption("help")) {
            printHelp(options);
            return;
        }

        if (cmd.hasOption("g")) {
            launchGui();
            return;
        }

        String type = cmd.getOptionValue("t", "now_playing");
        String pages = cmd.getOptionValue("p", "10");

        try {
            fetchAndPrintMovies(type, pages);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar tmdb-cli.jar", options);
    }

    private static void launchGui() {
        System.out.println("[Opening GUI interface...]");
        try {
            Terminal terminal = new DefaultTerminalFactory(System.out, System.in, StandardCharsets.UTF_8).createTerminal();
            Screen screen = new TerminalScreen(terminal);
            WindowBasedTextGUI gui = new MultiWindowTextGUI(screen);
            MyWindow myWindow = new MyWindow(gui);
            gui.addWindow(myWindow);
            screen.startScreen();

            myWindow.waitUntilClosed();

            screen.stopScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fetchAndPrintMovies(String type, String pages) throws Exception {
        System.out.println("[Fetching data for: " + type + "]");
        System.out.println("Page size: " + pages);
        System.out.println("Top 10 Movies:");

        ApiHandler apiHandler = new ApiHandler();
        JsonHandler jsonHandler = new JsonHandler();

        ArrayList<Movie> movies = jsonHandler.returnMovies(apiHandler.sendHttpRequest(type));

        movies.forEach(movie -> System.out.println(movie.toString()));
    }
}