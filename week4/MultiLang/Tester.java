
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class Tester {
    public void testCaesarCipher(){
        CaesarCipher cc = new CaesarCipher(5);
        FileResource fr = new FileResource();
        for (String str : fr.lines()){
            System.out.println(cc.encrypt(str));
        }
    }
    
    public void testCaesarCracker(){
        CaesarCracker cc = new CaesarCracker();
        //CaesarCracker cc = new CaesarCracker('a');
        FileResource fr = new FileResource();
        System.out.println(cc.decrypt(fr.asString()));
    }
    
    public void testVigenereCipher(){
        int VigenereKey[] = {17,14,12,4};
        VigenereCipher vc = new VigenereCipher(VigenereKey);
        FileResource fr = new FileResource();
        System.out.println(vc.encrypt(fr.asString()));
        System.out.println("-----");
        System.out.println(vc.decrypt(vc.encrypt(fr.asString())));
    }
    
    public void TestSliceString(){
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 0, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 4, 5)); 
    }
    
    public void TestTryKeyLength(){    
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("messages/secretmessage2.txt");
        for (int i : vb.tryKeyLength(fr.asString(), 4, 'e')){
            System.out.println(i);
        }
        System.out.println(vb.tryKeyLength(fr.asString(), 4, 'e').toString());
    }
    
    public void VigenereBreakerTest(){
        int keyReturn[] = new int[4];
        String decrypted = new String();
        VigenereBreaker anotherVigenere = new VigenereBreaker();
        String message = anotherVigenere.breakVigenere();
        keyReturn = anotherVigenere.tryKeyLength(message,4,'e');
        VigenereCipher VC  = new VigenereCipher(keyReturn) ;
        decrypted += VC.decrypt(message);
        System.out.println(decrypted); 
    } 
    
    public void testDictCount(){
        VigenereBreaker vb = new VigenereBreaker();
        HashSet<String> dict = vb.readDictionary(new FileResource("dictionaries/English"));
        System.out.println(vb.countWords(new FileResource("test-message/athens.txt").asString(), dict)); 
        System.out.println(vb.countWords(new FileResource("test-message/athens_keyflute.txt").asString(), dict)); 
    }
    
    public void testBreakVigenere(){
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.breakVigenere()); 
    }
    
    public void testBreakVigenere111(){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere2(); 
    }
}
