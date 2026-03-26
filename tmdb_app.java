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

        tmdb_app.getChoice(cmd);
    }

    public static void getChoice(CommandLine arg) {
        String choice = arg.getOptionValue("t");

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
    }
}
