/**
 * Created by HP on 27.02.2017.
 */
public class ArrayQueue extends AbstractQueue implements Queue {
    private int end = 0;
    private int start = 0;
    private Object[] elements = new Object[1];

    protected void myEnqueue(Object value) {
        ensureCapacity();
        if (end == elements.length) {
            end = 0;
        }
        elements[end] = value;
        end++;
    }

    private void ensureCapacity() {
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

    public Object element() {
        return elements[start];
    }

    protected Queue create() {
        return new ArrayQueue();
    }

    protected void myClear() {
        start = 0;
        end = 0;
    }

    protected void myDequeue() {
        start = (start + 1) % elements.length;
    }
}
