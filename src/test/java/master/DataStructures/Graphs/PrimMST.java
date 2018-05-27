package master.DataStructures.Graphs;
// 一个用于Prim最小生成树（MST）算法的Java程序。图的邻接矩阵表示

public class PrimMST
{
    // 图中顶点数
    private static final int V=5;

    // 用最小键查找顶点的实用函数值，从MST中尚未包含的顶点集
    int minKey(int key[], Boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min)
            {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    // 一个实用程序函数，用来打印存储在父本中的构造的MST
    void printMST(int parent[], int n, int graph[][])
    {
        System.out.println("Edge   Weight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i]+" - "+ i+"    "+
                    graph[i][parent[i]]);
    }

    // 使用邻接矩阵表示来构造和打印MST的函数
    void primMST(int graph[][])
    {
        // 存储构造MST的数组
        int parent[] = new int[V];

        // 用于在切割中选择最小重量边的关键值
        int key[] = new int [V];

        // 表示在MST中还没有包含的顶点集合
        Boolean mstSet[] = new Boolean[V];

        // 初始化所有键为INFINITE
        for (int i = 0; i < V; i++)
        {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // 在MST中总是包含第一个顶点。
        key[0] = 0;     // 把键值为0，这样顶点就在这里
        // 选为第一个顶点
        parent[0] = -1; // 第一个节点总是MST的根

        // MST会有V个顶点
        for (int count = 0; count < V-1; count++)
        {
            // 从MST中尚未包含的顶点集合中选择thd最小键顶点
            int u = minKey(key, mstSet);

            // 将选定的顶点添加到MST集合
            mstSet[u] = true;

            //更新选定顶点的相邻顶点的键值和父索引。只考虑那些尚未包含在MST中的顶点
            for (int v = 0; v < V; v++)

                // 图u v是非零的，只是对于顶点m mstSet v的顶点来说是假的对于顶点来说还没有包含在MST更新中只有当图u v小于键v时
                if (graph[u][v]!=0 && mstSet[v] == false &&
                        graph[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = graph[u][v];
                }
        }

        // print the constructed MST
        printMST(parent, V, graph);
    }

    public static void main (String[] args)
    {
        /* Let us create the following graph
           2    3
        (0)--(1)--(2)
        |    / \   |
        6| 8/   \5 |7
        | /      \ |
        (3)-------(4)
             9          */
        PrimMST t = new PrimMST();
        int graph[][] = new int[][] {{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0},
        };

        // Print the solution
        t.primMST(graph);
    }
}

