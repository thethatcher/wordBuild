package wordBuild;

import java.util.Scanner;

public class WordBuild {

	public static void main(String[]Args){
		WordBuild wb = new WordBuild();
		System.out.println("----------------------------------------\nWelcome to Word Build!");
		wb.mainMenu();
	}
	
	private void mainMenu(){
		Scanner input = new Scanner(System.in);
		Game gm = new Game();
		int response = 0;
		//TODO read in the rules from the rules file.
		String rules = "The rules have not been read in yet.";
		System.out.println("----------------------------------------"
				+ "\nPlease choose an option below.");
		System.out.println("1. Rules\n2. Play vs. Computer\n3. 2-Player game\n4.Exit");
		if(input.hasNextInt()){
			response = input.nextInt();
		}
		else{
			input.next();
		}
		if(response < 1 || response > 4){
			System.out.println("Invalid Input.");
			mainMenu();
		}
		else{
			switch (response){
			case 1: System.out.println(rules);
					break;
			case 2: System.out.println("AI Game will now begin.");
					gm.gameMenu();
					//TODO make game for AI rather than 2-play hotseat. 
					break;
			case 3: System.out.println("Multiplayer game will now begin");
				gm.gameMenu();
			//TODO make game for 2-players, rather than AI
					break;
			case 4: System.exit(0);
			}
			mainMenu();
		}
	}
}
	