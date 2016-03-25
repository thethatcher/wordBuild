package wordBuild;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
	Scanner input = new Scanner(System.in);
	Scanner wList;
	String letters = pickWord();
	int response = 0;
	int wordLength = letters.length();	
	
	public void gameMenu(){
		System.out.println("......................................\n"
				+ "Please choose an option below.");
		System.out.println("Active letters: | " + letters + " |");
		System.out.println("1. Enter Word\n2. Scramble Letters\n3. Give Up\n4.Exit");
		if(input.hasNextInt()){
			response = input.nextInt();
		}
		else{
			input.next();
		}
		if(response < 1 || response > 4){
			System.out.println("Invalid Input.");
			gameMenu();
		}
		else{
			switch (response){
			case 1: 
				System.out.println("Enter your new word of length " + (wordLength+1) + " and containing the active letters below.");
				System.out.println("| " + letters + " |");
				String temp = "";
				if(input.hasNext()){
					temp = input.next();
				}
				else{
					input.next();
				}
				enterWord(temp);
				break;
			case 2: System.out.println("Active Letters Scrambled");
				letters = scrambleWord(letters);
					break;
			case 3: System.out.println("error");
			//TODO go back to the main menu.
					break;
			case 4: System.exit(0);
			}
			gameMenu();
		}
	}
	
	public String pickWord(){
		//TODO select a 3 letter word at random from a file. 
		Random rand = new Random();
		Scanner read;
		String word = "";
		int line = rand.nextInt(67);
		try{
			read = new Scanner(new File("3Letters.txt"));
			for(int i = 0;i < line;i++){
				read.nextLine();
			}
			word = read.nextLine();
		} catch(Exception e){
			System.out.println("file not found.");
		}
		return word;
	}
	
	/**
	 * Takes the string given to it and shuffles the characters within it. 
	 * @param s the String that needs to be sorted
	 * @return a scrambled word, which would be a perfect anagram of the parameter s. 
	 */
	public String scrambleWord(String s){
		StringBuilder sb = new StringBuilder();
		List<Character> cList = new ArrayList<Character>();
		for(int i = 0; i < s.length(); i++){
			cList.add(s.charAt(i));
		}
		Collections.shuffle(cList);
		for(char c:cList){
			sb.append(c);
		}
		return sb.toString();
	}
	
	public void enterWord(String word){
			if(!validateWord(word)){
				System.out.println("Invalid word");
			}
			else{
				letters = word;
				wordLength++;
			}
	}
	
	public boolean validateWord(String word){
		List<Character> cList = new ArrayList<Character>();
		for(int i = 0; i < word.length(); i++){
			cList.add(word.charAt(i));
		}
		char[] lettersArray = letters.toCharArray();
		if(word.length()!=wordLength+1){
			return false;
		}
		for(char c:lettersArray){
			Collections.sort(cList);
			if(cList.contains(c)){
				cList.remove((Character)c);
			}
			else{
				return false;
			}
		}
		if(!checkWord(word)){
			return false;
		}
		return true;
	}
	
	public boolean checkWord(String word){
		String rWord = "";
		//TODO optimize this method for large N
		try{
			wList = new Scanner(new File("wordList.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(wList.hasNext()){
			rWord = wList.nextLine();
			if(rWord.equals(word)){
				return true;
			}
		}
		return false;
	}
}
