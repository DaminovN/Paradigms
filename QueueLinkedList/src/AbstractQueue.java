import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by HP on 14.03.2017.
 */
public abstract class AbstractQueue implements Queue {
    protected int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected abstract void myClear();

    public void clear() {
        size = 0;
        myClear();
    }

    protected abstract void myDequeue();

    public Object dequeue() {
        size--;
        Object res = element();
        myDequeue();
        return res;
    }

    protected abstract Queue create();

    private Queue helper(BiConsumer<Queue, Object> myFunction) {
        Queue q = create();
        for (int i = 0; i < size; i++) {
            Object element = dequeue();
            myFunction.accept(q, element);
            enqueue(element);
        }
        return q;
    }

    public Queue filter(Predicate<Object> predicate) {
        return helper((q, element) -> {
                    if (predicate.test(element)) {
                        q.enqueue(element);
                    }
                });
    }

    public Queue map(Function<Object, Object> function) {
        return helper((q, element) -> q.enqueue(function.apply(element)));
    }

    protected abstract void myEnqueue(Object value);


    public void enqueue(Object value) {
        myEnqueue(value);
        size++;
    }
}
