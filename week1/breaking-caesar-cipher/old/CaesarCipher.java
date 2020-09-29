import edu.duke.*;
/**
 * Write a description of class CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String shiftAlphabet(int key) {
        return alphabet.substring(key) + alphabet.substring(0, key);
    }

    private boolean hasValue (String s) { return s != null && ! s.isEmpty(); }

    public String encrypt (String input, int key) {
        if (! hasValue(input)) return "";
        if (key == 0) return input;

        StringBuilder encrypted = new StringBuilder(input);
        String myAlphabet = alphabet.toUpperCase() + alphabet.toLowerCase();
        String shiftedAlphabet = shiftAlphabet(key).toUpperCase() +
                                 shiftAlphabet(key).toLowerCase();
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = myAlphabet.indexOf(currChar);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }

        return encrypted.toString();
    }

    public String encryptTwoKeys (String input, int key1, int key2) {
        if (!hasValue(input)) { return ""; }
        if (key1 == 0 && key2 == 0) { return input; }
        StringBuilder encrypted = new StringBuilder(input);
        String myAlphabet = alphabet.toUpperCase() + alphabet.toLowerCase();
        String shifted1 = shiftAlphabet(key1).toUpperCase() +
                shiftAlphabet(key1).toLowerCase();
        String shifted2 = shiftAlphabet(key2).toUpperCase() +
                shiftAlphabet(key2).toLowerCase();
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = myAlphabet.indexOf(currChar);
            if (idx != -1) {
                // Which alphabet do I use?  even i is key1, odd i is key2
                String shiftedAlphabet = (i % 2 == 0) ? shifted1: shifted2;
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }

        return encrypted.toString();
    }

}
