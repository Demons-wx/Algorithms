package string;

import sort.insertion.Insertion;

/**
 * @author wangxuan
 * @date 2018/5/24 15:22
 */
public class InsertionForMSD extends Insertion {

    public static void sort(String[] a, int lo, int hi, int d) {
        // 对前d个字符排序，从a[lo]到a[hi]
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    private static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }
}
