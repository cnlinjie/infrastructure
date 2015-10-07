package com.github.cnlinjie.infrastructure.util.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Linjie
 * @date 2015/10/7.
 */
public class AnalyticalExcelUtils {


    public static void main(String[] args) throws IOException {
        List<List<Object[]>> lists = readXls(new File("C:\\Users\\Linjie\\Downloads\\test.xlsx"));
        System.out.println(lists);
    }



    /**
     * @param file xls 文件
     * @return 返回的List中包含多个Sheet ，一个Sheet 包含多条记录，一条记录包含多个Object
     * @throws java.io.IOException
     */
    public static List<List<Object[]>> readXls(File file) throws IOException {
        Sheet sheet;
        Workbook workbook;
        String extension = file.getName().substring(file.getName().lastIndexOf(".")+1).toLowerCase();
        List<List<Object[]>> result = new ArrayList<List<Object[]>>(); // 整个Excel返回集的容器
        List<Object[]> rows; // 一个Sheet 的容器(包含多行)
        Object[] cells; // 一行的容器
        if ("xls".equals(extension)) {
            workbook = new HSSFWorkbook(new FileInputStream(file));
        } else if ("xlsx".equals(extension)) {
            workbook = new XSSFWorkbook(new FileInputStream(file));
        } else {
            throw new IllegalArgumentException("不是正确的Excel文件");
        }

        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            sheet = workbook.getSheetAt(sheetIndex);// 获得不为空的这个sheet
            if (null != sheet) {
                rows = new ArrayList<Object[]>();
                int firstRowNum = sheet.getFirstRowNum(); // 第一行
                int lastRowNum = sheet.getLastRowNum(); // 最后一行
                for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (null != row) {// 如果行不为空，
                        short firstCellNum = row.getFirstCellNum(); // 该行的第一个单元格
                        short lastCellNum = row.getLastCellNum(); // 该行的最后一个单元格
                        cells = new Object[lastCellNum]; // 列容器
                        // 循环获取单元格信息
                        for (short cellNum = firstCellNum; cellNum <= lastCellNum; cellNum++) {
                            Cell cell = row.getCell(cellNum);
                            if (null != cell) {
                                cells[cellNum] = getValue(cell);
                            }
                        }
                        rows.add(cells); // 加一行
                    }
                }
                result.add(rows);
            }
        }
        return result;
    }

    /**
     * 得到Excel表中的值
     *
     * @param cell Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings("static-access")
    private static Object getValue(Cell cell) {
        if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return cell.getBooleanCellValue();
        } else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == cell.CELL_TYPE_FORMULA) {
            return cell.getCellFormula();
        } else {
            // 返回字符串类型的值
            return cell.getStringCellValue();
        }
    }


}
