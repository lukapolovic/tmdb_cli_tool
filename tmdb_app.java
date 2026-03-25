import org.apache.commons.cli.*;

public class tmdb_app {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("h", "help", false, "show help");
        options.addOption("t", "type", true, "Type selected");

        CommandLineParser parse = new DefaultParser();
        try {
            CommandLine cmd = parse.parse(options, args);
            if(cmd.hasOption("h")) {
                tmdb_app.printHelp();
            } else if(cmd.hasOption("t")) {
                String choice = cmd.getOptionValue("t");

                switch (choice) {
                    case "playing":
                        System.out.println("PLAYING");
                        break;

                    case "popular":
                        System.out.println("POPULAR");
                        break;

                    case "top":
                        System.out.println("TOP");
                        break;

                    case "upcoming":
                        System.out.println("UPCOMING");
                        break;

                    default:
                        System.out.println("You've entered a non-supported type value");
                        break;
                }
            } else {
                System.out.println("Either use -h or -t flags");
            }
        } catch (ParseException e) {
            System.out.println(e);
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
