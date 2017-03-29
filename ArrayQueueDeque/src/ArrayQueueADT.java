/**
 * Created by HP on 27.02.2017.
 */
// INV: ((size > 0 && a_0, a_1 ... a_(size-1) ! null) || size == 0)
public class ArrayQueueADT {
    private int start = 0;
    private int size = 0;
    private Object[] elements = new Object[2];
    // q!=null
    private static int end(ArrayQueueADT q) {
        if( q.start + q.size < q.elements.length ) {
            return q.start + q.size;
        }
        else {
            return (q.start + q.size) % q.elements.length;
        }
    }
    // Pre: value != null && q!=null
    // Post: size' = size + 1 && (for all 0 <= i < size: q'[i] == q[i]) && q'[size] = value
    public static void enqueue(ArrayQueueADT q, Object value) {
        ensureCapacity(q);
        q.elements[end(q)] = value;
        q.size++;
    }

    // Pre: value != null && q!=null
    // Post: size' = size + 1 && (for all 1 <= i < size': q'[i] == q[i - 1]) && q'[0] == value
    public static void push(ArrayQueueADT q, Object value) {
        ensureCapacity(q);
        q.size++;
        q.start = (q.start - 1 + q.elements.length) % q.elements.length;
        q.elements[q.start] = value;
    }

    // Pre: size > 0 && q!=null
    // Post: size' = size && (for all 0 <= i < size': q'[i] == q[i]) && res == q'[size - 1]
    public static Object peek(ArrayQueueADT q) {
        assert q.size != 0 : "Queue is Empty";
        return q.elements[(end(q) - 1 + q.elements.length) % q.elements.length];
    }

    // Pre: size > 0 && q!=null
    // Post: size' = size - 1 && (for all 0 <= i < size': q'[i] == q[i]) && res == q[size - 1]
    public static Object remove(ArrayQueueADT q) {
        assert q.size != 0 : "Queue is empty";
        q.size--;
        return q.elements[end(q)];
    }

    // Pre: q!= null
    // Post: size' = size && (for all 0 <= i < size': q'[i] == q[i])
    private static void ensureCapacity(ArrayQueueADT q) {
        if (q.size == q.elements.length) {
            Object[] newElements = new Object[q.size * 2];
            if (q.start == 0) {
                System.arraycopy(q.elements, 0, newElements, 0, q.size);
            } else {
                System.arraycopy(q.elements, q.start, newElements, 0, q.size - q.start);
                System.arraycopy(q.elements, 0, newElements, q.size - q.start, end(q));
            }
            q.elements = newElements;
            q.start = 0;
        }
    }

    // Pre: size > 0 && q!= null
    // Post: size' = size && (for all 0 <= i < size': q'[i] == q[i]) && res == q'[0]
    public static Object element(ArrayQueueADT q) {
        assert q.size != 0 : "Queue is empty";
        return q.elements[q.start];
    }

    // Pre: q!= null
    // Post: size' = size && (for all 0 <= i < size': q'[i] == q[i]) && res == size
    public static int size(ArrayQueueADT q) {
        return q.size;
    }

    // Pre: q!= null
    // Post: size' = size && (for all 0 <= i < size': q'[i] == q[i]) && res == (size == 0)
    public static boolean isEmpty(ArrayQueueADT q) {
        return q.size == 0;
    }

    // Pre: q!= null
    // Post: size' == 0
    public static void clear(ArrayQueueADT q) {
        q.start = 0;
        q.size = 0;
    }

    // Pre: size > 0 && q!= null
    // Post: size' = size - 1 && (for all 0 <= i < size': q'[i] == q[i + 1]) && res == q[0]
    public static Object dequeue(ArrayQueueADT q) {
        assert q.size != 0 : "Queue is empty";
        q.size--;
        Object res = q.elements[q.start];
        q.start = (q.start + 1) % q.elements.length;
        return res;
    }
}
