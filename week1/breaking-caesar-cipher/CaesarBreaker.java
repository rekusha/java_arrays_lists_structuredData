import edu.duke.*;

/**
 * Write a description of class CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
    private String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private int ALPHABET_LENGTH = ALPHABET.length();
    private int LETTER_E_POSITION = ALPHABET.indexOf('e');
    
    private int[] countLetters(String message){
        int[] counts = new int[ALPHABET_LENGTH];
        for (int i=0; i<message.length();i++){
            char ch = Character.toLowerCase(message.charAt(i));
            int index = ALPHABET.indexOf(ch);
            if (index != -1){
                counts[index]+=1;
            }
        }
        return counts;
    }
    
    private int maxIndex(int[] values){
        WordLengths w = new WordLengths();
        return w.indexOfMax(values);
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freq = countLetters(encrypted);
        int maxDex = maxIndex(freq);
        int dkey = maxDex-LETTER_E_POSITION;
        if (maxDex<LETTER_E_POSITION){
            dkey = ALPHABET_LENGTH - (LETTER_E_POSITION-maxDex);
        }
        return cc.encrypt(encrypted, ALPHABET_LENGTH-dkey);
    }
    
    public void testDecrypt(){
        FileResource fr = new FileResource();
        System.out.println(decrypt(fr.asString()));
    }
    
    private String halfOfString(String message, int start){
        StringBuilder ss = new StringBuilder();
        for(int i = start; i<message.length(); i+=2){
            ss.append(message.charAt(i));
        }
        return ss.toString();
    }
    
    public void testHalfOfString(){
        System.out.println(halfOfString("Qbkm Zgis", 1));
    }
}
