package searching.symboltables;

/**
 * @author wangxuan
 * @date 2018/1/22 16:01
 */
public class SeparateChainingHashST<Key, Value> {

    private int N; // 键值对总数
    private int M; // 散列表大小
    private SequentialSearchST<Key, Value>[] st; // 存放链表对象的数组

    @SuppressWarnings("unchecked")
    public SeparateChainingHashST(int M) {
        // 创建M条链表
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<Key, Value>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }


}
