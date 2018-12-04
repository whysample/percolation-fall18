
public class PercolationUF implements IPercolate{
	protected boolean[][] myGrid;
	protected int myOpenCount;
	protected IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	
	/**
	 * Constructor that creates an object of PercolationUF. This creates the grid.
	 * @param finder is an object of IUnionFind that is used to create a set of grids.
	 * @param size is an int that represents the size of the length and width of the grid.
	 */
	public PercolationUF(int size, IUnionFind finder) {
		myGrid= new boolean[size][size];
		finder.initialize(size*size+2);
		myFinder=finder;
		VTOP=size*size;
		VBOTTOM=size*size+1;
	}
	
	/**
	 * Is a function that returns true if the cell's units are inside the grid. It returns false if they are not.
	 * @param row is an int that represents the row the specific cell is in. 
	 * @param col is an int that represents the colunmn the specific cell is in.
	 * @return a boolean concerning whether or not the cell is inside myGrid
	 */
	public boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
	
	/**
	 * Is a function that changes the cell given by the parameters to true and then creates a union between the cell
	 * and other surrounding open cells in the grid.
	 * @param row is an int that represents the cell's row
	 * @param col is an int that represents the cell's column
	 */
	@Override
	public void open(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (myGrid[row][col] != false)
			return;
		myOpenCount += 1;
		myGrid[row][col] = true;
		if(row==0) {
			myFinder.union(VTOP,index(row,col));
		}
		if(row==myGrid.length-1) {
			myFinder.union(VBOTTOM,index(row,col));
		}
		if(inBounds(row-1,col)&&myGrid[row-1][col]==true){
			myFinder.union(index(row-1,col),index(row,col));	
		}
		if(inBounds(row+1,col)&&myGrid[row+1][col]==true){
			myFinder.union(index(row+1,col),index(row,col));	
		}
		if(inBounds(row,col-1)&&myGrid[row][col-1]==true){
			myFinder.union(index(row,col-1),index(row,col));	
		}
		if(inBounds(row,col+1)&&myGrid[row][col+1]==true){
			myFinder.union(index(row,col+1),index(row,col));	
		}
	}
	
	/**
	 * Is a function that returns a boolean to check if the cell given by the parameters is open.
	 * @param row is an int that represents the cell's row
	 * @param col is an int that represents the cell's column
	 * @return a boolean checking if the cell is open
	 */
	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}
	
	/**
	 * Is a function that returns a boolean if the cell is full by checking if it is connected to the top. It throws an
	 * exception if the cell given by the parameters is out of bounds.
	 * @param row is an int that represents the cell's row
	 * @param col is an int that represents the cell's column
	 * @return a boolean if the cell is connected to the top.
	 */
	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myFinder.connected(index(row,col),VTOP);
		
	}
	
	/**
	 * This is a helper function that returns an int representing the cell's specific integer. This is used to check unions as well
	 * as to create unions of cells.
	 * @param row is an int that represents the cell's row
	 * @param col is an int that represents the cell's column
	 * @return an int representing the specific index of the cell
	 */
	public int index(int row, int col) {
		return row*myGrid.length+col;
	}
	
	/**
	 * This is a function that returns the number of open sites present on the grid
	 * @return myOpenCount which is an int representing the number of oper sites in the grid
	 */
	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	/**
	 * This function returns a boolean concerning whether the entire system percolates. This is checked by checking to see 
	 * if VTOP is connected to VBOTTOM. 
	 * @return a boolean checking to see if system percolates
	 */
	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}
}
