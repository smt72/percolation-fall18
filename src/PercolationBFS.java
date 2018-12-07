import java.util.*;

public class PercolationBFS extends PercolationDFSFast {

	public PercolationBFS(int n) {
		super(n);
	}
	
	@Override 
	protected void dfs(int row, int col) {
		Queue<Integer> queue = new LinkedList<>();
		myGrid[row][col] = FULL;
		int q = row * myGrid.length + col;
		queue.add(q);
		
		while (queue.size() != 0) {
			int first = queue.remove();
			int r = first / myGrid.length;
			int c = first % myGrid.length;
			
			int[] deltar = {0,0,-1,1};
			int[] deltac = {1,-1,0,0};
			
			for (int k = 0; k < 4; k++) {
				if (inBounds(r + deltar[k], c + deltac[k])) {
					if (isOpen(r + deltar[k], c + deltac[k])) {
						if (!isFull(r + deltar[k], c + deltac[k])) {
							myGrid[r + deltar[k]][c + deltac[k]] = FULL;
							queue.add((r + deltar[k]) * myGrid.length + 
									(c + deltac[k]));
						}
					}
				}
			}
		}
	}
}
