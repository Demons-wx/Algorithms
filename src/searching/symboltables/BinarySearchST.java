package searching.symboltables;

import edu.princeton.cs.algs4.Queue;

/**
 * 符号表
 * 二分查找(基于有序数组)
 * 思路：
 * 使用一对平行的数组，一个存储键一个存储值。可以保证键有序。使用数组的索引高效的实现get()和其他操作
 * 实现：
 * 核心：rank()方法
 * 如果表中存在该键，rank()应该返回该键的位置，也就是表中小于它的键的数量；
 * 如果表中不存在该键，rank()还是应该返回表中小于它的键的数量。
 *
 * @param <Key>
 * @param <Value>
 * @author Demons
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;

    /**
     * 初始化数组大小
     *
     * @param capacity
     */
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }

    /**
     * 找出该键应该存放的位置
     * 二分查找
     * @param key
     * @return
     */
    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public void put(Key key, Value val) {
        // 查找键，找到则更新否则新建元素
        int i = rank(key);
        // 找到键
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        // 未找到键
        for (int j = N; j > i; j--) {
            // 后面的键和值都向后移动一个位置
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Iterable<Key> keys() {
        return keys(keys[0], keys[N - 1]);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi)) {
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }
}
