package engine.menu;

import engine.Game;
import engine.Screen;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author Voki
 */
public class InGameScreen extends Screen {

    public final Pause pauseMenu = new Pause(game);
    private JPanel overlay = null;

    public InGameScreen(Game game) {
        super(game);
        initComponents();
        pauseButton.addActionListener((ActionEvent e) -> new Thread(() -> pauseGame(pauseMenu)).start());

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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gamePanel = new javax.swing.JPanel();
        textlog = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setLayout(new javax.swing.OverlayLayout(this));

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

        jTextPane1.setEditable(false);
        jTextPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 427, Short.MAX_VALUE)
                        .addComponent(textlog, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textlog, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(gamePanel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel gamePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton textlog;
    // End of variables declaration//GEN-END:variables
}
