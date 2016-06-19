package engine;

import engine.menu.*;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Voki
 */
public class Game {

    public final JFrame window = new JFrame("Titel");

    public final IntroScreen introScreen = new IntroScreen(this);
    public final CreditsScreen creditsScreen = new CreditsScreen(this);
    public final OptionsScreen optionsScreen = new OptionsScreen(this);
    public final KeybindingsScreen keybindingsScreen = new KeybindingsScreen(this);
    public final TreeScreen treeScreen = new TreeScreen(this);
    public final NewPathScreen newPathScreen = new NewPathScreen(this);
    public final InGameScreen inGameScreen = new InGameScreen(this);
    public final MainMenuScreen mainScreen = new MainMenuScreen(this);

    public final Screen menuList[] = {introScreen, creditsScreen, optionsScreen,
        keybindingsScreen, treeScreen, newPathScreen, inGameScreen, mainScreen};
    public Screen activeScreen;

    private boolean ingame = false;

    public static String FOLDER;

    static {
        try (Scanner scanner = new Scanner(new File("stories/storyselect"))) {
            FOLDER = "stories/" + scanner.nextLine() + "/";
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Game() {
        window.setMinimumSize(new Dimension(800, 600));
        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                Dimension size = window.getSize();
                Dimension min = window.getMinimumSize();
                if (size.getWidth() < min.getWidth()) {
                    window.setSize((int) min.getWidth(), (int) size.getHeight());
                }
                if (size.getHeight() < min.getHeight()) {
                    window.setSize((int) size.getWidth(), (int) min.getHeight());
                }
            }
        });
        try {
            //        for (Screen screen : menuList) {
//            if(!(screen instanceof CreditsScreen)||!(screen instanceof InGameScreen)||!(screen instanceof Pause)||!(screen instanceof OptionsScreen)) {
//                try {
//                    screen.setImage(new Ref<>(ImageIO.read(new File("data/bg.png"))));
//                } catch (IOException ex) {
//                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }

            File menuBackground = new File("data/bg.png");

            mainScreen.setImage(new Ref<>(ImageIO.read(menuBackground)));
            keybindingsScreen.setImage(new Ref<>(ImageIO.read(menuBackground)));
            newPathScreen.setImage(new Ref<>(ImageIO.read(menuBackground)));
            optionsScreen.setImage(new Ref<>(ImageIO.read(menuBackground)));
            treeScreen.setImage(new Ref<>(ImageIO.read(menuBackground)));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void load() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(800, 600));
        window.pack();
        window.setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        window.setVisible(true);
        setScreen(introScreen);

        Arrays.asList(menuList).forEach(Screen::start);
        KeyManager.startListening();
    }

    public void setScreen(Screen screen) {
        if (window.getContentPane() instanceof Screen) {
            ((Screen) window.getContentPane()).pause();
        }
        if (ingame && activeScreen == treeScreen && screen == inGameScreen) {
            inGameScreen.continueGame(inGameScreen.pauseMenu);
        }
        activeScreen = screen;
        screen.resume();
        if (ingame && screen == mainScreen) {
            if (window.getContentPane() == inGameScreen) {
                ingame = false;
            } else {
                window.setContentPane(inGameScreen);
                inGameScreen.continueGame(inGameScreen.pauseMenu);
                screen.repaint();
                screen.revalidate();
                new Thread(() -> {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    inGameScreen.pauseGame(inGameScreen.pauseMenu);
                    screen.repaint();
                    screen.revalidate();
                }).start();
                return;
            }
        }
        if (screen == inGameScreen) {
            ingame = true;
        }
        window.setContentPane(screen);
        screen.revalidate();
    }

    public void keyPressedAccept() {
        activeScreen.keyPressedAccept();
    }

    public void keyPressedCancel() {
        activeScreen.keyPressedCancel();
    }

    public void keyPressedUp() {
        activeScreen.keyPressedUp();
    }

    public void keyPressedDown() {
        activeScreen.keyPressedDown();
    }

    public static final String getSoundPath() {
        return FOLDER + "sound/";
    }

    public static final String getTextPath() {
        return FOLDER + "text/";
    }

    public static final String getImagePath() {
        return FOLDER + "image/";
    }

    public static final String getAutoSavePath() {
        return FOLDER + "last";
    }
}
