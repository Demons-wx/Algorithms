package string;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author wangxuan
 * @date 2018/4/27 16:13
 */
public class KeyIndexCountSort {

    public static void sort(Pair[] a, int R) {
        int N = a.length;

        Pair[] aux = new Pair[N];
        int[] count = new int[R + 1];

        // 计算出现的频率
        for (int i = 0; i < N; i++) {
            count[a[i].key() + 1]++;
        }

        // 将频率转换为索引
        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        // 将元素分类
        for (int i = 0; i < N; i++) {
            aux[count[a[i].key()]++] = a[i];
        }

        // 回写
        for (int i = 0; i < N; i++) {
            a[i] = aux[i];
        }
    }

    public static void main(String[] args) {
        int R = 4;
        List<Pair> pairList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            pairList.add(new Pair("name_" + i, new Random().nextInt(R)));
        }

        Pair[] pairs = new Pair[pairList.size()];
        pairs = pairList.toArray(pairs);

        for (Pair pair : pairs) {
            System.out.println(pair);
        }

        sort(pairs, R);

        System.out.println("================排序之后=================");

        for (Pair pair : pairs) {
            System.out.println(pair);
        }
    }
}

class Pair {
    String name;
    int groupNum;

    int key() {
        return groupNum;
    }

    public Pair(String name, int groupNum) {
        this.name = name;
        this.groupNum = groupNum;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "name='" + name + '\'' +
                ", groupNum=" + groupNum +
                '}';
    }
}
