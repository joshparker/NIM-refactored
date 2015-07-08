//package edu.neumont.csc130;

import java.util.Scanner;

public class Game {

	public static Database data = new Database();
	private int row1 = 3, row2 = 5, row3 = 7;
	public Scanner scan = new Scanner(System.in);
	
//	public static void main(String[] args){
//		new Game().mainMenu();
//	}
	
	
	public void mainMenu(){
		System.out.println("Welcome to NIM!\n\n");
		boolean runtime = true;
		while(runtime){
			System.out.println("Please choose a Game Type by number\n"
					+ "(1) Player vs Player\n"
					+ "(2) Player vs Computer\n"
					+ "(3) Computer vs Computer");
			int playChoice = -1;
			try{
				playChoice = Integer.parseInt(scan.nextLine());
				
				this.game(playChoice);
			} catch(Exception e){
				System.out.println("Please input a valid menu number");
			}
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
					System.out.println("Player 1's Turn");
					playerTurn();
				}else{
					System.out.println("Player 2's Turn");
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
						boolean removing_stuff = true;
						
						while(removing_stuff){
							try{
								int amount = Integer.parseInt(scan.nextLine());
								if(amount <= 0 || amount > row1){
									throw new Exception();
								}
								row1 -= amount;
								removing_stuff = false;
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
						boolean removing_stuff = true;
						
						while(removing_stuff){
							try{
								int amount = Integer.parseInt(scan.nextLine());
								if(amount <= 0 || amount > row2){
									throw new Exception();
								}
								row2 -= amount;
								removing_stuff = false;
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
						boolean removing_stuff = true;
						
						while(removing_stuff){
							try{
								int amount = Integer.parseInt(scan.nextLine());
								if(amount <= 0 || amount > row3){
									throw new Exception();
								}
								row3 -= amount;
								removing_stuff = false;
								
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
