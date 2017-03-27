/**
 * Created by HP on 18.02.2017.
 */
public class BinarySearch {
    // pre : args.length > 0 && (args отсортирован по невозрастанию начиная с 1 позиции.) && args[args.length - 1] <= args[0]
    // post : R >= 0 && R == (минимальное значение индекса i, при котором args[i+1] <= args[0])
    public static void main(String[] args) {
        int[] a;
        int x;
        // x == 0
        x = Integer.parseInt(args[0]);
        // x == (int) args[0]
        int n;
        // x == (int) args[0] && n == 0
        n = args.length - 1;
        // x == (int) args[0] && n == args.length - 1 && n > 0
        a = new int[n];
        // x == (int) args[0] && n == args.length - 1 && n > 0
        for (int i = 0; i < n; i++) { // inv : x == (int) args[0] && n == args.length - 1 && n > 0 && i>=0 && i<n
            a[i] = Integer.parseInt(args[i + 1]);
            // x == (int) args[0] && n == args.length - 1 && n > 0 && i>=0 && i<n && a[i] == (int) args[i + 1]
        }
        // x == (int) args[0] && n == args.length - 1 && n > 0
        int l = 0, r = 0, m = 0;
        // x == (int) args[0] && n == args.length - 1 && n > 0 && l == 0 && r == 0 && m == 0
        r = n;
        while (l < r) { // inv :  x == (int) args[0] && n == args.length - 1 && n > 0 && l >= 0 && r <= n && l < r
            m = (l + r) / 2;
            // x == (int) args[0] && n == args.length - 1 && n > 0 && l >= 0 && r <= n && l < r && m == (l + r) / 2
            if (a[m] <= x) {
                // a[m] <= x && x == (int) args[0] && n == args.length - 1 && n > 0 && l >= 0 && r <= n && l < r && m == (l + r) / 2
                r = m;
                // a[m] <= x && x == (int) args[0] && n == args.length - 1 && n > 0 && l >= 0 && r' <= n && l <= r' && r' == m
            } else {
                // !(a[m] <= x) && x == (int) args[0] && n == args.length - 1 && n > 0 && l >= 0 && r <= n && l < r && m == (l + r) / 2
                l = m + 1;
                // !(a[m] <= x) && x == (int) args[0] && n == args.length - 1 && n > 0 && l' >= 0 && r <= n && l' <= r && l' == m + 1
            }
            // x == (int) args[0] && n == args.length - 1 && n > 0 && l' >= 0 && r' <= n && l' <= r' 
        }
        // x == (int) args[0] && n == args.length - 1 && n > 0 && l' >= 0 && r' <= n && r' == R
        System.out.println(r);
    }
}
