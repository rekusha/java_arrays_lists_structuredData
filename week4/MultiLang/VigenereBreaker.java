import java.util.*;
import edu.duke.*;
import java.io.File;

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
    
    public String mostCommonCharIn(HashSet<String> dictionary){
    	HashMap<String, Integer> map = new HashMap<String, Integer>();
    	for (char ch = 'a'; ch <= 'z'; ++ch){
    	    map.put(String.valueOf(ch), 0); 
        }
    	
    	for(String word: dictionary) {
    		word = word.toLowerCase();
    		String[] letters = word.split("");
            for (String letter: map.keySet()) {
                for (String s: letters) {
                    if (s.equals(letter)) {
                    	map.put(letter, map.get(letter)+1);
                    }
                }
            }
    	}
    	
    	int max = 0;
    	String result ="";
    	for (Map.Entry<String, Integer> e : map.entrySet()) {
    	    if (max < e.getValue()) {
                max = e.getValue();
                result = e.getKey();
            }
    	}
        return result;
    }
    
    public HashMap<String, String> breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages){
    	 HashMap<String, String> decrpytedMessages = new HashMap<String, String>();
    	 String language = "";
    	 int wordcount = 0;
         for (String lang: languages.keySet()) {
             System.out.println("Currently breaking into "+lang);
             String decrypted_string = breakForLanguage(encrypted, languages.get(lang));
             //System.out.println(decrypted_string);
             int count = countWords(decrypted_string, languages.get(lang));
             if (wordcount < count) {
            	 wordcount = count;
            	 language = lang;
             }
             //System.out.println(count + " valid words\n");
             System.out.println();
             decrpytedMessages.put(lang, decrypted_string);
         }
         System.out.println("The language of this message is " + language);
         System.out.println(wordcount + " valid words\n");
         return decrpytedMessages;
    }
    
    public void breakVigenere2() {
    	FileResource fr = new FileResource();
        String message = fr.asString();
    	HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
    	DirectoryResource dr = new DirectoryResource();
    	for (File d: dr.selectedFiles()) {
    		FileResource fr2 = new FileResource(d.toString());
    		HashSet<String> result = new HashSet<String>();
            for (String line: fr2.lines()) {
                line = line.toLowerCase();
                result.add(line);
            }
            languages.put(d.getName(), result);
            //System.out.println("Finished reading "+f.getName());
    	}
    	HashMap<String, String> decrypted = breakForAllLanguages(message, languages);
        System.out.println(decrypted.get("French").substring(0, 200));
    }
}
