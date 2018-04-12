package master.DataStructures.Stacks;

/**
 * Describe:
 * Created by pengp on 2018/3/14.
 */
public /**
 * A class which implements a stack using a linked list
 *
 * Contains all the stack methods : push, pop, printStack, isEmpty
 **/

class LinkedListStack {

    Node head = null;
    int size = 0;

    public void push(int x) {
        Node n = new Node(x);
        if (getSize() == 0) {
            head = n;
        }
        else {
            Node temp = head;
            n.next = temp;
            head = n;
        }
        size++;
    }

    public void pop() {
        if (getSize() == 0) {
            System.out.println("Empty stack. Nothing to pop");
        }

        Node temp = head;
        head = head.next;
        size--;

        System.out.println("Popped element is: " + temp.data);
    }

    public void printStack() {

        Node temp = head;
        System.out.println("Stack is printed as below: ");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();

    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getSize() {
        return size;
    }

}

