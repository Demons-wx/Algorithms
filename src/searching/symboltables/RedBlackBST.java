package searching.symboltables;

/**
 * 红黑树的插入算法
 * @author wx
 *
 * @param <Key>
 * @param <Value>
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
	
	private Node root;
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private class Node{
		Key key;	// 键
		Value val; 	// 相关联的值
		Node left, right;	// 左右子树
		int N;	// 这棵树中结点总数
		boolean color;	// 由其父结点指向它的链接的颜色
		
		Node(Key key, Value val, int N, boolean color){
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	
	private boolean isRed(Node h){
		if(h == null)
			return false;
		return h.color == RED;
	}
	
	private Node rotateLeft(Node h){
		Node x = h.right;
		h.right = x.left;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	private Node rotateRight(Node h){
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	private void flipColor(Node h){
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	
	private int size(Node h){
		if(h == null){
			return 0;
		} else{
			return h.N;
		}
	}
	
	/**
	 * 查找key，找到则更新其值，否则为它新键一个结点
	 * @param key
	 * @param val
	 */
	public void put(Key key, Value val){
		root = put(root, key, val);
		root.color = BLACK;
	}
	
	private Node put(Node h, Key key, Value val){
		// 标准的插入操作，和父结点用红链接相连
		if(h == null){	
			return new Node(key, val, 1, RED);
		}
		
		int cmp = key.compareTo(h.key);
		if(cmp < 0){
			h.left = put(h.left, key, val);
		} else if(cmp > 0){
			h.right = put(h.right, key, val);
		} else{
			h.val = val;
		}
		
		// 将含有红色右链接的3-结点(或临时4-结点)左旋转
		if(isRed(h.left) && !isRed(h.right)){
			h = rotateLeft(h);
		}
		// 将临时的4-结点中两条连续红链接中上层链接右旋转
		if(isRed(h.left) && isRed(h.left.left)){
			h = rotateRight(h);
		}
		// 进行颜色转换并将红链接在树中向上传递
		if(isRed(h.left) && isRed(h.right)){
			flipColor(h);
		}
		
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
}

