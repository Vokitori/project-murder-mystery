package engine.menu;

import engine.DecisionButton;
import engine.ImagePanel;
import engine.Game;
import engine.GameData;
import engine.Screen;
import engine.SlowTextWriter;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 * @author Voki
 */
public class InGameScreen extends Screen {

    public final Pause pauseMenu = new Pause(game);
    private JPanel overlay = null;
    private GameData gameData = null;

    public InGameScreen(Game game) {
        super(game);
        initComponents();
        pauseButton.addActionListener((ActionEvent e) -> new Thread(() -> pauseGame(pauseMenu)).start());
    }

    public void load(String startFile) {
        load(startFile, 0);
    }

    protected void load(String startFile, int skipSmallNodes) {
        try {
            gameData = new GameData(startFile);
            gameData.skipNodes(skipSmallNodes);
            nextScreen();
            ((ImagePanel) slot1).setImage(gameData.getSlot1());
            ((ImagePanel) slot2).setImage(gameData.getSlot2());
            ((ImagePanel) slot3).setImage(gameData.getSlot3());
            ((ImagePanel) slot4).setImage(gameData.getSlot4());

            ((ImagePanel) slot1).setKeepRatio(true);
            ((ImagePanel) slot2).setKeepRatio(true);
            ((ImagePanel) slot3).setKeepRatio(true);
            ((ImagePanel) slot4).setKeepRatio(true);
            ((ImagePanel) gamePanel).setImage(gameData.getBackground());
        } catch (IOException ex) {
            Logger.getLogger(InGameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void autoLoad() {
        try (Scanner scanner = new Scanner(new File(Game.getAutoSavePath()))) {
            load(scanner.nextLine(), scanner.nextInt());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InGameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void autoSave() {
        try (PrintWriter writer = new PrintWriter(Game.getAutoSavePath())) {
            writer.println(gameData.getCurrentBigNode().replace(Game.getTextPath(), ""));
            writer.println(gameData.getCurrentSmallNode() - 1);
            writer.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InGameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void keyPressedAccept() {
        if (active) {
            if (gameData.hasNextNode()) {
                nextScreen();
            }
        }
    }

    public final void nextScreen(int i) {
        if (!SlowTextWriter.canContinue) {
            return;
        }
        gameData.nextNode(i);
        nextScreen(null);
    }

    public final void nextScreen() {
        if (!SlowTextWriter.canContinue) {
            return;
        }
        gameData.nextNode();
        nextScreen(null);
    }

    private void nextScreen(Void v) {
        if (gameData.getDecisionList() != null && !gameData.getDecisionList().isEmpty()) {
            ((DecisionButton) decisionTop).setDecision(this, gameData.getDecisionList().get(0).decisionText, 0);
            ((DecisionButton) decisionBottom).setDecision(this, gameData.getDecisionList().get(1).decisionText, 1);
            textDecisionPanel.remove(textBox);
            textDecisionPanel.add(choicePanel, BorderLayout.CENTER);
        } else {
            SlowTextWriter.fillSlowlyWith(textBox, gameData.getText());
            textDecisionPanel.remove(choicePanel);
            textDecisionPanel.add(textBox, BorderLayout.CENTER);
        }

        autoSave();
        SlowTextWriter.skip = false;
        validate();
        repaint();
    }

    public void pauseGame(Pause pauseMenu) {
        overlay = pauseMenu.container;
        pauseGame((Screen) pauseMenu);
        pauseMenu.resume();
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
        menu.pause();
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

    @Override
    public void keyPressedCancel() {
        SlowTextWriter.skip = true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textPanel = new javax.swing.JPanel();
        textBox = new javax.swing.JTextPane();
        choicePanel = new javax.swing.JPanel();
        decisionTop = new DecisionButton();
        decisionBottom = new DecisionButton();
        gamePanel = new engine.ImagePanel();
        textlog = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        textDecisionPanel = new javax.swing.JPanel();
        slot1 = new engine.ImagePanel(true);
        slot2 = new engine.ImagePanel(true);
        slot3 = new engine.ImagePanel();
        slot4 = new engine.ImagePanel();

        textPanel.setBackground(new java.awt.Color(244, 63, 220));
        textPanel.setLayout(new java.awt.BorderLayout());

        textBox.setEditable(false);
        textBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        textBox.setFocusable(false);
        textBox.setRequestFocusEnabled(false);
        textPanel.add(textBox, java.awt.BorderLayout.CENTER);
        textBox.setHighlighter(null);

        choicePanel.setBackground(new java.awt.Color(150, 150, 150));
        choicePanel.setLayout(new java.awt.BorderLayout());

        decisionTop.setText("choiceTop");
        choicePanel.add(decisionTop, java.awt.BorderLayout.NORTH);

        decisionBottom.setText("choiceBottom");
        choicePanel.add(decisionBottom, java.awt.BorderLayout.SOUTH);

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

        textDecisionPanel.setBackground(new java.awt.Color(220, 76, 76));
        textDecisionPanel.setMaximumSize(new java.awt.Dimension(559, 101));
        textDecisionPanel.setMinimumSize(new java.awt.Dimension(559, 101));
        textDecisionPanel.setOpaque(false);
        textDecisionPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout slot1Layout = new javax.swing.GroupLayout(slot1);
        slot1.setLayout(slot1Layout);
        slot1Layout.setHorizontalGroup(
            slot1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        slot1Layout.setVerticalGroup(
            slot1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot2Layout = new javax.swing.GroupLayout(slot2);
        slot2.setLayout(slot2Layout);
        slot2Layout.setHorizontalGroup(
            slot2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        slot2Layout.setVerticalGroup(
            slot2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot3Layout = new javax.swing.GroupLayout(slot3);
        slot3.setLayout(slot3Layout);
        slot3Layout.setHorizontalGroup(
            slot3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        slot3Layout.setVerticalGroup(
            slot3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot4Layout = new javax.swing.GroupLayout(slot4);
        slot4.setLayout(slot4Layout);
        slot4Layout.setHorizontalGroup(
            slot4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        slot4Layout.setVerticalGroup(
            slot4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textDecisionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gamePanelLayout.createSequentialGroup()
                        .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 427, Short.MAX_VALUE)
                        .addComponent(textlog, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gamePanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(slot1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(63, 63, 63)
                        .addComponent(slot3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );

        gamePanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {pauseButton, textlog});

        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textlog, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slot1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(slot3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(slot2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(slot4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(textDecisionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        gamePanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {pauseButton, textlog});

        add(gamePanel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel choicePanel;
    private javax.swing.JButton decisionBottom;
    private javax.swing.JButton decisionTop;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JButton pauseButton;
    private javax.swing.JPanel slot1;
    private javax.swing.JPanel slot2;
    private javax.swing.JPanel slot3;
    private javax.swing.JPanel slot4;
    private javax.swing.JTextPane textBox;
    private javax.swing.JPanel textDecisionPanel;
    private javax.swing.JPanel textPanel;
    private javax.swing.JButton textlog;
    // End of variables declaration//GEN-END:variables
}
