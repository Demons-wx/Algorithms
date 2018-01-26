package graph;

import edu.princeton.cs.algs4.Stack;

/**
 * 通过递归的调用dfs()来构造marked[]和edgeTo[]
 * edgeTo[]数组起到搜索中绳子的作用，这个数组可以找到从每个与s连通的顶点回到s的路径，
 * 它会记住该顶点到起点的已知路径中的最后一个顶点，寻着这个顶点就能找到起点
 *
 *
 * @author wangxuan
 * @date 2018/1/26 15:15
 */
public class DepthFirstPaths {

    private boolean[] marked; // 这个顶点上调用过dfs()了吗？
    private int[] edgeTo; // 从起点到一个顶点的已知路径上的最后一个顶点
    private final int s;

    public DepthFirstPaths(Graph G, int s) {

        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;

        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
