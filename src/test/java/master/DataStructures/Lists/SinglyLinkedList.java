package master.DataStructures.Lists;

public class SinglyLinkedList{
    /**Head refered to the front of the list */
    private Node head;

    /**
     * Constructor of SinglyLinkedList
     */
    public SinglyLinkedList(){
        head = null;
    }

    /**
     * This method inserts an element at the head
     *
     * @param x Element to be added
     */
    public void insertHead(int x){
        Node newNode = new Node(x); //Create a new link with a value attached to it
        newNode.next = head; 		//Set the new link to point to the current head
        head = newNode; 			//Now set the new link to be the head
    }


    /**
     * Inserts a new node at a specified position
     * @param head     head node of the linked list
     * @param data     data to be stored in a new node
     * @param position position at which a new node is to be inserted
     * @return  reference of the head of the linked list
     */

    Node InsertNth(Node head, int data, int position) {

        Node newNode = new Node();
        newNode.value = data;

        if (position == 0) {
            newNode.next = head;
            return newNode;
        }

        Node current = head;

        while (--position > 0) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
        return head;
    }

    /**
     * This method deletes an element at the head
     *
     * @return The element deleted
     */
    public Node deleteHead(){
        Node temp = head;
        head = head.next; //Make the second element in the list the new head, the Java garbage collector will later remove the old head
        return temp;
    }

    /**
     * Checks if the list is empty
     *
     * @return true is list is empty
     */
    public boolean isEmpty(){
        return(head == null);
    }

    /**
     * Prints contents of the list
     */
    public void display(){
        Node current = head;
        while(current!=null){
            System.out.print(current.getValue()+" ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * Main method
     *
     * @param args Command line arguments
     */
    public static void main(String args[]){
        SinglyLinkedList myList = new SinglyLinkedList();

        System.out.println(myList.isEmpty()); //Will print true

        myList.insertHead(5);
        myList.insertHead(7);
        myList.insertHead(10);

        myList.display(); // 10(head) --> 7 --> 5

        myList.deleteHead();

        myList.display(); // 7(head) --> 5
    }
}

