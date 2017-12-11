package searching.symboltables;

import edu.princeton.cs.algs4.Queue;

/**
 * ���ڶ���������ķ��ű�
 * �����������
 * 		һ�Ŷ��������(BST)��һ�Ŷ�����������ÿ����㶼����һ��Comparable�ļ�(�Լ��������ֵ)
 * 		��ÿ�����ļ����������������е�������ļ���С����������������ļ���
 * ����(�ڶ���������в���һ�����ĵݹ��㷨)��
 * 		������ǿյģ������δ���У�
 * 		��������ҵļ��͸����ļ���ȣ��������У�
 * 		�������Ǿ͵ݹ�����ʵ��������м������ҡ���������ҵļ���С��ѡ�����������ϴ���ѡ����������
 * @author Demons
 *
 */
public class BST<Key extends Comparable<Key>, Value> {

	private Node root; // ����������ĸ��ڵ�
	
	private class Node{
		private Key key; // ��
		private Value val; // ֵ
		private Node left, right; // ָ������������
		private int N; // �Ըý��Ϊ���������еĽ������
		
		public Node(Key key, Value val, int N){
			this.key = key; this.val = val; this.N = N;
		}
	}
	
	public int size(){
		return size(root);
	}
	
	private int size(Node x){
		if(x == null) return 0;
		else return x.N;
	}
	
	public Value get(Key key){
		return get(root, key);
	}
	
	public boolean contains(Key key){
		return get(key) != null;
	}
	
	private Value get(Node x, Key key){
		// ����xΪ�����������в��Ҳ�����key��Ӧ��ֵ
		// ����Ҳ���������null
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0){
			return get(x.left, key);
		}else if(cmp > 0){
			return get(x.right, key);
		}else{
			return x.val;
		}
	}
	
	public void put(Key key, Value val){
		// ����key���ҵ����������ֵ������Ϊ������һ���µĽ��
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val){
		// ���key��������xΪ���������������������ֵ
		// ������key��valΪ��ֵ�Ե��½����뵽��������
		if(x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if(cmp < 0){
			x.left = put(x.left, key, val);
		}else if(cmp > 0){
			x.right = put(x.right, key, val);
		}else{
			x.val = val;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	/**
	 * ��С��
	 * 	���������������Ϊ�գ���ôһ�Ŷ������������С�ļ����Ǹ���㣻
	 * 	��������ӷǿգ���ô������С�ļ���������������С�ļ���
	 * @return
	 */
	public Key min(){
		return min(root).key;
	}
	
	private Node min(Node x){
		if(x.left == null) return x;
		return min(x.left);
	}
	/**
	 * ����
	 * @return
	 */
	public Key max(){
		return max(root).key;
	}
	
	private Node max(Node x){
		if(x.right == null) return x;
		return max(x.right);
	}
	
	/**
	 * С�ڵ���key������(floor)
	 * 		���������keyС�ڶ���������ĸ��ڵ�ļ�����ôfloorһ���ڸ������������У�
	 * 		���������key���ڶ���������ĸ����ļ�����ôֻ�е�������������д���С�ڵ���key�Ľ��ʱ
	 * 			С�ڵ���key�ļ��Ż�������������������������С�ڵ���key��������
	 * 		��key�ȶ�������������н��ļ���Сʱ������null
	 * @param key
	 * @return
	 */
	public Key floor(Key key){
		Node x = floor(root, key);
		if(x == null)
			return null;
		return x.key;
	}
	
	private Node floor(Node x, Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0) return x;
		if(cmp < 0) return floor(x.left, key);
		// ����������д���С�ڵ���key�Ľ��ʱ���ش˽�㣬���򷵻ظ����
		Node t = floor(x.right, key);
		if(t != null) return t;
		else return x;
	}
	
	/**
	 * ���ڵ���key����С��
	 * @param key
	 * @return
	 */
	public Key cell(Key key){
		Node x = cell(root, key);
		if(x == null)
			return null;
		return x.key;
	}
	
	private Node cell(Node x, Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0) return x;
		if(cmp > 0) return cell(x.right, key);
		Node t = cell(x.left, key);
		if(t != null) return t;
		else return x;
	}
	
	/**
	 * �ҵ�����Ϊk�ļ�
	 * ����������еĽ����t����k�����Ǿͼ���(�ݹ��)���������в�������Ϊk�ļ���
	 * ���t����k�����Ǿͷ��ظ�����еļ���
	 * ���tС��k�����Ǿ͵ݹ�����������в�������Ϊ(k-t-1)�ļ���
	 * @param k
	 * @return
	 */
	public Key select(int k){
		return select(root, k).key;
	}
	private Node select(Node x, int k){
		// ��������Ϊk�Ľ��
		if(x == null) return null;
		int t = size(x.left); // ��x.leftΪ���������еĽ�����
		if(t > k) return select(x.left, k);
		else if(t < k) return select(x.right, k-t-1);
		else return x;
	}
	
	/**
	 * ���ظ�����������
	 * 
	 * @param key
	 * @return
	 */
	public int rank(Key key){
		return rank(root, key);
	}
	private int rank(Node x, Key key){
		// ������xΪ���ڵ��������С��x.key�ļ�������
		if(x == null) return 0;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) return rank(x.left, key);
		else if(cmp > 0) return rank(x.right, key) + size(x.left) + 1; // �������Ľ�����+���ڵ�+��������С��key�ĸ���
		else return size(x.left);
	}
	
	/**
	 * ���ϵ�������ڵ��������ֱ������һ�������ӡ�
	 * Ȼ��ָ��ý�������ָ��ý�����������
	 * ��ʱ���Ѿ�û���κ�����ָ��Ҫ��ɾ���Ľ�㣬������ᱻ�����ռ����������
	 */
	public void deleteMin(){
		root = deleteMin(root);
	}
	private Node deleteMin(Node x){
		if(x.left == null) return x.right;
		x.left = deleteMin(x.left); // �ݹ�������
		x.N = size(x.left) + size(x.right) + 1; // ɾ����֮���µĽ����
		return x;
	}
	
	/**
	 * ɾ������������е������
	 */
	public void deleteMax(){
		root = deleteMax(root);
	}
	private Node deleteMax(Node x){
		if(x.right == null) return x.left;
		x.right = deleteMax(x.right); // �ݹ�������
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	/**
	 * ɾ��
	 * ��ɾ�����x�������ĺ�̽�������λ��
	 * - ��ָ�򼴽���ɾ���Ľ������ӱ���Ϊt��
	 * - ��xָ�����ĺ�̽��min(t.right)��
	 * - ��x��������(ԭ��ָ��һ�����н�㶼����x.key�Ķ��������)ָ��deleteMin(t.right),Ҳ������ɾ�������н���Զ�����x.key���Ӷ����������
	 * - ��x��������(��Ϊ��)��Ϊt.left(�������еļ���С�ڱ�ɾ���Ľ������ĺ�̽��)��
	 * @param key
	 */
	public void delete(Key key){
		root = delete(root, key);
	}
	private Node delete(Node x, Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) x.left = delete(x.left, key);
		else if(cmp > 0) x.right = delete(x.right, key);
		else{
			if(x.right == null) return x.left;
			if(x.left == null) return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	/**
	 * ����������ķ�Χ����
	 * ���ظ�����Χ�ڵļ�
	 * @return
	 */
	public Iterable<Key> keys(){
		return keys(min(), max());
	}
	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}
	private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
		if(x == null) return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if(cmplo < 0) keys(x.left, queue, lo, hi);
		if(cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
		if(cmphi > 0) keys(x.right, queue, lo, hi);
	}
}
