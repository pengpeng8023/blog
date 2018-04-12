package master.DataStructures.Bags;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 不允许删除元素的集合（只收集和重复）
 *
 * @param <Element> - 元素的一般类型
 */
public class Bag<Element> implements Iterable<Element> {

    private Node<Element> firstElement; // 袋子的第一个元素
    private int size; // 袋子的大小

    private static class Node<Element> {
        private Element content;
        private Node<Element> nextElement;
    }

    /**
     * Create an empty bag
     */
    public Bag() {
        firstElement = null;
        size = 0;
    }

    /**
     * @return 如果这个袋子是空的，否则是假的
     */
    public boolean isEmpty() {
        return firstElement == null;
    }

    /**
     * @return 元素的个数
     */
    public int size() {
        return size;
    }

    /**
     * @param element - the element to add
     */
    public void add(Element element) {
        Node<Element> oldfirst = firstElement;
        firstElement = new Node<>();
        firstElement.content = element;
        firstElement.nextElement = oldfirst;
        size++;
    }

    /**
     * 检查包是否包含一个特定的元素
     *
     * @param element 你要找的元素
     * @return 如果袋子包含元素，否则是假的
     */
    public boolean contains(Element element) {
        Iterator<Element> iterator = this.iterator();
        while(iterator.hasNext()) {
            if (iterator.next().equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return 一个迭代器，它按任意顺序遍历袋中元素
     */
    public Iterator<Element> iterator() {
        return new ListIterator<>(firstElement);
    }

    @SuppressWarnings("hiding")
    private class ListIterator<Element> implements Iterator<Element> {
        private Node<Element> currentElement;

        public ListIterator(Node<Element> firstElement) {
            currentElement = firstElement;
        }

        public boolean hasNext() {
            return currentElement != null;
        }

        /**
         * 袋子里不允许拿走
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Element next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Element element = currentElement.content;
            currentElement = currentElement.nextElement;
            return element;
        }
    }

    /**
     * main-method for testing
     */
    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();

        bag.add("1");
        bag.add("1");
        bag.add("2");

        System.out.println("size of bag = " + bag.size());
        for (String s : bag) {
            System.out.println(s);
        }

        System.out.println(bag.contains(null));
        System.out.println(bag.contains("1"));
        System.out.println(bag.contains("3"));
    }

}
