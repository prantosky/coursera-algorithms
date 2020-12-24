import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF sites;
    private final WeightedQuickUnionUF sitesWithoutBottomConnection;
    private final int n;
    private int numberOfOpenSites;
    private final boolean[][] map;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        this.sites = new WeightedQuickUnionUF(n * n + 2);
        this.sitesWithoutBottomConnection = new WeightedQuickUnionUF(n * n + 1);
        this.n = n;
        this.numberOfOpenSites = 0;

        map = new boolean[n][n];
        for (int i = 1; i <= n; i++) {
            sites.union(0, i);
            sitesWithoutBottomConnection.union(0, i);
        }
        for (int i = n * n; i > n * n - n; i--) {
            sites.union(n * n + 1, i);
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1) * n + col;
    }

    private boolean areNotValidArguments(int row, int col) {
        return row < 1 || row > n || col < 1 || col > n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (areNotValidArguments(row, col)) throw new IllegalArgumentException();
        if (!isOpen(row, col)) {
            numberOfOpenSites++;
            map[row - 1][col - 1] = true;
            if (!areNotValidArguments(row - 1, col) && isOpen(row - 1, col)) {
                sites.union(getIndex(row, col), getIndex(row - 1, col));
                sitesWithoutBottomConnection.union(getIndex(row, col), getIndex(row - 1, col));
            }
            if (!areNotValidArguments(row, col - 1) && isOpen(row, col - 1)) {
                sites.union(getIndex(row, col), getIndex(row, col - 1));
                sitesWithoutBottomConnection.union(getIndex(row, col), getIndex(row, col - 1));
            }
            if (!areNotValidArguments(row + 1, col) && isOpen(row + 1, col)) {
                sites.union(getIndex(row, col), getIndex(row + 1, col));
                sitesWithoutBottomConnection.union(getIndex(row, col), getIndex(row + 1, col));
            }
            if (!areNotValidArguments(row, col + 1) && isOpen(row, col + 1)) {
                sites.union(getIndex(row, col), getIndex(row, col + 1));
                sitesWithoutBottomConnection.union(getIndex(row, col), getIndex(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
//        System.out.println("isOpen: row: " + row + ", col: " + col);
        if (areNotValidArguments(row, col)) throw new IllegalArgumentException();
        return map[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (areNotValidArguments(row, col)) throw new IllegalArgumentException();
        if (!isOpen(row, col)) return isOpen(row, col);

        int site = sitesWithoutBottomConnection.find(getIndex(row, col));
        int top = sitesWithoutBottomConnection.find(0);
        return site == top;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if (n == 1) return isOpen(1, 1);
        int top = sites.find(0);
        int bottom = sites.find(n * n + 1);
        return top == bottom;
    }
}