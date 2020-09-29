import edu.duke.*;
/**
 * Write a description of class WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        int lastIndex = counts.length - 1;
        for (String word : resource.words()){
            if (word.isEmpty()) { break; }
            int wordLen = word.length();
            int len = wordLen;
            if (! Character.isLetter(word.charAt(0))) { len -= 1; } 
            if (! Character.isLetter(word.charAt(wordLen-1))) { len -= 1; }
            if (len > lastIndex){
                counts[lastIndex]++; 
            } else if (len > 0) { 
                counts[len]++; 
            }
        }
    }
    
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i=0; i <counts.length; i++){
            if (counts[i]==0){continue;}
            System.out.println(counts[i] + " words of length " + i);
        }
        System.out.println(indexOfMax(counts));
    }
    
    public int indexOfMax(int[] values) {
        if (values.length == 0){
            return -1;
        }

        int maxIdx = 0;
        for (int k = 0; k < values.length; k++){
            if (values[k] > values[maxIdx]){
                maxIdx = k;
            }
        }
        return maxIdx;
    }
}