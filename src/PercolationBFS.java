import java.util.*;
public class PercolationBFS extends PercolationDFSFast{
	public PercolationBFS(int size) {
		super(size);
	}
	@Override
	protected void dfs(int row, int col) {
		Queue<Integer> p=new LinkedList<>();
		int size=myGrid.length;
		if (! inBounds(row,col)) return;
		if (isFull(row, col) || !isOpen(row, col))
			return;
		myGrid[row][col] = FULL;
		p.add(row*size+col);
		while(p.size()!=0) {
			Integer p1=p.remove();
			int row1=p1/size;
			int col1=p1%size;
			if(inBounds(row1-1,col1)&&myGrid[row1-1][col1]==OPEN&&myGrid[row1-1][col1]!=FULL){
				myGrid[row1-1][col1] = FULL;
				p.add((row1-1)*size+col1);
			}
			if(inBounds(row1+1,col1)&&myGrid[row1+1][col1]==OPEN&&myGrid[row1+1][col1]!=FULL){
				myGrid[row1+1][col1] = FULL;
				p.add((row1+1)*size+col1);
			}
			if(inBounds(row1,col1-1)&&myGrid[row1][col1-1]==OPEN&&myGrid[row1][col1-1]!=FULL){
				myGrid[row1][col1-1] = FULL;
				p.add((row1)*size+col1-1);
			}
			if(inBounds(row1,col1+1)&&myGrid[row1][col1+1]==OPEN&&myGrid[row1][col1+1]!=FULL){
				myGrid[row1][col1+1] = FULL;
				p.add((row1)*size+col1+1);
			}
		}
	}

}
