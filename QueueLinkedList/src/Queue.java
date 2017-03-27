import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by HP on 14.03.2017.
 */
public interface Queue {
    // I: size >= 0 && (for all 0 <= i < size: a[i] != null)

    // Pre: value != null
    // Post: size' = size + 1 && (for all 0 <= i < size: a'[i] == a[i]) && a'[size] == value
    public void enqueue(Object value);

    // Pre: size > 0
    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i]) && res == a'[size]
    public Object element();

    // Pre: size > 0
    // Post: size' = size - 1 && (for all 0 <= i < size': a'[i] == a[i + 1]) && res == a[0]
    public Object dequeue();

    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i]) && res == size
    public int size();

    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i]) && res == (size == 0)
    public boolean isEmpty();

    // Post: size' == 0
    public void clear();

    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i]) &&
    //                  (for all i | 0 <= i < size, predecate.test(a[i]) == true : a[i] elementof qRes &&
    //             a[i] + "#" + toString(i) elementof qhlp
    //                  (for all 0 <= i < size': hlp[i] == a[i] + "#" + toString(i))
    //                  (for all i,j | 0 <= i < j < size_q : hlp.index(qhlp[i]) < hlp.index(qhlp[j]))
    public Queue filter(Predicate<Object> predicate);

    // Post: size' = size && (for all 0 <= i < size': a'[i] == a[i]) &&
    //                  (for all i | 0 <= i < size: qRes[i] == function.apply(a[i]) ) && size_q = size
    public Queue map(Function<Object,Object> function);
}
