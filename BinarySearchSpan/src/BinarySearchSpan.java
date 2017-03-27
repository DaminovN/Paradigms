/**
 * Created by HP on 22.02.2017.
 */
public class BinarySearchSpan {
    // pre : a.length > 0 && (a отсортирован по невозрастанию.)
    // post : R >= 0 R == R1
    static int binSearchSpan(int[] a, int l, int r, int x) { //inv : l <= r && a.length > 0 && (a отсортирован по невозрастанию.) && a[a.length - 1] <= x
        // l <= r && a.length > 0 && (a отсортирован по невозрастанию.) && a[a.length - 1] <= x
        if (l == r) {
            // l == r && a.length > 0 && (a отсортирован по невозрастанию.) && a[a.length - 1] <= x && l == R
            return r;
        }
        int m = (l + r) / 2;
        // l <= r && a.length > 0 && (a отсортирован по невозрастанию.) && a[a.length - 1] <= x && m == (l + r) / 2
        if (a[m] <= x) {
            // l <= r && a.length > 0 && (a отсортирован по невозрастанию.) && a[a.length - 1] <= x && m == (l + r) / 2 && a[m] <= x
            return binSearchSpan(a, l, m, x);
        } else {
            // l <= r && a.length > 0 && (a отсортирован по невозрастанию.) && a[a.length - 1] <= x && m == (l + r) / 2 && !(a[m] <= x)
            return binSearchSpan(a, m + 1, r, x);
        }
    }
    // pre : args.length > 0 && (args отсортирован по невозрастанию начиная с 1 позиции.)
    // post : R1 + 1 == Позиция куда можно вставить args[0] в args начиная с 1ой позиции && R2 == Диапазон
    public static void main(String[] args) {
        if (args.length == 1) { // args.length == 1
            System.out.println("0 0");
            return;
        }
        int[] a;
        int x;
        // x == 0
        x = Integer.parseInt(args[0]);
        int n;
        // x == (int) args[0] && n == 0
        n = args.length - 1;
        // x == (int) args[0] && n == args.length - 1
        a = new int[n];
        for (int i = 0; i < n; i++) { // inv : x == (int) args[0] && n == args.length - 1 && n > 0 && i>=0 && i<n
            a[i] = Integer.parseInt(args[i + 1]);
            // x == (int) args[0] && n == args.length - 1 && n > 0 && i>=0 && i<n && a[i] == (int) args[i + 1]
        }
        // x == (int) args[0] && n == args.length - 1 && n > 0
        int l = 0, r = n;
        // x == (int) args[0] && n == args.length - 1 && n > 0 && l == 0 && r == 0
        r = binSearchSpan(a, 0, n, x);
        // x == (int) args[0] && n == args.length - 1 && n > 0 && l == 0 && r == R1
        if (r == n || a[r] != x) { // x == (int) args[0] && n == args.length - 1 && r == R1 && (r == n || a[r] != x) && n > 0
            System.out.println(r + " 0");
            return;
        }
        // x == (int) args[0] && n == args.length - 1 && l == 0 && r <= n && n > 0 && r == R1
        int res = r;
        // x == (int) args[0] && n == args.length - 1 && l == 0 && r <= n && n > 0 && res == R1
        l = 0;
        r = n;
        // x == (int) args[0] && n == args.length - 1 && l == 0 && r == n && n > 0 && res == R1
        while (l < r) { // inv : x == (int) args[0] && n == args.length - 1 && n > 0 && l >= 0 && r <= n && l < r && res == R1
            int m = (l + r) / 2;
            // x == (int) args[0] && n == args.length - 1 && n > 0 && l >= 0 && r <= n && l < r && res == R1 && m == (l + r) / 2
            if (a[m] < x) { // x == (int) args[0] && n == args.length - 1 && n > 0 && l >= 0 && r <= n && l < r && res == R1 && m == (l + r) / 2 && a[m] < x
                r = m;
                // x == (int) args[0] && n == args.length - 1 && n > 0 && l >= 0 && r' <= n && l <= r' && r' == m && res == R1 && a[m] < x
            } else { // x == (int) args[0] && n == args.length - 1 && n > 0 && l >= 0 && r <= n && l < r && res == R1 && m == (l + r) / 2 && !(a[m] < x)
                l = m + 1;
                // x == (int) args[0] && n == args.length - 1 && n > 0 && l' >= 0 && r <= n && l' <= r && l' == m + 1 && res == R1 && !(a[m] < x)
            }
            // x == (int) args[0] && n == args.length - 1 && n > 0 && l' >= 0 && r' <= n && l' <= r' && res == R1
        }
        // x == (int) args[0] && n == args.length - 1 && n > 0 && l' >= 0 && r' <= n && l' <= r' && res == R1 && R == (r' - res)
        System.out.println(res + " " + (r - res));
    }
}
