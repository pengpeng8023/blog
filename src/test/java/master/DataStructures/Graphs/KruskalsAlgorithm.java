package master.DataStructures.Graphs;
//Kruskal算法的Java程序找到一个给定的连通的、无向和加权的图的最小生成树

import java.util.Arrays;

public class KruskalsAlgorithm
{
    // 一个表示图形边缘的类
    class Edge implements Comparable<Edge>
    {
        int src, dest, weight;

        // 比较器函数用于根据权重对边进行排序
        public int compareTo(Edge compareEdge)
        {
            return this.weight-compareEdge.weight;
        }
    };

    // 一个用于表示工会发现的子集的类
    class subset
    {
        int parent, rank;
    };

    int V, E;    // V-> no. of vertices & E->no.of edges
    Edge edge[]; // collection of all edges

    // 创建一个带有V顶点和E边的图形
    KruskalsAlgorithm(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }

    // 一个实用函数，用来找到一个元素的集合
    // (使用路径压缩技术)
    int find(subset subsets[], int i)
    {
        // 找到根并使根成为i的父结点（路径压缩）
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    // 两个x和y的结合的函数
    // (使用联盟排名)
    void Union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // 在高秩树的根下附加较小的秩树
        // (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

            // 如果等级是相同的，那么就把一个作为根，并将它的秩增加一个
        else
        {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // 使用Kruskal算法构建MST的主要功能
    void KruskalMST()
    {
        Edge result[] = new Edge[V];  // Tnis将存储产生的MST
        int e = 0;  // 用于结果的索引变量
        int i = 0;  // 用于排序边的索引变量
        for (i=0; i<V; ++i)
            result[i] = new Edge();

        // 第一步：把所有的边都按照它们的重量顺序排列。如果我们不被允许改变给定的图形，我们可以创建一个边数组的副本
        Arrays.sort(edge);

        // 为创建V s子集分配内存
        subset subsets[] = new subset[V];
        for(i=0; i<V; ++i)
            subsets[i]=new subset();

        // 用单个元素创建V子集
        for (int v = 0; v < V; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;  // 用于选择下一条边的索引

        // 要取的边数等于V-1
        while (e < V - 1)
        {
            // 第二步：选择最小的边。为下一个迭代增加索引
            Edge next_edge = new Edge();
            next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            // 如果包含这条边并不会导致循环，将其包含在结果中并增加下一条边的结果索引
            if (x != y)
            {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
            //别人丢弃next_edge
        }

        // 打印结果的内容以显示构建的MST
        System.out.println("Following are the edges in the constructed MST");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src+" -- "+result[i].dest+" == "+
                    result[i].weight);
    }

    // Driver Program
    public static void main (String[] args)
    {

        /* Let us create following weighted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */
        int V = 4;  // 图中顶点数
        int E = 5;  // 图边数
        KruskalsAlgorithm graph = new KruskalsAlgorithm(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 10;

        // add edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 6;

        // add edge 0-3
        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].weight = 5;

        // add edge 1-3
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 15;

        // add edge 2-3
        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].weight = 4;

        graph.KruskalMST();
    }
}

