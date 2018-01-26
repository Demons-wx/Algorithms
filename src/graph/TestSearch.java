package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * TestSearch接受由命令行得到的一个输入流的名称和起始节点编号，从输入流中读取
 * 一幅图，用这幅图和给定的起始节点创建一个Search对象，然后用marked()打印出图
 * 中和起点连通的所有顶点。它也调用了count()并打印了图是否连通的(当且仅当搜索能
 * 够标记图中的所有顶点时图才是连通的)。
 *
 * 运行：docs/tinyG.txt 0
 * @author wangxuan
 * @date 2018/1/23 16:33
 */
public class TestSearch {

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        System.out.println(G);
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                StdOut.print(v + " ");
        }
        StdOut.println();

        if (search.count() != G.V())
            StdOut.print("NOT ");
        StdOut.println("connected");
    }
}
