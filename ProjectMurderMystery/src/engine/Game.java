package engine;

import engine.menu.NewPath;
import engine.menu.Intro;
import engine.menu.Options;
import engine.menu.InGame;
import engine.menu.MainMenu;
import engine.menu.Credits;
import engine.menu.RouteTree;
import engine.menu.Keybindings;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Voki
 */
public class Game {

    public final JFrame window = new JFrame("Titel");
    public final Intro intro = new Intro(this);
    public final Credits credits = new Credits(this);
    public final Options options = new Options(this);
    public final Keybindings keybindings = new Keybindings(this);
    public final RouteTree routeTree = new RouteTree(this);
    public final NewPath newPath = new NewPath(this);
    public final InGame inGame = new InGame(this);
    public final MainMenu mainMenu = new MainMenu(this);

    public void load() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(800, 600));
        window.pack();
        window.setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public void start() {

        window.setVisible(true);
        
        setMenu(intro);
    }

    public void setMenu(MenuPanel menu) {
        menu.resume();
        window.setContentPane(menu);
        menu.revalidate();
    }
}
