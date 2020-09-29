
/**
 * Write a description of class WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    private boolean isVowel(char ch){
        ch = Character.toLowerCase(ch);
        String x = Character.toString(ch);
        String etalon = "aeiou";
        if (etalon.contains(x)){
            return true;
        }
    return false;
    }
    
    public void testIsLowel(){
        System.out.println(isVowel('F'));
        System.out.println(isVowel('A'));
        System.out.println(isVowel('S'));
        System.out.println(isVowel('f'));
        System.out.println(isVowel('a'));
    }
    
    private String replaceVowels(String phrase, char ch){
        StringBuilder sstring = new StringBuilder(phrase);
        for (int i = 0; i < sstring.length(); i++){
            if (isVowel(sstring.charAt(i))){
                sstring.setCharAt(i, ch);
            }
        }
        return sstring.toString();
    }
    
    public void testReplaceVowels(){System.out.println(replaceVowels("Hello world!", '*'));}
    
    private String emphasize(String phrase, char ch){
        StringBuilder sstring = new StringBuilder(phrase);
        ch = Character.toLowerCase(ch);
        for (int i=0; i < sstring.length(); i++){
            char currentChar = Character.toLowerCase(sstring.charAt(i));
            if (currentChar==ch){
                if (i%2!=0){sstring.setCharAt(i, '+');} 
                else {sstring.setCharAt(i, '*');}
            }
        }
        return sstring.toString();
    }
    
    public void testEmphasize(){System.out.println(emphasize("Mary Bella Abracadabra", 'a'));}
}
