import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder(message);
        String s = new String();
        for (int i = whichSlice; i<message.length(); i += totalSlices){
            s += sb.charAt(i);
        }
        return s;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++){
            key[i] = cc.getKey(sliceString(encrypted, i, klength));
        }
        return key;
    }

    public String breakVigenere () {
        //WRITE YOUR CODE HERE
        return new FileResource().asString();
        
    }
    
}
