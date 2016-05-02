package engine;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Voki
 */
public class Game {

    private final JFrame window = new JFrame("Titel");
    private final Intro intro = new Intro();
    private final Credits credits = new Credits();
    private final Options options = new Options();
    private final Keybindings keybindings = new Keybindings();
    private final RoutTree routTree = new RoutTree();
    private final ChoosePath choosePath = new ChoosePath();
    private final InGame inGame = new InGame();

    public void load() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(800, 600));
        window.pack();
        window.setLocationRelativeTo(null);
    }

    public void start() {

        window.setVisible(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        setMenu(intro);
    }

    public void setMenu(JPanel menu) {
        window.setContentPane(menu);
        menu.revalidate();
    }
}
