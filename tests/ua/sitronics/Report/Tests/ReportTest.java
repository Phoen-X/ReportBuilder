package ua.sitronics.Report.Tests;

import junit.framework.Assert;
import org.junit.Test;
import ua.sitronics.Help.Encoding;
import ua.sitronics.Help.ReportFormat;
import ua.sitronics.Report.CsvReport;
import ua.sitronics.Report.ReportBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Phoen-X
 * Date: 10.11.12
 * Time: 22:15
 */
public class ReportTest
{
	@Test
	public void testReportCreation() throws Exception
	{
		System.out.println("========= TESTING REPORT CREATION ======== ");
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(2);


		String[] headers = {"целое число", "булевое значение", "Boolean Field", "Array Field"};
		String[] fields = {"целое число", "булевое значение", "Boolean Field", "Array Field"};
		
		for (int i = 0; i < headers.length; i++)
		{
			map.put(fields[i], headers[i]);
		}

		CsvReport rep = new CsvReport(map, Encoding.UTF_8);

		for (int i = 0; i < fields.length; i++)
		{
			Assert.assertEquals(fields[i], rep.getFields().get(i));
		}

		for (int i = 0; i < headers.length; i++)
		{
			Assert.assertEquals(headers[i], rep.getHeaders().get(i));
		}
	}

	@Test
	public void testReportGeneration() throws Exception
	{
		System.out.println("========= TESTING REPORT GENERATION ======== ");
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(2);


		String[] headers = {"целое число", "булевое значение", "String Field"};
		String[] fields  = {"intField",    "boolField",        "stringField"};

        //заполняем таблцу соответствия
		for (int i = 0; i < headers.length; i++)
		{
			map.put(fields[i], headers[i]);
		}

		CsvReport rep = (CsvReport) ReportBuilder.getReport(ReportFormat.CSV, map);
        // тестовые данные
		ArrayList<GetterTestClass> list = new ArrayList<GetterTestClass>();
		list.add(new GetterTestClass("test1", 1));
		list.add(new GetterTestClass("test2", 11));
		list.add(new GetterTestClass("test3", -1));
		list.add(new GetterTestClass("test4", 2));

        File repFile = new File("TestReport.txt");
		rep.create(repFile, list);

        Assert.assertTrue("Report does not exists", repFile.exists());
        Assert.assertTrue("Report is not readable", repFile.canRead());

        // Проверяем содержимое
        ArrayList<String> reportRows = new ArrayList<String>(list.size() + 1);
        BufferedReader reader = new BufferedReader(new FileReader(repFile));
        String row;
        while((row = reader.readLine()) != null)
        {
            reportRows.add(row);
        }
        
        String header = reportRows.get(0);

	}
}
