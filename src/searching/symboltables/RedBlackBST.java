package searching.symboltables;

/**
 * ������Ĳ����㷨
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
		Key key;	// ��
		Value val; 	// �������ֵ
		Node left, right;	// ��������
		int N;	// ������н������
		boolean color;	// ���丸���ָ���������ӵ���ɫ
		
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
	 * ����key���ҵ��������ֵ������Ϊ���¼�һ�����
	 * @param key
	 * @param val
	 */
	public void put(Key key, Value val){
		root = put(root, key, val);
		root.color = BLACK;
	}
	
	private Node put(Node h, Key key, Value val){
		// ��׼�Ĳ���������͸�����ú���������
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
		
		// �����к�ɫ�����ӵ�3-���(����ʱ4-���)����ת
		if(isRed(h.left) && !isRed(h.right)){
			h = rotateLeft(h);
		}
		// ����ʱ��4-����������������������ϲ���������ת
		if(isRed(h.left) && isRed(h.left.left)){
			h = rotateRight(h);
		}
		// ������ɫת���������������������ϴ���
		if(isRed(h.left) && isRed(h.right)){
			flipColor(h);
		}
		
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
}

