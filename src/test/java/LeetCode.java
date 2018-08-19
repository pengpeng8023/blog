import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Describe:
 * Created by pengp on 2018/3/30.
 */
public class LeetCode {

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
    /*二叉树的最大深度*/
    int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int depth_left = maxDepth(root.left) + 1;
        int depth_right = maxDepth(root.right) + 1;
        return depth_left > depth_right ? depth_left : depth_right;
    }
    /*移动路线为圈 遍历 截串 数组 替换*/
    public boolean judgeCircle(String moves) {
        int rs = moves.length() - moves.replace("R","").length();
        int ls = moves.length() - moves.replace("L","").length();
        int us = moves.length() - moves.replace("U","").length();
        int ds = moves.length() - moves.replace("D","").length();
        return rs == ls && us == ds;
    }
    //数组拆分
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i=0;i<nums.length;i=i+2){
            sum += nums[i];
        }
        return sum;
    }
    //二进制加法

    /*
     * 第一步：相加各位的值，不算进位，得到010，二进制每位相加就相当于各位做异或操作，101^111。
     * 第二步：计算进位值，得到1010，相当于各位做与操作得到101，再向左移一位得到1010，(101&111)<<1。
     * 第三步重复上述两步， 各位相加 010^1010=1000，进位值为100=(010&1010)<<1。
     * 继续重复上述两步：1000^100 = 1100，进位值为0，跳出循环，1100为最终结果。
     */
    public int getSum(int a, int b) {
        while(b!=0){
            int t = a ^ b;
            b = (a&b)<<1;
            a = t;
        }
        return a;
    }
    //写字符串需要的行数
    public int[] numberOfLines(int[] widths, String S) {
        int row = 1;
        int count = 0;
        char[] chars = S.toCharArray();
        for (int i=0;i<chars.length;i++){
            count += widths[chars[i]-97];
            if (count>100){
                row ++;
                count = widths[chars[i]-97];
            }
        }

        return new int[]{row,count};
    }
    //分糖果
    public int distributeCandies(int[] candies) {
        Set set = new HashSet();
        int len=candies.length;
        for(int i=0;i<len;i++){
            set.add(candies[i]);
        }
        return set.size()>=len/2?len/2:set.size();
    }
    class Employee{
        int id;
        int importance;
        List<Integer> subordinates;
    }
    public int getImportance(List<Employee> employees, int id) {
        int sum = 0;
        for(int i=0;i<employees.size();i++){
            if(employees.get(i).id == id){
                sum+=employees.get(i).importance;
                for(int j=0;j<employees.get(i).subordinates.size();j++){
                    sum += getImportance(employees,employees.get(i).subordinates.get(j));
                }
            }

        }
        return sum;
    }
    // Excel表列序号
    public int titleToNumber(String s) {
        int sum = 0;
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i=len-1;i>=0;i--){
           sum += (chars[i]-64)*Math.pow(26,(len-i-1));
        }
        return sum;
    }
    //键盘行
    public String[] findWords(String[] words) {
        String rex = "[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*";
        List<String> list = new ArrayList<>();
        for(int i=0;i<words.length;i++){
            if (words[i].toLowerCase().matches(rex)){
                list.add(words[i]);
            }
        }
        String[] strs = new String[list.size()];

        return list.toArray(strs);
    }
    // 托普利茨矩阵
    public boolean isToeplitzMatrix(int[][] matrix) {
        int len = matrix.length;
        for(int i=len-1;i>0;i--){
            for(int j=matrix[i].length-1;j>0;j--){
                if(matrix[i-1][j-1]!=matrix[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    //二叉树的层平均值
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null){
            return list;
        }
        q.add(root);
        while (!q.isEmpty()){
            int n = q.size();
            double sum = 0;
            for (int i=0;i<n;i++){
                TreeNode node = q.poll();
                sum +=node.val;
                if (node.left!=null) q.offer(node.left);
                if (node.right!=null) q.offer(node.right);
            }
            list.add(sum/n);
        }
        return list;
    }
    //取补数
    public int findComplement(int num) {
        int max = 0;
        int j = 0;
        while (max<num){
            max += Math.pow(2,j);
            j++;
        }
        return max - num;
    }
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList();
        for(int i=1;i<=n;i++){
            if(i%5==0 && i%3==0){
                list.add("FizzBuzz");
            }else if(i%3==0){
                list.add("Fizz");
            }else if(i%5 ==0){
                list.add("Buzz");
            }else{
                list.add(Integer.valueOf(i).toString());
            }
        }
        return list;
    }
    //各位相加
    public int addDigits(int num) {
        //return 1+ (num -1) %9;
        return num == 0?0:(num % 9==0?9:(num % 9));
    }
    //重组矩阵
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int n = nums.length,m=nums[0].length;
        if (r*c != n*m) return nums;
        int[][] result = new int[r][c];
        for (int i = 0;i<r*c;i++){
            result[i/c][i%c] = nums[i/m][i%m];
        }
        return result;
    }
    //计数二进制子串
    public int countBinarySubstrings(String s) {
        //先定义当前树的数量，前一个数的数量，结果数
        int preCount=0,curCount=1,result=0;
        for(int i =0;i<s.length()-1;i++){
            //如果后面数字和前面数字相同，当前数加1
            if(s.charAt(i) == s.charAt(i+1)) curCount++;
            else {
                //如果不同那么当前数量设置给前面数量，当前数量设置成1
                preCount = curCount;
                curCount = 1;
            }
            //判断前面数量是否比当前数量多，是的话就是需要的结果，加1
            if (preCount >= curCount) result++;
        }
        return result;
    }
    //最大连续1的数量
    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0,curNum=0,len=nums.length;
        for(int i=0;i<len;i++){
            if(nums[i] == 1) curNum++;
            else {
                curNum = 0;
            }
            result = result > curNum?result:curNum;
        }
        return result;
    }
    //棒球比赛
    public int calPoints(String[] ops) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        int sum = 0;
        for (String op:ops){
            if("C".equals(op)){
                sum -= list.removeLast();
            }else if("D".equals(op)){
                list.add(list.peekLast()*2);
                sum += list.peekLast();
            }else if("+".equals(op)){
                list.add(list.peekLast()+list.get(list.size()-2));
                sum += list.peekLast();
            }else{
                list.add(Integer.valueOf(op));
                sum +=list.peekLast();
            }
        }
        return sum;
    }
    class NumArray {
        int[] nums;
        public NumArray(int[] nums) {
            for(int i=1;i<nums.length;i++) nums[i] += nums[i-1];
            this.nums = nums;
        }

        public int sumRange(int i, int j) {
            if (i==0) return this.nums[j];
            return this.nums[j] - this.nums[i-1];
        }
    }
    //反转二叉树
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        else if (root.left == null && root.right == null) return root;
        else if (root.left == null){
            root.left = root.right;
            root.right = null;
        }else if(root.right == null){
            root.right = root.left;
            root.left = null;
        }else{
            TreeNode temp = root.right;
            root.right = root.left;
            root.left = temp;
        }
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
    //将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        TreeNode treeNode = helper(nums,0,nums.length-1);
        return treeNode;

    }
    public TreeNode helper(int[] nums,int low,int high){
        if (low >= high) { // Done
            return null;
        }
        int mid = (high+low)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums,low,mid-1);
        node.right = helper(nums,mid+1,high);
        return node;
    }
    //移动零
    public void moveZeroes(int[] nums) {
        int count=0;
        int len = nums.length;
        for(int i=0;i<len;i++)
            if (nums[i] != 0)
                nums[count++] = nums[i];
        for(;count<len;count++){
            nums[count] = 0;
        }
    }
    //合并有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode merger;
        if (l1.val < l2.val){
            merger = l1;
            merger.next = mergeTwoLists(l1.next,l2);
        }else {
            merger = l2;
            merger.next = mergeTwoLists(l2.next,l1);
        }
        return merger;
    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    //岛屿的周长
    public int islandPerimeter(int[][] grid) {
        int sum = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j] == 1){
                    sum +=4;
                    if(i<grid.length-1){
                        if(grid[i][j]==grid[i+1][j]){
                            sum -=2;
                        }
                    }
                    if(j<grid[i].length-1){
                        if(grid[i][j]==grid[i][j+1]){
                            sum -=2;
                        }
                    }
                }

            }
        }
        return sum;
    }
    //Range Addition II
    public int maxCount(int m, int n, int[][] ops) {
        return 0;
    }
    //大写判断
    public boolean detectCapitalUse(String word) {
        String wordUp = word.toUpperCase();
        String wordLow = word.toLowerCase();
        if(wordUp.equals(word) || word.equals(wordLow))
            return true;
        if(wordUp.substring(0,1).equals(word.substring(0,1)) && wordLow.substring(1).equals(word.substring(1)))
            return true;
        return false;
    }
    //数数并说
    public String countAndSay(int n) {
        return "";
    }
    /**
     * 需求：
     * 对逗号分隔的一组单词根据如下进行分组并输出(需要去除重复单词);
     * 字符串由相同个数的相同字符组成,比如,opt和pot视为一组,
     * 其中,输入的数据保证单词内不存在重复的字符
     * [实例]:输入:pot,max,tpo,hi,hide,ih,pot
     * 输出：opt,tpo,pot,
     *       hi,ih
     *       max
     *       hide
     */

    public List fenzu(String str){
        long t1 = System.currentTimeMillis();
        List<List<String>> list = new ArrayList<>();
        String [] arr1 = str.split(",");
        for (String s:arr1){
            boolean flag = true;
            label:
            for (int i=0;i<list.size();i++){
                for (int j=0;j<list.get(i).size();j++){
                    if (s.equals(list.get(i).get(j))) {
                        flag =false;
                        break label;
                    }
                }
                byte[] b1=list.get(i).get(0).getBytes();
                byte[] b2=s.getBytes();
                Arrays.sort(b1);
                Arrays.sort(b2);
                if (new String(b1).equals(new String(b2))){
                    flag =false;
                    list.get(i).add(s);
                    break label;
                }
            }
            if(flag) {
                List<String> l = new ArrayList<>();
                l.add(s);
                list.add(l);
            }

        }
        System.out.println(System.currentTimeMillis()-t1);
        return list;
    }
    //求最大面积三角形
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0;
        for(int[] i:points){
            for (int[] j:points){
                for (int[] k:points){
                    double area = 0.5*Math.abs(i[0]*j[1]+j[0]*k[1]+k[0]*i[1]-i[0]*k[1]-j[0]*i[1]-k[0]*j[1]);
                    maxArea = Math.max(maxArea,area);
                }
            }
        }
        return maxArea;
    }
    /*转置矩阵*/
    public int[][] transpose(int[][] A) {

        List<List<Integer>> list = new ArrayList<>();
        int[] a = new int[]{};
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if(list.size()<=j){
                    list.add(new ArrayList());
                }
                list.get(j).add(A[i][j]);
            }
        }
        int[][] arr = new int[list.size()][list.get(0).size()];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                arr[i][j] = list.get(i).get(j);
            }
        }
        return arr;
    }
    /*图片反转*/
    public int[][] flipAndInvertImage(int[][] A) {
        int[][] arrs = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                arrs[i][j] = 1-A[i][A[i].length-j-1];
            }
        }
        return arrs;
    }
    /* 回文素数 */
    public int primePalindrome(int N) {
        while(N < Integer.MAX_VALUE){
            if (N==1) {
                N++;
                continue;
            }
            if (N == 2){
                return N;
            }
            /*找寻下一个回文数*/
            String n = N+"";
            int l = n.length();
            List<Integer> cands = new LinkedList<>();
            int half = Integer.valueOf(n.substring(0, (l + 1) / 2));
            for (int i = half; i <= half + 1; i++) {
                String halfString = "" + i;
                if (l % 2 == 1) {
                    halfString = halfString.substring(0, halfString.length() - 1);
                }
                String newString = "" + i + new StringBuilder(halfString).reverse().toString();
                cands.add(Integer.valueOf(newString));
            }
            int ori = Integer.valueOf(n), result = Integer.MAX_VALUE;
            for (int cand : cands) {
                if (cand >= ori && cand < result) {
                    result = cand;
                }
            }
            N=result;

            /* 判断是否是素数 */
            Boolean flag = true;
            for (int i = 2; i * i <= N; i++) {
                if(N % i == 0){
                    flag = false;
                    break;
                }

            }
            if (flag){
                break;
            }
            N++;
        }
        return N;
    }
    /*山脉数组的峰顶索引*/
    public int peakIndexInMountainArray(int[] A) {
        int a = A[0];
        for (int i = 1; i < A.length; i++) {
            if(A[i] > a){
                a = A[i];
            }else{
                return i-1;
            }
        }
        return 0;
    }
    @Test
    public void Test01(){
        String arr[] ={"gin", "zen", "gig", "msg"};
        //System.out.println(Arrays.toString(twoSum(arr,4)));
        //System.out.println(numJewelsInStones("aA","aAAbbbb"));
        //System.out.println(hammingDistance(1,4));
        //System.out.println(reverseString("12dw23dw12grg"));
        //System.out.println(uniqueMorseRepresentations(arr));
        //System.out.println(selfDividingNumbers(9,22));
        //System.out.println(judgeCircle("UDLLR"));
        //System.out.println(arrayPairSum(new int[]{1,3,5,7,2,4,6,8}));
        //System.out.println(getSum(5,3));
        //System.out.println(numberOfLines(new int[]{4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10},"bbbcccdddaaa"));
        List list = new ArrayList();
        List list1 = new ArrayList();
        Employee e1 = new Employee();
        Employee e2 = new Employee();
        //[[1,2,[2]], [2,3,[]]]

        e1.id = 1;
        e1.importance = 2;
        list1.add(2);
        e1.subordinates = list1;
        e2.id = 2;
        e2.importance = 3;
        e2.subordinates = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        //System.out.println(getImportance(list,1));
        //System.out.println(titleToNumber("AA"));
        //System.out.println(findWords(new String[]{"dassds","assd","ecd"}));

        int [][] matrix = new int[][]{{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        //System.out.println(isToeplitzMatrix(matrix));
       // System.out.println(findComplement(4));
        //System.out.println(fizzBuzz(4));
        //System.out.println(addDigits(3625));
        //System.out.println(countBinarySubstrings("00110011"));
        //System.out.println(findMaxConsecutiveOnes(new int[]{1,1,1,1,1,0,0,1,1,1,}));
        BinaryTree root = new BinaryTree(4);
        root.insert(2);
        root.insert(7);
        root.insert(1);
        root.insert(3);
        root.insert(6);
        root.insert(9);
        //invertTree(root.root);
        //sortedArrayToBST(new int[]{-10,-3,0,5,9});
        int[] ints =new int[]{0,0,1};
        //moveZeroes(ints);
        int[][] grid = new int[][]{
                                    {0,1,0,0},
                                    {1,1,1,1},
                                    {0,1,1,0},
                                    {1,1,1,0}};
        //System.out.println(islandPerimeter(grid));
        //System.out.println(detectCapitalUse("F"));
        //System.out.println(fenzu("pot,max,tpo,hi,hide,ih,pot,opt,hide,hdie"));
        //System.out.println(largestTriangleArea(new int[][]{{0,0},{0,1},{1,0}}));

        //System.out.println(transpose(new int[][]{{1,2},{3,4},{5,6}}));
//        Long t = System.currentTimeMillis();
//        System.out.println(primePalindrome(9989900));
//        System.out.println(System.currentTimeMillis()-t);
//        System.out.println(flipAndInvertImage(new int[][]{{1,1,0,0},{1,0,0,1},{0,1,1,1},{1,0,1,0}}));
        System.out.println(peakIndexInMountainArray(new int[]{1,2,3,4,5,2,1}));
    }
}
