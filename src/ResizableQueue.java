import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizableQueue<Item> implements Iterable<Item> {

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        @Override
        public boolean hasNext() {
            return i < N;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = items[(i + first) % items.length];
            i++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private int N;
    private int first;
    private int last;
    Item[] items;

    public ResizableQueue() {
        items = (Item[]) new Object[2];
        first = 0;
        last = 0;
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int capacity) {
        assert capacity >= N;
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < N; i++)
            copy[i] = items[(first + i) % items.length];
        items = copy;
        first = 0;
        last = N;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (N == items.length)
            resize(2*items.length);
        items[last++] = item;
        if (last == items.length)
            last = 0;
        N++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = items[first];
        items[first] = null;
        N--;
        first++;
        if (first == items.length)
            first = 0;
        if (N > 0 && N == items.length/4)
            resize(items.length/2);
        return item;
    }

    public Item peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return items[first];
    }

    public static void main(String[] args) {
        ResizableQueue<String> queue = new ResizableQueue<>();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (!s.equals("-"))
                queue.enqueue(s);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("Left: " + queue.size());
    }
}
