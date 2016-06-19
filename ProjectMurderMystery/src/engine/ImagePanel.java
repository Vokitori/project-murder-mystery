/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author link
 */
public class ImagePanel extends JPanel {

    private Ref<BufferedImage> image = new Ref<>();
    protected boolean keepRatio = false;
    private boolean inverted = false;

    public ImagePanel() {
        this(false);
    }

    public ImagePanel(boolean inverted) {
        setOpaque(false);
        this.inverted = inverted;
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
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            int x0 = (int) ((getWidth() - w) / 2.0);
            int y0 = getHeight() - h;
            if (inverted) {
                w = -w;
                x0 = getWidth() - x0;
            }
            g2d.drawImage(image.object, x0, y0, w, h, this);
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
