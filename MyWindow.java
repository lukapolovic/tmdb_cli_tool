import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;

public class MyWindow extends BasicWindow {
    public MyWindow(WindowBasedTextGUI gui) {
        super("TMDB CLI Tool");

        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        ActionListBox menu = new ActionListBox();

        menu.addItem("Top Panel", () -> {
            MessageDialog.showMessageDialog(gui, "Top", "Top pannel selected");
        });

        menu.addItem("Middle Panel", () -> {
            BasicWindow newWindow = new BasicWindow("Middle panel");

            Panel p = new Panel();
            p.addComponent(new Label("Middle panel content"));
            p.addComponent(new Button("Back", newWindow::close));

            newWindow.setComponent(p);
            gui.addWindowAndWait(newWindow);
        });

        menu.addItem("Bottom Panel", () -> {
            MessageDialog.showMessageDialog(gui, "Bottom", "Bottom pannel selected");
        });

        panel.addComponent(menu);
        setComponent(panel);
    }
}
