package master.DataStructures.Trees;

/**
 * Describe:
 * Created by pengp on 2018/3/14.
 */
public class PrintTopViewofTree
{
    public static void main(String[] args)
    {
        /* Create following Binary Tree
             1
           /  \
          2    3
           \
            4
             \
              5
               \
                6*/
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(5);
        root.left.right.right.right = new TreeNode(6);
        PrintTopTree t = new PrintTopTree(root);
        System.out.println("Following are nodes in top view of Binary Tree");
        t.printTopView();
    }
}
