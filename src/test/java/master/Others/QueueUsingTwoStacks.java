package master.Others;

/**
 * This class is the example for the Queue class
 *
 * @author sahilb2
 *
 */
public class QueueUsingTwoStacks {

    /**
     * Main method
     *
     * @param args Command line arguments
     */
    public static void main(String args[]){
        QueueWithStack myQueue = new QueueWithStack();
        myQueue.insert(1);
        // instack: [(top) 1]
        // outStack: []
        myQueue.insert(2);
        // instack: [(top) 2, 1]
        // outStack: []
        myQueue.insert(3);
        // instack: [(top) 3, 2, 1]
        // outStack: []
        myQueue.insert(4);
        // instack: [(top) 4, 3, 2, 1]
        // outStack: []

        System.out.println(myQueue.isEmpty()); //Will print false

        System.out.println(myQueue.remove()); //Will print 1
        // instack: []
        // outStack: [(top) 2, 3, 4]

        myQueue.insert(5);
        System.out.println(myQueue.peek()); //Will print 2
        // instack: [(top) 5]
        // outStack: [(top) 2, 3, 4]

        myQueue.remove();
        System.out.println(myQueue.peek()); //Will print 3
        // instack: [(top) 5]
        // outStack: [(top) 3, 4]
        myQueue.remove();
        System.out.println(myQueue.peek()); //Will print 4
        // instack: [(top) 5]
        // outStack: [(top) 4]
        myQueue.remove();
        // instack: [(top) 5]
        // outStack: []
        System.out.println(myQueue.peek()); //Will print 5
        // instack: []
        // outStack: [(top) 5]
        myQueue.remove();
        // instack: []
        // outStack: []

        System.out.println(myQueue.isEmpty()); //Will print true

    }
}
