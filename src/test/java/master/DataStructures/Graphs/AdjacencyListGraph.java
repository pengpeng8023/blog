package master.DataStructures.Graphs;

import java.util.ArrayList;

public class AdjacencyListGraph<E extends Comparable<E>> {

    ArrayList<Vertex> verticies;

    public AdjacencyListGraph() {
        verticies = new ArrayList<>();
    }

    private class Vertex {
        E data;
        ArrayList<Vertex> adjacentVerticies;

        public Vertex(E data) {
            adjacentVerticies = new ArrayList<>();
            this.data = data;
        }

        public boolean addAdjacentVertex(Vertex to) {
            for (Vertex v: adjacentVerticies) {
                if (v.data.compareTo(to.data) == 0) {
                    return false; // 边已经存在
                }
            }
            return adjacentVerticies.add(to); // 这将返回true;
        }

        public boolean removeAdjacentVertex(E to) {
            // 在这里使用索引，所以不需要实现ArrayList的equals方法就可以轻松地删除它。删除(对象o)使用
            for (int i = 0; i < adjacentVerticies.size(); i++) {
                if (adjacentVerticies.get(i).data.compareTo(to) == 0) {
                    adjacentVerticies.remove(i);
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 这种方法从两个指定的verticies之间的图形中移除一条边
     *
     * @param from 顶点的数据是来自于
     * @param to 顶点的数据将会是
     * @return 如果边不存在，返回false，如果边缘存在并被删除，则返回true
     */
    public boolean removeEdge(E from, E to) {
        Vertex fromV = null;
        for (Vertex v: verticies) {
            if (from.compareTo(v.data) == 0) {
                fromV = v;
                break;
            }
        }
        if (fromV == null) return false;
        return fromV.removeAdjacentVertex(to);
    }
    /**
     * 这种方法在两个指定的verticies之间增加了一条边。
     *
     * @param from 顶点的数据是来自于
     * @param to 顶点的数据将会是
     * @return 如果边不存在，返回true，返回false
     */
    public boolean addEdge(E from, E to) {
        Vertex fromV = null, toV = null;
        for (Vertex v: verticies) {
            if (from.compareTo(v.data) == 0) { // 看看是否从顶点已经存在
                fromV = v;
            } else if (to.compareTo(v.data) == 0) { // 看看顶点是否已经存在
                toV = v;
            }
            if (fromV != null && toV != null) break; // 两个节点都存在，所以停止搜索
        }
        if (fromV == null) {
            fromV = new Vertex(from);
            verticies.add(fromV);
        }
        if (toV == null) {
            toV = new Vertex(to);
            verticies.add(toV);
        }
        return fromV.addAdjacentVertex(toV);
    }

    /**
     * 这给出了图表中的verticia和它们的邻接表
     *
     * @return 返回描述此图的字符串
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v: verticies) {
            sb.append("Vertex: ");
            sb.append(v.data);
            sb.append("\n");
            sb.append("Adjacent verticies: ");
            for (Vertex v2: v.adjacentVerticies) {
                sb.append(v2.data);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
