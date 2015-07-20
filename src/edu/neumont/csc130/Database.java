import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;


public class Database {
	private Hashtable<String, Integer> appearances = new Hashtable<String, Integer>();
	private Hashtable<String, ArrayList<String>> outcomeTable = new Hashtable<String, ArrayList<String>>();
	private Hashtable<String, Double> values = new Hashtable<String, Double>();
	
	private Hashtable<NimRows, Integer> 			row_apperances = 	new Hashtable<NimRows, Integer>();
	private Hashtable<NimRows, ArrayList<NimRows>> 	row_outcomes = 		new Hashtable<NimRows, ArrayList<NimRows>>();
	private Hashtable<NimRows, Double> 				row_values = 		new Hashtable<NimRows, Double>();

	public Database(){
		values.put("0-0-0", -1.0);
		values.put("1-0-0", 1.0);
		values.put("0-1-0", 1.0);
		values.put("0-0-1", 1.0);

		row_values.put(new NimRows(0,0,0), -1.0);
		row_values.put(new NimRows(1,0,0), 1.0);
		row_values.put(new NimRows(0,1,0), 1.0);
		row_values.put(new NimRows(0,0,1), 1.0);
	}

	public String getNextMove(String current){

		String best_move = "0-0-0";
		values.remove("0-0-0");
		values.put("0-0-0", -1.0);
		boolean runagain = true;
		while(runagain){
			for(String s:(ArrayList<String>)this.getPossibleMoves(current)){
				if(this.getValue(best_move) < this.getValue(s)){
					best_move = s;
				}else if(this.getValue(best_move) == this.getValue(s)){
					java.util.Random rand = new java.util.Random();
					if(rand.nextBoolean()){
						best_move = s;
					}
				}
			}

			if((current.equals("1-0-0") || current.equals("0-1-0") || current.equals("0-0-1"))){
				runagain = false;
			}else{
				if(!best_move.equals("0-0-0")){
					runagain = false;
				}
			}
		}


		return best_move;
	}

	
	public String getRandMove(String current){
		String move = "0-0-0";
		ArrayList<String> possibleMoves = this.getPossibleMoves(current);
		int numPossibleMoves = possibleMoves.size();
		Random rand = new java.util.Random();

		move = possibleMoves.get(rand.nextInt(numPossibleMoves));
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

		values.put(rows, newVal);
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
		int[] rows = new int[3];
		String[] stringRows = current.split("-");
		for(int i = 0; i < 3; i++){
			rows[i] = Integer.parseInt(stringRows[i]);
		}

		ArrayList<String> outcome = new ArrayList<String>();
		NimRows nimRows;
		
		for(int a = 0; a < 3; a++){
			for(int b = 0; b < rows[a]; b++){
				switch(a){
				case 0:
					nimRows = new NimRows(b,rows[1],rows[2]);
					outcome.add(nimRows.returnAllRowsInString());
					appearances.put(nimRows.returnAllRowsInString(), 1);
					values.put(nimRows.returnAllRowsInString(), 0.0);
					break;
				case 1:
					nimRows = new NimRows(rows[0],b,rows[2]);
					outcome.add(nimRows.returnAllRowsInString());
					appearances.put(nimRows.returnAllRowsInString(), 1);
					values.put(nimRows.returnAllRowsInString(), 0.0);
					break;
				case 2:
					nimRows = new NimRows(rows[0],rows[1],b);
					outcome.add(nimRows.returnAllRowsInString());
					appearances.put(nimRows.returnAllRowsInString(), 1);
					values.put(nimRows.returnAllRowsInString(), 0.0);
					break;
				}
		
			}
		}

		outcomeTable.put(current, outcome);

	}

	public ArrayList<String> getPossibleMoves(String current){

		if(outcomeTable.get(current) == null){
			this.pathGenerate(current);
		}

		return outcomeTable.get(current);
	}
}
