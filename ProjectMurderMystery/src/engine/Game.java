package engine;

import engine.menu.NewPathScreen;
import engine.menu.IntroScreen;
import engine.menu.OptionsScreen;
import engine.menu.InGameScreen;
import engine.menu.MainMenuScreen;
import engine.menu.CreditsScreen;
import engine.menu.TreeScreen;
import engine.menu.KeybindingsScreen;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Voki
 */
public class Game {

    public final JFrame window = new JFrame("Titel");
    public final IntroScreen introScreen = new IntroScreen(this);
    public final CreditsScreen creditsScreen = new CreditsScreen(this);
    public final OptionsScreen optionsScreen = new OptionsScreen(this);
    public final KeybindingsScreen keybindingsScreen = new KeybindingsScreen(this);
    public final TreeScreen treeScreen = new TreeScreen(this);
    public final NewPathScreen newPathScreen = new NewPathScreen(this);
    public final InGameScreen inGameScreen = new InGameScreen(this);
    public final MainMenuScreen mainScreen = new MainMenuScreen(this);
    boolean ingame = false;

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
        setScreen(introScreen);
    }

    public void setScreen(Screen screen) {
        screen.resume();
        if (ingame && screen == mainScreen) {
            if (window.getContentPane() == inGameScreen) {
                ingame = false;
            } else {
                window.setContentPane(inGameScreen);
                screen.repaint();
                screen.revalidate();
                return;
            }
        }
        if (screen == inGameScreen) {
            ingame = true;
        }
        window.setContentPane(screen);
        screen.revalidate();
    }
}
