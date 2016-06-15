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
        panel1 = new java.awt.Panel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        textField1 = new java.awt.TextField();
        textField2 = new java.awt.TextField();
        textField3 = new java.awt.TextField();
        textField4 = new java.awt.TextField();

        back.setText("back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        label1.setText("Text beschleunigen");

        label2.setText("Auswählen");

        label3.setText("Rauf");

        label4.setText("Runter");

        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(textField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(textField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(textField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addGap(72, 72, 72))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        label1.getAccessibleContext().setAccessibleName("panel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(324, 324, 324)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(323, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 346, Short.MAX_VALUE)
                .addComponent(back)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Panel panel1;
    private java.awt.TextField textField1;
    private java.awt.TextField textField2;
    private java.awt.TextField textField3;
    private java.awt.TextField textField4;
    // End of variables declaration//GEN-END:variables
}
