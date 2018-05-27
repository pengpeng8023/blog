package master.Conversions;

/**
 + *
 + * 将任何十六进制数转换为八进制
 + * @author Tanmay Joshi
 + *
 + */
import java.util.Scanner;

public class HexToOct
{
    /**
     +	 *
     +	 * 这个方法将一个十六进制数转换为十进制数
     +	 * @param The Hexadecimal Number
     +	 * @return The Decimal number
     +	 */
    public static int hex2decimal(String s)
    {
        String str = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++)
        {
            char a = s.charAt(i);
            int n = str.indexOf(a);
            val = 16*val + n;
        }
        return val;
    }

    /**
     +	 *
     +	 * 这个方法将一个十进制数转换为八进制数
     +	 * @param The Decimal Number
     +	 * @return The Octal number
     +	 */
    public static int decimal2octal(int q)
    {
        int now;
        int i=1;
        int octnum=0;
        while(q>0)
        {
            now=q%8;
            octnum=(now*(int)(Math.pow(10,i)))+octnum;
            q/=8;
            i++;
        }
        octnum/=10;
        return octnum;
    }
    // 主方法，从用户那里获得了hex输入，并将其转换为八进制。
    public static void main(String args[])
    {
        String hexadecnum;
        int decnum,octalnum;
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Hexadecimal Number : ");
        hexadecnum = scan.nextLine();

        // 首先将十六进制转换为十进制
        decnum = hex2decimal(hexadecnum);       //将字符串传递给hex2decimal函数并在变量decnum中取得小数形式
        //十进制转换为八进制
        octalnum=decimal2octal(decnum);
        System.out.println("Number in octal: "+octalnum);


    }
}

