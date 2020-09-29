
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

    public int[] countLetters (String message) {
        int[] counts = new int[ALPHABET_LENGTH];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int index = ALPHABET.indexOf(ch);
            if (index != -1) { counts[index] += 1; }
        }
        return counts;
    }

    public int maxIndex (int[] values) {
        WordLengths w = new WordLengths();
        return w.indexOfMax(values);
    }


    private int keyOffset(int value) {
        int key = value - LETTER_E_POSITION;
        if (value < LETTER_E_POSITION){ key = ALPHABET_LENGTH - (LETTER_E_POSITION - value); }
        return key;
    }

    public String decrypt (String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        WordLengths w = new WordLengths();
        int[] freqs = countLetters(encrypted);
        int maxIndex = maxIndex(freqs);
        int dkey = keyOffset(maxIndex);
        return cc.encrypt(encrypted, ALPHABET_LENGTH-dkey);
    }

    private boolean hasValue (String s) { return s != null && s.length() != 0; }

    public String halfOfString (String message, int start) {
        if (! hasValue(message)) return "";

        StringBuilder sb = new StringBuilder("");
        int messageLen = message.length();
        for (int k = start; k < messageLen; k += 2) {
            sb.append(message.charAt(k));
        }
        return sb.toString();
    }

    public int getKey (String s) {
        return hasValue(s) ? maxIndex(countLetters(s)) : -1;
    }

    public String decryptTwoKeys (String encrypted) {
        if (! hasValue(encrypted)) return "";

        String oddChars = halfOfString(encrypted, 0);
        String evenChars = halfOfString(encrypted, 1);
        ps("oddChars", oddChars);
        ps("evenChars", evenChars);

        int key1 = keyOffset(getKey(oddChars));
        int key2 = keyOffset(getKey(evenChars));

        System.out.println("key1 = "+key1+", key2 = "+key2);

        return new CaesarCipher().encryptTwoKeys(encrypted, ALPHABET_LENGTH-key1, ALPHABET_LENGTH-key2);
    }

    private void ps(String label, String value) {
        //System.out.println(label+" :'"+value+"'");
    }

}
