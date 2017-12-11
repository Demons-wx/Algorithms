package sort.heap.maxPQ;

/**
 * ���ڶѵ����ȶ���
 * 
 * ���ȶ��У�
 * 		֧�����ֲ��������ݽṹ��ɾ�����Ԫ�غͲ���Ԫ�ء�
 * ������
 * 		��һ�Ŷ�������ÿ����㶼���ڵ������������ӽ��ʱ��������Ϊ������
 * ����ѣ�
 * 		��һ���ܹ��ö��������ȫ�����������Ԫ�أ����������а��ղ㼶�洢(��ʹ������ĵ�һ��λ��)��
 * ���ʣ�
 * 		λ��K�Ľ��ĸ�����λ��ΪK/2�����������ӽ���λ�÷ֱ���2K��2K+1
 * ���ܣ�
 * 		����Ԫ�أ�logN
 * 		ɾ�����Ԫ�أ�logN
 * @author Demons
 *
 * @param <Key>
 */
public class MaxPQ<Key extends Comparable<Key>> {

	private Key[] pq; // ���ڶѵ���ȫ������
	private int N = 0; // �洢��pq[1..N]�У�pq[0]û��ʹ��
	
	/**
	 * ����һ���������ΪmaxN�����ȶ���
	 * @param maxN
	 */
	public MaxPQ(int maxN){
		pq = (Key[]) new Comparable[maxN+1];
	}
	
	/**
	 * ���ض����Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty(){
		return N == 0;
	}
	
	/**
	 * ���ض����е�Ԫ�ظ���
	 * @return
	 */
	public int size(){
		return N;
	}
	
	/**
	 * �����ȶ����в���һ��Ԫ��
	 * @param v
	 */
	public void insert(Key v){
		pq[++N] = v;
		swim(N);
	}
	
	/**
	 * ɾ�����������Ԫ��
	 * @return
	 */
	public Key delMax(){
		Key max = pq[1]; // �Ӹ��ڵ�õ����Ԫ��
		exch(1, N--); // ��������һ����㽻��,������
		pq[N+1] = null; // ��ֹԽ��
		sink(1);
		return max;
	}
	
	/**
	 * �Ƚ�
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean less(int i, int j){
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	/**
	 * ����
	 * @param i
	 * @param j
	 */
	private void exch(int i, int j){
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	/**
	 * �������ϵĶ�����(�ϸ�)
	 * ��������򻯵�״̬��Ϊĳ������ñ����ĸ�������������ƣ���ô����
	 * ��Ҫͨ�������������ĸ�������޸��ѡ�
	 * @param k
	 */
	private void swim(int k){
		while(k > 1 && less(k/2, k)){
			exch(k/2, k);
			k = k/2;
		}
	}
	
	/**
	 * �������µĶ�����(�³�)
	 * ͨ���������������ӽ���еĽϴ������ָ��ѡ�
	 * @param k
	 */
	private void sink(int k){
		while(2*k <= N){
			int j = 2*k;
			if(j < N && less(j, j+1)) j++;
			if(!less(k,j)) break;
			exch(k, j);
			k = j;
		}
	}
}
