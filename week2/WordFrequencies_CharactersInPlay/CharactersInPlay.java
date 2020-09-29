import java.util.*;
import edu.duke.*;

/**
 * Write a description of class CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> namesOfCharacters;
    private ArrayList<Integer> numOfNames;
    
    public CharactersInPlay(){
        namesOfCharacters = new ArrayList<String>();
        numOfNames = new ArrayList<Integer>();
    }
    
    private void update(String person){
        if (!namesOfCharacters.contains(person)){
            namesOfCharacters.add(person);
            numOfNames.add(1);
        } else {
            int index = namesOfCharacters.indexOf(person);
            int value = numOfNames.get(index);
            numOfNames.set(index, value+1);
        }
    }
    
    private void findAllCharacters(){
        namesOfCharacters.clear();
        numOfNames.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()){
            line = line.trim();
            int index = line.indexOf(".");
            if (index == -1){
                continue;
            } else {
                update(line.substring(0,index));
            }
        }
    }
    
    public void tester(){
        findAllCharacters();
        for (int i = 0; i < namesOfCharacters.size(); i++){
            System.out.println(namesOfCharacters.get(i) + " " + numOfNames.get(i));
        }
        System.out.println("-------------");
        charactersWithNumParts(2, 10);
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for (int i = 0; i < namesOfCharacters.size(); i++){
            int num = numOfNames.get(i);
            if (num >= num1 && num <= num2){
                System.out.println(namesOfCharacters.get(i) + " " + numOfNames.get(i));
            }  
        }
    }
}
