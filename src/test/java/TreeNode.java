/**
 * Describe:
 * Created by pengp on 2018/3/30.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    public void display(){
        System.out.print(this.val+"\t");
    }
    @Override
    public String toString() {
        return String.valueOf(val);
    }
}