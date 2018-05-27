package master.ciphers;

/**
 * 柱状转位密码加密和解密.
 * @author <a href="https://github.com/freitzzz">freitzzz</a>
 */
public class ColumnarTranspositionCipher {
    private static String keyword;
    private static Object[][] table;
    private static String abecedarium;
    public static final String ABECEDARIUM="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.;:-@";
    private static final String ENCRYPTION_FIELD="≈";
    private static final char ENCRYPTION_FIELD_CHAR='≈';
    /**
     * 用柱状转位密码规则对某个字符串进行加密
     * @param word 被加密使用关键字
     * @param keyword 关键字串
     * 使用关键字的@param关键字串
     * @return 一个字符串，这个字是用柱状转位密码规则加密的。
     */
    public static String encrpyter(String word,String keyword){
        ColumnarTranspositionCipher.keyword=keyword;
        abecedariumBuilder(500);
        table=tableBuilder(word);
        Object[][] sortedTable=sortTable(table);
        String wordEncrypted="";
        for(int i=0;i<sortedTable[0].length;i++){
            for(int j=1;j<sortedTable.length;j++){
                wordEncrypted+=sortedTable[j][i];
            }
        }
        return wordEncrypted;
    }

    /**
     * 用柱状转位密码规则对某个字符串进行加密
     * @param word 被加密词
     * @param keyword 使用字符
     * @param abecedarium 使用的是使用的abecedarium。空为默认
     * @return 一个字符串，这个字是用柱状转位密码规则加密的。
     */
    public static String encrpyter(String word,String keyword,String abecedarium){
        ColumnarTranspositionCipher.keyword=keyword;
        if(abecedarium!=null){
            ColumnarTranspositionCipher.abecedarium=abecedarium;
        }else{
            ColumnarTranspositionCipher.abecedarium=ABECEDARIUM;
        }
        table=tableBuilder(word);
        Object[][] sortedTable=sortTable(table);
        String wordEncrypted="";
        for(int i=0;i<sortedTable[0].length;i++){
            for(int j=1;j<sortedTable.length;j++){
                wordEncrypted+=sortedTable[j][i];
            }
        }
        return wordEncrypted;
    }
    /**
     * 用柱状转位密码规则将一个加密的字符串去掉
     * @return 由柱状转位元密码规则解密的字符串。
     */
    public static String decrypter(){
        String wordDecrypted="";
        for(int i=1;i<table.length;i++){
            for(int j=0;j<table[i].length;j++){
                wordDecrypted+=table[i][j];
            }
        }
        return wordDecrypted.replaceAll(ENCRYPTION_FIELD,"");
    }
    /**
     * 用柱状转位密码规则，构建一个表，并将其按行排列。
     * @return 将被加密的对象填充成行和列的对象
     */
    private static Object[][] tableBuilder(String word){
        Object[][] table=new Object[rows(word)+1][keyword.length()];
        char[] wordInChards=word.toCharArray();
        //Fils in the respective numbers
        table[0]=findElements();
        int charElement=0;
        for(int i=1;i<table.length;i++){
            for(int j=0;j<table[i].length;j++){
                if(charElement<wordInChards.length){
                    table[i][j]=(char)wordInChards[charElement];
                    charElement++;
                }else{
                    table[i][j]=ENCRYPTION_FIELD_CHAR;
                }
            }
        }
        return table;
    }
    /**
     * 确定表中关于柱状转位密码规则的行数
     * @return 返回一个int数，其中包含表格的行数，以便尊重柱状转位密码规则。
     */
    private static int rows(String word){
        if((double)word.length()/keyword.length()>word.length()/keyword.length()){
            return (word.length()/keyword.length())+1;
        }else{
            return word.length()/keyword.length();
        }
    }
    private static Object[] findElements(){
        Object[] charValues=new Object[keyword.length()];
        for(int i=0;i<charValues.length;i++){
            for(int j=0;j<abecedarium.length();j++){
                if(keyword.charAt(i)==abecedarium.charAt(j))charValues[i]=j;
            }
        }
        return charValues;
    }
    private static Object[][] sortTable(Object[][] table){
        Object[][] tableSorted=new Object[table.length][table[0].length];
        for(int i=0;i<tableSorted.length;i++){
            for(int j=0;j<tableSorted[i].length;j++){
                tableSorted[i][j]=table[i][j];
            }
        }
        for(int i=0;i<tableSorted[0].length;i++){
            for(int j=i+1;j<tableSorted[0].length;j++){
                if((int)tableSorted[0][i]>(int)table[0][j]){
                    Object[] column=getColumn(tableSorted,tableSorted.length,i);
                    switchColumns(tableSorted,j,i,column);
                }
            }
        }
        return tableSorted;
    }
    private static Object[] getColumn(Object[][] table,int rows,int column){
        Object[] columnArray=new Object[rows];
        for(int i=0;i<rows;i++){
            columnArray[i]=table[i][column];
        }
        return columnArray;
    }
    private static void switchColumns(Object[][] table, int firstColumnIndex,int secondColumnIndex,Object[] columnToSwitch){
        for(int i=0;i<table.length;i++){
            table[i][secondColumnIndex]=table[i][firstColumnIndex];
            table[i][firstColumnIndex]=columnToSwitch[i];
        }
    }
    /**
     * 创建一个带有指定ascii码的abecedarium
     * @param value 基于ASCII表的字符数量
     */
    private static void abecedariumBuilder(int value){
        abecedarium="";
        for(int i=0;i<value;i++){
            abecedarium+=(char)i;
        }
    }
    private static void showTable(){
        for(int i=0;i<table.length;i++){
            for(int j=0;j<table[i].length;j++){
                System.out.print(table[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        String keywordForExample="asd215";
        String wordBeingEncrypted="This is a test of the Columnar Transposition Cipher";
        System.out.println("### Example of Columnar Transposition Cipher ###\n");
        System.out.println("Word being encryped ->>> "+wordBeingEncrypted);
        System.out.println("Word encrypted ->>> "+ ColumnarTranspositionCipher.encrpyter(wordBeingEncrypted,keywordForExample));
        System.out.println("Word decryped ->>> "+ ColumnarTranspositionCipher.decrypter());
        System.out.println("\n### Encrypted Table ###");
        showTable();
    }
}
