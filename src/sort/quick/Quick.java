package sort.quick;

import sort.heap.Heap;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import fundamentals.algorithmsanalysis.Stopwatch;

/**
 * ��������
 * ˼·��
 * 		ͨ���ݹ�ĵ����з���ʵ������
 * ʵ�֣�
 * 		1����ȡa[lo]��Ϊ�з�Ԫ��v�����Ǹ����ᱻ�Ŷ���Ԫ�ء�
 * 		2����ѭ���У�a[i]С��vʱ����������i��a[j]����vʱ�����Ǽ�Сj��
 * 		3��������a[i]����v��a[j]С��vʱ�����ǽ���a[i]��a[j]����֤i����Ԫ�ض�������v��j�Ҳ��Ԫ�ض���С��V��
 * 		4����ָ������ʱ�����ǽ���a[lo]��a[j],�зֽ����������з�ֵ��������a[j]���ˡ�
 * ���ܣ�
 * 		ʱ�临�Ӷȣ�NlogN
 * @author Demons
 *
 */
public class Quick {
	
	public static void sort(Comparable[] a){
//		StdRandom.shuffle(a);	// ��������������
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return; // ��ֹ�ݹ�
		int j = partition(a, lo, hi);	// �з�
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}
	
	private static int partition(Comparable[] a, int lo, int hi){
		int i = lo, j = hi+1;	// ����ɨ��ָ��
		Comparable v = a[lo];	// �з�Ԫ��
		while(true){
			// ɨ�����ң����ɨ���Ƿ����������Ԫ��
			while(less(a[++i], v)){
				if(i == hi) break;
			}
			while(less(v, a[--j])){
				if(j == lo) break;
			}
			if(i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);	// ��v=a[j]������ȷ��λ��
		return j;
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
		/*for(int i=0; i<5; i++){
			StdOut.print(a[i]+" ");
		}*/
		for (int i = a.length-1; i > a.length-101; i--) {
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
//		Integer[] a = {1,2,3,4,5,6,7,8}; // Integer����ʵ����Comparable�ӿ�
		int N = 10000000;
		Double[] a = new Double[N];
		for (int i = 0; i < N; i++) {
			a[i] = StdRandom.uniform();
		}
		Stopwatch time = new Stopwatch();
		sort(a);
		System.out.println("��ʱ��" + time.elapsdTime());
		assert isSorted(a):"����δ����";
		show(a);
	}
}
