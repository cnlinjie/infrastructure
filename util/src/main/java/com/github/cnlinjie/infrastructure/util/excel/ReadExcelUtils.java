package com.github.cnlinjie.infrastructure.util.excel;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * 读取Excel表格
 * Created by linjie on 2017/4/8.
 */
public class ReadExcelUtils {

    public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";

    private static final Logger logger = LoggerFactory.getLogger(ReadExcelUtils.class);

    public static List<List> readExcel(String path) throws IOException {
        logger.debug("ReadExcel,path:{}",path);
        List<List> allData = Lists.newArrayList();
        if (path == null) {
            return null;
        } else {
            String postfix = path.substring(path.lastIndexOf(".") + 1).toLowerCase();
            try {
                InputStream input = new FileInputStream(path);  //建立输入流
                Workbook wb = null;
                //根据文件格式(2003或者2007)来初始化
                if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    wb = new HSSFWorkbook(input);
                } else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    wb = new XSSFWorkbook(input);
                }
                Sheet sheet = wb.getSheetAt(0);     //获得第一个表单
                Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器
                List cellsData = null;
                while (rows.hasNext()) {
                    Row row = rows.next();  //获得行数据
                    Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器
                    cellsData = Lists.newArrayList();
                    allData.add(cellsData);
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        switch (cell.getCellType()) {   //根据cell中的类型来输出数据
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                cellsData.add(cell.getNumericCellValue());
                                break;
                            case HSSFCell.CELL_TYPE_STRING:
                                cellsData.add(cell.getStringCellValue());
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                cellsData.add(cell.getBooleanCellValue());
                                break;
                            case HSSFCell.CELL_TYPE_FORMULA:
                                cellsData.add(cell.getCellFormula());
                                break;
                            default:
                                logger.error("unsuported sell type");
                                break;
                        }
                    }
                }
                return allData;
            } catch (IOException ex) {
                logger.error("", ex);
                return null;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        List<List> lists = ReadExcelUtils.readExcel("/Users/linjie/Projects/cdgx_java/人员角色模版.xls");
        System.out.println(lists);
    }

}
