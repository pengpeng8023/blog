package master.Conversions;

import java.util.Scanner;

/**
 * 把任何八进制数转换成十进制数
 *
 * @author Zachary Jones
 *
 */
public class OctalToDecimal {

    /**
     * Main method
     *
     * @param args
     *            Command line arguments
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Octal Input: ");
        String inputOctal = sc.nextLine();
        int result = convertOctalToDecimal(inputOctal);
        if (result != -1)
            System.out.println("Result convertOctalToDecimal : " + result);
        sc.close();
    }

    /**
     * 这个方法将一个八进制数转换成十进制数。
     *
     * @param inputOctal
     *            The octal number
     * @return The decimal number
     */
    public static int convertOctalToDecimal(String inputOctal) {

        try {
            // 八进制的实际转换：
            Integer outputDecimal = Integer.parseInt(inputOctal, 8);
            return outputDecimal;
        } catch (NumberFormatException ne) {
            // 如果输入不是有效的八进制，打印一条警告信息
            System.out.println("Invalid Input, Expecting octal number 0-7");
            return -1;
        }
    }
}