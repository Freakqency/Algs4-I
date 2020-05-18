import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackPractice<Item> implements Iterable<Item> {
    private int N;
    private Node<Item> top;

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        public Node(Item i) {item = i;}
    }

    public StackPractice() {
        N = 0;
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return N;
    }

    public Item peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = top.item;
        return item;
    }

    public void push(Item data) {
        Node<Item> oldTop = top;
        top = new Node<>(data);
        top.next = oldTop;
        N++;
    }

    public Item pop() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = top.item;
        top = top.next;
        N--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator(top);
    }

    private class LinkedListIterator implements Iterator<Item> {
        private Node<Item> current;
        public LinkedListIterator (Node<Item> top) {
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
        StackPractice<String> stack = new StackPractice<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!s.equals("-"))
                stack.push(s);
            else
                StdOut.print(stack.pop() + " ");
        }
        StdOut.println(stack.size() + "left in stack" );
    }
}
