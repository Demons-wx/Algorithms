package fundamentals.unionfind;

public class QuickUnionUF {
	private int[] id; // ����id(�Դ�����Ϊ����)
	private int count; // ��������
	
	public QuickUnionUF(int N){
		// ��ʼ������id����
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	public int count(){
		return count;
	}
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	public int find(int p){
		// �ҳ�����������
		while(p != id[p]){
			p = id[p];
		}
		return p;
	}
	public void unoin(int p, int q){
		// ��p��q�ĸ��ڵ�ͳһ
		int pRoot = find(p);
		int qRoot = find(q);
		if(pRoot == qRoot) return;
		id[pRoot] = qRoot;
		count--;
	}
}
