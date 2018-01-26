package graph;

/**
 * 深度优先搜索
 *
 * 要搜索一幅图，只需用一个递归方法来遍历所有顶点，在访问其中一个顶点时：
 * - 将它标记为已访问；
 * - 递归地访问它地所有没有被标记过地邻居顶点。
 *
 * @author wangxuan
 * @date 2018/1/23 17:03
 */
public class DepthFirstSearch {

    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }
}
