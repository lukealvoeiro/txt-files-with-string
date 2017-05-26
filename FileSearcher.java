import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSearcher {
	
	static BinarySearchTree<Word> wordTree = new BinarySearchTree<Word>();

	//reads a file and fills the BST tree with the words found in the files
	public static void FillTree(String dir) throws FileNotFoundException {
		//creating new file object array containing all files in directory
		File directory = new File(dir);
		File[] listFiles = directory.listFiles();
		
		for(int i=0; i<listFiles.length; i++){
			if(!listFiles[i].isHidden()){
				//all non-hidden files are processed
				
				Scanner fr = new Scanner(listFiles[i]);
				
				//splitting the lines into an array of strings
				String[] wordArr = fr.nextLine().split(" ");
				
				for(int j=0; j<wordArr.length; j++){
					//for every word in the array of strings, if not already a word object, create one. 
					//if not, insert the file where it was found into the corresponding word object
					
					Word nextWord = new Word(wordArr[j].replaceAll("[-+.^:,]",""));
					if(!wordTree.contains(nextWord)){
						nextWord.addFile(listFiles[i].getName());
						wordTree.insert(nextWord);
					}
					else{
						wordTree.find(nextWord).addFile(listFiles[i].getName());
					}			
				}
			}
		}
	}
	
	//executes the program, prompting the user for their choice and presenting different outputs depending
	//on this choice.
	public static void main(String[] args) throws FileNotFoundException {
		FillTree(args[0]); //fills the tree out using the files in the directory
		
		Scanner scan = new Scanner(System.in);
		
		char command = 'o'; //any character that is not an option
		while(command != 'q'){
			System.out.println("Select action (q, a, s) ");
			command = scan.nextLine().charAt(0);
			if(command == 'a'){
				wordTree.printTree(); //print the entire binary tree!
			}
			else if(command == 's'){
				System.out.println("Word to find: ");
				String search = scan.nextLine(); //get word to be searched
				Word searchWord = new Word(search); //convert string to Word object and search in BST
				Word tmp = wordTree.find(searchWord);
				if(tmp == null){ //if not found
					System.out.println("Word is not in any of the files.");
				}
				else{ //has been found, print it out!
					System.out.println(tmp);
				}
			}
			else if(command != 'q'){ //invalid user input
				System.out.println("Please enter a valid action.");
			}
		}
	}
}
