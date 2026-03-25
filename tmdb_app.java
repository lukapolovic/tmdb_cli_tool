import org.apache.commons.cli.*;

public class tmdb_app {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("h", "help", false, "show help");
        options.addOption("t", "type", false, "Type selected");

        CommandLineParser parse = new DefaultParser();
        try {
            CommandLine cmd = parse.parse(options, args);
            if(cmd.hasOption("h")) {
                System.out.println(
                        "-----------------------------------------------\n" +
                        "This tool uses TMDB API to fetch movie information and display it in the terminal\n" +
                        "-----------------------------------------------\n" +
                        "Example usages:\n" +
                        "tmdb_app -type \"playing\"\n" + "tmdb_app -type \"popular\"\n" + "tmdb_app -type \"top\"\n" + "tmdb_app -type \"upcoming\"");
            } else if(cmd.hasOption("type")) {
                System.out.println("You selected type");
            } else {
                System.out.println("Either use -h or -t flags");
            }
        } catch (ParseException e) {
            System.out.println(e);
        }
    }
}
