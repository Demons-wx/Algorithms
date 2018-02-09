package graph;

import edu.princeton.cs.algs4.Stack;

/**
 * 通过递归的调用dfs()来构造marked[]和edgeTo[]
 * edgeTo[]数组起到搜索中绳子的作用，这个数组可以找到从每个与s连通的顶点回到s的路径，
 * 它会记住该顶点到起点的已知路径中的最后一个顶点，寻着这个顶点就能找到起点
 *
 * 0 to 0: 0
 * 0 to 1: 0-1
 * 0 to 2: 0-2
 * 0 to 3: 0-6-4-5-3
 * 0 to 4: 0-6-4
 * 0 to 5: 0-6-4-5
 * 0 to 6: 0-6
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

//G:
//0: 6, 2, 1, 5
//1: 0
//2: 0
//3: 5, 4
//4: 5, 6, 3
//5: 3, 4, 0
//6: 0, 4
//
//s: 0
//
//marked = [f, f, f, f, f, f, f]
//edgeTo = [0, 0, 0, 0, 0, 0, 0]
//
//开始：
//v = 0
//marked = [t, f, f, f, f, f, f]
//w = 6
//edgeTo = [0, 0, 0, 0, 0, 0, 0]
//
//进入递归 1层：
//v = 6
//marked = [t, f, f, f, f, f, t]
//w = 0 jump!
//w = 4
//edgeTo = [0, 0, 0, 0, 6, 0, 0]
//
//进入递归 2层：
//v = 4
//marked = [t, f, f, f, t, f, t]
//w = 5
//edgeTo = [0, 0, 0, 0, 6, 4, 0]
//
//进入递归 3层：
//v = 5
//marked = [t, f, f, f, t, t, t]
//w = 3
//edgeTo = [0, 0, 0, 5, 6, 4, 0]
//
//进入递归 4层：
//v = 3
//marked = [t, f, f, t, t, t, t]
//w = 5 jump!
//w = 4 jump!
//遍历结束！
//
//回到第3层：
//v = 5
//w = 4 jump!
//w = 0 jump!
//遍历结束！
//
//回到第2层：
//v = 4
//w = 6 jump!
//w = 3 jump!
//遍历结束！
//
//回到第1层：
//v = 6
//遍历结束！
//
//回到开始：
//v = 0
//w = 2
//edgeTo = [0, 0, 0, 5, 6, 4, 0]
//
//进入第1层：
//v = 2
//marked = [t, f, t, t, t, t, t]
//w = 0 jump!
//遍历结束！
//
//回到开始：
//v = 0
//w = 1
//edgeTo = [0, 0, 0, 5, 6, 4, 0]
//
//进入第1层：
//v = 1
//marked = [t, t, t, t, t, t, t]
//w = 0 jump!
//遍历结束！
//
//回到开始：
//v = 0
//w = 5 jump!
//遍历结束！
//
//构造完毕：
//         [0, 1, 2, 3, 4, 5, 6]
//edgeTo = [0, 0, 0, 5, 6, 4, 0]
//marked = [t, t, t, t, t, t, t]
//
//0 to 0: 0
//0 to 1: 0-1
//0 to 2: 0-2
//0 to 3: 0-6-4-5-3
//0 to 4: 0-6-4
//0 to 5: 0-6-4-5
//0 to 6: 0-6


