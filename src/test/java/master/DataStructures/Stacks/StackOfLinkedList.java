package master.DataStructures.Stacks;

/**
 * Describe:
 * Created by pengp on 2018/3/14.
 */
public class StackOfLinkedList {

    public static void main(String[] args) {

        LinkedListStack stack = new LinkedListStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        stack.printStack();

        System.out.println("Size of stack currently is: " + stack.getSize());

        stack.pop();
        stack.pop();

    }

}

