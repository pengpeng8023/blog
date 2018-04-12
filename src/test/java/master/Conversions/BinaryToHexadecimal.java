package master.Conversions;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 将任意二进制数转换为十六进制数
 *
 * @author Nishita Aggarwal
 *
 */
public class BinaryToHexadecimal {

    /**
     * 该方法将二进制数转换为十六进制数。
     *
     * @param binary The binary number
     * @return The hexadecimal number
     */
    static String binToHex(int binary)
    {
        //在范围内为二进制数存储十六进制码:0000到1111，也就是十进制数字0到15
        HashMap<Integer,String> hm=new HashMap<>();
        //存储十六进制码的字符串
        String hex="";
        int i;
        for(i=0 ; i<10 ; i++)
        {
            hm.put(i, String.valueOf(i));
        }
        for(i=10 ; i<16 ; i++)	hm.put(i,String.valueOf((char)('A'+i-10)));
        int currbit;
        while(binary != 0)
        {
            int code4 = 0;	//将十进制数字存储为4个十进制数字
            for(i=0 ; i<4 ; i++)
            {
                currbit = binary % 10;
                binary = binary / 10;
                code4 += currbit * Math.pow(2, i);
            }
            hex= hm.get(code4) + hex;
        }
        return hex;
    }

    /**
     * Main method
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter binary number:");
        int binary = sc.nextInt();
        String hex = binToHex(binary);
        System.out.println("Hexadecimal Code:" + hex);
        sc.close();
    }
}
