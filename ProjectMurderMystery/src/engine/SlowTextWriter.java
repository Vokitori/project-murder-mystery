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

    private static final Lock LOCK = new ReentrantLock();
    public static boolean skip = false;
    public static boolean canContinue = true;

    public static final synchronized void fillSlowlyWith(JTextComponent into, String text) {
        canContinue = false;
        try {
            LOCK.lock();
            Thread thread = new Thread(() -> {
                for (int i = 1; i <= text.length(); i++) {
                    if (skip) {
                        break;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SlowTextWriter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    into.setText(text.substring(0, i));
                }
                canContinue = true;
                skip = false;
                into.setText(text);
            });
            thread.setDaemon(true);
            thread.start();
        } finally {
            LOCK.unlock();
        }
    }
}
