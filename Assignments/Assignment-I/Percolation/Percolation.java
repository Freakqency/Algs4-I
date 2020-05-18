import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int TOP = 0;
    private final boolean[][] id;
    private final int count;
    private final int bottom;
    private int openSites;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final WeightedQuickUnionUF weightedQuickUnionUFNoBottom;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n < 0");
        count = n;
        id = new boolean[count][count];
        bottom = count * count + 1;
        weightedQuickUnionUF = new WeightedQuickUnionUF(count * count + 2);
        weightedQuickUnionUFNoBottom = new WeightedQuickUnionUF(count * count + 1);
    }

    // validate the given indexes
    private void validate(int i, int j) {
        if (i < 1 || i > count || j < 1 || j > count)
            throw new IllegalArgumentException();
    }

    // get the index of array for weighted quick union
    private int getWQUIndex(int i, int j) {
        return count * (i - 1) + j;
    }

    // opens the site (row, col) if it is not open already
    public void open(int i, int j) {
        validate(i, j);
        if (isOpen(i, j))
            return;
        id[i-1][j-1] = true;
        openSites++;
        if (i == 1) {
            weightedQuickUnionUF.union(getWQUIndex(i, j), TOP);
            weightedQuickUnionUFNoBottom.union(getWQUIndex(i, j), TOP);
        }

        if (i == count) {
            weightedQuickUnionUF.union(getWQUIndex(i, j), bottom);
        }

        if (j > 1 && isOpen(i, j-1)) {
            weightedQuickUnionUF.union(getWQUIndex(i, j), getWQUIndex(i, j-1));
            weightedQuickUnionUFNoBottom.union(getWQUIndex(i, j), getWQUIndex(i, j-1));
        }

        if (j < count && isOpen(i, j+1)) {
            weightedQuickUnionUF.union(getWQUIndex(i, j), getWQUIndex(i, j+1));
            weightedQuickUnionUFNoBottom.union(getWQUIndex(i, j), getWQUIndex(i, j+1));
        }

        if (i > 1 && isOpen(i - 1, j)) {
            weightedQuickUnionUF.union(getWQUIndex(i, j), getWQUIndex(i-1, j));
            weightedQuickUnionUFNoBottom.union(getWQUIndex(i, j), getWQUIndex(i-1, j));
        }

        if (i < count && isOpen(i+1, j)) {
            weightedQuickUnionUF.union(getWQUIndex(i, j), getWQUIndex(i+1, j));
            weightedQuickUnionUFNoBottom.union(getWQUIndex(i, j), getWQUIndex(i+1, j));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int i, int j) {
        validate(i, j);
        return id[i - 1][j - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int i, int j) {
        validate(i, j);
        return weightedQuickUnionUFNoBottom.find(getWQUIndex(i, j)) == weightedQuickUnionUFNoBottom.find(TOP);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
         return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.find(TOP) == weightedQuickUnionUF.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            percolation.open(p, q);
        }
        System.out.println(percolation.numberOfOpenSites()+"");
        System.out.println(percolation.percolates()+"");
    }
}
