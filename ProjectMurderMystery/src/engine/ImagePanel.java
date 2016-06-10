/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author link
 */
public class ImagePanel extends JPanel {

    private Ref<BufferedImage> image;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image != null) {
            g.drawImage(image.object, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void setImage(Ref<BufferedImage> image) {
        this.image = image;
    }

}
