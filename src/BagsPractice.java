import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BagsPractice<Item> implements Iterable<Item> {
    public static class Node<Item> {
        Item item;
        Node<Item> next;
        Node(Item i) { item = i; }
    }

    private int N;
    private Node<Item> top;

    public BagsPractice() {
        N = 0;
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return N;
    }

    public void add(Item data) {
        Node<Item> oldTop = top;
        top = new Node<Item>(data);
        top.next = oldTop;
        N++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator(top);
    }

    private class LinkedListIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedListIterator(Node<Item> top) {
            current = top;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        BagsPractice<String> bag = new BagsPractice<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            bag.add(s);
        }
        StdOut.print(bag.size() + "Size of bag");
        for (String s : bag)
            StdOut.println(s + "");
    }
}
