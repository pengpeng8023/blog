package dataStructure.study;

/**
 * Created by Administrator on 2018\8\19 0019.
 */
public class Study03Stack {
}
class LinkForStack{
    public long dData;
    public LinkForStack next;
    public LinkForStack(long dd){
        dData = dd;
    }
    public void displayLink(){
        System.out.print(dData + " ");
    }
}
class LinkListForStack{
    private LinkForStack first;
    public LinkListForStack(){
        first = null;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public void insertFirst(long dd){
        LinkForStack newLink = new LinkForStack(dd);
        newLink.next = first;
        first = newLink;
    }
    public long deleteFirst(){
        LinkForStack temp = first;
        first = first.next;
        return temp.dData;
    }
    public void displayList(){
        LinkForStack current = first;
        while (current !=null){
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }
}
class LinkStack{
    private LinkListForStack list;
    public LinkStack(){
        list = new LinkListForStack();
    }
    public void push(long j){
        list.insertFirst(j);
    }
    public long pop(){
        return list.deleteFirst();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public void displayStack(){
        System.out.println("Stack (top-->bottom): ");
        list.displayList();
    }

    public static void main(String[] args) {
        LinkStack stack = new LinkStack();
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.displayStack();

        stack.push(50);
        stack.push(60);
        stack.push(70);

        stack.displayStack();

        stack.pop();
        stack.pop();
        stack.displayStack();
    }
}
class FirstLastList{
    private LinkForStack first;
    private LinkForStack last;
    public FirstLastList(){
        first = null;
        last = null;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public void insertFirst(long dd){
        LinkForStack newLink = new LinkForStack(dd);
        if (isEmpty()){
            last = newLink;
        }
        newLink.next = first;
        first = newLink;
    }
    public void insertLast(long dd){
        LinkForStack newLink = new LinkForStack(dd);
        if (isEmpty()){
            first = newLink;
        }else {
            last.next = newLink;
        }
        last = newLink;
    }
    public long deleteFirst(){
        long temp = first.dData;
        if (first.next == null){
            last = null;
        }
        first = first.next;
        return temp;
    }
    public void displayList(){
        System.out.print("List (first -- > last): ");
        LinkForStack current = first;
        while (current !=null){
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        FirstLastList list = new FirstLastList();
        list.insertFirst(11);
        list.insertFirst(22);
        list.insertFirst(33);
        list.insertFirst(44);
        list.insertLast(55);
        list.insertLast(66);
        list.insertLast(77);
        list.insertLast(88);
        list.displayList();
        list.deleteFirst();
        list.deleteFirst();
        list.displayList();
    }
}
class LinkQueue{
    private FirstLastList list;
    public LinkQueue(){
        list = new FirstLastList();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public void insert(long j){
        list.insertLast(j);
    }
    public long remove(){
        return list.deleteFirst();
    }
    public void displayQueue(){
        System.out.println("Queue (front-->rea): ");
        list.displayList();
    }

    public static void main(String[] args) {
        LinkQueue queue = new LinkQueue();
        queue.insert(20);
        queue.insert(30);
        queue.insert(40);

        queue.displayQueue();

        queue.insert(50);
        queue.insert(60);
        queue.insert(70);
        queue.displayQueue();

        queue.remove();
        queue.remove();
        queue.remove();
        queue.displayQueue();

    }
}
class SortedList{
    private LinkForStack first;

    public SortedList(){
        first = null;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void insert(long key){
        LinkForStack newLink = new LinkForStack(key);
        LinkForStack previous = null;
        LinkForStack current = first;

        while (current !=null && current.dData < key){
            previous = current;
            current = current.next;
        }
        if (previous == null){
            first = newLink;
        }else {
            previous.next = newLink;
        }
        newLink.next = current;
    }
    public LinkForStack remove(){
        LinkForStack temp = first;
        first = first.next;
        return temp;
    }

    public  void displayList(){
        System.out.print("List (first-->last): ");
        LinkForStack current = first;
        while (current !=null){
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }
    public SortedList(LinkForStack[] linkArr){
        first = null;
        for (int i=0;i<linkArr.length;i++){
            insert(linkArr[i]);
        }
    }
    public void insert(LinkForStack k){
        LinkForStack previous = null;
        LinkForStack current = first;
        while (current !=null && k.dData > current.dData){
            previous = current;
            current = current.next;
        }
        if (previous == null){
            first = k;
        }else{
            previous.next = k;
        }
        k.next = current;
    }
    public static void main(String[] args) {
        SortedList list = new SortedList();
        list.insert(20);
        list.insert(40);
        list.insert(30);
        list.insert(50);
        list.displayList();

        list.remove();
        list.displayList();

        LinkForStack[] linkForStacks = new LinkForStack[10];
        for (int i=0;i<10;i++){
            int n = (int)(Math.random()*100);
            LinkForStack newLInk = new LinkForStack(n);
            linkForStacks[i] = newLInk;
        }
        System.out.print("Unsorted array: ");
        for (int i=0;i<10;i++){
            System.out.print(linkForStacks[i].dData + " ");
        }
        System.out.println("");

        SortedList sortedList = new SortedList(linkForStacks);
        for (int i=0;i<10;i++){
            linkForStacks[i] = sortedList.remove();
        }
        System.out.print("Sorted Array: ");
        for (int i=0;i<10;i++){
            System.out.print(linkForStacks[i].dData + " ");
        }
        System.out.println("");
    }
}