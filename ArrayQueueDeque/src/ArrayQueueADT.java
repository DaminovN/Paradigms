/**
 * Created by HP on 27.02.2017.
 */
public class ArrayQueueADT {
    private int end = 0;
    private int start = 0;
    private int size = 0;
    private Object[] elements = new Object[1];

    // Pre: value != null
    // Post: size' = size + 1 && (for all 0 <= i < size: q'[i] == q[i]) && q'[size] = value
    public static void enqueue(ArrayQueueADT q, Object value) {
        ensureCapacity(q);
        q.size++;
        if (q.end == q.elements.length) {
            q.end = 0;
        }
        q.elements[q.end] = value;
        q.end++;
        q.end %= (q.elements.length + 1);
    }

    // Pre: value != null
    // Post: size' = size + 1 && (for all 1 <= i < size': q'[i] == q[i - 1]) && q'[0] == value
    public static void push(ArrayQueueADT q, Object value) {
        ensureCapacity(q);
        q.size++;
        q.start = (q.start - 1 + q.elements.length) % q.elements.length;
        q.elements[q.start] = value;
    }

    // Pre: size > 0
    // Post: size' = size && (for all 0 <= i < size': q'[i] == q[i]) && res == q'[size - 1]
    public static Object peek(ArrayQueueADT q) {
        assert q.size != 0 : "Queue is Empty";
        return q.elements[(q.end - 1 + q.elements.length) % q.elements.length];
    }

    // Pre: size > 0
    // Post: size' = size - 1 && (for all 0 <= i < size': q'[i] == q[i]) && res == q[size - 1]
    public static Object remove(ArrayQueueADT q) {
        assert q.size != 0 : "Queue is empty";
        q.size--;
        q.end = (q.end - 1 + q.elements.length) % q.elements.length;
        return q.elements[q.end];
    }

    // Post: size' = size && (for all 0 <= i < size': q'[i] == q[i])
    private static void ensureCapacity(ArrayQueueADT q) {
        if (q.size == q.elements.length) {
            Object[] newElements = new Object[q.size * 2];
            if (q.start == 0) {
                System.arraycopy(q.elements, 0, newElements, 0, q.size);
            } else {
                System.arraycopy(q.elements, q.start, newElements, 0, q.size - q.start);
                System.arraycopy(q.elements, 0, newElements, q.size - q.start, q.end);
            }
            q.elements = newElements;
            q.start = 0;
            q.end = q.size;
        }
    }

    // Pre: size > 0
    // Post: size' = size && (for all 0 <= i < size': q'[i] == q[i]) && res == q'[0]
    public static Object element(ArrayQueueADT q) {
        assert q.size != 0 : "Queue is empty";
        return q.elements[q.start];
    }

    // Post: size' = size && (for all 0 <= i < size': q'[i] == q[i]) && res == size
    public static int size(ArrayQueueADT q) {
        return q.size;
    }

    // Post: size' = size && (for all 0 <= i < size': q'[i] == q[i]) && res == (size == 0)
    public static boolean isEmpty(ArrayQueueADT q) {
        return q.size == 0;
    }

    // Post: size' == 0
    public static void clear(ArrayQueueADT q) {
        q.start = 0;
        q.end = 0;
        q.size = 0;
    }

    // Pre: size > 0
    // Post: size' = size - 1 && (for all 0 <= i < size': q'[i] == q[i + 1]) && res == q[0]
    public static Object dequeue(ArrayQueueADT q) {
        assert q.size != 0 : "Queue is empty";
        q.size--;
        Object res = q.elements[q.start];
        q.start = (q.start + 1) % q.elements.length;
        return res;
    }
}
