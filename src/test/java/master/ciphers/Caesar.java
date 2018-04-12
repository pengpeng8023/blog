package master.ciphers;

/**
 * Describe:
 * Created by pengp on 2018/3/14.
 */

import java.util.Scanner;

/**
 * 凯撒密码的Java实现
 * 它是一种替代密码，在这个密码中，明文中的每一个字母都被一个字母替换为字母表中的固定数量的位置
 **/
public class Caesar {
    public static String encode (String message,int shift)
    {
        String encoded = "";
        for(int i = 0 ; i<message.length() ;i++)
        {
            int current = message.charAt(i); //用char来改变字符因为ascii是有序的拉丁字母
            if(current==32)
            {
                encoded += " ";
                continue;

            }
            else if (current>=65 && current<= 90)
            {
                int numAlphabet = message.charAt(i);
                if(shift + numAlphabet > 90)
                {
                    int j = 90 - numAlphabet;
                    char nextKey = (char)(65 + (shift - j - 1));
                    encoded += nextKey;

                }
                else
                {
                    char nextKey = (char)(current + shift);
                    encoded += nextKey;
                }
            }
            else if (current>=97 && current <= 122)
            {
                int numAlphabet = message.charAt(i);
                if(shift + numAlphabet > 122)
                {
                    int j = 122 - numAlphabet;
                    char nextKey = (char)(97 + (shift - j - 1));
                    encoded += nextKey;
                }
                else
                {
                    char nextKey = (char)(current + shift);
                    encoded += nextKey;
                }
            }
        }
        return encoded;
    }
    public static String decode (String message,int shift)
    {
        String decoded = "";
        for(int i = 0 ; i<message.length() ;i++)
        {
            int current = message.charAt(i);
            if(current==32)
            {
                decoded += " ";
                continue;

            }
            else if (current>=65 && current<= 90)
            {
                int numAlphabet = message.charAt(i);
                if(numAlphabet - shift < 65)
                {
                    int j = numAlphabet - 65;
                    char nextKey = (char)(90 - (shift - j - 1));
                    decoded += nextKey;

                }
                else
                {
                    char nextKey = (char)(current - shift);
                    decoded += nextKey;
                }
            }
            else if (current>=97 && current <= 122)
            {
                int numAlphabet = message.charAt(i);
                if(numAlphabet - shift < 97)
                {
                    int j = numAlphabet - 97;
                    char nextKey = (char)(122 - (shift - j - 1));
                    decoded += nextKey;
                }
                else
                {
                    char nextKey = (char)(current - shift);
                    decoded += nextKey;
                }
            }
        }
        return decoded;
    }
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the message (Latin Alphabet)");
        String message = input.nextLine();
        System.out.println(message);
        System.out.println("Please enter the shift number");
        int shift = input.nextInt() % 26;
        System.out.println("(E)ncode or (D)ecode ?");
        char choice = input.next().charAt(0);
        if(choice == 'E' || choice=='e')
            System.out.println("ENCODED MESSAGE IS \n" + encode(message,shift)); //发送我们的函数来处理
        if(choice =='D' || choice =='d')
            System.out.println("DECODED MESSAGE IS \n" + decode(message,shift));
    }

}