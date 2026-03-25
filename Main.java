import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("h", "help", false, "show help");
        options.addOption("t", "type", false, "Type selected");

        CommandLineParser parse = new DefaultParser();
        try {
            CommandLine cmd = parse.parse(options, args);
            if(cmd.hasOption("h")) {
                System.out.println("I AM HELPING YOU");
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
