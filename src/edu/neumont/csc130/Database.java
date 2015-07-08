import java.util.ArrayList;
import java.util.Hashtable;


public class Database {
	public Hashtable<String, Integer> appearances = new Hashtable<String, Integer>();
	private Hashtable<String, ArrayList<String>> outcomes = new Hashtable<String, ArrayList<String>>();
	private Hashtable<String, Double> values = new Hashtable<String, Double>();

	public Database(){
		values.put("0-0-0", -1.0);
		values.put("1-0-0", 1.0);
		values.put("0-1-0", 1.0);
		values.put("0-0-1", 1.0);
	}

	public String getNextMove(String current){
		//take the current state of the board and return the highest rated next move from the Array/Hashtable

		String best_move = "0-0-0";
		values.remove("0-0-0");
		values.put("0-0-0", -1.0);
		//making DAMN sure 0-0-0 has a bad value
		boolean runagain = true;
		while(runagain){
//			System.out.println("Current "+current);
//			System.out.println("Possible moves: "+this.getPossibleMoves(current));
			
			
			for(String s:(ArrayList<String>)this.getPossibleMoves(current)){
//				System.out.println("S :: "+s+" :: "+this.getValue(s));
				if(this.getValue(best_move) < this.getValue(s)){
					best_move = s;
				}else if(this.getValue(best_move) == this.getValue(s)){
					java.util.Random rand = new java.util.Random();
					if(rand.nextBoolean()){
						best_move = s;
					}
				}
			}
			
			

			if(!(current.equals("1-0-0") || current.equals("0-1-0") || current.equals("0-0-1"))){
				if(!best_move.equals("0-0-0")){
					runagain = false;
				}
			}else{
				runagain = false;
			}
		}


		return best_move;
	}

	
	public String getRandMove(String current){
		String move = "0-0-0";
		move = this.getPossibleMoves(current).get(new java.util.Random().nextInt(this.getPossibleMoves(current).size()));
		return move;
	}
	
	public void updateValues(String rows, double value){

		if(!appearances.containsKey(rows)){
			appearances.put(rows, 0);
		}

		if(!values.containsKey(rows)){
			values.put(rows, 0.0);
		}

		double newVal = ((value)+(values.get(rows)*appearances.get(rows)))/((appearances.get(rows))+1);
		//		System.err.println(newVal+", "+appearances.get(rows));
		values.put(rows, (Double)newVal);
		appearances.put(rows, appearances.get(rows)+1);
	}

	public double getValue(String rows){
		if(!values.containsKey(rows) ){
			values.put(rows, 0.0);
			appearances.put(rows, 0);
		}
		if( ( rows.equals("1-0-0") || rows.equals("0-1-0") || rows.equals("0-0-1") ) ){
			values.put(rows, 1.0);
		}
		if( rows.equals("0-0-0")){
			values.put(rows, -1.0);
		}

		return values.get(rows);
	}

	private void pathGenerate(String current){
		int[] bort = new int[3];
		String[] stuff = current.split("-");
		for(int i = 0; i < 3; i++){
			bort[i] = Integer.parseInt(stuff[i]);
		}

		ArrayList<String> outcomez = new ArrayList<String>();

		String stuffs = null;
		for(int a = 0; a < 3; a++){
			for(int b = 0; b < bort[a]; b++){
				switch(a){
				case 0:
					stuffs = b+"-"+bort[1]+"-"+bort[2];
					outcomez.add(stuffs);
					appearances.put(stuffs, 1);
					values.put(stuffs, 0.0);
					break;
				case 1:
					stuffs = bort[0]+"-"+b+"-"+bort[2];
					outcomez.add(stuffs);
					appearances.put(stuffs, 1);
					values.put(stuffs, 0.0);
					break;
				case 2:
					stuffs = bort[0]+"-"+bort[1]+"-"+b;
					outcomez.add(stuffs);
					appearances.put(stuffs, 1);
					values.put(stuffs, 0.0);
					break;
				}
			}
		}

		outcomes.put(current, outcomez);

	}

	public ArrayList<String> getPossibleMoves(String current){

		if(outcomes.get(current) == null){
			this.pathGenerate(current);
		}

		return outcomes.get(current);
	}
}
