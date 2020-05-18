import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueConstant<Item> implements Iterable<Item>{
    private int N;
    Node<Item> first;
    Node<Item> last;

    class Node<Item> {
        private Item item;
        Node<Item> next;
    }

    public QueueConstant(){
        N = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Item peek(){
        if (isEmpty())
            throw new NoSuchElementException();
        return first.item;
    }

    public int size(){
        return N;
    }

    public void enqueue(Item data) {
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = data;
        last.next = null;
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

    private class LinkedListIterator implements Iterator<Item> {
        private  Node<Item> current;
        public LinkedListIterator(Node<Item> first) {
            current = first;
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

    public static void main (String[] args) {
        QueueConstant<String> queue = new QueueConstant<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!s.equals("-"))
                queue.enqueue(s);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("Left on Queue: " + queue.size());
    }
}
