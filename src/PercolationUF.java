
public class PercolationUF implements IPercolate {
	private boolean[][] myGrid;
	private int myOpenCount;
	private IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	
	public PercolationUF(int size, IUnionFind finder) {
		myGrid = new boolean[size][size];
		finder.initialize(size * size + 2);
		myFinder = finder;
		VTOP = size * size;
		VBOTTOM = size * size +1;
	}
	
	@Override
	public void open(int row, int col) {
		if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException();
		}
		int[] deltar = {0,0,-1,1};
		int[] deltac = {1,-1,0,0};
		
		if (!isOpen(row,col)) {
			myGrid[row][col] = true;
			if (row == 0) {
				myFinder.union(VTOP, myGrid.length * row + col);
			}
			if (row == myGrid.length - 1) {
				myFinder.union(VBOTTOM, myGrid.length * row + col);
			}
		}
		
		for (int k = 0; k < 4; k++) {
			if (inBounds(row + deltar[k], col + deltac[k])) {
				if (isOpen(row + deltar[k], col + deltac[k])) {
					myFinder.union(myGrid.length * row + col, 
							myGrid.length * (row + deltar[k]) + 
							col + deltac[k]);
				}
			}
		}	
		
	}
	@Override
	public boolean isOpen(int row, int col) {
		if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException();
		}
		return myGrid[row][col];
	}
	@Override
	public boolean isFull(int row, int col) {
		if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException();
		}
		if (myFinder.connected(VTOP, myGrid.length * row + col)) {
			return true;
		}
		return false;
	}
	@Override
	public boolean percolates() {
		if (myFinder.connected(VTOP, VBOTTOM)) {
			return true;
		}
		return false;
	}
	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
	
	

}
