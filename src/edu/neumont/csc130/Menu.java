import java.util.Scanner;


public class Menu {

	public Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args){
		new Menu().mainMenu();
	}
	
	public void mainMenu(){
		Game nimGame = new Game();
		System.out.println("Welcome to NIM!\n\n");
		boolean runtime = true;
		while(runtime){
			nimGame.reset();
			
			
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
							nimGame.reset();
							nimGame.game(playChoice-1);
							System.out.println("Game "+i+" complete");
						}
					}catch(Exception e){
						System.out.println("Please choose a valid number;");
					}
				}else{
					nimGame.reset();
					nimGame.game(playChoice-1);
				}
			} catch(Exception e){
				System.out.println("Please input a valid menu number");
			}
		}
	}
	
}
