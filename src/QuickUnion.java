import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;

public class QuickUnion {
    private int count;
    private int[] id;

    public QuickUnion(int n){
        id = new int[n];
        count = n;
        for (int i = 0; i < count; i++){
            id[i] = i;
        }
    }

    public void validate(int p){
        if (p < 0 || p >= count)
            throw new IllegalArgumentException("p out of range");
    }

    public int root(int p){
        validate(p);
        while(id[p] != p){
            p = id[p];
        }
        return p;
    }

    public boolean connected(int p, int q){
        validate(p);
        validate(q);
        return root(p) == root(q);
    }

    public void union(int p, int q){
        validate(p);
        validate(q);
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ)
            return;
        id[rootP] = rootQ;
    }

    public static void main(String[] args){
        Stopwatch stopwatch = new Stopwatch();
        int n = StdIn.readInt();
        QuickUnion qu = new QuickUnion(n);
        if (n < 0) throw new IllegalArgumentException();
        while ((!StdIn.isEmpty())){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!qu.connected(p, q)){
                qu.union(p, q);
                System.out.println(p + " " + q);
            }
        }
        System.out.println(stopwatch.elapsedTime()+"");
    }
}
