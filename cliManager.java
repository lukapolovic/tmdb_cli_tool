import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import org.apache.commons.cli.*;
import com.googlecode.lanterna.terminal.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class cliManager {
    public void printTableView(ArrayList<Movie> listOfMovies) {
        try {
            Terminal terminal = new DefaultTerminalFactory(System.out, System.in, StandardCharsets.UTF_8).createTerminal();
            AtomicInteger y = new AtomicInteger(2);

            terminal.enterPrivateMode();
            terminal.setForegroundColor(TextColor.ANSI.WHITE);

            terminal.enableSGR(SGR.BOLD);
            String titleOfTerm = "The results of your search (navigate with arrow keys)";
            terminal.setCursorPosition(0, 0);
            for (char c : titleOfTerm.toCharArray()) {
                terminal.putCharacter(c);
            }
            terminal.disableSGR(SGR.BOLD);

            for(Movie movie : listOfMovies) {
                terminal.setCursorPosition(2, y.get());

                String text = movie.toString();
                for (char c : text.toCharArray()) {
                    terminal.putCharacter(c);
                }

                y.addAndGet(2);
            }

            terminal.flush();

            Thread.sleep(10000);

            terminal.exitPrivateMode();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public CommandLine parseCli(Options options, String[] args) {
        CommandLineParser parse = new DefaultParser();
        try {
            CommandLine cmdLine = parse.parse(options, args);

            if(cmdLine.hasOption("h")) {
                cliManager.printHelp();
                return null;
            } else if(cmdLine.hasOption("t")) {
                return cmdLine;
            } else {
                System.out.println("Either use -h or -t flags");
                return null;
            }
        } catch (ParseException e) {
            System.err.println(e);
            return null;
        }
    }

    public String checkChoice(CommandLine arg) throws IllegalArgumentException{
        String choice = arg.getOptionValue("t");
        List<String> acceptedChoices = new ArrayList<>(4);
        acceptedChoices.addAll(Arrays.asList("now_playing", "popular", "top_rated", "upcoming"));

        if(!acceptedChoices.contains(choice)) {
           throw new IllegalArgumentException("Wrong type argument. Please refer to -h or --help");
        }

        return choice;
    }

    public static void printHelp() {
        System.out.println(
                "-----------------------------------------------\n" +
                        "This tool uses TMDB API to fetch movie information and display it in the terminal\n" +
                        "-----------------------------------------------\n" +
                        "Example usages:\n" +
                        "tmdb_app -type \"now_playing\"\n" +
                        "tmdb_app -type \"popular\"\n" +
                        "tmdb_app -type \"top_rated\"\n" +
                        "tmdb_app -type \"upcoming\""
        );
    }

    private String trim(String text, int max) {
        return text.length() > max ? text.substring(0, max) + "..." : text;
    }
}
