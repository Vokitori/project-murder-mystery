package engine;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author link
 */
public class KeyManager {

    private static final HashMap<String, Key> KEYS = new HashMap<>();
    private static KeyboardFocusManager focusManager;
    private static final KeyEventDispatcher DISPATCHER = (KeyEvent ke) -> {
        switch (ke.getID()) {
            case KeyEvent.KEY_PRESSED:
                KEYS.values().stream().filter((Key key) -> {
                    return key.keyCode == ke.getKeyCode();
                }).forEach((Key key) -> key.command.execute());
                break;
        }
        return false;
    };

    private KeyManager() {
    }

    public synchronized static void addKey(String name, int keyCode, Command command) {
        KEYS.put(name, new Key(keyCode, command));
    }

    public synchronized static Key removeKey(String name) {
        return KEYS.remove(name);
    }

    public synchronized static void clear() {
        KEYS.clear();
    }

    public static String toStringStatic() {
        return KEYS.toString();
    }

    public static boolean isListening() {
        return focusManager != null;
    }

    public static void startListening() {
        if (focusManager != null) {
            return;
        }
        focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        focusManager.addKeyEventDispatcher(DISPATCHER);
    }

    public static void stopListening() {
        if (focusManager == null) {
            return;
        }
        focusManager.removeKeyEventDispatcher(DISPATCHER);
        focusManager = null;
    }

    public static class Key {

        public final int keyCode;
        public final Command command;

        public Key(int keyCode, Command command) {
            this.keyCode = keyCode;
            this.command = command;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 23 * hash + this.keyCode;
            hash = 23 * hash + Objects.hashCode(this.command);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            return this.keyCode == ((Key) obj).keyCode;
        }
    }
}
