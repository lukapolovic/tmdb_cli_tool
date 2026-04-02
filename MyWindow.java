import com.fasterxml.jackson.core.JsonProcessingException;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;

import java.util.ArrayList;

public class MyWindow extends BasicWindow {
    public MyWindow(WindowBasedTextGUI gui) {
        super("TMDB CLI Tool");

        apiHandler apiHand = new apiHandler();
        jsonHandler jsonHand = new jsonHandler();

        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        ActionListBox menu = new ActionListBox();

        menu.addItem("Now Playing Movies", () -> {
            BasicWindow newWindow = new BasicWindow("Now Playing Movies");
            Panel newPanel = new Panel();
            panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
            ActionListBox movieMenu = new ActionListBox();

            try {
                ArrayList<Movie> listOfMovies = jsonHand.returnMovies(apiHand.sendHttpRequest("now_playing"));
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

            movieMenu.addItem("Exit", newWindow::close);

            newPanel.addComponent(movieMenu);
            newWindow.setComponent(newPanel);
            gui.addWindowAndWait(newWindow);
        });

        menu.addItem("Popular Movies", () -> {
            MessageDialog.showMessageDialog(
                    gui,
                    "Popular Movies",
                    "Here are the top 10 most popular movies!"
            );
        });

        menu.addItem("Top Rated Movies", () -> {
            MessageDialog.showMessageDialog(
                    gui,
                    "Top Rated Movies",
                    "Here are the top 10 best rated movies of all time!"
            );
        });

        menu.addItem("Upcoming Movies", () -> {
            MessageDialog.showMessageDialog(
                    gui,
                    "Upcoming Movies",
                    "Here are the top 10 most popular upcoming movies!"
            );
        });

        menu.addItem("Exit", MyWindow.this::close);

        panel.addComponent(menu);
        setComponent(panel);
    }
}
