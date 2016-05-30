package engine.menu;

import engine.Game;
import engine.Screen;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Voki
 */
public class IntroScreen extends Screen {

    public IntroScreen(Game game) {
        super(game);
        setBackground(Color.black);
    }

    @Override
    public void resume() {
        super.resume();
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(222);
                game.setScreen(game.mainScreen);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        t.start();

    }

}
