package fundamentals.unionfind;

/**
 * union-find的实现
 * @author Demons
 *
 */
public class QuickFindUF {

	private int[] id; // 分量id(以触点作为索引)
	private int count; // 分量数量
	
	public QuickFindUF(int N){
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
		return id[p];
	}
	
	public void union(int p, int q){
		// 将p和q归并到相同的分量中
		int pID = find(p);
		int qID = find(q);
		
		// 如果p和q已经在相同的分量中，则不需要采取任何操作
		if(pID == qID) return;
		
		// 将p的分量重命名为q的名称
		for(int i=0; i<id.length; i++){
			if(id[i] == pID){
				id[i] = qID;
			}
		}
		count--;
	}
	
}
