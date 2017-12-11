package sort.merge;

import edu.princeton.cs.algs4.StdOut;

/**
 * �Ե����ϵĹ鲢����
 * ˼�룺
 * 		�Ե����ϵĹ鲢������α����������飬�����������С���������鲢��
 * 		������Ĵ�Сsz�ĳ�ʼֵΪ1��ÿ�μӱ������һ��������Ĵ�Сֻ���������С��sz��ż����
 * 		��ʱ��Ż����sz(���������szС)��
 * @author Demons
 *
 */
public class MergeBU {
	private static Comparable[] aux; //�鲢����ĸ�������
	
	/* ԭ�ع鲢�ĳ��󷽷� */
	public static void merge(Comparable[] a, int lo, int mid, int hi){
		// ��a[lo..mid] �� a[mid+1..hi] �鲢
		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			// ��a[lo..hi] ���Ƶ� aux[lo..hi]
			aux[k]  = a[k];
		}
		for (int k = lo; k <= hi; k++) { 
			// �鲢��a[lo..hi]
			if(i > mid){
				a[k] = aux[j++]; // �����þ���ȡ�Ұ��Ԫ��
			}else if(j > hi){
				a[k] = aux[i++]; // �Ұ���þ���ȡ����Ԫ��
			}else if(less(aux[j], aux[i])){
				a[k] = aux[j++]; // �Ұ�ߵĵ�ǰԪ��С�����ߵĵ�ǰԪ�أ�ȡ�Ұ��Ԫ��
			}else{
				a[k] = aux[i++]; // �Ұ�ߵĵ�ǰԪ�ش��ڵ������ߵ�ǰԪ�أ�ȡ����Ԫ��
			}
		}
	}
	
	public static void sort(Comparable[] a){
		// ����lgN�������鲢
		int N = a.length;
		aux = new Comparable[N];
		for(int sz = 1; sz < N; sz = sz+sz){ // sz�������С
			for(int lo = 0; lo <= N - sz; lo += sz+sz){ // lo:����������
				merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
			}
		}
	}
	
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
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
//		Integer[] a = {8,4,22,45,1,23,67}; // Integer����ʵ����Comparable�ӿ�
		Character[] a = {'M','E','R','G','E','S','O','R','T','E','X','A','M','P','L','E'};
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
