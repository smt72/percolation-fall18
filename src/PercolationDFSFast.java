
public class PercolationDFSFast extends PercolationDFS{
	public PercolationDFSFast(int n) {
		super(n);
		
	}
	@Override
	protected void updateOnOpen(int row, int col) {
		if (row == 0) {
			dfs(row,col);
		}
		else {
			int[] deltar = {0,0,-1,1};
			int[] deltac = {1,-1,0,0};
			
			for (int k = 0; k < 4; k++) {
				if (inBounds(row + deltar[k], col + deltac[k])) {
					if (isFull(row + deltar[k], col + deltac[k])) {
						dfs(row,col);
						break;
					}
				}
			}
		}
	}
}
