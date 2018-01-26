package searching.symboltables;

import edu.princeton.cs.algs4.Queue;

/**
 * 基于二叉查找树的符号表
 * 二叉查找树：
 * 一颗二叉查找树(BST)是一颗二叉树，其中每个结点都含有一个Comparable的键(以及相关联的值)
 * 且每个结点的键都大于其左子树中的任意结点的键而小于右子树的任意结点的键。
 * 查找(在二叉查找树中查找一个键的递归算法)：
 * 如果树是空的，则查找未命中；
 * 如果被查找的键和根结点的键相等，查找命中；
 * 否则我们就递归的在适当的子树中继续查找。如果被查找的键较小就选择左子树，较大则选择右子树。
 *
 * @author Demons
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root; // 二叉查找树的根节点

    private class Node {
        private Key key; // 键
        private Value val; // 值
        private Node left, right; // 指向子树的链接
        private int N; // 以该结点为根的子树中的结点总数

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private Value get(Node x, Key key) {
        // 在以x为根结点的子树中查找并返回key对应的值
        // 如果找不到，返回null
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    public void put(Key key, Value val) {
        // 查找key，找到则更新它的值，否则为它创建一个新的结点
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        // 如果key存在于以x为根结点的子树中则更新它的值
        // 否则将以key和val为键值对的新结点插入到该子树中
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * 最小键
     * 如果根结点的左链接为空，那么一颗二叉查找树中最小的键就是根结点；
     * 如果左链接非空，那么树种最小的键就是左子树中最小的键。
     *
     * @return
     */
    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    /**
     * 最大键
     *
     * @return
     */
    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    /**
     * 小于等于key的最大键(floor)
     * 如果给定的key小于二叉查找树的根节点的键，那么floor一定在根结点的左子树中；
     * 如果给定的key大于二叉查找树的根结点的键，那么只有当根结点右子树中存在小于等于key的结点时
     * 小于等于key的键才会出现在右子树，否则根结点就是小于等于key的最大键。
     * 当key比二叉查找树中所有结点的键都小时，返回null
     *
     * @param key
     * @return
     */
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        // 如果右子树中存在小于等于key的结点时返回此结点，否则返回根结点
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * 大于等于key的最小键
     *
     * @param key
     * @return
     */
    public Key cell(Key key) {
        Node x = cell(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node cell(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return cell(x.right, key);
        Node t = cell(x.left, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * 找到排名为k的键
     * 如果左子树中的结点数t大于k，我们就继续(递归的)在左子树中查找排名为k的键；
     * 如果t等于k，我们就返回根结点中的键；
     * 如果t小于k，我们就递归的在右子树中查找排名为(k-t-1)的键。
     *
     * @param k
     * @return
     */
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        // 返回排名为k的结点
        if (x == null) return null;
        int t = size(x.left); // 以x.left为根的子树中的结点个数
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    /**
     * 返回给定键的排名
     *
     * @param key
     * @return
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        // 返回以x为根节点的子树中小于x.key的键的数量
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return rank(x.right, key) + size(x.left) + 1; // 左子树的结点个数+根节点+右子树中小于key的个数
        else return size(x.left);
    }

    /**
     * 不断的深入根节点的左子树直到遇见一个空链接。
     * 然后将指向该结点的链接指向该结点的右子树。
     * 此时，已经没有任何链接指向要被删除的结点，因此它会被垃圾收集器清理掉。
     */
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left); // 递归左子树
        x.N = size(x.left) + size(x.right) + 1; // 删除了之后新的结点数
        return x;
    }

    /**
     * 删除二叉查找树中的最大结点
     */
    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right); // 递归右子树
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * 删除
     * 在删除结点x后用它的后继结点填补它的位置
     * - 将指向即将被删除的结点的链接保存为t；
     * - 将x指向它的后继结点min(t.right)；
     * - 将x的右链接(原本指向一颗所有结点都大于x.key的二叉查找树)指向deleteMin(t.right),也就是在删除后所有结点仍都大于x.key的子二叉查找树；
     * - 将x的左链接(本为空)设为t.left(其下所有的键都小于被删除的结点和它的后继结点)。
     *
     * @param key
     */
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * 二叉查找树的范围查找
     * 返回给定范围内的键
     *
     * @return
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }
}
