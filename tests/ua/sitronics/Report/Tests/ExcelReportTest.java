package ua.sitronics.Report.Tests;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import ua.sitronics.Help.ReportFormat;
import ua.sitronics.Report.ExcelReport;
import ua.sitronics.Report.ReportBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 19.11.12
 * Time: 10:44
 */
public class ExcelReportTest
{
    @Test
    public void testCreate() throws Exception
    {
        System.out.println("========= TESTING EXCEL REPORT GENERATION ======== ");
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(2);


        String[] headers = {"целое число", "булевое значение", "String Field"};
        String[] fields  = {"intField",    "boolField",        "stringField"};

        //заполняем таблцу соответствия
        for (int i = 0; i < headers.length; i++)
        {
            map.put(fields[i], headers[i]);
        }

        ExcelReport rep = (ExcelReport) ReportBuilder.getReport(ReportFormat.XLS, map);
        // тестовые данные
        ArrayList<GetterTestClass> list = new ArrayList<GetterTestClass>();
        list.add(new GetterTestClass("test1", 1));
        list.add(new GetterTestClass("test2", 11));

        File repFile = new File("TestReport.xls");
        rep.create(repFile, list);

        System.out.println("checking report created");

        Assert.assertTrue(repFile.exists());
        Assert.assertTrue(repFile.isFile());
        Assert.assertTrue(repFile.canRead());

        System.out.println("trying to open report");

        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(repFile));
        HSSFSheet sheet = wb.getSheet("Report");

        Assert.assertTrue(sheet != null);

        HSSFRow headerRow = sheet.getRow(0);

        Assert.assertTrue(headerRow != null);

        int cellId = 0;
        for (String header : headers)
        {
            HSSFCell cell = headerRow.getCell(cellId++);
            System.out.println("checking title: " + header);
            Assert.assertEquals(header, cell.getStringCellValue());
        }

        System.out.println("checking data");
        HSSFRow row = sheet.getRow(1); // 1 обьект
        Assert.assertEquals("1", row.getCell(0).getStringCellValue());
        Assert.assertEquals("false", row.getCell(1).getStringCellValue());
        Assert.assertEquals("test1", row.getCell(2).getStringCellValue());

        row = sheet.getRow(2); // 2 обьект
        Assert.assertEquals("11", row.getCell(0).getStringCellValue());
        Assert.assertEquals("false", row.getCell(1).getStringCellValue());
        Assert.assertEquals("test2", row.getCell(2).getStringCellValue());

        repFile.delete();
    }
}
