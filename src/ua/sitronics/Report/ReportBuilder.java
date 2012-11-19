package ua.sitronics.Report;

import ua.sitronics.Help.ReportFormat;

import java.util.LinkedHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 12.11.12
 * Time: 12:22
 */
public class ReportBuilder
{
    public static SimpleReport getReport(ReportFormat format, LinkedHashMap<String, String> fieldMap)
    {
        switch (format)
        {
            case CSV: return new CsvReport(fieldMap);
            case XLS: return new ExcelReport(fieldMap);
        }
        return null;
    }
}
