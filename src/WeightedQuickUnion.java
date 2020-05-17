import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;

public class WeightedQuickUnion {
    private int[] id;
    private int count;
    private int[] size;

    public WeightedQuickUnion(int n){
        count = n;
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < count; i++){
            id[i] = i;
            size[i] = i;
        }
    }

    private void validate(int p){
        if (p < 0 || p >= count)
            throw new IllegalArgumentException();
    }

    public int find(int p){
        validate(p);
        while (id[p] != p)
            p = id[p];
        return p;
    }

    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (size[rootP] < size[rootQ]) {
            id[rootP] = rootQ;
            size[rootP] += size[rootQ];
            return;
        }
        id[rootQ] = rootP;
        size[rootQ] += size[rootP];
    }

    public static void main(String[] args){
        Stopwatch stopwatch = new Stopwatch();
        int n = StdIn.readInt();
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(n);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (weightedQuickUnion.find(p) != weightedQuickUnion.find(q)){
                weightedQuickUnion.union(p, q);
                System.out.println(p + " " + q);
            }
        }
        System.out.println(stopwatch.elapsedTime() + "");
    }
}
