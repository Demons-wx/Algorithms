package sort.selection;

import edu.princeton.cs.algs4.StdOut;

/**
 * ѡ������
 * ˼�룺
 * 		���ȣ��ҵ���������С���Ǹ�Ԫ�أ���Σ������������е�һ��Ԫ�ؽ���λ�ã�
 * 		(�����һ��Ԫ�ؾ�����СԪ�أ���ô���ͺ��Լ�����λ��)
 * 		�ٴΣ���ʣ�µ�Ԫ�����ҵ���С��Ԫ�أ�����������ĵڶ���Ԫ�ؽ���λ�á��������ֱ��������������
 * �ص㣺
 * 		����ʱ��������޹ء��Ѿ�����������һ��Ԫ��������е��������õ�����ʱ�侹Ȼһ��
 * 		�����ƶ������ٵġ�ÿ�ν�������ı���������Ԫ�ص�ֵ�����ѡ����������N�ν���������������
 * 		����Ĵ�С�����Թ�ϵ�����ǽ��о��������κ��㷨�����߱�����������
 * 
 * ���ڳ���ΪN�����飬ѡ��������Ҫ��ԼN^2/2�αȽϺ�N�ν�����
 * @author Demons
 *
 */
public class Selection {
	public static void sort(Comparable[] a){
		// �� a[] ����������
		int N = a.length;
		for (int i = 0; i < N; i++) {
			// ��a[i]��a[i+1...N]����С��Ԫ�ؽ���
			int min = i;
			for (int j = i+1; j < N; j++) {
				if(less(a[j],a[min])){
					min = j;
				}
			}
			exch(a, i, min);
		}
	}
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
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
//		String[] a = In.readStrings();
		
		sort(a);
		/**
		 * assert���÷���
		 * 		assert conditon ����condition��һ������Ϊ��(true)�ı��ʽ��������ʽ�Ľ��Ϊtrue����ô����Ϊ�棬�������κ��ж�
		 *		������ʽΪfalse�������ʧ�ܣ�����׳�һ��AssertionError����
		 *		assert condition:expr ð�ź������һ�����ʽ��ͨ�����ڶ���ʧ�ܺ����ʾ��Ϣ
		 * ��Eclipse���������У�����ʱ�����Ǳ�����������ʱ��ѡ��"Run"����Argumentsҳ���е� "VM Arguments" ������-eaѡ������ö���������ʱ�����á�
		 */
		assert isSorted(a):"����δ����";
		show(a);
		
	}
}
