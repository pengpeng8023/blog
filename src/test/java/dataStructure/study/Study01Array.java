package dataStructure.study;

import java.util.Arrays;

/** 数组的增删查
 * Created by Administrator on 2018\8\11 0011.
 */
public class Study01Array {
    public static void main(String[] args) {
        /*part01*/
        int maxSize = 10;
        HighArray highArray = new HighArray(maxSize);
        highArray.insert(77);
        highArray.insert(65);
        highArray.insert(34);
        highArray.insert(23);
        highArray.insert(7);
        highArray.insert(45);
        highArray.insert(234);
        highArray.insert(567);
        highArray.insert(897);
        highArray.insert(86);
        highArray.display();
        long searchKey = 897L;
        if (highArray.find(searchKey))
            System.out.println("Found "+searchKey);
        else
            System.out.println("Can`t find "+searchKey);
        highArray.delete(13);
        highArray.delete(6);
        highArray.display();
        highArray.sortInsert();
        highArray.display();
        /*part02*/
        OrdArray ordArray = new OrdArray();
        ordArray.insert(77);
        ordArray.insert(65);
        ordArray.insert(897);
        ordArray.insert(34);
        ordArray.insert(23);
        ordArray.insert(7);
        ordArray.insert(234);
        ordArray.insert(45);
        ordArray.insert(567);
        ordArray.insert(86);
        ordArray.insert(87);
        ordArray.display();
        ordArray.find(234);
        searchKey = 234;
        int index = ordArray.find(searchKey);
        if (index != ordArray.size())
            System.out.println("Found "+searchKey + " index " + index);
        else
            System.out.println("Can`t find "+searchKey);
        ordArray.delete(86);
        ordArray.display();
        ordArray.delete(67);
        ordArray.display();
    }
}

/**
 * 无需数组线性操作
 */
class HighArray{
    private long[] a;
    private int nElems;


    public HighArray(int maxCon){
        this.a = new long[maxCon];
        this.nElems = 0;
    }
    public int size(){
        return this.nElems;
    }
    public void insert(long value) {
        if (nElems == a.length){
            a = Arrays.copyOf(a,a.length+1);
        }
        a[nElems] = value;
        nElems++;
    }
    public boolean find(long searchKey){
        int j;
        for (j = 0;j<nElems;j++){
            if(a[j] == searchKey)
                break;
        }
        if (j == nElems){
            return false;
        }else {
            return true;
        }
    }
    public boolean delete(long value){
        int j;
        for (j = 0;j<nElems;j++){
            if (value == a[j])
                break;
        }
        if (j == nElems)
            return false;
        else {
            for (int k =j;k<nElems-1;k++)
                a[k] = a[k+1];
            nElems--;
            a = Arrays.copyOf(a, nElems);
            return true;
        }
    }
    public void display(){
        System.out.print("HighArray: ");
        for (long i:a) {
            System.out.print(i+ " ");
        }
        System.out.println("");
    }
    /**
     * 插入排序
     */
    public void sortInsert(){
        for(int i=1;i<nElems;i++){
            int j = i-1;
            long key = a[i];
            while (j>=0 && a[j] > key){
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = key;
        }
    }
}

/**
 * 有序数组 二分操作
 */
class OrdArray{
    private long[] a;
    private int nElems = 0;

    public OrdArray() {
        this.a = new long[0];
    }
    public OrdArray(int maxSize) {
        this.a = new long[maxSize];
    }

    public int size(){
        return this.nElems;
    }

    public int find(long searchKey){
        int low = 0;
        int high = nElems-1;
        while (true){
            int mid = (low + high)/2;
            if(searchKey == a[mid]){
                return mid;
            }else if(low >=high){
                return nElems;
            }else {
                if(a[mid] < searchKey){
                    low = mid +1;
                }else {
                    high = mid -1;
                }
            }
        }
    }

    /**
     * 二分查找顺序插入数据
     * @param value
     */
    public void insert2(long value){
        if (nElems == a.length){
            a = Arrays.copyOf(a,a.length+1);
        }
        int low = 0;
        int high = nElems-1;
        while (true){
            int mid = (low + high)/2;
            if(nElems == 0){
                a[0] = value;
                nElems++;
                break;
            }else if(value >= a[nElems-1]){
                a[nElems] = value;
                nElems++;
                break;
            }else if(value <= a[0]){
                for(int k =nElems-1;k>=0;k--){
                    a[k+1] = a[k];
                }
                a[0] = value;
                nElems++;
                break;
            }else if(value == a[mid]){
                for(int k =nElems-1;k>=mid;k--){
                    a[k+1] = a[k];
                }
                a[mid] = value;
                nElems++;
                break;
            }else if(a[mid] < value && a[mid+1] > value){
                for(int k =nElems-1;k>mid;k--){
                    a[k+1] = a[k];
                }
                a[mid+1] = value;
                nElems++;
                break;
            }else if(a[mid] > value && a[mid-1] < value){
                for(int k =nElems-1;k>=mid-1;k--){
                    a[k+1] = a[k];
                }
                a[mid] = value;
                nElems++;
                break;
            }else {
                if(a[mid] < value){
                    low = mid +1;
                }else {
                    high = mid -1;
                }
            }
        }
    }
    public void insert(long value){
        if (nElems == a.length){
            a = Arrays.copyOf(a,a.length+1);
        }
        int j;
        for (j=0;j<nElems;j++){
            if(a[j] > value)
                break;
        }
        for (int k=nElems;k>j;k--){
            a[k] = a[k-1];
        }
        a[j] = value;
        nElems++;
    }
    public boolean delete(long value){
        int j = find(value);
        if (j == nElems){
            return false;
        }
        for(int i = j+1;i<nElems;i++){
            a[i-1] = a[i];
        }
        nElems--;
        a = Arrays.copyOf(a,nElems);
        return true;
    }
    public void display(){
        System.out.print("OrdArray: ");
        for (long i:a) {
            System.out.print(i+ " ");
        }
        System.out.println("");
    }
}
class Person{
    private String lastName;
    private String firstName;
    private int age;

    public Person(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }
    public void display(){
        System.out.print(" Last Name " + lastName);
        System.out.print(" ,First Name " + firstName);
        System.out.println(" ,Age " + age);
    }
    public String getLastName(){
        return lastName;
    }
}
class ArrayInobj{
    private Person[] a;
    private int nElems;
    public ArrayInobj(int maxSize){
        a = new Person[maxSize];
        nElems = 0;
    }
    public Person find(String searchName){
        int j;
        for (j = 0;j<nElems;j++){
            if (searchName.equals(a[j].getLastName()))
                break;
        }
        if (j == nElems){
            return null;
        }else {
            return a[j];
        }
    }
    public void insert(String last,String first,int age){
        if(nElems == a.length){
            a = Arrays.copyOf(a,nElems+1);
        }
        a[nElems] = new Person(last,first,age);
        nElems++;
    }
    public boolean delete(String searchName){
        int j;
        for (j=0;j<nElems;j++){
            if(searchName.equals(a[j].getLastName())){
                break;
            }
        }
        if(j == nElems){
            return false;
        }else{
            for(int k=j;k<nElems-1;k++){
                a[k] = a[k+1];
            }
            nElems--;
            a = Arrays.copyOf(a,nElems);
            return true;
        }
    }
    public void display(){
        for (Person p:a) {
            p.display();
        }
    }
    public void insertionSort(){
        int in,out;
        for (out=1;out<nElems;out++){
            Person temp = a[out];
            in =out - 1;
            while (in >=0&& a[in].getLastName().compareTo(temp.getLastName())>0){
                a[in +1] = a[in];
                in--;
            }
            a[in +1] = temp;
        }
    }
    public static void main(String[] args) {
        int maxSioze = 10;
        ArrayInobj arr = new ArrayInobj(maxSioze);
        arr.insert("a", "A", 23);
        arr.insert("b", "B", 45);
        arr.insert("c", "C", 21);
        arr.insert("d", "D", 65);
        arr.insert("e", "E", 71);
        arr.insert("f", "F", 46);
        arr.insert("g", "G", 15);
        arr.insert("h", "H", 34);
        arr.insert("i", "I", 31);
        arr.insert("j", "J", 78);
        arr.insert("k", "K", 5);
        arr.display();
        String searchName = "g";
        Person found = arr.find(searchName);
        if (found != null) {
            System.out.print("Found ");
            found.display();
        } else {
            System.out.println("Not Found" + searchName);
        }
        System.out.println("Delete a,c and j");
        arr.delete("a");
        arr.delete("c");
        arr.delete("j");
        arr.insertionSort();
        arr.display();
    }
}