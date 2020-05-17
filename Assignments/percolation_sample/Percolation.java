import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] id;
    private int count;
    private int top = 0;
    private int bottom;
    private int openSites;
    private WeightedQuickUnionUF weightedQuickUnionUF;

    public Percolation(int n){
        if (n < 0)
            throw new IllegalArgumentException("n < 0");
        count = n;
        id = new boolean[n][n];
        bottom = count * count + 1;
        weightedQuickUnionUF = new WeightedQuickUnionUF(count * count + 2);
    }

//    private void validate(int i, int j){
//        if (i < 0 || i >= count || j < 0 || j >= count)
//            throw new IllegalArgumentException();
//    }

    private int getWQUIndex(int i, int j){
        return count * (i - 1) + j;
    }

    public void open(int i, int j){
//        validate(i, j);
        if (isOpen(i, j))
            return;
        id[i-1][j-1] = true;
        openSites++;
        if (i == 1)
            weightedQuickUnionUF.union(getWQUIndex(i, j), top);
        if (j == count)
            weightedQuickUnionUF.union(getWQUIndex(i, j), bottom);
        if (j > 1 && isOpen(i, j-1))
            weightedQuickUnionUF.union(getWQUIndex(i, j), getWQUIndex(i, j-1));
        if (j < count && isOpen(i, j+1))
            weightedQuickUnionUF.union(getWQUIndex(i, j), getWQUIndex(i, j+1));
        if (i > 1 && isOpen(i - 1, j))
            weightedQuickUnionUF.union(getWQUIndex(i, j), getWQUIndex(i-1, j));
        if (i < count && isOpen(i+1, j))
            weightedQuickUnionUF.union(getWQUIndex(i, j), getWQUIndex(i+1, j));
    }

    public boolean isOpen(int i, int j){
//        validate(i, j);
        return id[i - 1][j - 1];
    }

    public boolean isFull(int i, int j){
        return weightedQuickUnionUF.find(getWQUIndex(i, j)) == weightedQuickUnionUF.find(top);
    }

    public int numberOfOpenSites(){
         return openSites;
    }

    public boolean percolates(){
        return weightedQuickUnionUF.find(top) == weightedQuickUnionUF.find(bottom);
    }

    public static void main(String[] args){
        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            percolation.open(p, q);
        }
        System.out.println(percolation.numberOfOpenSites()+"");
        System.out.println(percolation.percolates()+"");
    }

}
