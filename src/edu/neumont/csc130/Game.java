package edu.neumont.csc130;

public class Game {

	public static Database data = new Database();
	private int row1 = 3, row2 = 5, row3 = 7;

	public static void main(String[] args){
		new Game().mainMenu();
	}
	
	
	public void mainMenu(){
		boolean runtime = true;
		while(runtime){
			//copy menu text here
		}
	}
	
	
	
	//Merge the PVP,PVC, and CVC classes into one here
	public void game (int GameType){


		boolean p1Turn = true;
		boolean gameNotWon = true;
		while(gameNotWon){

			switch(GameType){
			case 0:
				if(p1Turn){
					playerTurn();
				}else{
					playerTurn();
				}
				break;
			case 1:
				if(p1Turn){
					playerTurn();
				}else{
					computerTurn();
				}
				break;
			case 2:
				if(p1Turn){
					computerTurn();
				}else{
					computerTurn();
				}
				break;
			default:
				System.out.println("Bad gametype number");
				gameNotWon = false;
			}

			if(row1 == 0 && row2 == 0 && row3 == 0){
				gameNotWon = false;
				switch(GameType){
				case 0:
					if(p1Turn){
						System.out.println("Player 2 wins!");
					}else{
						System.out.println("Player 1 wins!");
					}
					break;
				case 1:
					if(p1Turn){
						System.out.println("The Computer wins!");
					}else{
						System.out.println("Player wins!");
					}
					break;
				case 2:
					//show game statistics for CvC?
					break;
				default:
					break;
				}
			}


			p1Turn = !p1Turn;

		}

	}

	public void computerTurn(){

		//Computers Turn
	}

	public void playerTurn(){

		//Non-computers turn

	}
}
