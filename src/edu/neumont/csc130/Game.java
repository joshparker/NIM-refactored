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
