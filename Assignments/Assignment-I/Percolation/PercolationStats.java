import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONST = 1.96;
    private final int trials;
    private final double sdVal;
    private final double meanVal;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0)
            throw new IllegalArgumentException();
        trials = t;
        double[] values = new double[trials];
        for (int exp = 0; exp < trials; exp++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int i = StdRandom.uniform(1, n +1);
                int j = StdRandom.uniform(1, n +1);
                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                }
            }
            double openSite = percolation.numberOfOpenSites();
            double expValue = openSite/(n * n);
            values[exp] = expValue;
        }
        meanVal = StdStats.mean(values);
        sdVal = StdStats.stddev(values);
    }

    // sample mean of percolation threshold
    public double mean() {
        return meanVal;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return sdVal;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return meanVal - ((CONST * sdVal)/Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return meanVal + ((CONST * sdVal)/ Math.sqrt(trials));
    }

    // test client
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, t);
        String confidence = "[" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]";
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " + confidence);
    }

}
