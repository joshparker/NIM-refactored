
public class NimRows {

	private int row1;
	private int row2;
	private int row3;
	
	public NimRows(int row1, int row2, int row3){
		
		this.row1 = row1;
		this.row2 = row2;
		this.row3 = row3;
		
	}
	
	public String returnAllRowsInString(){
		
		return row1+"-"+row2+"-"+row3;
		
	}
	
}
