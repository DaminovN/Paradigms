/**
 * Created by HP on 14.03.2017.
 */
public class LinkedQueue extends AbstractQueue implements Queue {

    private class Node {
        Object value;
        Node next;

        public Node(Object value, Node next) {
            assert value != null : "value is null";
            this.value = value;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;

    protected void myEnqueue(Object value) {
        if (head == null) {
            head = new Node(value, null);
            tail = head;
        } else {
            tail.next = new Node(value, null);
            tail = tail.next;
        }
    }

    protected Queue create() {
        return new LinkedQueue();
    }

    public Object element() {
        return head.value;
    }

    protected void myDequeue() {
        head = head.next;
        if (size == 0) {
            myClear();
        }
    }

    protected void myClear() {
        head = null;
        tail = null;
    }

}
