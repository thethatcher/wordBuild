package wordBuild;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
	Scanner input = new Scanner(System.in);	
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
				wordLength++;
				System.out.println("Enter your new word of length " + wordLength + ".");
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
			//TODO create the game method
					break;
			case 4: System.exit(0);
			}
			gameMenu();
		}
	}
	
	public String pickWord(){
		String word = "car";
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
	}
	
	public boolean validateWord(String word){
		List<Character> cList = new ArrayList<Character>();
		for(int i = 0; i < word.length(); i++){
			cList.add(word.charAt(i));
		}
		char[] lettersArray = letters.toCharArray();
		if(word.length()!=wordLength){
			return false;
		}
		//TODO debug this.
		for(char c:lettersArray){
			Collections.sort(cList);
			if(cList.contains(c)){
				//inexoutofboundsexception?
				cList.remove(c);
			}
			else{
				return false;
			}
		}
		//TODO run the check against the dictionary list. 
		return true;
	}
}
