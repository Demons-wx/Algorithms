package string;

/**
 * 低位优先的字符串排序
 * @author wangxuan
 * @date 2018/4/27 17:39
 */
public class LSD {

    public static void sort(String[] a, int W) {
        // 通过前W个字符将a[]排序
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        // 根据第d个字符用键索引计数法排序
        for (int d = W - 1; d >= 0; d--) { // 遍历所有要排序的字符
            // 计算出现频率
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) { // 遍历所有字符串
                count[a[i].charAt(d) + 1]++;
            }
            // 将频率转换为索引
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            // 将元素分类
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            // 回写
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }
}
