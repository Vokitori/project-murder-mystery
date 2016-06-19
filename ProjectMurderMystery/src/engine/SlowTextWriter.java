package engine;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.JTextComponent;

/**
 * @author link
 */
public class SlowTextWriter {

    private static Lock lock = new ReentrantLock();

    public static final synchronized void fillSlowlyWith(JTextComponent into, String text) {
        try {
            lock.lock();
            Thread thread = new Thread(() -> {
                for (int i = 1; i < text.length(); i++) {
                    into.setText(text.substring(0, i));
                }
            });
            thread.setDaemon(true);
            thread.start();
        } finally {
            lock.unlock();
        }
    }
}
