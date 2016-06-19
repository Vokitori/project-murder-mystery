package engine.menu;

import engine.Game;
import engine.Screen;
import java.awt.event.ActionEvent;

/**
 *
 * @author Voki
 */
public class TreeScreen extends Screen {

    public TreeScreen(Game game) {
        super(game);

        initComponents();
        back.addActionListener((ActionEvent e) -> {
            game.setScreen(game.mainScreen);
        });
        load.addActionListener((ActionEvent e) -> {
            game.inGameScreen.autoLoad();
            game.setScreen(game.inGameScreen);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        back = new javax.swing.JButton();
        load = new javax.swing.JButton();

        jScrollPane1.setViewportView(jTree1);

        back.setText("back");

        load.setText("load");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(load)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                .addComponent(back)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(255, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(load)
                    .addComponent(back))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JButton load;
    // End of variables declaration//GEN-END:variables
}
