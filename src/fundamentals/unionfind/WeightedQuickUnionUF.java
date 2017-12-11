package fundamentals.unionfind;

/**
 * union-find�㷨�ļ�Ȩʵ��
 * @author Demons
 *
 */
public class WeightedQuickUnionUF {

	private int[] id; // ����������(�ɴ�������)
	private int[] sz; // �������ڵ�����Ӧ�ķ����Ĵ�С(�ɴ�������)
	private int count; // ��ͨ����������
	
	public WeightedQuickUnionUF(int N){
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i; // ��ʼÿ�����㶼�Ǹ��ڵ�
		}
		for (int i = 0; i < N; i++) {
			sz[i] = 1; // ��¼ĳ�����еĽڵ���
		}
	}
	public int count(){
		return count;
	}
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	private int find(int p){
		// ���������ҵ����ڵ�
		while(p != id[p]){
			p = id[p];
		}
		return p;
	}
	public void union(int p, int q){
		int i = find(p);
		int j = find(q);
		if(i == j) return;
		// ��С���ĸ��ڵ����ӵ������ĸ��ڵ�
		if(sz[i] < sz[j]){
			id[i] = j;
			sz[j] += sz[i];
		}else{
			id[j] = i;
			sz[i] += sz[j];
		}
		count--;
	}
}
