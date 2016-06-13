package engine;

import engine.menu.InGameScreen;
import engine.stuff.Decision;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;

/**
 * @author link
 */
public class DecisionButton extends JButton implements ActionListener {

    private InGameScreen igs;
    private int number;

    public DecisionButton() {
        super();
        addActionListener(this);
    }

    
    public void setDecision(InGameScreen igs, String text, int number) {
        setText(text);
        this.igs = igs;
        this.number = number;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        igs.nextScreen(number);
    }

}
