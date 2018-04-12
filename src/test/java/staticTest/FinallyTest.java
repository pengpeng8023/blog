package staticTest;

/**
 * Describe: finally 出栈形式输出
 * Created by pengp on 2018/4/10.
 */
public class FinallyTest {
    public void test(int i){
        try{
            if(i==1){
                throw new Exception("输入大于9");
            }else if(i==2){
                throw new Exception("输入大于9");
            }else if(i==3){
                throw new Exception("输入大于9");
            }
            System.out.println("aaaaaa");
        }catch (Exception e) {
            test(++i);
        } finally {
            if (i==1){
                System.out.println("1111111");
            }else if(i==2){
                System.out.println("2222222");
            }else if(i==3){
                System.out.println("3333333");
            }else if(i==4){
                System.out.println("4444444");
            }
        }
    }

    public static void main(String[] args) {
        FinallyTest test = new FinallyTest();
        test.test(1);
        /**
         *  aaaaaa
         *  4444444
         *  3333333
         *  2222222
         *  1111111
         */
    }
}
