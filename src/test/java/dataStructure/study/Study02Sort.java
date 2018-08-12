package dataStructure.study;

import java.util.Arrays;

/**
 * Created by Administrator on 2018\8\12 0012.
 */
public class Study02Sort {
    public static void main(String[] args) {
        int maxSize = 10;
        Array array = new Array(maxSize);
        array.insert(77);
        array.insert(77);
        array.insert(29);
        array.insert(29);
        array.insert(7);
        array.insert(29);
        array.insert(234);
        array.insert(57);
        array.insert(70);
        array.insert(70);
        array.display();
        //array.bubbleSort();
        //array.selectSort();
        //array.insertSort();
        //array.bubbleSortDoubleEnd();
        array.noDups();
        array.display();
    }

}
class Array{
    private long[] a;
    private int size;
    public Array(int maxSize){
        a = new long[maxSize];
        size = 0;
    }
    public void insert(long value){
        a[size] = value;
        size++;
    }
    public void display(){
        for (long l:a) {
            System.out.print(l+" ");
        }
        System.out.println("");
    }

    /**
     * 冒泡排序
     */
    public void bubbleSort(){
        int out,in;
        for(out=0;out<size-1;out++){
            for(in = size-1;in>out;in--){
                if(a[in-1] > a[in]){
                    swap(in-1,in);
                }
            }
        }
    }

    /**
     * 冒泡排序优化
     */
    public void bubbleSortOpt(){
        int out,in;
        for(out=0;out<size-1;out++){
            boolean didSwap = false;
            for(in = size-1;in>out;in--){
                if(a[in-1] > a[in]){
                    swap(in-1,in);
                    didSwap = true;
                }
            }
            if (!didSwap)
                return;
        }
    }
    /**
     * 冒泡排序双向滚动
     */
    public void bubbleSortDoubleEnd(){
        int in,out1,out2;
        boolean flag = true;
        for(out2=0,out1 = size-1;out2<size-1&& out1>0;){
            if(flag){
                /**
                 * 往右冒泡
                 * out2下标左边已排好
                 */
                for(in = out2;in<out1;in++){
                    if(a[in] > a[in+1]){
                        swap(in,in+1);
                    }
                }
                flag = false;
                out1--;
            }else{
                /**
                 * 往左冒泡
                 * out1下标右边已排好
                 */
                for(in = out1;in>out2;in--){
                    if(a[in-1] > a[in]){
                        swap(in-1,in);
                    }
                }
                flag = true;
                out2++;
            }

        }
    }
    /**
     * 两下标元素交换位置
     * @param index1
     * @param index2
     */
    public void swap(int index1,int index2){
        long temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }
    /**
     * 选择排序
     */
    public void selectSort(){
        int out,in,min;
        for (out = 0;out<size;out++){
            min = out;
            for(in = out +1;in<size;in++){
                if(a[in] < a[min])
                    min = in;
            }
            swap(min,out);
        }
    }
    /**
     * 插入排序
     */
    public void insertSort(){
        for(int i=1;i<size;i++){
            int j = i-1;
            long key = a[i];
            while (j>=0 && a[j] > key){
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = key;
        }
    }
    /**
     * 删除重复元素O(n)
     */
    public void noDups(){
        insertSort();
        int dup = 0;
        for(int i=1;i<size;i++){
            if(a[i] == a[i-1]){
                dup++;
            }else{
                a[i-dup] = a[i];
            }
        }
        size -=dup;
        a = Arrays.copyOf(a,size);
    }
}
