public class PercolationDFSFast extends PercolationDFS {
	/**
	 * This constructor creates an object of PercolationDFSFast and dictates the size of the grid
	 * by the parameter size
	 * @param size is an int that represents the size of the length and width of the grid
	 */
	public PercolationDFSFast(int size){
		super(size);
	}
	
	/**
	 * This function checks to see if the cell should be full. It checks to see if it is on the first row.
	 * Ihen, it checks if the surrounding cells are full. 
	 */
	@Override
	protected void updateOnOpen(int row, int col) {
		boolean v= false;
		if(row==0) {
			v=true;
		}
		else {
			if(inBounds(row,col+1)) {
				if(myGrid[row][col+1]==FULL) {
					v=true;
				}
			}
			if(inBounds(row,col-1)) {
				if(myGrid[row][col-1]==FULL) {
					v=true;
				}
			}
			if(inBounds(row-1,col)) {
				if(myGrid[row-1][col]==FULL) {
					v=true;
				}
			}
			if(inBounds(row+1,col)) {
				if(myGrid[row+1][col]==FULL) {
					v=true;
				}
			}
		}
		if(v==true) {
			dfs(row,col);
		}
	}
}