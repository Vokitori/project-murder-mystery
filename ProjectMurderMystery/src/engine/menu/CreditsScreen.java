package engine.menu;

import engine.Game;
import engine.Ref;
import engine.Screen;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Voki
 */
public class CreditsScreen extends Screen {
    public CreditsScreen(Game game) {
        super(game);
        
        initComponents();
        try {
        setImage(new Ref<>(ImageIO.read(new File("data/credits.png"))));
        } catch (IOException ex) {
            Logger.getLogger(CreditsScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        creditsText.setBackground(new Color(0, 0, 0, 0));
        
        back.addActionListener((ActionEvent e) -> {
            game.setScreen(game.mainScreen);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JButton();
        creditsText = new javax.swing.JTextArea();

        back.setText("Back");

        creditsText.setEditable(false);
        creditsText.setColumns(20);
        creditsText.setFont(new java.awt.Font("Courier 10 Pitch", 0, 24)); // NOI18N
        creditsText.setForeground(new java.awt.Color(0, 0, 0));
        creditsText.setRows(5);
        creditsText.setText("Spiel gemacht von:\nThomas,\nViktoria,\nJulia,\nMaximilian");
        creditsText.setCaretColor(new java.awt.Color(255, 255, 255));
        creditsText.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(376, 376, 376)
                        .addComponent(back))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(creditsText, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(309, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(creditsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 308, Short.MAX_VALUE)
                .addComponent(back)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JTextArea creditsText;
    // End of variables declaration//GEN-END:variables
}
