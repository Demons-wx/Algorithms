package sort.shell;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
/**
 * ϣ������
 * 		��ǿ��Ĳ�������
 * ˼�룺
 * 		ʹ������������Ϊh��Ԫ�ض�������ģ����������鱻��Ϊh�������顣
 * 		���仰˵��һ��h������������h��������������������֯��һ����ɵ�һ�����顣
 * 		h��ʼֵ > N/3��h=h/3����h�ݼ���1ʱ��������ɡ�
 * @author Demons
 *
 */
public class Shell {

	public static void sort(Comparable[] a){
		// ��a[]������������
		int N = a.length;
		int h = 1;
		while(h < N/3){
			h = 3 * h + 1; // 1,4,13,40,121....
		}
		while(h >= 1){
			// �������Ϊh����
			for (int i = h; i < N; i++) {
				// ��a[i]���뵽a[i-h],a[i-2*h],a[i-3*h]...֮��
				for (int j = i; j >= h && less(a[j], a[j-h]); j-=h) {
					exch(a, j, j-h);
				}
			}
			h = h / 3;
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
		String[] a = In.readStrings();
		sort(a);
		assert isSorted(a);
		show(a);
		
	}
}
