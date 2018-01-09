
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.Scanner;
public class MarkovRunner {
    public static void main(String[] args){
        runMarkov();
    }
    
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public static void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public static void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        Scanner s = new Scanner(System.in);
        Scanner d = new Scanner(System.in);
        Scanner f = new Scanner(System.in);
        System.out.println("How many charaters will be used for the prediction? (1 to n)");
        int len = s.nextInt();
        System.out.println("What is the length (NO. of chars) of the generated text?");
        int size = d.nextInt();
        System.out.println("Please set a random seed: ");
        int seed = f.nextInt();
        MarkovWord markovWord = new MarkovWord(len); 
        runModel(markovWord, st, size, seed); 
    } 

    private static void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    public void testHashMap(){
        EfficientMarkovWord emw = new EfficientMarkovWord(5);
        emw.setRandom(531);
        //String input = "this is a test yes this is really a test yes a test this is wow";
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        String input = st.replace('\n', ' ');
        emw.setTraining(input);
    }
}
