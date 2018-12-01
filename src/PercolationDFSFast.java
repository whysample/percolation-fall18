public class PercolationDFSFast extends PercolationDFS {
	public PercolationDFSFast(int size){
		super(size);
	}
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