
public class PercolationUF implements IPercolate{
	protected boolean[][] myGrid;
	protected int myOpenCount;
	protected IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	
	public PercolationUF(IUnionFind finder,int size) {
		myGrid= new boolean[size][size];
		finder.initialize(size*size+2);
		myFinder=finder;
		VTOP=size*size;
		VBOTTOM=size*size+1;
	}
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
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
	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}
	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myFinder.connected(index(row,col),VTOP);
		
	}
	
	public int index(int row, int col) {
		return row*myGrid.length+col;
	}
	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}
}
