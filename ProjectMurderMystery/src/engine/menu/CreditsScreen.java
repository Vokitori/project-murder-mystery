package engine.menu;

import engine.Game;
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

    private BufferedImage backgroundImage = null;
    public CreditsScreen(Game game) {
        super(game);
        
        initComponents();
        try {
            backgroundImage = ImageIO.read(new File("data/credits.png"));
        } catch (IOException ex) {
            Logger.getLogger(CreditsScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        creditsText.setBackground(new Color(0, 0, 0, 0));
        
        back.addActionListener((ActionEvent e) -> {
            game.setScreen(game.mainScreen);
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JButton();
        creditsText = new javax.swing.JTextArea();

        back.setText("Back");

        creditsText.setEditable(false);
        creditsText.setColumns(20);
        creditsText.setFont(new java.awt.Font("Book Antiqua", 0, 24)); // NOI18N
        creditsText.setRows(5);
        creditsText.setText("Game by:\nThomas,\nJulia,\nViktoria,\nMaximilian");
        creditsText.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(back))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(creditsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(creditsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(back)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JTextArea creditsText;
    // End of variables declaration//GEN-END:variables
}
