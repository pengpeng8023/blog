package master.DataStructures.Trees;

/**
 * Describe:
 * Created by pengp on 2018/3/14.
 */
public class TreeTraversal {
    public static void main(String[] args) {
        Node tree = new Node(5);
        tree.insert(3);
        tree.insert(2);
        tree.insert(7);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        // Prints 5 3 2 4 7 6 8
        System.out.println("Pre order traversal:");
        tree.printPreOrder();
        System.out.println();
        // Prints 2 3 4 5 6 7 8
        System.out.println("In order traversal:");
        tree.printInOrder();
        System.out.println();
        // Prints 2 4 3 6 8 7 5
        System.out.println("Post order traversal:");
        tree.printPostOrder();
        System.out.println();
        // Prints 5 3 7 2 4 6 8
        System.out.println("Level order traversal:");
        tree.printLevelOrder();
        System.out.println();
    }
}
