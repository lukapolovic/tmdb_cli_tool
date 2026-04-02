import com.fasterxml.jackson.core.JsonProcessingException;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;

public class MyWindow extends BasicWindow {
    public MyWindow(WindowBasedTextGUI gui) {
        super("TMDB CLI Tool");

        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        ActionListBox menu = new ActionListBox();

        menu.addItem("Now Playing Movies", () -> {
            createMovieListGUI(gui, "Now Playing Movies", "now_playing");
        });

        menu.addItem("Popular Movies", () -> {
            createMovieListGUI(gui, "Popular Movies", "popular");
        });

        menu.addItem("Top Rated Movies", () -> {
            createMovieListGUI(gui, "Top Rated Movies", "top_rated");
        });

        menu.addItem("Upcoming Movies", () -> {
            createMovieListGUI(gui, "Upcoming Movies", "upcoming");
        });

        menu.addItem("Exit", MyWindow.this::close);

        panel.addComponent(menu);
        setComponent(panel);
    }

    public static void createMovieListGUI(WindowBasedTextGUI gui, String newWindowTitle, String endpoint) {
        BasicWindow newWindow = new BasicWindow(newWindowTitle);
        Panel newPanel = new Panel();
        newPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        ActionListBox movieMenu = new ActionListBox();

        MyWindow.createMenuMovieItems(gui, movieMenu, endpoint);

        movieMenu.addItem("Exit", newWindow::close);

        newPanel.addComponent(movieMenu);
        newWindow.setComponent(newPanel);
        gui.addWindowAndWait(newWindow);
    }

    public static void createMenuMovieItems(WindowBasedTextGUI gui, ActionListBox movieMenu, String endpoint) {
        apiHandler apiHand = new apiHandler();
        jsonHandler jsonHand = new jsonHandler();

        try {
            ArrayList<Movie> listOfMovies = jsonHand.returnMovies(apiHand.sendHttpRequest(endpoint));
            for(Movie movie : listOfMovies) {
                movieMenu.addItem(movie.getTitle(), () -> {
                    createMovieInfoGUI(gui, movie);
                });
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createMovieInfoGUI(WindowBasedTextGUI gui, Movie movie) {
        BasicWindow window = new BasicWindow(movie.getTitle()) {
            @Override
            public boolean handleInput(KeyStroke key) {
                if (key.getKeyType() == KeyType.Escape) {
                    this.close();
                    return false;
                }
                super.handleInput(key);
                return false;
            }
        };

        Panel mainPanel = new Panel(new LinearLayout(Direction.VERTICAL));

        Panel grid = new Panel(new GridLayout(2));

        grid.addComponent(new Label("Title:"));
        grid.addComponent(new Label(movie.getTitle()));

        grid.addComponent(new Label("Release Date:"));
        grid.addComponent(new Label(movie.getReleaseDate().toString()));

        grid.addComponent(new Label("Rating:"));
        grid.addComponent(new Label(String.format("%.1f (%d votes)",
                movie.getRating(), movie.getRatingVotes())));

        grid.addComponent(new Label("Popularity:"));
        grid.addComponent(new Label(String.valueOf(movie.getPopularity())));

        grid.addComponent(new Label("Language:"));
        grid.addComponent(new Label(movie.getOriginalLanguage()));

        grid.addComponent(new Label("Adult:"));
        grid.addComponent(new Label(movie.isAdultRated() ? "Yes" : "No"));

        mainPanel.addComponent(grid.withBorder(Borders.singleLine("Movie Info")));

        String wrappedOverview = wrapText(movie.getOverview(), 50);

        TextBox overviewBox = new TextBox(
                new TerminalSize(50, 10),
                wrappedOverview
        );

        overviewBox.setReadOnly(true);
        overviewBox.setVerticalFocusSwitching(false); // optional

        mainPanel.addComponent(
                overviewBox.withBorder(Borders.singleLine("Overview"))
        );

        mainPanel.addComponent(new Label("Press ESC to exit"));

        window.setComponent(mainPanel);
        gui.addWindowAndWait(window);
    }

    private static String wrapText(String text, int maxWidth) {
        StringBuilder wrapped = new StringBuilder();
        int index = 0;

        while (index < text.length()) {
            int end = Math.min(index + maxWidth, text.length());

            // try to break at last space
            int lastSpace = text.lastIndexOf(" ", end);
            if (lastSpace <= index) {
                lastSpace = end;
            }

            wrapped.append(text, index, lastSpace).append("\n");
            index = lastSpace + 1;
        }

        return wrapped.toString();
    }
}
