
import engine.KeyManager;
import engine.Ref;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 * @author link
 */
public class KeyTest {

    public static void main(String[] args) throws InterruptedException {
        Ref<Integer> ref = new Ref<>(-1);

        KeyManager.addKey("w", KeyEvent.VK_W, ref, (Ref<Integer> r) -> {
            r.object++;
            System.out.println("W" + r);
        });

        KeyManager.addKey("o", KeyEvent.VK_ENTER, ref, (Ref<Integer> r) -> {
            KeyManager.removeKey("o");
        });
        KeyManager.startListening();
        JFrame jframe = new JFrame();
        jframe.setVisible(true);
    }
}
