package master.DataStructures.Graphs;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * 广度优先搜索的实现
 *
 * @author Unknown
 *
 */
public class BFS{

    /**
     * BFS在使用的代码中实现。
     *
     * @param a 在图形上执行搜索的结构，邻接矩阵等。
     * @param vertices 要使用的顶点
     * @param source 源
     */
    public static void bfsImplement(byte [][] a,int vertices,int source){  //通过邻接矩阵而不是顶点
        byte []b=new byte[vertices];    //包含每个顶点状态的旗容器
        Arrays.fill(b,(byte)-1);   //状态初始化
		/*       code   status
				 -1  =  ready
				  0  =  waiting
				  1  =  processed       */

        Stack st = new Stack();     //操作堆栈
        st.push(source);            //分配资源
        while(!st.isEmpty()){
            b[Integer.valueOf(st.peek().toString())]=(byte)0; //分配等待状态
            System.out.println(st.peek());
            int pop=Integer.valueOf(st.peek().toString());
            b[pop]=(byte)1;               //分配处理状态
            st.pop();                  //删除队列头
            for(int i=0;i<vertices;i++){
                if(a[pop][i]!=0 && b[i]!=(byte)0 && b[i]!=(byte)1 ){
                    st.push(i);
                    b[i]=(byte)0;   //分配等待状态
                }}}
    }


    /**
     * The main method
     *
     * @param args Command line arguments
     */
    public static void main(String args[]){
        Scanner in=new Scanner(System.in);
        int vertices=in.nextInt(),source=in.nextInt();
        byte [][]a=new byte [vertices][vertices];
        //初始化a的所有元素都是初始值为0

        for(int i=0;i<vertices;i++){
            int size =in.nextInt();
            for(int j=0;j<size;j++){
                a[i][in.nextInt()]=1;      //通过赋值1来获取邻接项
            }
        }
        bfsImplement(a,vertices,source);         //function call
        in.close();
    }
}
