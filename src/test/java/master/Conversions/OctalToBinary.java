package master.Conversions;

import java.util.Scanner;

/**
 * 把任何八进制数转换成二进制数
 *
 * @author Zachary Jones
 *
 */
public class OctalToBinary {

    /**
     * Main method
     *
     * @param args Command line arguments
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int o = sc.nextInt();
        System.out.println("Binary equivalent: " + convertOctalToBinary(o));
        sc.close();
    }

    /**
     * 这个方法将一个八进制数转换成一个二进制数。
     *
     * @param o The octal number
     * @return The binary number
     */
    public static int convertOctalToBinary(int o) {
        Scanner scan;
        int num;

        System.out.println("Octal to Binary");
        scan = new Scanner(System.in);
        System.out.println("\nEnter the number : ");
        num = Integer.parseInt(scan.nextLine(), 8);


        String binary = Integer.toBinaryString(num);
        System.out.println("Binary Value is : " + binary);
        return num;
    }
}