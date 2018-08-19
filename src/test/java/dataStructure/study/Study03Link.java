package dataStructure.study;


import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Arrays;

/** 链表
 * Created by Administrator on 2018\8\19 0019.
 */
public class Study03Link {
    public static void main(String[] args) {
        LinkList list = new LinkList();
        list.insertFirst(22,2.99);
        list.insertFirst(33,3.99);
        list.insertFirst(44,4.99);
        list.insertFirst(55,5.99);
        list.insertFirst(66,6.99);
        list.insertFirst(77,7.99);
        list.displayList();
        Link f =list.find(33);
        if(f!=null){
            System.out.println("Found link with key: " + f.iData);
        }else{
            System.out.println("Can`t find link");

        }
       Link d =list.delete(66);
        if(d!=null){
            System.out.println("Delete link with key: "+d.iData);
        }else{
            System.out.println("Can`t find link");
        }
        list.displayList();
    }
}
class Link{
    public int iData;
    public double dData;
    public Link next;

    public Link(int id, double dd){
        iData = id;
        dData = dd;
    }
    public void displayLink(){
        System.out.print("{" + iData + "," + dData + "}");
    }
}
class LinkList{
    private Link first;
    public LinkList(){
        first = null;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public void insertFirst(int id,double dd){
        Link newLink = new Link(id,dd);
        newLink.next = first; //newLink-->old first
        first = newLink;    //first-->newLink
    }
    public Link find(int key){
        Link current = first;
        while (current.iData != key){
            if(current.next == null)
                return null;
            else
                current = current.next;
        }
        return current;
    }
    public Link delete(int key){
        Link current = first;
        Link previous = first;
        while(current.iData != key){
            if (current.next == null)
                return null;
            else{
                previous = current;
                current = current.next;
            }
        }
        if (current == first){
            first = first.next;
        }else {
            previous.next = current.next;
        }
        return current;
    }
    public Link deleteFirst(){
        Link temp = first;
        first = first.next;
        return temp;
    }
    public void displayList(){
        System.out.print("List (first-->last: ");
        Link current = first;
        while (current!=null){
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }
}
class Link2{
    public long dData;
    public Link2 next;
    public Link2(long d){
        dData = d;
    }
    public void displayLink2(){
        System.out.print(dData + " ");
    }
}
