package searching.symboltables;

import edu.princeton.cs.algs4.Queue;

/**
 * ���ű�
 * ���ֲ���(������������)
 * ˼·��
 * 		ʹ��һ��ƽ�е����飬һ���洢��һ���洢ֵ�����Ա�֤������ʹ�������������Ч��ʵ��get()����������
 * ʵ�֣�
 * 		���ģ�rank()����
 * 		������д��ڸü���rank()Ӧ�÷��ظü���λ�ã�Ҳ���Ǳ���С�����ļ���������
 * 		������в����ڸü���rank()����Ӧ�÷��ر���С�����ļ���������
 * @author Demons
 *
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

	private Key[] keys;
	private Value[] vals;
	private int N;
	
	/**
	 * ��ʼ�������С
	 * @param capacity
	 */
	public BinarySearchST(int capacity){
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}
	
	public int size(){
		return N;
	}
	
	public Value get(Key key){
		if(isEmpty()){
			return null;
		}
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) ==0){
			return vals[i];
		}else{
			return null;
		}
	}
	
	/**
	 * �ҳ��ü�Ӧ�ô�ŵ�λ��
	 * @param key
	 * @return
	 */
	public int rank(Key key){
		int lo = 0, hi = N-1;
		while(lo <= hi){
			int mid = lo + (hi -lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp < 0){
				hi = mid -1;
			}else if(cmp > 0){
				lo = mid + 1;
			}else{
				return mid;
			}
		}
		return lo;
	}

	public void put(Key key, Value val){
		// ���Ҽ����ҵ�����·����½�Ԫ��
		int i = rank(key);
		// �ҵ���
		if(i < N && keys[i].compareTo(key) == 0){
			vals[i] = val;
			return;
		}
		// δ�ҵ���
		for(int j = N; j > i; j--){
			// ����ļ���ֵ������ƶ�һ��λ��
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public boolean contains(Key key){
		return get(key) != null;
	}
	
	public Iterable<Key> keys(){
		return keys(keys[0], keys[N-1]);
	}
	
	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> q = new Queue<>();
		for(int i = rank(lo); i < rank(hi); i++){
			q.enqueue(keys[i]);
		}
		if(contains(hi)){
			q.enqueue(keys[rank(hi)]);
		}
		return q;
	}
}
