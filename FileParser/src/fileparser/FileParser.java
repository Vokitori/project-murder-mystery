package fileparser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.print.DocFlavor;

/**
 * @author Voki
 */
public class FileParser {

    public static LinkedList<SmallNode> parseBigNode(File file) throws FileNotFoundException, IOException {
        LinkedList<SmallNode> nodeList = new LinkedList<>();
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("//Z");
        String fileContent = scanner.next();
        String[] screenBlockArray;
        screenBlockArray = fileContent.split("##");
        for (int i = 0; i < screenBlockArray.length; i++) {
            if (!screenBlockArray[i].trim().isEmpty()) {
                nodeList.add(parseSmallNode(new StringBuilder(screenBlockArray[i])));
            }
        }
        return nodeList;
    }

    private static SmallNode parseSmallNode(StringBuilder screenBlock) throws IOException {
        Ref<DataPackage<BufferedImage>> slot1 = new Ref<>(),
                slot2 = new Ref<>(), slot3 = new Ref<>(),
                slot4 = new Ref<>(), background = new Ref<>();
        Ref<DataPackage<GameEvent>> gameEvent = new Ref<>();
        Ref<DataPackage<Music>> music = new Ref<>();
        Ref<String> text = new Ref<>();
        Ref<LinkedList<Decision>> decisionList = new Ref<>();

        removeComments(screenBlock);
        String[] splittedScreenBlock = screenBlock.toString().split("#");

        parseLogicBlock(splittedScreenBlock[0], slot1, slot2, slot3, slot4, background, gameEvent, music);
        parseTextBlock(splittedScreenBlock[1], text, decisionList);

        return new SmallNode(slot1.object, slot2.object, slot3.object,
                slot4.object, background.object, gameEvent.object,
                music.object, text.object, decisionList.object);
    }

    //<editor-fold defaultstate="collapsed" desc="parseLogicBlock();">
    private static void parseLogicBlock(String logic, Ref<DataPackage<BufferedImage>> slot1,
            Ref<DataPackage<BufferedImage>> slot2, Ref<DataPackage<BufferedImage>> slot3,
            Ref<DataPackage<BufferedImage>> slot4, Ref<DataPackage<BufferedImage>> background,
            Ref<DataPackage<GameEvent>> gameEvent, Ref<DataPackage<Music>> music) throws IOException {
        logic = logic.replaceAll("\\s+", "");
        String[] line = logic.split(";");
        for (int i = 0; i < line.length; i++) {
            if (line[i].trim().length() < 1) {
                continue;
            }
            String[] part = line[i].split("=");
            DataAction action = DataAction.LOAD;
            switch (part[1].toLowerCase()) {
                case "keep":
                    action = DataAction.KEEP;
                    break;
                case "delete":
                    action = DataAction.DELETE;
                    break;
            }

            BufferedImage img = null;
            Music musicFile = null;
            GameEvent gEvent = null;
            switch (part[0]) {
                case "slot1":
                    if (action == DataAction.LOAD) {
                        img = ImageIO.read(new File(part[1]));
                    }
                    slot1.object = new DataPackage<>(img, action);
                    break;
                case "slot2":
                    if (action == DataAction.LOAD) {
                        img = ImageIO.read(new File(part[1]));
                    }
                    slot2.object = new DataPackage<>(img, action);
                    break;
                case "slot3":
                    if (action == DataAction.LOAD) {
                        img = ImageIO.read(new File(part[1]));
                    }
                    slot3.object = new DataPackage<>(img, action);
                    break;
                case "slot4":
                    if (action == DataAction.LOAD) {
                        img = ImageIO.read(new File(part[1]));
                    }
                    slot4.object = new DataPackage<>(img, action);
                    break;
                case "background":
                    if (action == DataAction.LOAD) {
                        img = ImageIO.read(new File(part[1]));
                    }
                    background.object = new DataPackage<>(img, action);
                    break;
                case "event":
                    if (action == DataAction.LOAD) {

                        gEvent = new GameEvent(part[1]);
                    }
                    gameEvent.object = new DataPackage<>(gEvent, action);
                    break;
                case "music":
                    if (action == DataAction.LOAD) {
                        musicFile = new Music(part[1]);
                    }
                    music.object = new DataPackage<>(musicFile, action);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
//</editor-fold>

    private static void parseTextBlock(String textBox, Ref<String> text, Ref<LinkedList<Decision>> decisionList) {
        textBox = textBox.trim();
        if (!textBox.contains("{")) {
            text.object = textBox;
        } else {
            parseDecision(new StringBuilder(textBox), decisionList, text);
        }

    }

    private static void parseDecision(StringBuilder textBox, Ref<LinkedList<Decision>> decisionList, Ref<String> text) {
        ArrayList<String> substringList = new ArrayList<>();
        for (int i = 0; textBox.indexOf("{") != -1; i++) {
            int start = textBox.indexOf("{");
            int end = textBox.indexOf("}", start);
            substringList.add(textBox.substring(start + 1, end));
            textBox.delete(start, end);
            textBox.deleteCharAt(textBox.indexOf("}"));
        }
        text.object = textBox.toString().trim();
        substringToDecision(decisionList, substringList);
    }
    
    private static void substringToDecision(Ref<LinkedList<Decision>> decisionList, ArrayList<String> substringList){
        decisionList.object = new LinkedList<>();
        for (int i = 0; i < substringList.size(); i++) {
            String[] arr = substringList.get(i).split("\\|");
            Decision decision = new Decision(new File(arr[0]), arr[1]);
            decisionList.object.add(decision);
        }
    }

    private static void removeComments(StringBuilder screenBlock) {

        while (screenBlock.indexOf("//") != -1) {
            removeComment(screenBlock);
        }

    }

    private static void removeComment(StringBuilder screenBlock) {
        int start = screenBlock.indexOf("//");
        int end = screenBlock.indexOf("\n", start);

        screenBlock.delete(start, end);
    }

}
