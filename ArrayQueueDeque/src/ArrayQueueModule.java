/**
 * Created by HP on 27.02.2017.
 */
public class ArrayQueueModule {
    private static int end = 0;
    private static int start = 0;
    private static int size = 0;
    private static Object[] elements = new Object[1];

    // Pre: value != null
    // Post: size' = size + 1 && (for all 0 <= i < size: a'[i] == a[i]) && a'[size] = value
    public static void enqueue(Object value) {
        ensureCapacity();
        size++;
        if (end == elements.length) {
            end = 0;
        }
        elements[end] = value;
        end++;
        end %= (elements.length + 1);
    }

    // Pre: value != null
    // Post: size' = size + 1 && (for all 1 <= i < size': a'[i] == a[i - 1]) && a'[0] == value
    public static void push(Object value) {
        ensureCapacity();
        size++;
        start = (start - 1 + elements.length) % elements.length;
        elements[start] = value;
    }

    // Pre: size > 0
    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i]) && res == a'[size - 1]
    public static Object peek() {
        assert size != 0 : "Queue is Empty";
        return elements[(end - 1 + elements.length) % elements.length];
    }

    // Pre: size > 0
    // Post: size' = size - 1 && (for all 0 <= i < size': a'[i] == a[i]) && res == a[size - 1]
    public static Object remove() {
        assert size != 0 : "Queue is empty";
        size--;
        end = (end - 1 + elements.length) % elements.length;
        return elements[end];
    }

    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i])
    private static void ensureCapacity() {
        if (size == elements.length) {
            Object[] newElements = new Object[size * 2];
            if (start == 0) {
                System.arraycopy(elements, 0, newElements, 0, size);
            } else {
                System.arraycopy(elements, start, newElements, 0, size - start);
                System.arraycopy(elements, 0, newElements, size - start, end);
            }
            elements = newElements;
            start = 0;
            end = size;
        }
    }

    // Pre: size > 0
    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i]) && res == a'[0]
    public static Object element() {
        assert size != 0 : "Queue is empty";
        return elements[start];
    }

    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i]) && res == size
    public static int size() {
        return size;
    }

    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i]) && res == (size == 0)
    public static boolean isEmpty() {
        return size == 0;
    }

    // Post: size' == 0
    public static void clear() {
        start = 0;
        end = 0;
        size = 0;
    }

    // Pre: size > 0
    // Post: size' = size - 1 && (for all 0 <= i < size': a'[i] == a[i + 1]) && res == a[0]
    public static Object dequeue() {
        assert size != 0 : "Queue is empty";
        size--;
        Object res = elements[start];
        start = (start + 1) % elements.length;
        return res;
    }
}