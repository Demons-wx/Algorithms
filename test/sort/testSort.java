package sort;

/**
 * @author wangxuan
 * @date 2017/12/12 11:06
 */
public class testSort {


    private static void insertionSort(Comparable[] a) {
         int N = a.length;
         for (int i = 1; i < N; i++) {
             for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                 exch(a, j, j-1);
             }
         }
    }

    private static void selectionSort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
        }
    }



    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
