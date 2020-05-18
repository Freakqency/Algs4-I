import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FixedCapacityStack<Item> implements Iterable<Item> {
    private int N;
    private Item[] items;
    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{
        private int i = N-1;

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next(){
            if (!hasNext())
                throw new NoSuchElementException();
            return items[i--];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public FixedCapacityStack(int capacity) {
        items = (Item[]) new Object[capacity];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean isFull() {
        return N == items.length;
    }

    public void push(Item item) {
        items[N++] = item;
    }

    public Item pop() {
        return items[--N];
    }

    public static void main(String[] args) {
        int max = Integer.parseInt(args[0]);
        FixedCapacityStack<String> stack = new FixedCapacityStack<String>(max);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!s.equals("-"))
                stack.push(s);
            else if (stack.isEmpty())
                StdOut.println("BAD INPUT");
            else
                StdOut.println(stack.pop() + " ");
        }
        StdOut.println();
        StdOut.println("Left In Stack: ");
        for (String s : stack)
            StdOut.print(s + " ");
        StdOut.println();
    }
}
