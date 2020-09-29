import java.util.*;
import edu.duke.*;
 /* Write a description of class WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()){
            word = word.toLowerCase();
            if (word.length()==0){ continue; }
            int index = myWords.indexOf(word);
            if (index==-1){
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    public void tester(){
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++){
            System.out.println(myFreqs.get(i)+ " " + myWords.get(i));
        }
        int index = indexOfMax();
        System.out.println("____");
        System.out.println("uniqui " + myWords.size());
        System.out.println("____");
        System.out.println("The word that occurs most often and its count are: " + myWords.get(index) +" " + myFreqs.get(index));
    }
    
    public int indexOfMax(){
        int index = 0;
        int max = 0;
        for (int i=0; i<myFreqs.size();i++){
            if (myFreqs.get(i)>max){
                index = i;
                max = myFreqs.get(i);
            }
        }
        return index;
    }
}
