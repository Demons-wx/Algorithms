package graph;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 0 to 0: 0
 * 0 to 1: 0-1
 * 0 to 2: 0-2
 * 0 to 3: 0-5-3
 * 0 to 4: 0-6-4
 * 0 to 5: 0-5
 * 0 to 6: 0-6
 *
 * @author wangxuan
 * @date 2018/1/29 16:31
 */
public class BreadthFirstPaths {

    private boolean[] marked; // 到达该顶点的最短路径已知吗？
    private int[] edgeTo; // 到达该顶点的已知路径上的最后一个顶点
    private final int s; // 起点

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true; // 标记起点
        queue.enqueue(s); // 将它加入队列
        while (!queue.isEmpty()) {
            int v = queue.dequeue(); // 从队列中删去下一顶点
            for (int w : G.adj(v)) {
                if (!marked[w]) {// 对于每个未被标记的相邻顶点
                    edgeTo[w] = v; // 保存最短路径的最后一条边
                    marked[w] = true; // 标记它，因为最短路径已知
                    queue.enqueue(w); // 并将它添加到队列中
                }
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
//marked = [t, f, f, f, f, f, f]
//queue = [0]
//
//进入循环：
//v = 0
//queue = []
//w = 6
//edgeTo = [0, 0, 0, 0, 0, 0, 0]
//marked = [t, f, f, f, f, f, t]
//queue = [6]
//
//w= 2
//edgeTo = [0, 0, 0, 0, 0, 0, 0]
//marked = [t, f, t, f, f, f, t]
//queue = [6, 2]
//
//w= 1
//edgeTo = [0, 0, 0, 0, 0, 0, 0]
//marked = [t, t, t, f, f, f, t]
//queue = [6, 2, 1]
//
//w= 5
//edgeTo = [0, 0, 0, 0, 0, 0, 0]
//marked = [t, t, t, f, f, t, t]
//queue = [6, 2, 1, 5]
//
//弹出6:
//v = 6
//queue = [2, 1, 5]
//
//w = 0 jump!
//w = 4
//edgeTo = [0, 0, 0, 0, 6, 0, 0]
//marked = [t, t, t, f, t, t, t]
//queue = [2, 1, 5, 4]
//
//弹出2, 1, 5：
//v = 5
//queue = [4]
//
//w = 3
//edgeTo = [0, 0, 0, 5, 6, 0, 0]
//marked = [t, t, t, t, t, t, t]
//queue = [4, 3]
//
//弹出4, 3  ok!
//
//         [0, 1, 2, 3, 4, 5, 6]
//edgeTo = [0, 0, 0, 5, 6, 0, 0]
//marked = [t, t, t, t, t, t, t]
//
//0 to 0: 0
//0 to 1: 0-1
//0 to 2: 0-2
//0 to 3: 0-5-3
//0 to 4: 0-6-4
//0 to 5: 0-5
//0 to 6: 0-6



