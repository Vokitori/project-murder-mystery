package engine.stuff;

import java.io.File;

/**
 * @author Voki
 */
public class Decision {

    public final File file;
    public final String decisionText;

    public Decision(File f, String s) {
        this.file = f;
        this.decisionText = s;
    }

}
