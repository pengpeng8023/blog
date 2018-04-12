package master.Conversions;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */

// Driver program
public class AnyBaseToDecimal {
    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inp = br.readLine();
        int base =  Integer.parseInt(br.readLine());

        System.out.println("Input in base " + base + " is: " + inp);
        System.out.println("Decimal value of " + inp + " is: " + convertToDecimal(inp, base));

        br.close();
    }

    /**
     * 这个方法会产生任意给定输入数量的十进制值
     * @param inp_num 字符串，我们需要十进制值和整数格式
     * @return 十进制的字符串格式
     */

    public static String convertToDecimal(String inp_num, int base) {
        int len = inp_num.length();
        int num = 0;
        int pow = 1;

        for (int i=len-1; i>=0; i--) {
            if (valOfChar(inp_num.charAt(i)) >= base) {
                return "Invalid Number";
            }
            num += valOfChar(inp_num.charAt(i))*pow;
            pow *= base;
        }
        return String.valueOf(num);
    }

    /**
     * 这个方法产生输入字符的整数值并返回它
     * @param c 我们需要的是这个的整数值
     * @return 输入char的整数值
     */

    public static int valOfChar(char c) {
        if (c >= '0' && c <= '9') {
            return (int)c - '0';
        }
        else {
            return (int)c - 'A' + 10;
        }
    }
}
