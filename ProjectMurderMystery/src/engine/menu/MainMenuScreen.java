package engine.menu;

import engine.Game;
import engine.ImageButton;
import engine.Ref;
import engine.Screen;
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
public class MainMenuScreen extends Screen {

    public static final Ref<BufferedImage> BUTTON_IMAGE = new Ref<>();

    static {
        try {
            BUTTON_IMAGE.object = ImageIO.read(new File("data/button.png"));
        } catch (IOException ex) {
            Logger.getLogger(MainMenuScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MainMenuScreen(Game game) {
        super(game);

        initComponents();

        newPath.addActionListener((ActionEvent e) -> {
            game.setScreen(game.newPathScreen);
        });
        load.addActionListener((ActionEvent e) -> {
            game.setScreen(game.treeScreen);
        });
        options.addActionListener((ActionEvent e) -> {
            game.setScreen(game.optionsScreen);
        });
        credits.addActionListener((ActionEvent e) -> {
            game.setScreen(game.creditsScreen);
        });
        leaveGame.addActionListener((ActionEvent e) -> {
            game.window.dispose();
        });

        try {
            Ref<BufferedImage> img = new Ref<>(ImageIO.read(new File("data/NotFree.png")));
            ((ImageButton) credits).setImage(BUTTON_IMAGE);
            ((ImageButton) leaveGame).setImage(BUTTON_IMAGE);
            ((ImageButton) load).setImage(BUTTON_IMAGE);
            ((ImageButton) newPath).setImage(BUTTON_IMAGE);
            ((ImageButton) options).setImage(BUTTON_IMAGE);

        } catch (IOException ex) {
            Logger.getLogger(NewPathScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        credits = new ImageButton();
        newPath = new ImageButton();
        load = new ImageButton();
        leaveGame = new ImageButton();
        options = new ImageButton();

        credits.setText("Credits");

        newPath.setText("New Path");

        load.setText("Load");

        leaveGame.setText("Leave Game");

        options.setText("Options");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newPath, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(load, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(options, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(credits, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(331, Short.MAX_VALUE)
                .addComponent(leaveGame, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(324, 324, 324))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {credits, load, newPath, options});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(newPath, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(load, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(options, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(credits, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
                .addComponent(leaveGame, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {credits, load, newPath, options});

    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton credits;
    private javax.swing.JButton leaveGame;
    private javax.swing.JButton load;
    private javax.swing.JButton newPath;
    private javax.swing.JButton options;
    // End of variables declaration//GEN-END:variables
}
