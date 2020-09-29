import java.util.*;
import  edu.duke.*;
/**
 * Write a description of class CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String, Integer> dnaCount;
    public CodonCount(){
        dnaCount = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna){
        dnaCount.clear();
        int counter = (dna.length()-start)/3;
        int iter = 0;
        String current;
        while (iter < counter){
            current = dna.substring(iter*3+start, iter*3+start+3);
            if (dnaCount.containsKey(current)){
                dnaCount.put(current, dnaCount.get(current)+1);
            }else{
                dnaCount.put(current, 1);
            }
            iter += 1;
        }
        System.out.println(dnaCount);
    }
    
    private void printCodonCounts(int start, int end){
        int current = 0;
        for (String index : dnaCount.keySet()){
            current = dnaCount.get(index);
            if (current >= start && current <= end){
                System.out.println(index+": "+current+"\t");
            }
        }
    }
    
    private String getMostCommonCodon() {
        int largest = 0;
        int current = 0;
        String largest_count = null;
        for (String index : dnaCount.keySet()) {
            current = dnaCount.get(index);
            if (largest < current) {
                largest = current;
                largest_count = index;
            }
        }
        return largest_count;
    }
    
    public void Test() {
       //String dna = "CGTTCAAGTTCAA";
       FileResource DNA = new FileResource("dnaMystery2.txt");
       String dna = DNA.asString().trim();
       System.out.println(dna);
       int start = 7;
       int end = 7;
        
       buildCodonMap(0, dna);
       System.out.println("Reading frame starting with 0 results in "+dnaCount.size()+" unique codons"+"\t");
       String the_largest_count = getMostCommonCodon();
       System.out.println("and most common codon is "+the_largest_count+" with count "+dnaCount.get(the_largest_count)+"\t");  
       System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
       printCodonCounts(start, end);
        
       buildCodonMap(1, dna);
       System.out.println("Reading frame starting with 1 results in "+dnaCount.size()+" unique codons"+"\t");
       the_largest_count = getMostCommonCodon();
       System.out.println("and most common codon is "+the_largest_count+" with count "+dnaCount.get(the_largest_count)+"\t");  
       System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
       printCodonCounts(start, end);
        
       buildCodonMap(2, dna);
       System.out.println("Reading frame starting with 2 results in "+dnaCount.size()+" unique codons"+"\t");
       the_largest_count = getMostCommonCodon();
       System.out.println("and most common codon is "+the_largest_count+" with count "+dnaCount.get(the_largest_count)+"\t");  
       System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
       printCodonCounts(start, end);
    }
    
}
