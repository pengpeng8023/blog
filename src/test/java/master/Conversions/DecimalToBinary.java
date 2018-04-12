package master.Conversions;

import java.util.Scanner;

/**
 * 这个类把一个十进制数转换成二进制数
 * @author Unknown
 *
 */
public class DecimalToBinary {

    /**
     * Main Method
     *
     * @param args Command Line Arguments
     */
    public static void main(String args[]) {
        conventionalConversion();
        bitwiseConversion();
    }

    /**
     * 该方法使用传统算法将十进制数转换为二进制数。
     */
    public static void conventionalConversion() {
        int n, b = 0, c = 0, d;
        Scanner input = new Scanner(System.in);
        System.out.printf("Conventional conversion.\n\tEnter the decimal number: ");
        n = input.nextInt();
        while (n != 0) {
            d = n % 2;
            b = b + d * (int) Math.pow(10, c++);
            n /= 2;
        } //converting decimal to binary
        System.out.println("\tBinary number: " + b);
    }

    /**
     * 这个方法使用一个位算法将一个十进制数转换成二进制数
     */
    public static void bitwiseConversion() {
        int n, b = 0, c = 0, d;
        Scanner input = new Scanner(System.in);
        System.out.printf("Bitwise conversion.\n\tEnter the decimal number: ");
        n = input.nextInt();
        while (n != 0) {
            d = (n & 1);
            b += d * (int) Math.pow(10, c++);
            n >>= 1;
        }
        System.out.println("\tBinary number: " + b);
    }

}

