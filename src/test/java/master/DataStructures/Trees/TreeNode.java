package master.DataStructures.Trees;

/**
 * Describe:
 * Created by pengp on 2018/3/14.
 */
public class TreeNode
{
    // Members
    public Object key;
    public TreeNode left, right;

    // Constructor
    public TreeNode(Object key)
    {
        this.key = key;
        left = right = null;
    }
}