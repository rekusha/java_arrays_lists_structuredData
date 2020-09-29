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
        String MaxDecryption = new String();
        FileResource resource = new FileResource("messages/secretmessage2.txt");
        String message = resource.asString();
        HashSet<String> DictContent = new HashSet<String>();
        FileResource dictResource = new FileResource("dictionaries/English");
        DictContent = readDictionary(dictResource);
        MaxDecryption = breakForLanguage(message,DictContent);
        return MaxDecryption;        
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet dict = new HashSet<String>();
        for (String s : fr.lines()){
            if (!dict.contains(s.toLowerCase())){
            dict.add(s.toLowerCase());
            }

        }
        return dict;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        ArrayList<String> input = new ArrayList<String>(Arrays.asList(message.toLowerCase().split("\\W")));
        int count = 0;
        for (String s : input){
            if (dictionary.contains(s)){
                count+=1;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int key = 0;
        int words = 0;
        String decrypt = new String();
        for (int i =1; i<=100;i++){
            int[] keys = tryKeyLength(encrypted, i, 'e');
            VigenereCipher vc = new VigenereCipher(keys);
            String message = vc.decrypt(encrypted);
            int count = countWords(message, dictionary);
            if (count > words){
                key = i;
                decrypt = message;
                words = count;
            }
        }
        return decrypt + "\r keyLength=" + key;
    }
}
