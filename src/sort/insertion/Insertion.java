package sort.insertion;

import edu.princeton.cs.algs4.StdOut;

/**
 * ��������
 * ˼·��
 * 		����i��Ԫ�ز��뵽ǰ���Ѿ������i-1��Ԫ���С�
 * 		���ȣ���a[i]��a[i-1]�Ƚϣ����a[i] < a[i-1] ����λ��
 * 		�����Ƚϣ�a[i-1]��a[i-2]��С������������������򽻻�ֹͣ����ʱa[]��ǰi��Ԫ���Ѿ�����
 * ���ܣ�
 * 		�������£���ҪN-1�αȽϺ�0�ν�����
 * 		ƽ������£���Ҫ~ N^2/4�αȽϺ�~ N^2/4�ν���
 * 		�����£���Ҫ~ N^2/2�αȽϺ�~ N^2/2�ν���
 * @author Demons
 *
 */
public class Insertion {
	public static void sort(Comparable[] a){
		// ��a[]����������
		int N = a.length;
		for(int i=1; i<N; i++){
			// ��a[i] ���뵽a[i-1]��a[i-2]��a[i-3]...֮��
			for(int j=i; j>0 && less(a[j], a[j-1]); j--){
				exch(a, j, j-1);
			}
		}
	}
	// �Ƚ�
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
	// ����
	private static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	private static void show(Comparable[] a){
		// �ڵ����д�ӡ����
		for(int i=0; i<a.length; i++){
			StdOut.print(a[i]+" ");
		}
		StdOut.println();
	}
	public static boolean isSorted(Comparable[] a){
		// ��������Ԫ���Ƿ�����
		for(int i=1; i<a.length; i++){
			if(less(a[i], a[i-1])){
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		// �ӱ�׼�����ж�ȡ�ַ������������������
		Integer[] a = {8,4,22,45,1,23,67}; // Integer����ʵ����Comparable�ӿ�
		sort(a);
		assert isSorted(a):"����δ����";
		show(a);
	}
}
