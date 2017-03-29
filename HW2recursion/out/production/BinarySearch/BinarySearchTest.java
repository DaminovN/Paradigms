/**
 * Created by HP on 18.02.2017.
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] a;
        int x;
        x=Integer.parseInt(args[0]);
        int n;
        n = args.length - 1;
        a=new int[n];
        for (int i=0; i < n; i++) {
            a[i] = Integer.parseInt(args[i + 1]);
        }
        int l, r, m;
        l = 0;
        r = n;
        while (l < r) {
            m = (l + r) / 2;
            System.out.println(l + " " + r + " " + a[m] + " " + x);
            if (a[m] <= x) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        System.out.println(r);
    }
}
