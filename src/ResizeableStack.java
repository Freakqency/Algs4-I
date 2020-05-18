import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizeableStack<Item> implements Iterable<Item> {
    private int N;
    private Item[] items;

    public ResizeableStack(){
        items = (Item[]) new Object[2];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void resize(int capacity){
        assert capacity >= N;

        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < items.length; i++)
            copy[i] = items[i];
        items = copy;
    }

    public void push(Item item){
        if (N == items.length)
            resize(2*items.length);
        items[N++] = item;
    }

    public Item pop() {
        return items[--N];
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{
        private int i = N -1;

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return items[i--];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        ResizeableStack<String> stack = new ResizeableStack<>();
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!item.equals("-"))
                stack.push(item);
            else if (!stack.isEmpty())
                StdOut.print(stack.pop() + " ");
        }
        StdOut.println(stack.size() + "left on stack");
    }
}
