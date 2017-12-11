package sort.quick;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * �����зֵĿ�������
 * ˼·��
 * 		�������ұ�������һ�Σ�ά��һ��ָ��ltʹ��a[lo..lt-1]�е�Ԫ�ض�С��v��
 * 		һ��ָ��gtʹ��a[gt+1..hi]�е�Ԫ�ض�����v��
 * 		һ��ָ��i��ʹ��a[lt..i-1]�е�Ԫ�ض�����v��
 * 		��whileѭ���е�i==gt����ʱ��a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]������
 * ͼʾ��
 * 		�㷨P189
 * ���ܣ�
 * 		���ڰ��������ظ�Ԫ�ص����飬�����зֽ�����ʱ������Զ������𽵵͵������Լ���
 * 		�������ĵ��ŵĿ��������ھ������������ϵľ������Ӧ���ж�����������ڱȽϵ�
 * 		�����㷨���졣
 * @author Demons
 *
 */
public class Quick3way {
	
	public static void sort(Comparable[] a){
		StdRandom.shuffle(a);	// ��������������
		sort(a, 0, a.length-1);
	}
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return;
		int lt = lo, i = lo+1, gt = hi;
		Comparable v = a[lo]; // �����Ƚϵ�v��Ϊ����ĵ�һ��Ԫ��
		while(i <= gt){
			int cmp = a[i].compareTo(v);
			if(cmp < 0){ // a[i] < v
				exch(a, lt++, i++); // ��a[lt]��a[i]��������lt��i��1��
			}else if(cmp > 0){ // a[i] > v
				exch(a, i, gt--); // ��a[gt]��a[i]��������gt��1��
			}else{ // a[i] = v
				i++; 
			} // ����a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]����
		}
		sort(a, lo, lt-1);
		sort(a, gt+1, hi);
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
