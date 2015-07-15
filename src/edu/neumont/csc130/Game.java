//package edu.neumont.csc130;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public static Database data = new Database();
	private int row1 = 3, row2 = 5, row3 = 7;
	public Scanner scan = new Scanner(System.in);
	ArrayList<String> player1Moves = new ArrayList<String>();
	ArrayList<String> player2Moves = new ArrayList<String>();
	
	public void reset(){
		row1 = 3;
		row2 = 5;
		row3 = 7;
		player1Moves.clear();
		player2Moves.clear();
	}
	
	
	
	//Merge the PVP,PVC, and CVC classes into one here
	public void game (int GameType){
		
		

		boolean player1Turn = true;
		boolean gameNotWon = true;
		while(gameNotWon){

			switch(GameType){
			case 0:
				if(player1Turn){
					System.out.println("Player 1's Turn");
					playerTurn();
					player1Moves.add(row1+"-"+row2+"-"+row3);
				}else{
					System.out.println("Player 2's Turn");
					playerTurn();
					player2Moves.add(row1+"-"+row2+"-"+row3);
				}
				break;
			case 1:
				if(player1Turn){
					playerTurn();
					player1Moves.add(row1+"-"+row2+"-"+row3);
				}else{
					computerTurn();
					player2Moves.add(row1+"-"+row2+"-"+row3);
				}
				break;
			case 2:
				if(player1Turn){
					computerTurn();
					player1Moves.add(row1+"-"+row2+"-"+row3);
				}else{
					computerTurn();
					player2Moves.add(row1+"-"+row2+"-"+row3);
				}
				break;
			case 3:
				System.out.println("Goodbye!");
				gameNotWon = false;
				System.exit(1);
				break;
			default:
				System.out.println("Bad gametype number");
				gameNotWon = false;
			}

			if(row1 == 0 && row2 == 0 && row3 == 0){
				gameNotWon = false;
				switch(GameType){
				case 0:
					if(player1Turn){
						System.out.println("Player 2 wins!");
						for(int i = 1; i < player2Moves.size()+1; i++){
							data.updateValues(player2Moves.get(i-1), (double)i/((double)player2Moves.size()+1));
						}
						for(int i = 1; i < player1Moves.size()+1; i++){
							data.updateValues(player1Moves.get(i-1), (double)-i/((double)player1Moves.size()+1));
						}
					}else{
						System.out.println("Player 1 wins!");
						for(int i = 1; i < player1Moves.size()+1; i++){
							data.updateValues(player1Moves.get(i-1), (double)i/((double)player1Moves.size()+1));
						}
						for(int i = 1; i < player2Moves.size()+1; i++){
							data.updateValues(player2Moves.get(i-1), (double)-i/((double)player2Moves.size()+1));
						}
					}
					break;
				case 1:
					if(player1Turn){
						System.out.println("The Computer wins!");
						for(int i = 1; i < player2Moves.size()+1; i++){
							data.updateValues(player2Moves.get(i-1), (double)i/((double)player2Moves.size()+1));
						}
						for(int i = 1; i < player1Moves.size()+1; i++){
							data.updateValues(player1Moves.get(i-1), (double)-i/((double)player1Moves.size()+1));
						}
					}else{
						System.out.println("The Player wins!");
						for(int i = 1; i < player1Moves.size()+1; i++){
							data.updateValues(player1Moves.get(i-1), (double)i/((double)player1Moves.size()+1));
						}
						for(int i = 1; i < player2Moves.size()+1; i++){
							data.updateValues(player2Moves.get(i-1), (double)-i/((double)player2Moves.size()+1));
						}
					}
					break;
				case 2:
					if(player1Turn){
						for(int i = 1; i < player2Moves.size()+1; i++){
							data.updateValues(player2Moves.get(i-1), (double)i/((double)player2Moves.size()+1));
						}
						for(int i = 1; i < player1Moves.size()+1; i++){
							data.updateValues(player1Moves.get(i-1), (double)-i/((double)player1Moves.size()+1));
						}
					}else{
						for(int i = 1; i < player1Moves.size()+1; i++){
							data.updateValues(player1Moves.get(i-1), (double)i/((double)player1Moves.size()+1));
						}
						for(int i = 1; i < player2Moves.size()+1; i++){
							data.updateValues(player2Moves.get(i-1), (double)-i/((double)player2Moves.size()+1));
						}
					}
					//show game statistics for CvC?
					break;
				default:
					break;
				}
			}


			player1Turn = !player1Turn;

		}

	}

	public void computerTurn(){
		String[] rows = (data.getNextMove(row1+"-"+row2+"-"+row3)).split("-");
		row1 = Integer.parseInt(rows[0]);
		row2 = Integer.parseInt(rows[1]);
		row3 = Integer.parseInt(rows[2]);
	}

	public void playerTurn(){
		System.out.println("Current Rows:"
				+ "\n(1): "+row1
				+ "\n(2): "+row2
				+ "\n(3): "+row3);
		
		System.out.print("\n\nPlease select a row: ");
		boolean rowCheck = true;
		while(rowCheck){
			String rowChoice = scan.nextLine();
			if(!(rowChoice.equals("1") || rowChoice.equals("2") || rowChoice.equals("3"))){
				System.out.println("Please select a valid row: ");
			}else{
				switch(Integer.parseInt(rowChoice)){
				case 1:
					if(row1 == 0){
						System.out.println("That row is empty. Please select another row.");
					}else{
						rowCheck = false;
						System.out.println("How many do you want to remove?");
						boolean removing_sticks = true;
						
						while(removing_sticks){
							try{
								int amount = Integer.parseInt(scan.nextLine());
								if(amount <= 0 || amount > row1){
									throw new Exception();
								}
								row1 -= amount;
								removing_sticks = false;
							}catch(Exception e){
								System.out.println("Please choose a valid number.");
							}
						}
					}
					break;
				case 2:
					if(row2 == 0){
						System.out.println("That row is empty. Please select another row.");
					}else{
						rowCheck = false;
						System.out.println("How many do you want to remove?");
						boolean removing_sticks = true;
						
						while(removing_sticks){
							try{
								int amount = Integer.parseInt(scan.nextLine());
								if(amount <= 0 || amount > row2){
									throw new Exception();
								}
								row2 -= amount;
								removing_sticks = false;
							}catch(Exception e){
								System.out.println("Please choose a valid number.");
							}
						}
					}
					break;
				case 3:
					if(row3 == 0){
						System.out.println("That row is empty. Please select another row.");
					}else{
						rowCheck = false;
						System.out.println("How many do you want to remove?");
						boolean removing_sticks = true;
						
						while(removing_sticks){
							try{
								int amount = Integer.parseInt(scan.nextLine());
								if(amount <= 0 || amount > row3){
									throw new Exception();
								}
								row3 -= amount;
								removing_sticks = false;
								
							}catch(Exception e){
								System.out.println("Please choose a valid number.");
							}
						}
					}
					break;
				default:
					break;
				}
			}
		}
	}
}
