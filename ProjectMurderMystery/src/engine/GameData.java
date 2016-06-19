package engine;

import engine.fileparser.DataPackage;
import engine.stuff.Decision;
import engine.fileparser.FileParser;
import engine.stuff.GameEvent;
import engine.stuff.Music;
import engine.fileparser.SmallNode;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Voki
 */
public class GameData {

    private LinkedList<SmallNode> nodes;

    private final Ref<BufferedImage> slot1= new Ref<>();
    private final Ref<BufferedImage> slot2= new Ref<>();
    private final Ref<BufferedImage> slot3 = new Ref<>();
    private final Ref<BufferedImage> slot4= new Ref<>();
    private final Ref<BufferedImage> background= new Ref<>();
    private GameEvent gameEvent;
    private Music music;
    private String text;
    private LinkedList<Decision> decisionList;

    private String currentBigNode;
    private int currentSmallNode= 0;

    public GameData(String startFile) throws FileNotFoundException, IOException {
        currentBigNode = Game.getTextPath() + startFile;
        nodes = FileParser.parseBigNode(new File(currentBigNode), Game.getImagePath(), Game.getSoundPath(), Game.getTextPath());
    }

    public String getCurrentBigNode() {
        return currentBigNode;
    }

    public int getCurrentSmallNode() {
        return currentSmallNode;
    }

    public void nextNode(int decision) {
        try {
            currentBigNode = decisionList.get(decision).file.toString();
            currentSmallNode = 0;
            nodes = FileParser.parseBigNode(decisionList.get(decision).file, Game.getImagePath(), Game.getSoundPath(), Game.getTextPath());
            nextNode();
        } catch (IOException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean hasNextNode() {
        return !nodes.isEmpty();
    }

    public void skipNodes(int count) {
        for (int i = 0; i < count; i++) {
            nextNode();
        }
    }

    public void nextNode() {
        currentSmallNode++;

        SmallNode node = nodes.pop();
        slot1.object = getNext(slot1.object, node.slot1);
        slot2.object = getNext(slot2.object, node.slot2);
        slot3.object = getNext(slot3.object, node.slot3);
        slot4.object = getNext(slot4.object, node.slot4);
        background.object = getNext(background.object, node.background);
        gameEvent = getNext(gameEvent, node.gameEvent);
        music = getNext(music, node.music);
        text = node.text;
        decisionList = node.decisionList;
    }

    private <T> T getNext(T oldData, DataPackage<T> newData) {
        switch (newData.dataAction) {
            case DELETE:
                return null;
            case KEEP:
                return oldData;
            case LOAD:
                return newData.data;
        }
        throw new RuntimeException("DIS IS IMPOSSIBLE");
    }

    public Ref<BufferedImage> getSlot1() {
        return slot1;
    }

    public Ref<BufferedImage> getSlot2() {
        return slot2;
    }

    public Ref<BufferedImage> getSlot3() {
        return slot3;
    }

    public Ref<BufferedImage> getSlot4() {
        return slot4;
    }

    public Ref<BufferedImage> getBackground() {
        return background;
    }

    public GameEvent getGameEvent() {
        return gameEvent;
    }

    public Music getMusic() {
        return music;
    }

    public String getText() {
        return text;
    }

    public LinkedList<Decision> getDecisionList() {
        return decisionList;
    }

}
