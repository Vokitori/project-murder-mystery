package fileparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Voki
 */
public class Main {
    
    

    public static void main(String[] args) throws FileNotFoundException, IOException{
        LinkedList<SmallNode> s = FileParser.parseBigNode(new File ("../Test_Files/Baum.txt")); 
                
                System.out.println(s.get(0).music.data.music);
    }
    
}
