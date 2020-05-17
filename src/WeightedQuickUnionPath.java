import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;

public class WeightedQuickUnionPath {
    private int count;
    private int[] id;
    private int[] size;

    public WeightedQuickUnionPath(int n){
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
        while(id[p] != p){
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q){
        validate(p);
        validate(q);
        int rootP = find(p);
        int rootQ = find(q);
        if (size[rootP] < size[rootQ]){
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
        WeightedQuickUnionPath weightedQuickUnionPath = new WeightedQuickUnionPath(n);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (weightedQuickUnionPath.find(p) != weightedQuickUnionPath.find(q)){
                weightedQuickUnionPath.union(p, q);
                System.out.println(p + " " + q);
            }
        }
        System.out.println(stopwatch.elapsedTime() + "");
    }

}
