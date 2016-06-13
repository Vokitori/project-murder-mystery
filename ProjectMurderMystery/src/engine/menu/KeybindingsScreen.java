package engine.menu;

import engine.Command;
import engine.Game;
import engine.KeyManager;
import engine.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Voki
 */
public class KeybindingsScreen extends Screen {

    private static final String KEYBINDINGS_NAME[] = {"next", "accelerate", "up", "down"};
    private static final Command KEYBINDINGS_COMMAND[] = {null, null, null, null};
    private static final String KEYBINDINGS_KEY[] = {
        KeyEvent.VK_ENTER + ", " + KeyEvent.VK_SHIFT,
        KeyEvent.VK_SPACE + ", " + KeyEvent.VK_SHIFT,
        KeyEvent.VK_W + ", " + KeyEvent.VK_UP,
        KeyEvent.VK_S + ", " + KeyEvent.VK_DOWN};

    private static final File CONFIG_FILE = new File("data/keybindings.cfg");

    public final Properties properties = new Properties();

    public KeybindingsScreen(Game game) {
        super(game);
        initComponents();
        readProperties();
        writeProperties();
        back.addActionListener((ActionEvent e) -> {
            game.setScreen(game.optionsScreen);
        });
    }

    @Override
    public void start() {
        KEYBINDINGS_COMMAND[0] = game::keyPressedAccept;
        KEYBINDINGS_COMMAND[1] = game::keyPressedCancel;
        KEYBINDINGS_COMMAND[2] = game::keyPressedUp;
        KEYBINDINGS_COMMAND[3] = game::keyPressedDown;

        updateKeybindings();
    }

    private void updateKeybindings() {
        KeyManager.clear();
        for (int i = 0; i < KEYBINDINGS_NAME.length; i++) {
            if (!properties.containsKey(KEYBINDINGS_NAME[i])) {
                continue;
            }
            String values[] = properties.getProperty(KEYBINDINGS_NAME[i]).split(",");
            for (int j = 0; j < values.length; j++) {
                int key = Integer.parseInt(values[j].trim());
                KeyManager.addKey(KEYBINDINGS_NAME[i] + j, key, KEYBINDINGS_COMMAND[i]);
            }
        }
    }

    private void readProperties() {
        boolean fileLoadingFailed = true;
        try {
            properties.load(new FileReader(CONFIG_FILE));
            fileLoadingFailed = false;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(game.window,
                    "Can't find " + CONFIG_FILE.getName() + "! File does not exist.",
                    "Loading keybindings failed!", JOptionPane.ERROR_MESSAGE);
            try {
                CONFIG_FILE.createNewFile();
            } catch (IOException ex1) {
                JOptionPane.showMessageDialog(game.window,
                        "Can't create " + CONFIG_FILE.getName() + "!",
                        "Creating keybindings failed!", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(KeybindingsScreen.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(game.window,
                    "Can't load " + CONFIG_FILE.getName() + "!",
                    "Loading keybindings failed!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KeybindingsScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (fileLoadingFailed) {
            for (int i = 0; i < KEYBINDINGS_KEY.length; i++) {
                properties.setProperty(KEYBINDINGS_NAME[i], KEYBINDINGS_KEY[i]);
            }
        }
    }

    private void writeProperties() {
        try {
            properties.store(new FileWriter(CONFIG_FILE), "");
        } catch (IOException ex) {
            try {
                CONFIG_FILE.createNewFile();
                properties.store(new FileWriter(CONFIG_FILE), "");
            } catch (IOException ex1) {
                JOptionPane.showMessageDialog(game.window,
                        "Can't write to " + CONFIG_FILE.getName() + " file!",
                        "Saving keybindings failed!", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(KeybindingsScreen.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JButton();

        back.setText("back");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(214, Short.MAX_VALUE)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    // End of variables declaration//GEN-END:variables
}
