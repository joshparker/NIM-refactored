import java.util.Scanner;


public class PlayerVsPlayer {

	private boolean gamewin;
	private boolean play1turn;
	private boolean turntaken;
	private Scanner scan = new Scanner(System.in);

	public void pvpGame(){
		play1turn = true;
		int row1 = 3;
		int row2 = 5;
		int row3 = 7;
		while (!gamewin){
			String rows = "(1) " + row1 + "\n"
					+ "(2) " + row2 + "\n"
					+ "(3) " + row3 + "\n";
			System.out.println(rows);
			if(play1turn){
				System.out.println("Player 1's turn");
				turntaken = false;
			}else{
				System.out.println("Player 2's turn");
				turntaken = false;
			}
				while(!turntaken){
					System.out.println("Choose a row");
					int play1in = Integer.parseInt(scan.nextLine());
					if( (play1in == 1) && (row1 == 0) ||
							(play1in == 2) && (row2 == 0) ||
							(play1in == 3) && (row3 == 0) ){
						System.out.println("That row is already empty! Try again!");
					}else if(play1in != 1 && play1in != 2 && play1in != 3){
						System.out.println("That is not a valid row choice!");
					}else{
						System.out.println("Enter the amount you would like to take from the row");
						
						int playin2 = Integer.parseInt(scan.nextLine());
							if((play1in == 1) && ((row1 - playin2) < 0) || (play1in == 2) && ((row2 - playin2) < 0) || (play1in == 3) && ((row3 - playin2) < 0) ){
								System.out.println("You can't take more than the number of sticks in the row! Try again!");
						}else{
							if(play1in == 1){
								row1 = row1 - playin2;
								turntaken = true;
							}else if(play1in == 2){
								row2 = row2 - playin2;
								turntaken = true;
							}else if(play1in == 3){
								row3 = row3 - playin2;
								turntaken = true;
							}
							if(play1turn){
								play1turn = false;
							}else{
								play1turn = true;
							}
						}
					if((row1 == 0 && row2 == 0 && row3 == 0)){
						if(play1turn){
							System.out.println("Player 1 wins!!!");
							gamewin = true;
						}else{
							System.out.println("Player 2 wins!!!");
							gamewin = true;
						}
					}
				}
			}
		}/////Game end
	}

}

