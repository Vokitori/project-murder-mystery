package fileparser;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * @author Voki
 */
public class SmallNode {

    public final DataPackage<BufferedImage> slot1;
    public final DataPackage<BufferedImage> slot2;
    public final DataPackage<BufferedImage> slot3;
    public final DataPackage<BufferedImage> slot4;
    public final DataPackage<BufferedImage> background;
    public final DataPackage<GameEvent> gameEvent;
    public final DataPackage<Music> music;
    public final String text;
    public final LinkedList<Decision> decisionList;

    public SmallNode(DataPackage<BufferedImage> slot1,
            DataPackage<BufferedImage> slot2, DataPackage<BufferedImage> slot3,
            DataPackage<BufferedImage> slot4, DataPackage<BufferedImage> background,
            DataPackage<GameEvent> gameEvent, DataPackage<Music> music, String text,
            LinkedList<Decision> decisionList) {
        this.slot1 = slot1;
        this.slot2 = slot2;
        this.slot3 = slot3;
        this.slot4 = slot4;
        this.background = background;
        this.gameEvent = gameEvent;
        this.music = music;
        this.text = text;
        this.decisionList = decisionList;
    }

   

    

}
