/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Pause extends MenuPanel {

    public Pause(Game game) {
        super(game);
        initComponents();

        setBounds(50, 50, 800, 800);
        setBackground(Color.green);

        back.addActionListener((ActionEvent e) -> {
            game.inGame.remove(this);
        });
        exit.addActionListener((ActionEvent e) -> {
            game.setMenu(game.mainMenu);
        });
        load.addActionListener((ActionEvent e) -> {
            game.setMenu(game.routeTree);
        });
        options.addActionListener((ActionEvent e) -> {
            game.setMenu(game.options);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JButton();
        options = new javax.swing.JButton();
        load = new javax.swing.JButton();
        exit = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 255, 204));

        back.setText("back");
        add(back);

        options.setText("options");
        add(options);

        load.setText("Load");
        add(load);

        exit.setText("exit");
        add(exit);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton exit;
    private javax.swing.JButton load;
    private javax.swing.JButton options;
    // End of variables declaration//GEN-END:variables
}
