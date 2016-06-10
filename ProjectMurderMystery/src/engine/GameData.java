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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Voki
 */
public class GameData {

    private LinkedList<SmallNode> nodes;

    private Ref<BufferedImage> slot1;
    private Ref<BufferedImage> slot2;
    private Ref<BufferedImage> slot3;
    private Ref<BufferedImage> slot4;
    private Ref<BufferedImage> background;
    private GameEvent gameEvent;
    private Music music;
    private String text;
    private LinkedList<Decision> decisionList;
    public final String folder;

    public GameData() throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(new File("stories/storyselect"));
        folder = "stories/" + scanner.nextLine() + "/";
        String path = getTextPath() + "start.txt";
        nodes = FileParser.parseBigNode(new File(path), getImagePath(), getSoundPath());
        slot1 = new Ref<>();
        slot2 = new Ref<>();
        slot3 = new Ref<>();
        slot4 = new Ref<>();
        background = new Ref<>();
    }

    public final String getSoundPath() {
        return folder + "sound/";
    }

    public final String getTextPath() {
        return folder + "text/";
    }

    public final String getImagePath() {
        return folder + "image/";
    }

    public void nextNode(int decision) {
        try {
            nodes = FileParser.parseBigNode(decisionList.get(decision).file, getImagePath(), getSoundPath());
            nextNode();
        } catch (IOException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nextNode() {
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
