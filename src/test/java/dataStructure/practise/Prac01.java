package dataStructure.practise;

import java.util.Arrays;

/**
 * Created by Administrator on 2018\8\12 0012.
 */
public class Prac01 {
    public static void main(String[] args) {
/*part01*/
        int maxSize = 10;
        HighArray highArray = new HighArray(maxSize);
        highArray.insert(77);
        highArray.insert(65);
        highArray.insert(23);
        highArray.insert(23);
        highArray.insert(7);
        highArray.insert(23);
        highArray.insert(234);
        highArray.insert(567);
        highArray.insert(77);
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
        highArray.noDup();
        System.out.print("noDup ");
        highArray.display();
        System.out.println("Max " + highArray.getMax());
       // System.out.println("Max " + highArray.removeMax());
        highArray.display();
        int len = highArray.size();
        long[] b = new long[len];
        for (int i=0;i<len;i++){
            long max = highArray.removeMax();
            if(max != -1L) {
                b[i] = max;
            }
        }
        System.out.print("b ");
        for (long b1:b){
            System.out.print(b1+" ");
        }
        System.out.println("");
        OrdArray ordArray = new OrdArray();
        OrdArray arr1 = new OrdArray();
        arr1.insert(77);
        arr1.insert(65);
        arr1.insert(897);
        arr1.insert(23);
        arr1.insert(23);
        arr1.insert(7);
        arr1.insert(234);
        arr1.insert(45);
        arr1.insert(567);
        arr1.insert(86);
        arr1.insert(87);
        arr1.display();
        OrdArray arr2 = new OrdArray();
        arr2.insert(47);
        arr2.insert(69);
        arr2.insert(397);
        arr2.insert(23);
        arr2.insert(24);
        arr2.insert(10);
        arr2.display();
        ordArray = ordArray.meger(arr1,arr2);
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
     * 获取最大值
     * @return
     */
    public long getMax(){
        if(nElems == 0){
            return -1L;
        }
        long max = a[0];
        for (int i=1;i<nElems;i++){
            if(max < a[i])
                max = a[i];
        }
        return max;
    }

    /**
     * 获取最大值并删除
     * @return
     */
    public long removeMax(){
        if(nElems == 0){
            return -1L;
        }
        long max = a[0];
        for (int i=1;i<nElems;i++){
            if(max < a[i]){
                max = a[i];
            }
        }
        delete(max);
        return max;
    }

    /**
     * 删除重复数
     */
    public void noDup(){
        if(nElems>1){
            for(int i= 0;i<nElems-1;i++){
                for(int j=i+1;j<nElems;j++){
                    if(a[i] == a[j])
                        a[j] = -1;
                }
            }
            for(int k=0;k<nElems;k++){
                if(a[k] == -1){
                    for (int l = k;l<nElems-1;l++){
                        a[l] = a[l+1];
                    }
                    nElems--;
                }
            }
            a = Arrays.copyOf(a,nElems);
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

    public long[] getA() {
        return a;
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
            }else if(a[mid] < value && a[mid+1] > value){
                for(int k =nElems-1;k>mid;k--){
                    a[k+1] = a[k];
                }
                a[mid+1] = value;
                nElems++;
                break;
            }else if(a[mid] >= value && a[mid-1] < value){
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
    public OrdArray meger(OrdArray arr1, OrdArray arr2){
        OrdArray ordArray = new OrdArray(arr1.size()+ arr2.size());
        if(arr1.size()>arr2.size()){
            for (int i=0;i<arr1.size();i++){
                if(i<arr2.size()){
                    ordArray.insert2(arr2.getA()[i]);
                }
                ordArray.insert2(arr1.getA()[i]);
            }
        }else{
            for (int i=0;i<arr2.size();i++){
                if(i<arr1.size()){
                    ordArray.insert2(arr1.getA()[i]);
                }
                ordArray.insert2(arr2.getA()[i]);
            }
        }
        return ordArray;
    }
}
