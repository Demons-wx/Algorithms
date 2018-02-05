package graph;

/**
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

    }


}
