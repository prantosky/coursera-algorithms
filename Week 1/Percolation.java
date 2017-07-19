import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private final boolean[][] map;
	private final WeightedQuickUnionUF grid;
	private final WeightedQuickUnionUF grid_without_bottom;
	private int closedSites;
	private final int n;

	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		map = new boolean[n][n];
		closedSites = n * n;
		this.n = n;
		grid = new WeightedQuickUnionUF(n * n + 2);
		grid_without_bottom = new WeightedQuickUnionUF(n * n + 1);
	}

	public void open(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n) {
			throw new IllegalArgumentException();
		}
		if (isOpen(row, col)) {
			return;
		}
		map[row - 1][col - 1] = true;
		closedSites--;
		if (row - 1 > 0 && isOpen(row - 1, col)) {
			grid.union(map(row, col), map(row - 1, col));
			grid_without_bottom.union(map(row, col), map(row - 1, col));
		}
		if (row + 1 <= n && isOpen(row + 1, col)) {
			grid.union(map(row, col), map(row + 1, col));
			grid_without_bottom.union(map(row, col), map(row + 1, col));
		}
		if (col - 1 > 0 && isOpen(row, col - 1)) {
			grid.union(map(row, col), map(row, col - 1));
			grid_without_bottom.union(map(row, col), map(row, col - 1));
		}
		if (col + 1 <= n && isOpen(row, col + 1)) {
			grid.union(map(row, col), map(row, col + 1));
			grid_without_bottom.union(map(row, col), map(row, col + 1));
		}
		if (row == 1) {
			grid.union(0, map(row, col));
			grid_without_bottom.union(0, map(row, col));
		}
		if (row == n) {
			grid.union(n * n + 1, map(row, col));
		}
	}

	public boolean isOpen(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n) {
			throw new IllegalArgumentException();
		}
		return map[row - 1][col - 1];
	}

	public boolean isFull(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n) {
			throw new IllegalArgumentException();
		}
		return grid_without_bottom.connected(0, map(row, col));
	}

	public int numberOfOpenSites() {
		return n * n - closedSites;
	}

	public boolean percolates() {
		return grid.connected(0, n * n + 1);
	}

	private int map(int row, int col) {
		return (row - 1) * n + col;
	}
}