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

    private Ref<BufferedImage> image = new Ref<>();
    protected boolean keepRatio = false;

    public ImagePanel() {
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        if (image.object != null) {
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
        super.paint(g);
    }

    public void setImage(Ref<BufferedImage> image) {
        this.image = image;
    }

    public void setKeepRatio(boolean keepRatio) {
        this.keepRatio = keepRatio;
    }

}
