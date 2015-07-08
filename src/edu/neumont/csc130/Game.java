//package edu.neumont.csc130;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public static Database data = new Database();
	private int row1 = 3, row2 = 5, row3 = 7;
	public Scanner scan = new Scanner(System.in);
	ArrayList<String> p1moves = new ArrayList<String>();
	ArrayList<String> p2moves = new ArrayList<String>();
//	public static void main(String[] args){
//		new Game().mainMenu();
//	}
	
	
	public void mainMenu(){
		System.out.println("Welcome to NIM!\n\n");
		boolean runtime = true;
		while(runtime){
			reset();
			
			
			System.out.println("Please choose a Game Type by number\n"
					+ "(1) Player vs Player\n"
					+ "(2) Player vs Computer\n"
					+ "(3) Computer vs Computer\n"
					+ "(4) Exit");
			int playChoice = -1;
			try{
				playChoice = Integer.parseInt(scan.nextLine());
				
				if(playChoice == 3){
					System.out.println("How many times should the computer play itself?");
					try{
						int playtime = Integer.parseInt(scan.nextLine());
						
						if(playtime < 0){
							throw new Exception();
						}
						
						for(int i = 0; i < playtime; i++){
							reset();
							this.game(playChoice-1);
							System.out.println("Game "+i+" complete");
						}
					}catch(Exception e){
						System.out.println("Please choose a valid number;");
					}
				}else{
					reset();
					this.game(playChoice-1);
				}
			} catch(Exception e){
				System.out.println("Please input a valid menu number");
			}
		}
	}
	
	public void reset(){
		row1 = 3;
		row2 = 5;
		row3 = 7;
		p1moves.clear();
		p2moves.clear();
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
					p1moves.add(row1+"-"+row2+"-"+row3);
				}else{
					System.out.println("Player 2's Turn");
					playerTurn();
					p2moves.add(row1+"-"+row2+"-"+row3);
				}
				break;
			case 1:
				if(p1Turn){
					playerTurn();
					p1moves.add(row1+"-"+row2+"-"+row3);
				}else{
					computerTurn();
					p2moves.add(row1+"-"+row2+"-"+row3);
				}
				break;
			case 2:
				if(p1Turn){
					computerTurn();
					p1moves.add(row1+"-"+row2+"-"+row3);
				}else{
					computerTurn();
					p2moves.add(row1+"-"+row2+"-"+row3);
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
					if(p1Turn){
						System.out.println("Player 2 wins!");
						for(int i = 1; i < p2moves.size()+1; i++){
							data.updateValues(p2moves.get(i-1), (double)i/((double)p2moves.size()+1));
						}
						for(int i = 1; i < p1moves.size()+1; i++){
							data.updateValues(p1moves.get(i-1), (double)-i/((double)p1moves.size()+1));
						}
					}else{
						System.out.println("Player 1 wins!");
						for(int i = 1; i < p1moves.size()+1; i++){
							data.updateValues(p1moves.get(i-1), (double)i/((double)p1moves.size()+1));
						}
						for(int i = 1; i < p2moves.size()+1; i++){
							data.updateValues(p2moves.get(i-1), (double)-i/((double)p2moves.size()+1));
						}
					}
					break;
				case 1:
					if(p1Turn){
						System.out.println("The Computer wins!");
						for(int i = 1; i < p2moves.size()+1; i++){
							data.updateValues(p2moves.get(i-1), (double)i/((double)p2moves.size()+1));
						}
						for(int i = 1; i < p1moves.size()+1; i++){
							data.updateValues(p1moves.get(i-1), (double)-i/((double)p1moves.size()+1));
						}
					}else{
						System.out.println("The Player wins!");
						for(int i = 1; i < p1moves.size()+1; i++){
							data.updateValues(p1moves.get(i-1), (double)i/((double)p1moves.size()+1));
						}
						for(int i = 1; i < p2moves.size()+1; i++){
							data.updateValues(p2moves.get(i-1), (double)-i/((double)p2moves.size()+1));
						}
					}
					break;
				case 2:
					if(p1Turn){
						for(int i = 1; i < p2moves.size()+1; i++){
							data.updateValues(p2moves.get(i-1), (double)i/((double)p2moves.size()+1));
						}
						for(int i = 1; i < p1moves.size()+1; i++){
							data.updateValues(p1moves.get(i-1), (double)-i/((double)p1moves.size()+1));
						}
					}else{
						for(int i = 1; i < p1moves.size()+1; i++){
							data.updateValues(p1moves.get(i-1), (double)i/((double)p1moves.size()+1));
						}
						for(int i = 1; i < p2moves.size()+1; i++){
							data.updateValues(p2moves.get(i-1), (double)-i/((double)p2moves.size()+1));
						}
					}
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
