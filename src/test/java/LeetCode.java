import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Describe:
 * Created by pengp on 2018/3/30.
 */
public class LeetCode {
    @Test
    public void Test01(){
        String arr[] ={"gin", "zen", "gig", "msg"};
        //System.out.println(Arrays.toString(twoSum(arr,4)));

        //System.out.println(numJewelsInStones("aA","aAAbbbb"));
        //System.out.println(hammingDistance(1,4));
        //System.out.println(reverseString("12dw23dw12grg"));
        //System.out.println(uniqueMorseRepresentations(arr));
        System.out.println(selfDividingNumbers(9,22));
    }
    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    //宝石与石头
    int numJewelsInStones(String J, String S) {
        int num = 0;
        char j[] = J.toCharArray();
        char s[] = S.toCharArray();
        for(int i=0;i<s.length;i++){
            for(int k=0;k<j.length;k++){
                if(s[i] == j[k]){
                    num++;
                }
            }
        }
        return num;
    }
    //Nim游戏
    public boolean canWinNim(int n) {
        return n%4!=0;
    }
    //汉明距离
    public int hammingDistance(int x, int y) {
        //找寻二进制中1的数量，^非
        return Integer.bitCount(x^y);
    }
    //反转字符串
    public String reverseString(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
    //莫斯密码
    public int uniqueMorseRepresentations(String[] words) {
        Set set = new HashSet();
        String[] mosi = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        for(int i=0;i<words.length;i++){
            String ms = "";
            char[] chars = words[i].toCharArray();
            for(int j=0;j<chars.length;j++){
                ms = ms + mosi[chars[j]-97];
            }
            set.add(ms);
        }
        return set.size();
    }
    //自然数
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList();
        Integer num = left;
        for(;num<=right;num++){
            String numStr = num.toString();
                boolean flag = true;
                for(int i=0;i<numStr.length();i++){
                    Integer curNum = Integer.valueOf(Character.toString(numStr.charAt(i)));
                    if(curNum==0 || num % curNum !=0){
                        flag = false;
                    }
                }
                if (flag){
                    list.add(num);
                }
        }
        return list;
    }
    int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int depth_left = maxDepth(root.left) + 1;
        int depth_right = maxDepth(root.right) + 1;
        return depth_left > depth_right ? depth_left : depth_right;
    }
}
