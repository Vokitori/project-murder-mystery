package engine.menu;

import engine.ImagePanel;
import engine.Game;
import engine.GameData;
import engine.Screen;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Voki
 */
public class InGameScreen extends Screen {

    public final Pause pauseMenu = new Pause(game);
    private JPanel overlay = null;
    private GameData gameData = null;

    public InGameScreen(Game game) {
        super(game);
        try {
            gameData = new GameData();
        } catch (IOException ex) {
            Logger.getLogger(InGameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponents();
        pauseButton.addActionListener((ActionEvent e) -> new Thread(() -> pauseGame(pauseMenu)).start());
        onNextScreen();
        ((ImagePanel) slot1).setImage(gameData.getSlot1());
        ((ImagePanel) slot2).setImage(gameData.getSlot2());
        ((ImagePanel) slot3).setImage(gameData.getSlot3());
        ((ImagePanel) slot4).setImage(gameData.getSlot4());
        ((ImagePanel) gamePanel).setImage(gameData.getBackground());
    }

    public final void onNextScreen() {
        gameData.nextNode();
        textBox.setText(gameData.getText());
        validate();
        repaint();
        System.out.println(gameData.getSlot1().object);
    }

    public void pauseGame(Pause pauseMenu) {
        overlay = pauseMenu.container;
        pauseGame((Screen) pauseMenu);
    }

    private void pauseGame(Screen menu) {
        textlog.setEnabled(false);
        pauseButton.setEnabled(false);
        add(menu, 1, 0);
        game.inGameScreen.repaint();
        menu.revalidate();
    }

    public void continueGame(Screen menu) {
        textlog.setEnabled(true);
        pauseButton.setEnabled(true);
        overlay = null;
        remove(menu);
        game.inGameScreen.repaint();
        game.inGameScreen.revalidate();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (overlay != null) {
            overlay.paint(g);
        }
        if (gameData.getBackground() != null) {
            ((ImagePanel) gamePanel).setImage(gameData.getBackground());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gamePanel = new ImagePanel();
        textlog = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textBox = new javax.swing.JTextPane();
        slot1 = new ImagePanel();
        slot2 = new ImagePanel();
        slot3 = new ImagePanel();
        slot4 = new ImagePanel();

        setLayout(new javax.swing.OverlayLayout(this));

        gamePanel.setBackground(new java.awt.Color(205, 99, 99));
        gamePanel.setOpaque(false);

        textlog.setText("L");
        textlog.setFocusPainted(false);
        textlog.setFocusable(false);
        textlog.setRequestFocusEnabled(false);
        textlog.setRolloverEnabled(false);

        pauseButton.setText("P");
        pauseButton.setFocusPainted(false);
        pauseButton.setFocusable(false);
        pauseButton.setRequestFocusEnabled(false);
        pauseButton.setRolloverEnabled(false);

        textBox.setEditable(false);
        textBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(textBox);

        javax.swing.GroupLayout slot1Layout = new javax.swing.GroupLayout(slot1);
        slot1.setLayout(slot1Layout);
        slot1Layout.setHorizontalGroup(
            slot1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
        );
        slot1Layout.setVerticalGroup(
            slot1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot2Layout = new javax.swing.GroupLayout(slot2);
        slot2.setLayout(slot2Layout);
        slot2Layout.setHorizontalGroup(
            slot2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
        );
        slot2Layout.setVerticalGroup(
            slot2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot3Layout = new javax.swing.GroupLayout(slot3);
        slot3.setLayout(slot3Layout);
        slot3Layout.setHorizontalGroup(
            slot3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
        );
        slot3Layout.setVerticalGroup(
            slot3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot4Layout = new javax.swing.GroupLayout(slot4);
        slot4.setLayout(slot4Layout);
        slot4Layout.setHorizontalGroup(
            slot4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
        );
        slot4Layout.setVerticalGroup(
            slot4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gamePanelLayout.createSequentialGroup()
                        .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 427, Short.MAX_VALUE)
                        .addComponent(textlog, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addComponent(slot1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(slot3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slot4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        gamePanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {slot1, slot2, slot3, slot4});

        gamePanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {pauseButton, textlog});

        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textlog, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slot1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(slot4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        gamePanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {slot1, slot2, slot3, slot4});

        gamePanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {pauseButton, textlog});

        add(gamePanel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel gamePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pauseButton;
    private javax.swing.JPanel slot1;
    private javax.swing.JPanel slot2;
    private javax.swing.JPanel slot3;
    private javax.swing.JPanel slot4;
    private javax.swing.JTextPane textBox;
    private javax.swing.JButton textlog;
    // End of variables declaration//GEN-END:variables
}
