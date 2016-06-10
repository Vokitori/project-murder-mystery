
import engine.GameData;
import java.io.IOException;

/**
 * @author Voki
 */
public class GameDataTest {
    public static void main(String[] args) throws IOException {
        GameData data = new GameData();
        data.nextNode();
        System.out.println(data.getText());
    }

}
