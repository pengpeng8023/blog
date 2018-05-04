package com.ls.iima.framework.fileImport.utils;

import com.study.blog.SpringbootApplication;
import com.study.blog.loginAndRegister.service.TestRedisService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Describe:Excel表格操作
 * Created by pengp on 2017/12/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class ExcelUtils {
    @Autowired
    private TestRedisService testRedisService;
    @Test
    public void Test01() throws FileNotFoundException {
        List l = ExcelUtils.getDataForXSSF(new FileInputStream(new File("E:\\data1.xlsx")));
        testRedisService.saveTestData(l);
    }
    /**
     * 将excel-2007版之后的excel表中的数据读取出来 xlsx
     *
     * @param in
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static List<List<Map>> getDataForXSSF(InputStream in) {
        List<List<Map>> resultList = new ArrayList<List<Map>>();
        int rowSize = 0;
        //获取excel工作本
        XSSFWorkbook wb;
        try {
            wb = (XSSFWorkbook) WorkbookFactory.create(in);

            //初始化 表
            XSSFSheet sheet = null;
            //单元格
            XSSFCell cell = null;
            for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
                //根据下表获取表
                sheet = wb.getSheetAt(sheetIndex);
                //第一行为标题
                for (int rowIndex = 0; rowIndex < sheet.getLastRowNum()+1; rowIndex++) {
                    //获取每一行数据
                    XSSFRow row = sheet.getRow(rowIndex);
                    if (row == null) {
                        continue;
                    }
                    int tempRowSize = row.getLastCellNum() + 1;
                    if (tempRowSize > rowSize) {
                        rowSize = tempRowSize;
                    }
                    List<Map> valueList = new ArrayList<Map>(rowSize);
                    boolean hasValue = false;
                    for (int columIndex = 0; columIndex < row.getLastCellNum(); columIndex++) {
                        String value = "";
                        //获取每一个单元格
                        cell = row.getCell(columIndex);
                        if (cell != null) {
                            //一定要设置编码格式
                            switch (cell.getCellType()) {
                                case XSSFCell.CELL_TYPE_STRING:
                                    value = cell.getStringCellValue();
                                    break;
                                case XSSFCell.CELL_TYPE_NUMERIC:
//                                    if (cell.getCellStyle().getDataFormat() == 176) {
//                                        Date date = cell.getDateCellValue();
//                                        if (date != null) {
//                                            value = new SimpleDateFormat("yyyy/MM/dd").format(date);
//                                        } else {
//                                            value = "";
//                                        }
//                                    } else {
                                        value = new DecimalFormat("0.00").format(cell.getNumericCellValue());
//                                    }
                                    break;
                                case XSSFCell.CELL_TYPE_FORMULA:
                                    //导入时如果是公式生成的则无值
                                    if (!"".equals(cell.getStringCellValue())) {
                                        value = cell.getNumericCellValue() + "";
                                    } else {
                                        value = cell.getNumericCellValue() + "";
                                    }
                                    break;
                                case XSSFCell.CELL_TYPE_BLANK:
                                    break;
                                case XSSFCell.CELL_TYPE_ERROR:
                                    value = "";
                                    break;
                                case XSSFCell.CELL_TYPE_BOOLEAN:
                                    value = (cell.getBooleanCellValue() == true ? "true" : "false");
                                    break;
                                default:
                                    value = "";
                            }
                        }
                        if (columIndex == 0 && "".equals(value.trim())) {
                            break;
                        }
                        Map map = new HashMap<>();
                        int statType =0;
                        if(rowIndex<20){
                            statType = 0;
                        }else if(rowIndex<40){
                            statType = 1;
                        }else if(rowIndex<60){
                            statType = 2;
                        }else if(rowIndex<80){
                            statType = 3;
                        }else{
                            statType = 4;
                        }
                        map.put("STAR_TYPE",statType);
                        map.put("FEATURE_TYPE",columIndex+1);
                        map.put("CONS_FEATURE_VALUE",value.trim());
                        valueList.add(map);
                        hasValue = true;
                    }
                    if (hasValue) {
                        resultList.add(valueList);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultList;
    }

    /**
     * 将excel-2007版之前的excel表中的数据读取出来 xls
     *
     * @param in
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static List<List<String>> getDataForHSSF(InputStream in) {
        List<List<String>> resultList = new ArrayList<List<String>>();
        int rowSize = 0;
        BufferedInputStream bis = null;
        //打开HSSFWorkBook
        POIFSFileSystem fs;
        try {
            bis = new BufferedInputStream(in);
            fs = new POIFSFileSystem(bis);

            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFCell cell;
            for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
                HSSFSheet st = wb.getSheetAt(sheetIndex);
                //第一行为标题
                for (int rowIndex = 1; rowIndex < st.getLastRowNum(); rowIndex++) {
                    HSSFRow row = st.getRow(rowIndex);
                    if (row == null) {
                        continue;
                    }
                    int tempRowSize = row.getLastCellNum() + 1;
                    if (tempRowSize > rowSize) {
                        rowSize = tempRowSize;
                    }
                    List<String> valueList = new ArrayList<>(rowSize);
                    boolean hasValue = false;
                    for (int columIndex = 0; columIndex < row.getLastCellNum(); columIndex++) {
                        String value = "";
                        cell = row.getCell(columIndex);
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case HSSFCell.CELL_TYPE_STRING:
                                    value = cell.getStringCellValue();
                                    break;
                                case HSSFCell.CELL_TYPE_NUMERIC:
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        Date date = cell.getDateCellValue();
                                        if (date != null) {
                                            value = new SimpleDateFormat("yyyy/MM/dd").format(date);
                                        } else {
                                            value = "";
                                        }
                                    } else {
                                        value = new DecimalFormat("0").format(cell.getNumericCellValue());
                                    }
                                    break;
                                case HSSFCell.CELL_TYPE_FORMULA:
                                    //导入时如果是公式生成的则无值
                                    if (!"".equals(cell.getStringCellValue())) {
                                        value = cell.getNumericCellValue() + "";
                                    } else {
                                        value = cell.getNumericCellValue() + "";
                                    }
                                    break;
                                case HSSFCell.CELL_TYPE_BLANK:
                                    break;
                                case HSSFCell.CELL_TYPE_ERROR:
                                    value = "";
                                    break;
                                case HSSFCell.CELL_TYPE_BOOLEAN:
                                    value = (cell.getBooleanCellValue() == true ? "true" : "false");
                                    break;
                                default:
                                    value = "";
                            }
                        }
                        if (columIndex == 0 && "".equals(value.trim())) {
                            break;
                        }
                        valueList.add(value.trim());
                        hasValue = true;
                    }
                    if (hasValue) {
                        resultList.add(valueList);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultList;
    }
    public static void listToExcel(List<List<Map>> lists){
        try{
            XSSFWorkbook workBook=new XSSFWorkbook();
            OutputStream out=new FileOutputStream("E:/data2.xlsx");
            Sheet sheet = workBook.getSheet("1111");

            for(int i=1;i<lists.size();i++){
                for (int j=0;j<lists.get(0).size();j++){
                    int n = (i+1) * (j+1);
                    XSSFRow row= (XSSFRow) sheet.createRow(n);
                    for (int k=0;k<3;k++){
                        XSSFCell cell=row.createCell(i);
                        switch (k){
                            case 0:
                                cell.setCellValue((Double) lists.get(i).get(j).get("STAR_TYPE"));
                                break;
                            case 1:
                                cell.setCellValue((Double) lists.get(i).get(j).get("FEATURE_TYPE"));
                                break;
                            case 2:
                                cell.setCellValue((Double) lists.get(i).get(j).get("CONS_FEATURE_VALUE"));
                                break;
                        }
                    }
                }
            }
            workBook. removeSheetAt(0);    //移除workbook中的模板sheet
            workBook.write(out);
            out.flush();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
