package master.Sort;



import master.DataStructures.Trees.TreeNode;

import java.util.Arrays;

/**
 *二叉树排序
 */
public class BinaryTreeSort {

    public TreeNode root;

    public BinaryTreeSort(Object x) {
        root = new TreeNode(x);
    }//end TreeSort constructor

    public TreeNode insert(TreeNode node, Integer x) {
        if (node == null) {
            return node = new TreeNode(x);
        }else if (x < (Integer) node.key) {
            node.left = insert(node.left, x);
        }else {
            node.right = insert(node.right, x);
        }
        return node;
    }


    public TreeNode decimalInsert(TreeNode node, Double x) {
        if (node == null) {
            return node = new TreeNode(x);
        }else if (x < (Double) node.key) {
            node.left = decimalInsert(node.left, x);
        }else {
            node.right = decimalInsert(node.right, x);
        }
        return node;
    }


    public void treeSort(TreeNode node) {
        if (node != null) {
            treeSort(node.left);
            System.out.print(((Object) node.key) + ", ");
            treeSort(node.right);
        }
    }



    public static void main(String args[]) {
        int[] intArray = {12, 40, 9, 3, 19, 74, 7, 31, 23, 54, 26, 81, 12 };
        BinaryTreeSort ts = new BinaryTreeSort(new Integer(intArray[0]));
        for (int i = 1; i < intArray.length; i++) { //sorts every index of the list one at a time
            ts.insert(ts.root, new Integer(intArray[i])); //builds on the tree from a root node
        }//end for
        System.out.print("Integer Array Sorted in Increasing Order: ");
        ts.treeSort(ts.root);
        System.out.println(); //To sort a test array of integers

        Double[] decimalArray = {8.2, 1.5, 3.14159265, 9.3, 5.1, 4.8, 2.6};
        BinaryTreeSort dts = new BinaryTreeSort(new Double(decimalArray[0]).doubleValue());
        for (int i = 1; i < decimalArray.length; i++) { //sorts every index of the list one at a time
            dts.decimalInsert(dts.root, new Double(decimalArray[i]).doubleValue()); //builds on the tree from a root node
        }//end for
        System.out.print("Decimal Array, Sorted in Increasing Order: ");
        dts.treeSort(dts.root);
        System.out.println();

        String[] stringArray = {"c", "a", "e", "b","d", "dd","da","zz", "AA", "aa","aB","Hb", "Z"};
        int last = stringArray.length;
        Arrays.sort(stringArray); //Uses an imported arrays method to automatically alphabetize
        System.out.print("String Array Sorted in Alphabetical Order: ");
        ts.insert(ts.root, last);
        for(int i=0; i<last; i++){
            System.out.print(stringArray[i]+"\t");
            //To sort a test array of strings hard coded in the main method
            //Please Note that Capital letters always come before lower case
            //I tried to make the test array show its behavior clearly
        }
        System.out.println();
    }
}

