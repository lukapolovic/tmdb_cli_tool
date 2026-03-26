import org.apache.commons.cli.*;

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
