import edu.duke.*;
/**
 * Write a description of class WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    private void countWordLengths (FileResource resource, int[] counts){
        for (String word : resource.words()){
            int k = word.length();
            if (word.isEmpty()){continue;}
            if (!Character.isLetter(word.charAt(0))){k-=1;}
            if (!Character.isLetter(word.charAt(word.length()-1))){k-=1;}
            if (k >= counts.length){ k = counts.length-1;}
            counts[k]+=1;
        }
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i=0; i<counts.length; i++){
            if (counts[i]!=0){
                System.out.println(counts[i] +"  words of length "+ i);
            }
        }
        System.out.println("most frq length " + indexOfMax(counts));
    }
    
    protected int indexOfMax(int[] values){
    if (values.length == 0){return -1;}
    int maxIdx = 0;
    for (int i =0; i<values.length; i++){
        if (values[i] > values[maxIdx]){ maxIdx = i;}
    }
    return maxIdx;
    }
    
}
