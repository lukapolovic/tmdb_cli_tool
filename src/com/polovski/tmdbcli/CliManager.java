package src.com.polovski.tmdbcli;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.apache.commons.cli.*;
import com.googlecode.lanterna.terminal.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CliManager {
    public void startGui() {
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

    public CommandLine parseCli(Options options, String[] args) {
        CommandLineParser parse = new DefaultParser();
        try {
            CommandLine cmdLine = parse.parse(options, args);

            if(cmdLine.hasOption("h")) {
                CliManager.printHelp();
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
