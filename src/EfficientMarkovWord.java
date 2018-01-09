import java.util.*;
public class EfficientMarkovWord
{
   private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> map;
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
		buildMap();
		printHashMapInfo();
	}
    public void buildMap(){
        map = new HashMap<WordGram,ArrayList<String>>();
        int keylen = myOrder;
        for (int k=0;k <= myText.length-keylen;k++){
            WordGram keyGram = new WordGram(myText,k,keylen);
            if(!map.containsKey(keyGram)){
                ArrayList<String> list = getList(keyGram);
                map.put(keyGram,list);
            } else {}
        }
    }
    private int indexOf(String[] words, WordGram target, int start){
        int size = target.length();
        for (int k=start; k<words.length-size; k++){
            WordGram CurrentGram = new WordGram(words,k,size);
            if(target.equals(CurrentGram)) return k;
        }
        return -1;
    }
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
		WordGram keyGram = new WordGram(myText,index,myOrder);
		sb.append(keyGram.toString());
		sb.append(" ");
		for(int k=0; k < numWords-myOrder; k++){
		    ArrayList<String> follows = getFollows(keyGram);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			keyGram.shiftAdd(next);
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getList(WordGram keyGram) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int pos = 0;
	    while (pos < myText.length){
	        int start = indexOf(myText, keyGram, pos);
	        if(start == -1) break;
	        if(start + keyGram.length() > myText.length-1) break;
	        String next = myText[start+keyGram.length()];
	        follows.add(next);
	        pos = start+keyGram.length();
	    }
	    //System.out.println("keyGram: " + keyGram);
	    //System.out.println(follows);
	    return follows;
    }
    public ArrayList<String> getFollows(WordGram keyGram){
	    ArrayList<String> answer = map.get(keyGram);
	    return answer;
	}
    public void printHashMapInfo(){
	    ArrayList<WordGram> maxKey=new ArrayList<WordGram>();
	    int maxLen = 0;
	    for(WordGram key : map.keySet()){
	        //System.out.println("Key: " + key + "\n" +map.get(key) + "\n"); 
	        int currentLen = map.get(key).size();
	        if (currentLen > maxLen){
	            maxLen = currentLen;
	        }
	    }
	    for(WordGram key : map.keySet()){
	        int currentLen = map.get(key).size();
	        if(currentLen == maxLen) {
	            maxKey.add(key);
	            System.out.println("Key: " + key + "\n" +map.get(key) + "\n");
	        }
	    }
	    System.out.println("The map have " + map.size() + " keys in the HashMap");
	    System.out.println("the largest HashMap has the size of " + maxLen +
	                         ". Its key is " + maxKey);
	}
}
