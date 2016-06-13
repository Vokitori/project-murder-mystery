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
    protected boolean keepRatio = false;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image.object != null) {
            int x1, y1;
            int w = getWidth(), h = getHeight();
            if (keepRatio) {
                double imageRatio = (double) image.object.getWidth() / image.object.getHeight();
                double panelRatio = (double) getWidth() / getHeight();
                if (panelRatio > imageRatio) {
                    w = (int) (imageRatio * getHeight());
                    h = getHeight();
                } else {
                    w = getWidth();
                    h = (int) (getWidth() / imageRatio);
                }
            }
            g.drawImage(image.object, (int) ((getWidth() - w) / 2.0), getHeight() - h, w, h, this);
        }
    }

    public void setImage(Ref<BufferedImage> image) {
        this.image = image;
    }

    public void setKeepRatio(boolean keepRatio) {
        this.keepRatio = keepRatio;
    }

}
