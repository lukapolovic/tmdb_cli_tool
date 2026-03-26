import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class cliManager {
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
        acceptedChoices.addAll(Arrays.asList("playing", "popular", "top", "upcoming"));

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
                        "tmdb_app -type \"playing\"\n" +
                        "tmdb_app -type \"popular\"\n" +
                        "tmdb_app -type \"top\"\n" +
                        "tmdb_app -type \"upcoming\""
        );
    }
}
