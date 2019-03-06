import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private final int trials;
	private double[] result;
	private boolean meanFlag = false;
	private double mean;
	private boolean stddevFlag = false;
	private double stddev;
	private final int n;

	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException();
		}
		result = new double[trials];
		this.trials = trials;
		this.n = n;
	}

	public double mean() {
		if (meanFlag) {
			return mean;
		}
		Percolation grid = null;
		for (int i = 0; i < trials; i++) {
			grid = new Percolation(n);
			while (!grid.percolates()) {
				grid.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
			}
			result[i] = (double) grid.numberOfOpenSites() / (n * n);
		}
		mean = StdStats.mean(result);
		meanFlag = true;
		return mean;
	}

	public double stddev() {
		mean();
		if (stddevFlag) {
			return stddev;
		} else {
			stddevFlag = true;
			stddev = StdStats.stddev(result);
			return stddev;
		}
	}

	public double confidenceLo() {
		return mean() - 1.96 * stddev() / Math.sqrt(trials);
	}

	public double confidenceHi() {
		return mean() + 1.96 * stddev() / Math.sqrt(trials);
	}

	public static void main(String[] args) {
		PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println("mean\t\t\t= " + stats.mean());
		System.out.println("stddev\t\t\t= " + stats.stddev());
		System.out.println("95% confidence interval\t= [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
	}
}