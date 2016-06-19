package engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

/**
 * @author link
 */
public class ImageButton extends JButton {

    private Ref<BufferedImage> image = new Ref<>();

    @Override
    public void paint(Graphics g) {
        if (image.object != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.drawImage(image.object, 0, 0, getWidth(), getHeight(), this);
        } else {
            paintComponent(g);
        }
        paintBorder(g);
        paintChildren(g);
    }

    public void setImage(Ref<BufferedImage> image) {
        this.image = image;
    }

}
