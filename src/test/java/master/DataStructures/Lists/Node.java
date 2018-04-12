package master.DataStructures.Lists;

/**
 * This class is the nodes of the SinglyLinked List.
 * They consist of a vlue and a pointer to the node
 * after them.
 *
 * @author Unknown
 *
 */
public class Node{
    /** The value of the node */
    public int value;
    /** Point to the next node */
    public Node next; //This is what the link will point to
    public Node(){}
    /**
     * Constructor
     *
     * @param valuein Value to be put in the node
     */
    public Node(int valuein){
        value = valuein;
    }

    /**
     * Returns value of the node
     */
    public int getValue(){
        return value;
    }

}
