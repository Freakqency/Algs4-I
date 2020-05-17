import edu.princeton.cs.algs4.StdIn;

public class QuickFind {
    private int[] id;
    private int count;

    private void validate(int p){
        if (p < 0 || p >= count)
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (count-1));
    }

    public QuickFind(int n ){
        if (n < 0) throw new IllegalArgumentException();
        count = n;
        id = new int[n];
        for (int i = 0; i < count; i++){
            id[i] = i;
        }
    }

    public boolean connected(int p, int q){
        validate(p);
        validate(q);
        return id[p] == id[q];
    }

    public void union(int p, int q){
        validate(p);
        validate(q);
        int val_p = id[p];
        int val_q = id[q];

        if (val_p == val_q)
            return;

        for (int i =0; i < count; i++){
            if (id[i] == val_p)
                id[i] = val_q;
        }
    }

    public static void main(String[] args){
        int n = StdIn.readInt();
        QuickFind qf = new QuickFind(n);
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!qf.connected(p, q)){
                qf.union(p, q);
                System.out.println(p +" " +q);
            }
        }
    }
}
