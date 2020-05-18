public class LinkedListPractice {
    private Node head;

    static class Node {
        int data;
        Node next;
        Node(int d) {data = d; next = null;}
    }


    public void InsertAtBeginning(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void Print(){
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        LinkedListPractice linkedListPractice = new LinkedListPractice();
        linkedListPractice.InsertAtBeginning(10);
        linkedListPractice.InsertAtBeginning(20);
        linkedListPractice.InsertAtBeginning(30);
        linkedListPractice.InsertAtBeginning(40);
        linkedListPractice.InsertAtBeginning(50);
        linkedListPractice.Print();
    }
}
