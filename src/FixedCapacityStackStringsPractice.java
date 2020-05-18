import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FixedCapacityStackStringsPractice implements Iterable<String> {
    private final String[] stack;
    private int N;

    public FixedCapacityStackStringsPractice(int capacity){
        stack = new String[capacity];
        N = 0;
    }

    @Override
    public Iterator<String> iterator() {
        return new ReverseArrayIterator();
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean isFull() {
        return N == stack.length;
    }

    public void push(String item) {
        stack[N++] = item;
    }

    public String pop() {
        return stack[--N];
    }

    public String peek() {
        return stack[N-1];
    }

    private class ReverseArrayIterator implements Iterator<String>{
        private int i = N-1;

        public boolean hasNext() {
            return i >= 0;
        }

        public String next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return stack[i--];

        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args){
        int max = Integer.parseInt(args[0]);
        FixedCapacityStackStringsPractice Stack = new FixedCapacityStackStringsPractice(max);
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (!s.equals("-"))
                Stack.push(s);
            else if (Stack.isEmpty())
                StdOut.println("BAD INPUT");
            else
                StdOut.print(Stack.pop() + " ");
        }
        StdOut.println();
        StdOut.print("Left on Stack: ");
        for (String s : Stack)
            StdOut.print(s + " ");
        StdOut.println();
    }
}
