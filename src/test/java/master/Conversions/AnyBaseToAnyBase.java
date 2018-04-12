package master.Conversions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 从“any”到“任何”其他基的类，当“any”从2-36表示。
 * 从1到十进制，再到底2。包括辅助方法
 * 确定一个数字是否对给定的基础有效。
 * @author Michael Rolland
 * @version 2017.10.10
 *
 */
public class AnyBaseToAnyBase {

    // 最小和最大的基数你想要接受为有效输入
    static final int MINIMUM_BASE = 2;
    static final int MAXIMUM_BASE = 36;

    // Driver
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String n;
        int b1=0,b2=0;
        while (true) {
            try {
                System.out.print("Enter number: ");
                n = in.next();
                System.out.print("Enter beginning base (between "+MINIMUM_BASE+" and "+MAXIMUM_BASE+"): ");
                b1 = in.nextInt();
                if (b1 > MAXIMUM_BASE || b1 < MINIMUM_BASE) {
                    System.out.println("Invalid base!");
                    continue;
                }
                if (!validForBase(n, b1)) {
                    System.out.println("The number is invalid for this base!");
                    continue;
                }
                System.out.print("Enter end base (between "+MINIMUM_BASE+" and "+MAXIMUM_BASE+"): ");
                b2 = in.nextInt();
                if (b2 > MAXIMUM_BASE || b2 < MINIMUM_BASE) {
                    System.out.println("Invalid base!");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                in.next();
            }
        }
        System.out.println(base2base(n, b1, b2));
    }

    /**
     * 检查一个数字（作为一个字符串）是否对给定的基数有效。
     */
    public static boolean validForBase(String n, int base) {
        char[] validDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
                'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                'W', 'X', 'Y', 'Z'};
        // digitsForBase包含了所有的有效数字
        char[] digitsForBase = Arrays.copyOfRange(validDigits, 0, base);

        // 为了方便包含（）方法，将字符数组转换为set
        HashSet<Character> digitsList = new HashSet();
        for (int i=0; i<digitsForBase.length; i++)
            digitsList.add(digitsForBase[i]);

        // 检查n中的每一个数字都在该基的有效数字的列表中。
        for (char c : n.toCharArray())
            if (!digitsList.contains(c))
                return false;

        return true;
    }

    /**
     * 将任何整数从b1到b2的方法。从b1到十进制，然后是十进制到b2。
     * @param n The integer to be converted.
     * @param b1 Beginning base.
     * @param b2 End base.
     * @return n in base b2.
     */
    public static String base2base(String n, int b1, int b2) {
        // 声明变量：十进制的n值，
        //b1b1b1b2的性质，
        //以及将要返回的字符串。
        int decimalValue = 0, charB2;
        char charB1;
        String output="";
        // 遍历n的每个字符
        for (int i=0; i<n.length(); i++) {
            // 将字符存储在charB1中
            charB1 = n.charAt(i);
            // 如果是一个非数字，将它转换为十进制值9并将其存储在charB2中
            if (charB1 >= 'A' && charB1 <= 'Z')
                charB2 = 10 + (charB1 - 'A');
                // 否则，将整数值保存在charB2中
            else
                charB2 = charB1 - '0';
            // 将数字转换为十进制并将其添加到
            // decimalValue n
            decimalValue = decimalValue * b1 + charB2;
        }

        //将十进制值转换为b2:
        //一个数字由十进制转换为另一个基底
        //通过不断地除以基础和记录
        //余数，直到商为零。中的数字
        //新基地是剩余物，最后剩下的部分
        //是最左边的数字

        // While the quotient is NOT zero:
        while (decimalValue != 0) {
            // 如果余数是位<10，只需将其添加到
            // 新号码的左边。
            if (decimalValue % b2 < 10)
                output = Integer.toString(decimalValue % b2) + output;
                // 如果余数是10，那么添加一个字符
                // 对新数字的相应值。（A=10 B=11 C=12，…）
            else
                output = (char)((decimalValue % b2)+55) + output;
            // 再除以新的基数
            decimalValue /= b2;
        }
        return output;
    }
}
