package engine;

import engine.fileparser.DataPackage;
import engine.fileparser.Decision;
import engine.fileparser.FileParser;
import engine.fileparser.GameEvent;
import engine.fileparser.Music;
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

    private BufferedImage slot1;
    private BufferedImage slot2;
    private BufferedImage slot3;
    private BufferedImage slot4;
    private BufferedImage background;
    private GameEvent gameEvent;
    private Music music;
    private String text;
    private LinkedList<Decision> decisionList;

    public GameData() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("stories/storyselect"));

            String folderName = scanner.nextLine();
            try {
                String path = "stories/" + folderName + "/text/start.txt";
                nodes = FileParser.parseBigNode(new File(path));
            } catch (IOException ex) {
                Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nextNode(int decision) {
        try {
            nodes = FileParser.parseBigNode(decisionList.get(decision).file);
            nextNode();
        } catch (IOException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nextNode() {
        SmallNode node = nodes.pop();
        slot1 = getNext(slot1, node.slot1);
        slot2 = getNext(slot2, node.slot2);
        slot3 = getNext(slot3, node.slot3);
        slot4 = getNext(slot4, node.slot4);
        background = getNext(background, node.background);
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

    public BufferedImage getSlot1() {
        return slot1;
    }

    public BufferedImage getSlot2() {
        return slot2;
    }

    public BufferedImage getSlot3() {
        return slot3;
    }

    public BufferedImage getSlot4() {
        return slot4;
    }

    public BufferedImage getBackground() {
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
