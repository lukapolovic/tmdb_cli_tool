import com.fasterxml.jackson.core.JsonProcessingException;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;

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
                    MessageDialog.showMessageDialog(
                            gui,
                            movie.getTitle(),
                            movie.getOverview()
                    );
                });
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
