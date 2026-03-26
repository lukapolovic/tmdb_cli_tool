import org.apache.commons.cli.*;

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
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }
    }
}
