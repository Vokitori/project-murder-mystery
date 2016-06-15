package engine.menu;

import engine.Game;
import engine.Screen;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

/**
 *
 * @author Voki
 */
public class Pause extends Screen {

    public Pause(Game game) {
        super(game);
        initComponents();
        container.setLocation(0, 0);
        container.setPreferredSize(new Dimension(200, 200));
        back.addActionListener((ActionEvent e) -> {
            new Thread(() -> game.inGameScreen.continueGame(this)).start();
        });
        exit.addActionListener((ActionEvent e) -> {
            new Thread(() -> game.inGameScreen.continueGame(this)).start();
            game.setScreen(game.mainScreen);
        });
        load.addActionListener((ActionEvent e) -> {
            game.setScreen(game.treeScreen);
        });
        options.addActionListener((ActionEvent e) -> {
            game.setScreen(game.optionsScreen);
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bufferLeft = new javax.swing.JPanel();
        back = new javax.swing.JButton();
        options = new javax.swing.JButton();
        load = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        bufferRight = new javax.swing.JPanel();

        setBackground(new java.awt.Color(0, 255, 204));
        setOpaque(false);
        setLayout(new java.awt.GridLayout());

        bufferLeft.setBackground(new java.awt.Color(233, 124, 124));
        bufferLeft.setOpaque(false);
        bufferLeft.setLayout(null);
        add(bufferLeft);

        container.setBackground(new java.awt.Color(160, 9, 9));

        back.setText("back");

        options.setText("options");

        load.setText("Load");

        exit.setText("exit");

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(load, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(options, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                        .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(options, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(load, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        add(container);

        bufferRight.setBackground(new java.awt.Color(233, 124, 124));
        bufferRight.setOpaque(false);
        bufferRight.setLayout(null);
        add(bufferRight);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JPanel bufferLeft;
    private javax.swing.JPanel bufferRight;
    public final javax.swing.JPanel container = new javax.swing.JPanel();
    private javax.swing.JButton exit;
    private javax.swing.JButton load;
    private javax.swing.JButton options;
    // End of variables declaration//GEN-END:variables
}
