package master.DataStructures.Trees;


import java.util.LinkedList;

/**
 * This class implements the nodes that will go on the Binary Tree.
 * They consist of the data in them, the node to the left, the node
 * to the right, and the parent from which they came from.
 *
 * @author Unknown
 *
 */
public class Node{
    /** Data for the node */
    public int data;
    /** The Node to the left of this one */
    public Node left;
    /** The Node to the right of this one */
    public Node right;
    /** The parent of this node */
    public Node parent;

    /**
     * Constructor of Node
     *
     * @param value Value to put in the node
     */
    public Node(int value){
        data = value;
        left = null;
        right = null;
        parent = null;
    }
    public void insert (int value) {
        if (value < data) {
            if (left == null) {
                left = new Node(value);
            }else {
                left.insert(value);
            }
        }
        else {
            if (right == null) {
                right = new Node(value);
            }
            else {
                right.insert(value);
            }
        }
    }
    public void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }
        System.out.print(data + " ");
        if (right != null) {
            right.printInOrder();
        }
    }

    public void printPreOrder() {
        System.out.print(data + " ");
        if (left != null) {
            left.printPreOrder();
        }
        if (right != null) {
            right.printPreOrder();
        }
    }

    public void printPostOrder() {
        if (left != null) {
            left.printPostOrder();
        }
        if (right != null) {
            right.printPostOrder();
        }
        System.out.print(data + " ");
    }
    public void printLevelOrder() {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(this);
        while(!queue.isEmpty()) {
            Node n = queue.poll();
            System.out.print(n.data + " ");
            if (n.left != null) {
                queue.add(n.left);
            }
            if (n.right != null) {
                queue.add(n.right);
            }
        }
    }

    public int findHeight() {
        return findHeight(this);
    }

    private int findHeight(Node root) {
        if (root.left == null && root.right == null) {
            return 0;
        }
        else if (root.left != null && root.right != null) {
            return 1 + Math.max(findHeight(root.left), findHeight(root.right));
        }
        else if (root.left == null && root.right != null) {
            return 1 + findHeight(root.right);
        }
        else {
            return 1 + findHeight(root.left);
        }
    }
}

