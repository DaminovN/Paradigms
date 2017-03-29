/**
 * Created by HP on 27.02.2017.
 */
// INV: ((size > 0 && a_0, a_1 ... a_(size-1) ! null) || size == 0)
public class ArrayQueue {
    private int start = 0;
    private int size = 0;
    private Object[] elements = new Object[2];
    // true
    private int end() {
        if( start + size < elements.length ) {
            return start + size;
        }
        else {
            return (start + size)%elements.length;
        }
    }
    // Pre: value != null
    // Post: size' = size + 1 && (for all 0 <= i < size: a'[i] == a[i]) && a'[size] = value
    public void enqueue(Object value) {
        ensureCapacity();
        elements[end()] = value;
        size++;
    }

    // Pre: value != null
    // Post: size' = size + 1 && (for all 1 <= i < size': a'[i] == a[i - 1]) && a'[0] == value
    public void push(Object value) {
        ensureCapacity();
        size++;
        start = (start - 1 + elements.length) % elements.length;
        elements[start] = value;
    }

    // Pre: size > 0
    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i]) && res == a'[size - 1]
    public Object peek() {
        assert size != 0 : "Queue is Empty";
        return elements[(end() - 1 + elements.length) % elements.length];
    }

    // Pre: size > 0
    // Post: size' = size - 1 && (for all 0 <= i < size': a'[i] == a[i]) && res == a[size - 1]
    public Object remove() {
        assert size != 0 : "Queue is empty";
        size--;
        Object res = elements[end()];
        return res;
    }

    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i])
    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newElements = new Object[size * 2];
            if (start == 0) {
                System.arraycopy(elements, 0, newElements, 0, size);
            } else {
                System.arraycopy(elements, start, newElements, 0, size - start);
                System.arraycopy(elements, 0, newElements, size - start, end());
            }
            elements = newElements;
            start = 0;
        }
    }

    // Pre: size > 0
    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i]) && res == a'[0]
    public Object element() {
        assert size != 0 : "Queue is empty";
        return elements[start];
    }

    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i]) && res == size
    public int size() {
        return size;
    }

    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i]) && res == (size == 0)
    public boolean isEmpty() {
        return size == 0;
    }

    // Post: size' == 0
    public void clear() {
        start = 0;
        size = 0;
    }

    // Pre: size > 0
    // Post: size' = size - 1 && (for all 0 <= i < size': a'[i] == a[i + 1]) && res == a[0]
    public Object dequeue() {
        assert size != 0 : "Queue is empty";
        size--;
        Object res = elements[start];
        start = (start + 1) % elements.length;
        return res;
    }
}
