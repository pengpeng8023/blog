package master.DataStructures.Trees; /**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */

public class FindHeightOfTree {

    // Driver Program
    public static void main(String[] args) {
        Node tree = new Node(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(-1);
        tree.insert(29);
        tree.insert(93);
        tree.insert(6);
        tree.insert(0);
        tree.insert(-5);
        tree.insert(-6);
        tree.insert(-8);
        tree.insert(-1);

        // A level order representation of the tree
        tree.printLevelOrder();
        System.out.println();

        System.out.println("Height of the tree is: " + tree.findHeight());
    }
}



