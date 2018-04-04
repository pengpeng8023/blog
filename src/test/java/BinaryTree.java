
import java.util.Stack;

/**
 * Describe:二叉树学习
 * Created by pengp on 2018/3/30.
 */
public class BinaryTree {
    public TreeNode root = null;
    BinaryTree(int val){
        root = new TreeNode(val);
        root.left = null;
        root.right = null;
    }
    /*查找数据*/
    public TreeNode findKey(int value){
        TreeNode current = root;
        while (true){
            if (current == null){
                return null;
            }
            if(value == current.val){
                return current;
            }else if(value <current.val){
                current = current.left;
            }else if(value>current.val){
                current = current.right;
            }
        }

    }
    /*插入数据*/
    public String insert(int value){
        String error = null;
        TreeNode node = new TreeNode(value);

        if (root == null){
            root = node;
            root.left = null;
            root.right = null;
        }else{
            TreeNode current = root;
            TreeNode parent = null;
            while (true){
                if (value< current.val){
                    parent = current;
                    current = current.left;
                    if (current == null){
                        parent.left = node;
                        break;
                    }
                }else if(value > current.val){
                    parent = current;
                    current = current.right;
                    if (current == null){
                        parent.right = node;
                        break;
                    }
                }else {
                    error = "having same value in binary tree";
                }
            }
        }
        return error;
    }
    /*遍历数据*/


    /*
     * 一、中序遍历(递归)
     *  1、调用自身来遍历节点的左子树
     *  2、访问这个节点
     *  3、调用自身遍历节点右子树
     */
    public void inOrderTraverse(){
        System.out.println("中序遍历");
        inOrderTraverse(root);
        System.out.println();

    }
    public void inOrderTraverse(TreeNode node){
        if(node == null)
            return;
        inOrderTraverse(node.left);
        node.display();
        inOrderTraverse(node.right);
    }
    /*
     * 二、中序非递归遍历
     *  1、对于任意节点current，若节点不为空将该及诶单压栈，并将左子树及诶单置为current，重复此操作，知道current为空
     *  2、若左子树为空、栈顶节点出栈，访问节点后将该节点的右子树置为current
     *  3、重复1/2步骤，知道current为空且栈内及诶单为空
     */
    public void inOrderByStack(){
        System.out.println("中序非递归遍历");
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current!=null || !stack.isEmpty()){
            while (current!=null){
                stack.push(current);
                current = current.left;
            }
            if (!stack.isEmpty()){
                current = stack.pop();
                current.display();
                current = current.right;
            }
        }
        System.out.println();
    }
    /*
     * 三、前序遍历
     * 1、访问这个节点
     * 2、调用自身来遍历节点左子树
     * 3、调用自身来遍历节点右子树
     */
    public void preOrderTraverse(){
        System.out.print("前序遍历");
        preOrderTraverse(root);
        System.out.println();
    }
    public void preOrderTraverse(TreeNode node){
        if (node == null)
            return;
        node.display();
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }

    /*
     * 四、前序非递归遍历
     * 1、对于任意节点current，若该节点不为空则访问该节点后再将及诶单压栈，并将左子树节点置为current
     *    重复此操作，知道current为空
     * 2、若左子树为空，栈顶节点出栈，该节点的右子树置为current
     * 3、重复1/2步骤，直到current为空
     */
    public void preOrderByStack(){
        System.out.println("前序非递归遍历");
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while(current!=null || !stack.isEmpty()){
            while (current!=null){
                stack.push(current);
                current.display();
                current = current.left;
            }
            if (!stack.isEmpty()){
                current = stack.pop();
                current = current.right;
            }
        }
        System.out.println();
    }

    /*
     * 五、后序遍历
     *  1、调用自身来遍历左子树
     *  2、调用自身遍历右子树
     *  3、访问该节点
     */
    public void postOrderTraverse(){
        System.out.print("后语遍历：");
        postOrderTraverse(root);
        System.out.println();
    }
    public void postOrderTraverse(TreeNode node){
        if (node==null)
            return;
        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        node.display();
    }

    /*
     * 六、后序非递归遍历
     *  1、对于任意节点current，若该节点不为空则访问该节点后再将节点压栈，并将左子树节点置为current，
     *     重复此操作，直到current为空
     *  2、若左子树为空，取栈顶节点的右子树，如果右子树为空或柚子树=刚访问过，则访问该节点，并将preNode置为该节点
     *  3、重复1/2步骤操作，直到current为空
     */
    public void postOrderByStack(){
        System.out.print("后序非递归遍历:");
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        TreeNode preNode = null;
        while (current !=null || !stack.isEmpty()){
            while (current!=null){
                stack.push(current);
                current = current.left;
            }
            if (!stack.isEmpty()){
                current = stack.peek().right;
                if (current == null || current == preNode){
                    current = stack.pop();
                    current.display();
                    preNode = current;
                    current = null;
                }
            }
            System.out.println();
        }
    }
    /*
     *  获取最小值：依次向左直到为空
     */
    public int getMinValue(){
        TreeNode current = root;
        while (true){
            if (current.left == null)
                return current.val;
            current = current.left;
        }
    }

    /*
     *  获取最大值：依次向右
     */
    public int getMaxValue(){
        TreeNode current = root;
        while (true){
            if (current.right == null)
                return current.val;
            current = current.right;
        }
    }


    /**
     *
     * 1、删除节点为叶子结点
     * 2、删除节点只有一个子节点
     * 3、删除节点有两个子节点：需要寻找后继节点，集比必要删除的节点关键值高的节点是他的后继节点
     *      后继节点就是比要删除的节点的关键值要大的节点集合中的最小值。
     *
     */

    /**
     * 获取后继及诶单，即删除节点的左后代
     */
    private TreeNode getSuccess(TreeNode delNode){
        TreeNode success = delNode;
        TreeNode successParent = null;
        TreeNode current = delNode.right;
        while (current!=null){
            successParent = success;
            success = current;
            current = current.left;
        }
        //如果后继节点不是删除节点的右子节点
        if (success != delNode.right){
            //要将后继节点的右子节点指向后继节点父节点的左子节点
            successParent.left = success.right;
            success.right = delNode.right;
        }
        //在任何情况下都需要删除节点的左子节点指向后继节点的左子节点
        success.left = delNode.left;
        return success;
    }

    public boolean delete(int value){
        TreeNode current = root;//需要删除的节点
        TreeNode parent = null;//需要删除的节点父节点
        boolean isLeftChild = true; //需要删除的节点是否父节点的左子树

        while (true){
            if (value == current.val){
                break;
            }else if (value<current.val){
                isLeftChild = true;
                parent = current;
                current = current.left;
            }else if (value > current.val){
                isLeftChild = false;
                parent = current;
                current = current.right;
            }

            //找不到需要删除的及诶单，直接返回
            if (current==null)
                return false;
        }

        //分情况考虑
        //1、需要删除的节点为叶子结点
        if (current.left == null && current.right == null){
            //如果该节点为根节点，将根节点置为空
            if (current == root){
                root = null;
            }else {
                //如果该叶子结点是父节点的左子节点，将父节点的左子节点置为nul
                if (isLeftChild){
                    parent.left = null;
                }else {
                    //如果该叶子结点是父节点的右子节点，将父节点的有节点置为null
                    parent.right = null;
                }
            }
        }
        //2、需要删除的节点有一个子节点，且该子节点为左子节点
        else if (current.right == null){
            if(current == root){
                root = current.left;
            }else {
                //如果该节点是父节点对的左子节点，将该及诶单的左子节点变为福及诶单的左子节点
                if (isLeftChild){
                    parent.left = current.left;
                }else {
                    //如果该节点是父节点的右子节点，将该节点的左子节点变为父节点的右子节点
                    parent.right = current.left;
                }
            }
        }
        //3、需要删除的节点有一个子节点，且该节点为右子节点
        else if (current.left == null){
            //如果该节点为根节点，将根节点的右子节点变为根节点
            if(current == root){
                root = current.right;
            }else {
                //如果该节点是父节点的左子节点，将该节点的右子节点变为节点的左子节点
                if (isLeftChild){
                    parent.left = current.right;
                }else {
                    //如果该及诶单是父节点的右子节点，将该节点的右子节点变为父节点的右子节点
                    parent.right = current.right;
                }
            }
        //需要删除的节点有两个子节点，需要寻找该节点的后继节点替代删除节点
        }else{
            TreeNode success = getSuccess(current);
            //如果该节点为根节点，将后继节点变为根节点，并将根节点的左子节点变为后继节点的坐姿及诶单
            if (current == root){
                root = success;
            }else{
                //如果该节点是父节点的左子节点，将该节点的后继节点变为父节点的左子节点
                if (isLeftChild){
                    parent.left = success;
                } else {
                    //如果该节点是父节点的右子节点，将该节点的后继节点变为父节点的右子节点
                    parent.right = success;
                }

            }
        }
        current = null;
        return true;

    }












}



















