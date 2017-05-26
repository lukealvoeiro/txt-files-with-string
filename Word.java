import java.util.*;

public class Word implements Comparable<Word>{
	
	//String
	private String theWord;
	
	//List of files that contain this word
	private ArrayList<String> fileList = new ArrayList<String>();
	
	//Constructor
	public Word(String n){
		theWord = n;
	}
	
	//Setters
	public void theWord(String x){
		theWord = x;
	}
	public void addFile(String x){
		fileList.add(x);
	}
	
	//Getters
	public String getWord(){
		return theWord;
	}
	public ArrayList<String> getFileList(){
		return fileList;
	}

	//Method used for comparisons:
	public int compareTo(Word other){
		return theWord.compareTo(other.theWord);
	}
	
	//toString method
	public String toString(){
	String strFiles = "";
		for(int i=0; i<fileList.size(); i++)
			strFiles += fileList.get(i) + " ";
	String result = "Files containing '" + theWord + "': " + strFiles;
	return result;
	}
	
}


