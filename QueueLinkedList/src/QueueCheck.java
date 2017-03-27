
/**
 * Created by HP on 14.03.2017.
 */
public class QueueCheck {
    public static void main(String[] args) {
        Queue q = new LinkedQueue();
        q.enqueue(2);
        System.out.println(q.size() + " " + q.dequeue());
    }
}
