package engine;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Voki
 */
public abstract class Screen extends JPanel {

    protected final Game game;
    protected boolean active = false;

    public Screen(Game game) {
        this.game = game;
    }

    public void start() {
    }

    public void resume() {
        active = true;
    }

    public void pause() {
        active = false;
    }

    public void keyPressedAccept() {
        Component focus = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        if (focus instanceof JButton) {
            ((JButton) focus).doClick();
        }
    }

    public void keyPressedCancel() {
    }

    public void keyPressedUp() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().focusPreviousComponent();
    }

    public void keyPressedDown() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
    }
}
