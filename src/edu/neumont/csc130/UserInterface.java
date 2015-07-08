package edu.neumont.csc130;

public class UserInterface {

	public void displayMain(){
		
		String menu = "Nim Game:\n" 
		+ "(1) Play a Player vs Player game\n"
		+ "(2) Play a Player vs Computer game\n"
		+ "(3) Start a Computer vs Computer game\n"
		+ "(4) Exit";
		
		System.out.println(menu);
		
	}
	
	public void promptToPlayAgain(){
		
		System.out.println("Return to main menu and play again?\n"
				+ "(1) Yes\n"
				+ "(2) No\n");
		
	}
	
	//Build option method
	
}
