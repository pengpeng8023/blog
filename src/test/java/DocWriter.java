import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.util.*;
import java.util.Map.Entry;
/**
 * Created by zhouhs on 2017/1/5.
 */
public class DocWriter {

    public static void searchAndReplace(String srcPath, String destPath,Map<String, String> map) {
        try {
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));
            /**
             * 替换段落中的指定文字
             */
            Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
            while (itPara.hasNext()) {
                XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
                Set<String> set = map.keySet();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    List<XWPFRun> run=paragraph.getRuns();
                    for(int i=0;i<run.size();i++)
                    {
                        if(run.get(i).getText(run.get(i).getTextPosition())!=null &&
                                run.get(i).getText(run.get(i).getTextPosition()).equals(key))
                        {
                            /**
                             * 参数0表示生成的文字是要从哪一个地方开始放置,设置文字从位置0开始
                             * 就可以把原来的文字全部替换掉了
                             */
                            run.get(i).setFontSize(9);
                            run.get(i).setText(map.get(key),0);
                        }
                    }
                }
            }

            /**
             * 替换表格中的指定文字
             */
            Iterator<XWPFTable> itTable = document.getTablesIterator();
            while (itTable.hasNext()) {
                XWPFTable table = (XWPFTable) itTable.next();
                int count = table.getNumberOfRows();
                for (int i = 0; i < count; i++) {
                    XWPFTableRow row = table.getRow(i);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        for (Entry<String, String> e : map.entrySet()) {
                            if (cell.getText().equals(e.getKey())) {
                                cell.removeParagraph(0);
                                XWPFParagraph pIO =cell.addParagraph();
                                XWPFRun rIO = pIO.createRun();
                                rIO.setFontSize(9);
                                rIO.setText(e.getValue());
                            }
                        }
                    }
                }
            }
            FileOutputStream outStream = null;
            outStream = new FileOutputStream(destPath);
            document.write(outStream);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("${name}", "xxx");
        params.put("${sex}", "男");
        params.put("${political}", "共青团员");
        params.put("${place}", "sssss");
        params.put("${classes}", "3102");
        params.put("${id}", "213123123");
        params.put("${qq}", "213123");
        params.put("${tel}", "312313213");
        params.put("${oldJob}", "sadasd");
        params.put("${swap}", "是");
        params.put("${first}", "asdasd");
        params.put("${second}", "综合事务部");
        params.put("${award}", "asda");
        params.put("${achievement}", "完成科协网站的开发");
        params.put("${advice}", "没有建议");
        params.put("${attach}", "无");
        String srcPath = "E:\\sta.docx";
        String destPath = "E:\\sta2.doc";
        searchAndReplace(srcPath, destPath, params);
    }
}