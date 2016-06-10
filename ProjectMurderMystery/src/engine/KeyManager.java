package engine;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Consumer;

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
                }).forEach((Key key) -> (key.consumer.accept(key.object)));
                break;
        }
        return false;
    };

    private KeyManager() {
    }

    public synchronized static <T> void addKey(String name, int keyCode, T object, Consumer<T> consumer) {
        KEYS.put(name, new Key(keyCode, object, consumer));
    }

    public synchronized static Key removeKey(String name) {
        return KEYS.remove(name);
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
        public final Object object;
        public final Consumer consumer;

        public Key(int keyCode, Object object, Consumer consumer) {
            this.keyCode = keyCode;
            this.object = object;
            this.consumer = consumer;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 61 * hash + this.keyCode;
            hash = 61 * hash + Objects.hashCode(this.object);
            hash = 61 * hash + Objects.hashCode(this.consumer);
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
