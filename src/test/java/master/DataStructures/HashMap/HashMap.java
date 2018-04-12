package master.DataStructures.HashMap;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashMap<K,V> {
    public class hmnodes{ //HashMap节点
        K key;
        V value;
    }

    private int size=0; // 的大小
    private LinkedList<hmnodes> buckets[];  //列表地址数组

    public HashMap(){
        buckets=new LinkedList[4]; //首先创建任意大小的bucket
        for(int i=0;i<4;i++)
            buckets[i]=new LinkedList<>();
    }

    public void put(K key,V value) throws Exception{
        int bi=bucketIndex(key); //找到索引，新键将被插入到该索引的linklist中
        int fountAt=find(bi,key); //检查键是否已经存在
        if(fountAt==-1){
            hmnodes temp=new hmnodes(); //如果不存在创建新的节点和插入
            temp.key=key;
            temp.value=value;
            buckets[bi].addLast(temp);
            this.size++;
        }else{
            buckets[bi].get(fountAt).value=value;//如果已经存在修改值
        }

        double lambda = (this.size*1.0)/this.buckets.length;
        if(lambda>2.0){
            rehash();  //重复函数，当lambda超过2。0时增加桶的大小
        }

        return;
    }


    public V get(K key) throws Exception{
        int bi=bucketIndex(key);
        int fountAt=find(bi,key);
        if(fountAt==-1){
            return null;
        }else{
            return buckets[bi].get(fountAt).value;
        }
    }

    public V remove(K key) throws Exception{
        int bi=bucketIndex(key);
        int fountAt=find(bi,key);
        if(fountAt==-1){
            return null;
        }else{
            this.size--;
            return buckets[bi].remove(fountAt).value;
        }
    }

    public boolean containskey(K key) throws Exception{
        int bi=bucketIndex(key);
        int fountAt=find(bi,key);
        if(fountAt==-1){
            return false;
        }else{
            return true;
        }
    }

    public int size(){
        return this.size;
    }


    public boolean isempty(){
        return this.size==0;
    }

    public ArrayList<K> keyset() throws Exception{
        ArrayList<K> arr=new ArrayList<>();
        for(int i=0;i<buckets.length;i++){
            for(int j=0;j<buckets[i].size();j++){
                arr.add(buckets[i].get(j).key);
            }
        }
        return arr;
    }

    public ArrayList<V> valueset() throws Exception{
        ArrayList<V> arr=new ArrayList<>();
        for(int i=0;i<buckets.length;i++){
            for(int j=0;j<buckets[i].size();j++){
                arr.add(buckets[i].get(j).value);
            }
        }
        return arr;
    }

    public void display() throws Exception{
        for(int i=0;i<buckets.length;i++){
            System.out.print("Bucket: "+i+" ");
            for(int j=0;j<buckets[i].size();j++){
                hmnodes temp=buckets[i].get(j);
                System.out.print("["+temp.key+"->"+temp.value+"]");
            }
            System.out.println();
        }
    }

    public int find(int bi,K key) throws Exception{
        for(int i=0;i<buckets[bi].size();i++){
            if(key.equals(buckets[bi].get(i).key))
                return i;
        }
        return -1;
    }

    public int bucketIndex(K key) throws Exception{
        int bi=key.hashCode();
        return Math.abs(bi%buckets.length);
    }

    public static void main(String[] args) throws Exception {
        HashMap m = new HashMap();
        m.put("123","1232");
        m.put("128","1232");
        m.put("127","1232");
        m.put("126","1232");
        m.put("125","1232");
        m.put("124","1232");
        m.put("136","1232");
        m.put("135","1232");
        m.put("134","1232");
        m.put("146","1232");
        m.put("145","1232");
        m.put("144","1232");
        System.out.println(m.bucketIndex("2236"));
    }
    private void rehash() throws Exception{
        LinkedList<hmnodes> ob[]= buckets;
        buckets=new LinkedList[ob.length*2];
        for(int i=0;i<ob.length*2;i++)
            buckets[i]=new LinkedList<>();

        size = 0;
        for(int i=0;i<ob.length;i++){
            for(int j=0;j<ob[i].size();j++){
                put(ob[i].get(j).key,ob[i].get(j).value);
            }
        }

    }
}
