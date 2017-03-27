/**
 * Created by HP on 18.02.2017.
 */
public class BinarySearch {
    // pre : a.length > 0 && (a отсортирован по невозрастанию.) && a[a.length - 1] <= x
    // post : R >= 0 && R == (минимальное значение индекса i, при котором args[i+1] <= args[0])
    static int binSearch(int[] a, int l, int r, int x) { //inv : l <= r && a.length > 0 && (a отсортирован по невозрастанию.) && a[a.length - 1] <= x
        // l <= r && a.length > 0 && (a отсортирован по невозрастанию.) && a[a.length - 1] <= x
        if (l == r) {
            // l == r && a.length > 0 && (a отсортирован по невозрастанию.) && a[a.length - 1] <= x && l == R
            return r;
        }
        int m = (l + r) / 2;
        // l <= r && a.length > 0 && (a отсортирован по невозрастанию.) && a[a.length - 1] <= x && m == (l + r) / 2
        if (a[m] <= x) {
            // l <= r && a.length > 0 && (a отсортирован по невозрастанию.) && a[a.length - 1] <= x && m == (l + r) / 2 && a[m] <= x
            return binSearch(a, l, m, x);
        } else {
            // l <= r && a.length > 0 && (a отсортирован по невозрастанию.) && a[a.length - 1] <= x && m == (l + r) / 2 && !(a[m] <= x)
            return binSearch(a, m + 1, r, x);
        }
    }

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
        a = new int[n];
        // x == (int) args[0] && n == args.length - 1 && n > 0
        for (int i = 0; i < n; i++) { // inv : x == (int) args[0] && n == args.length - 1 && n > 0 && i>=0 && i<n
            a[i] = Integer.parseInt(args[i + 1]);
            // x == (int) args[0] && n == args.length - 1 && n > 0 && i>=0 && i<n && a[i] == (int) args[i + 1]
        }
        // x == (int) args[0] && n == args.length - 1 && n > 0
        int ans = 0;
        // x == (int) args[0] && n == args.length - 1 && n > 0 && ans == 0
        ans = binSearch(a, 0, n, x);
        // x == (int) args[0] && n == args.length - 1 && n > 0 && ans == R
        System.out.println(ans);
    }
}
