package engine.menu;

import engine.Game;
import engine.MenuPanel;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Voki
 */
public class Intro extends MenuPanel {

    public Intro(Game game) {
        super(game);
        setBackground(Color.black);
    }

    @Override
    public void resume() {
        super.resume();
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(222);
                game.setMenu(game.inGame);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        t.start();

    }

}
