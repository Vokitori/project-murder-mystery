package engine.fileparser;

import java.io.File;

/**
 * @author Voki
 */
public class Decision {
    File file;
    String decitionText;

    public Decision(File f, String s) {
        this.file = f;
        this.decitionText = s;
    }
    
}
