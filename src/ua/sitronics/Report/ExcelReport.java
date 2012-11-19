package ua.sitronics.Report;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import ua.sitronics.Help.ReflectionHelp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 19.11.12
 * Time: 10:31
 */
public class ExcelReport extends SimpleReport
{
    private HSSFWorkbook wb;
    private HSSFSheet reportSheet;
    private File file;
    private int currentRow = 0;

    public ExcelReport(LinkedHashMap<String, String> map)
    {
        super(map);
    }

    @Override
    protected void after() throws IOException
    {
        FileOutputStream stream = new FileOutputStream(file);
        wb.write(stream);
        stream.flush();
        stream.close();
    }

    @Override
    protected void prepare(File file) throws IOException
    {
        wb = new HSSFWorkbook();
        this.file = file;
        reportSheet = wb.createSheet("Report");
    }

    @Override
    protected void writeHeader()
    {
        HSSFRow headRow = reportSheet.createRow(currentRow++);
        int currCell = 0;
        for (String header : headers)
        {
            HSSFCell cell = headRow.createCell(currCell);
            cell.setCellValue(header);
            currCell++;
        }
    }

    @Override
    protected void writeFooter(ArrayList data)
    {
    }

    @Override
    protected void writeData(ArrayList data) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException
    {
        for (Object item : data)
        {
            HSSFRow row = reportSheet.createRow(currentRow++);
            int cellNum = 0;
            for (String field : fields)
            {
                HSSFCell cell = row.createCell(cellNum++);
                cell.setCellValue(ReflectionHelp.getValue(item, field).toString());
            }
        }
    }
}
