package master.DataStructures.Stacks;

import java.util.ArrayList;

/**
 * Describe:
 * Created by pengp on 2018/3/14.
 */
public class Stack2{
    /** ArrayList representation of the stack */
    ArrayList<Integer> stackList;

    /**
     * Constructor
     */
    Stack2(){
        stackList=new ArrayList<>();
    }

    /**
     * Adds value to the end of list which
     * is the top for stack
     *
     * @param value value to be added
     */
    void push(int value){
        stackList.add(value);
    }

    /**
     * Pops last element of list which is indeed
     * the top for Stack
     *
     * @return Element popped
     */
    int pop(){

        if(!isEmpty()){ // checks for an empty Stack

            int popValue=stackList.get(stackList.size()-1);
            stackList.remove(stackList.size()-1);  //removes the poped element from the list
            return popValue;
        }
        else{
            System.out.print("The stack is already empty  ");
            return -1;
        }

    }

    /**
     * Checks for empty Stack
     *
     * @return true if stack is empty
     */
    boolean isEmpty(){
        if(stackList.isEmpty())
            return true;

        else return false;

    }

    /**
     * Top element of stack
     *
     * @return top element of stack
     */
    int peek(){
        return stackList.get(stackList.size()-1);
    }
}

