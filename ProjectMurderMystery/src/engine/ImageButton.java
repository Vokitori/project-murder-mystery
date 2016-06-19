package engine;

import java.awt.Graphics;
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
            g.drawImage(image.object, 0, 0, getWidth(), getHeight(), this);
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
