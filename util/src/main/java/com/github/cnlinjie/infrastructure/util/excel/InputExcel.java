package com.github.cnlinjie.infrastructure.util.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by linjie on 2018/1/18.
 */
public class InputExcel {
    public static void main(String[] args) {
        writeExcel(null,1,"/Users/linjie/Downloads/gao.xls");
    }

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static void writeExcel(List<Map> dataList, int cloumnCount, String finalXlsxPath){
        OutputStream out = null;
        try {
            List<List<Object[]>> lists = AnalyticalExcelUtils.readXls(new File("/Users/linjie/Downloads/data.xls"));
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            Sheet sheet = workBook.getSheetAt(0);

            List<Object[]> objects = lists.get(0);
            out =  new FileOutputStream(finalXlsxPath);
            int rowInt = 4;
            int lastRowNum = sheet.getLastRowNum();
            System.out.println(lastRowNum);
            for (Object[] o : objects) {
                if (rowInt > lastRowNum) {
                    System.out.println("高程表数据已经填充完毕");
                    break;
                }
                Row row = sheet.getRow(rowInt);
                Cell cell = row.getCell(5);
                cell.setCellValue(o[0].toString());
                rowInt+=45;
            }
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("成功");
    }
    /**
     * 判断Excel的版本,获取Workbook
     *
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException {
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if (file.getName().endsWith(EXCEL_XLS)) {  //Excel 2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}
