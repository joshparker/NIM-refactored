package edu.neumont.csc130;

public class Game {

	public static Database data = new Database();

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
				p1Turn = !p1Turn;
				break;
			case 1:
				if(p1Turn){
					playerTurn();
				}else{
					computerTurn();
				}
				p1Turn = !p1Turn;
				break;

			case 2:
				if(p1Turn){
					computerTurn();
				}else{
					computerTurn();
				}
				p1Turn = !p1Turn;
				break;

			default:
				System.out.println("Bad gametype number");
				gameNotWon = false;
			}

		}

	}

	public void computerTurn(){

		//Computers Turn
	}

	public void playerTurn(){

		//Non-computers turn

	}
}
