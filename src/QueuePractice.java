import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueuePractice<Item> implements Iterable<Item> {
    private int N;

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        public Node(Item d) { item = d; next = null;}
    }

    private Node<Item> first;
    private Node<Item> last;

    public QueuePractice() {
        N = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public Item peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return first.item;
    }

    public void enqueue(Item data) {
        Node<Item> oldLast = last;
        last = new Node<Item>(data);
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
        N++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty())
            last = null;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator(first);
    }

    private class LinkedListIterator implements Iterator<Item>{
        private Node<Item> current;

        public LinkedListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext())
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
        QueuePractice<Integer> queue = new QueuePractice<>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);
        for (int i : queue)
            StdOut.print(i + " ");
    }
}
