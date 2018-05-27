package master.DataStructures.Graphs;

/**
 * Describe:
 * Created by pengp on 2018/3/14.
 */
public class AdjacencyMatrixGraph {
    private int _numberOfVertices;
    private int _numberOfEdges;
    private int[][] _adjacency;

    static final int EDGE_EXIST = 1;
    static final int EDGE_NONE = 0;

    public AdjacencyMatrixGraph(int givenNumberOfVertices) {
        this.setNumberOfVertices(givenNumberOfVertices);
        this.setNumberOfEdges(0);
        this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
        for (int i = 0; i < givenNumberOfVertices; i++) {
            for (int j = 0; j < givenNumberOfVertices; j++) {
                this.adjacency()[i][j] = AdjacencyMatrixGraph.EDGE_NONE;
            }
        }
    }

    private void setNumberOfVertices(int newNumberOfVertices) {
        this._numberOfVertices = newNumberOfVertices;
    }

    public int numberOfVertices() {
        return this._numberOfVertices;
    }

    private void setNumberOfEdges(int newNumberOfEdges) {
        this._numberOfEdges = newNumberOfEdges;
    }

    public int numberOfEdges() {
        return this._numberOfEdges;
    }

    private void setAdjacency(int[][] newAdjacency) {
        this._adjacency = newAdjacency;
    }

    private int[][] adjacency() {
        return this._adjacency;
    }

    private boolean adjacencyOfEdgeDoesExist(int from, int to) {
        return (this.adjacency()[from][to] != AdjacencyMatrixGraph.EDGE_NONE);
    }

    public boolean vertexDoesExist(int aVertex) {
        if (aVertex >= 0 && aVertex < this.numberOfVertices()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean edgeDoesExist(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            return (this.adjacencyOfEdgeDoesExist(from, to));
        }

        return false;
    }

    /**
     * 该方法在两个指定顶点之间添加一条边
     *
     * @param from 顶点的数据是来自于
     * @param to 顶点的数据将会是
     * @return 如果边不存在，返回true，返回false
     */
    public boolean addEdge(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            if (!this.adjacencyOfEdgeDoesExist(from, to)) {
                this.adjacency()[from][to] = AdjacencyMatrixGraph.EDGE_EXIST;
                this.adjacency()[to][from] = AdjacencyMatrixGraph.EDGE_EXIST;
                this.setNumberOfEdges(this.numberOfEdges() + 1);
                return true;
            }
        }

        return false;
    }

    /**
     * 该方法从两个指定顶点之间的图中移除一条边
     *
     * @param from the data of the vertex the edge is from
     * @param to the data of the vertex the edge is going to
     * @return 如果边不存在，返回false，如果边缘存在并被删除，则返回true
     */
    public boolean removeEdge(int from, int to) {
        if(!this.vertexDoesExist(from) || !this.vertexDoesExist(to)) {
            if (this.adjacencyOfEdgeDoesExist(from, to)) {
                this.adjacency()[from][to] = AdjacencyMatrixGraph.EDGE_NONE;
                this.adjacency()[to][from] = AdjacencyMatrixGraph.EDGE_NONE;
                this.setNumberOfEdges(this.numberOfEdges() - 1);
                return true;
            }
        }
        return false;
    }

    /**
     * 这给出了图中的顶点列表及其邻接关系
     *
     * @return 返回描述此图的字符串
     */
    public String toString() {
        String s = new String();
        s = "    ";
        for (int i = 0; i < this.numberOfVertices(); i++) {
            s = s + String.valueOf(i) + " ";
        }
        s = s + " \n";

        for (int i = 0; i < this.numberOfVertices(); i++) {
            s = s + String.valueOf(i) + " : ";
            for (int j = 0; j < this.numberOfVertices(); j++) {
                s = s + String.valueOf(this._adjacency[i][j]) + " ";
            }
            s = s + "\n";
        }
        return s;
    }

}
