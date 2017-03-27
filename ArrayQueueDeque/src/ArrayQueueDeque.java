import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 01.03.2017.
 */
public class ArrayQueueDeque {
    public static void fill(ArrayQueueModule q) {
        for (int i = 1; i <= 10; i++) {
            q.push(i);
        }
    }
    public static void main(String[] args) {
        ArrayQueueModule q = new ArrayQueueModule();
        fill(q);
        int i= 0;
        while (!q.isEmpty()) {
            if (i % 2 == 0) {
                System.out.println(q.remove());
            } else {
                System.out.println(q.dequeue());
            }
            i++;
        }
    }
}
