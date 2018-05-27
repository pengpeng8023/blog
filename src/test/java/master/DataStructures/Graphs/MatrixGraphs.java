package master.DataStructures.Graphs;

/**
 * Describe:
 * Created by pengp on 2018/3/14.
 */
public class MatrixGraphs {
    public static void main(String args[]) {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(10);
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 5);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        System.out.println(graph);
    }
}
