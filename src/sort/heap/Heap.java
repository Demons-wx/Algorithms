package sort.heap;

import edu.princeton.cs.algs4.StdOut;

/**
 * ������
 * ��������Է�Ϊ�����׶Σ�
 * 		�ѵĹ���׶Σ���ԭʼ����������֯���Ž�һ�����С�
 * 		�³�����׶Σ����ݼ���˳��ȡ������Ԫ�أ����õ���������
 * ���ܣ�
 * 		��N��Ԫ�����򣬶�����ֻ��Ҫ����2NlogN+2N�αȽ��Լ�һ������Ľ���
 * @author Demons
 *
 */
public class Heap {

	// ���ܱ�ʵ����
	private Heap(){}
	
	public static void sort(Comparable[] a){
		int N = a.length;
		// ����ѣ������� 
		for (int k = N/2; k >= 1; k--) { // ����ֻ��Ҫɨ��������һ���Ԫ�أ���Ϊ����������СΪ1���Ӷ�
			sink(a, k, N);
		}
		// �³�����
		while(N >1){
			exch(a, 1, N--); // ������Ԫ��a[i]��a[N]�������������Ԫ��ɾ�����������С��ճ�����λ�á�
			sink(a, 1, N); // �޸��ѣ��ٽ���������Ԫ���û�����
		}
	}
	
	private static void sink(Comparable[] a, int k, int N){
		while(2*k <= N){
			int j = 2*k; // j,j+1Ϊk�������ӽ��
			if(j < N && less(a, j, j+1)) j++; // �ҵ��ϴ���ӽ��
			if(!less(a, k, j)) break; // ���a[k]>a[j],�Ѿ��û����
			exch(a, k, j); 
			k = j;
		}
	}
	
	// ����Ѳ�ʹ������ĵ�һ��Ԫ��a[0],����Ҫ�������������1���Խ�a[0]��a[N-1]����
	private static boolean less(Comparable[] a ,int i, int j){
		return a[i-1].compareTo(a[j-1]) < 0;
	}
	
	private static void exch(Object[] a, int i, int j){
		Object swap = a[i-1];
		a[i-1] = a[j-1];
		a[j-1] = swap;
	}
	
	private static boolean less(Comparable v, Comparable w) {
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
		// �ӱ�׼�����ж�ȡ�ַ������������������
		Character[] a = {'M','E','R','G','E','S','O','R','T','E','X','A','M','P','L','E'};
		sort(a);
		assert isSorted(a):"����δ����";
		show(a);
	}
}







