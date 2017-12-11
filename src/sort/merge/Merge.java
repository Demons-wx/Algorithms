package sort.merge;

import edu.princeton.cs.algs4.StdOut;

/**
 * �鲢����
 * 		�Զ����µĹ鲢����
 * ˼�룺
 * 		������ܽ��������������������ܹ�ͨ���鲢��������������������������
 * 		sort()������������ʵ�����ڰ��Ŷ��merge()�������õ���ȷ˳��
 * ʵ�֣�
 * 		Ҫ��������a[lo..hi]���������Ƚ�����Ϊa[lo..mid] �� a[mid+1..hi]�����֣��ֱ�ͨ���ݹ���ý����ǵ�������
 * 		��������������鲢Ϊ���յ���������
 * ���ۣ�
 * 		���ڳ���ΪN���������飬�Զ����µĹ鲢������Ҫ 1/2NlogN �� NlogN �αȽϡ�
 * 		���ڳ���ΪN���������飬�Զ����µĹ鲢���������Ҫ�������� 6NlogN �Ρ�
 * @author Demons
 *
 */
public class Merge {
	
	private static Comparable[] aux; // �鲢����ĸ�������
	
	public static void sort(Comparable[] a){
		aux = new Comparable[a.length];	// һ���Է���ռ�
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		// ������a[lo..hi]����
		if(hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo ,mid); // �ݹ�Ľ���������
		sort(a, mid+1, hi); // �ݹ�Ľ��Ұ������
		if(less(a[mid+1], a[mid])){ // ���Ұ�ߵĵ�һ��Ԫ�ش������ߵ����һ��Ԫ��ʱ����Ϊ�����Ѿ����������鲢
			merge(a, lo, mid, hi); // �鲢���
		}
	}

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
