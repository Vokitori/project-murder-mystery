package engine.menu;

import engine.Game;
import engine.MenuPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

/**
 *
 * @author Voki
 */
public class InGame extends MenuPanel {

    public final Pause pauseMenu = new Pause(game);

    public InGame(Game game) {
        super(game);
        initComponents();
        pauseButton.addActionListener((ActionEvent e) -> pauseGame(pauseMenu));
    }

    public void pauseGame(MenuPanel menu) {
        textlog.setEnabled(false);
        pauseButton.setEnabled(false);
        add(menu, 1,0);
        menu.repaint();
        menu.revalidate();
    }

    public void continueGame(MenuPanel menu) {
        remove(menu);
        textlog.setEnabled(true);
        pauseButton.setEnabled(true);
        game.inGame.repaint();
        game.inGame.revalidate();
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

        pauseButton.setText("P");

        jTextPane1.setEditable(false);
        jTextPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                .addGap(165, 165, 165))
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gamePanelLayout.createSequentialGroup()
                    .addContainerGap(914, Short.MAX_VALUE)
                    .addComponent(textlog, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gamePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(textlog, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(380, Short.MAX_VALUE)))
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
