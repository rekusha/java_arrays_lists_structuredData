import edu.duke.*;

/**
 * Write a description of class CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String encrypt(String input, int key){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet2 = alphabet.substring(key)+alphabet.substring(0, key);
        StringBuilder x = new StringBuilder(input);
        for (int i=0; i<x.length(); i++){
            if (Character.isLetter(Character.toUpperCase(x.charAt(i)))){
                if (Character.isUpperCase(x.charAt(i))){
                    x.setCharAt(i, alphabet2.charAt(alphabet.indexOf(Character.toUpperCase(x.charAt(i)))));
                } else {
                    x.setCharAt(i, Character.toLowerCase(alphabet2.charAt(alphabet.indexOf(Character.toUpperCase(x.charAt(i))))));
                }
            } else { 
                continue; 
            }
        }
        return x.toString();
    }
    
    public void testEncrypt(){
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15));
        //System.out.println(encrypt("First Legion",23));
        //System.out.println(encrypt("First Legion",17));
    }
    
    public void testCaesar(){
        int key = 23;
        String encrypted = encrypt((new FileResource()).asString(), key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    private String encryptTwoKeys(String input, int key1, int key2){
        String stringkey1 = encrypt(input, key1);
        String stringkey2 = encrypt(input, key2);
        StringBuilder result = new StringBuilder(input);
        String value = "";
        for (int i=0; i < result.length(); i++){
            if (i%2==0){ value = stringkey1; } else { value = stringkey2; }
            result.setCharAt(i, value.charAt(i));
        }
        return result.toString();
    }
    
    public  void testEncryptTwoKeys(){
        //System.out.println(encryptTwoKeys("First Legion", 23, 17));
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }
    
}
