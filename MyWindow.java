import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;

public class MyWindow extends BasicWindow {
    public MyWindow(WindowBasedTextGUI gui) {
        super("TMDB CLI Tool");

        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        ActionListBox menu = new ActionListBox();

        menu.addItem("Now Playing Movies", () -> {
            MessageDialog.showMessageDialog(
                    gui,
                    "Now Playing Movies",
                    "Here are the top 10 movies that are being screened right now!"
            );
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
