package fundamentals.unionfind;

public class QuickUnionUF {
	private int[] id; // 分量id(以触点作为索引)
	private int count; // 分量数量
	
	public QuickUnionUF(int N){
		// 初始化分量id数组
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
		// 找出分量的名称
		while(p != id[p]){
			p = id[p];
		}
		return p;
	}
	public void unoin(int p, int q){
		// 将p和q的根节点统一
		int pRoot = find(p);
		int qRoot = find(q);
		if(pRoot == qRoot) return;
		id[pRoot] = qRoot;
		count--;
	}
}
