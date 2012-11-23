package ua.sitronics.Report;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
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
    private HSSFCellStyle headerCellStyle;
    private HSSFCellStyle dataCellStyle;
    private File file;
    private int currentRow = 0;
    private final int COL_WIDTH_MODIFIER = 256;
    private final int MAX_COL_WIDTH = 40*COL_WIDTH_MODIFIER;

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
        if(headerCellStyle == null)
            headerCellStyle = createHeaderCellStyle();
        if(dataCellStyle == null)
            dataCellStyle = createDataCellStyle();
    }

    private HSSFCellStyle createDataCellStyle()
    {
        HSSFCellStyle style = wb.createCellStyle();
        //границы
        short border = HSSFCellStyle.BORDER_THIN;
        style.setBorderBottom(border);
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        // шрифты
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        style.setFont(font);
        style.setWrapText(true);
        style.setAlignment(HSSFCellStyle.ALIGN_FILL);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;
    }

    private HSSFCellStyle createHeaderCellStyle()
    {
        HSSFCellStyle style = wb.createCellStyle();
        // закраска
        style.setFillBackgroundColor(HSSFColor.BLACK.index);
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //границы
        short border = HSSFCellStyle.BORDER_THIN;
        style.setBorderBottom(border);
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        // шрифт
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setWrapText(true);

        return style;
    }

    @Override
    protected void writeHeader()
    {
        HSSFRow headRow = reportSheet.createRow(currentRow++);
        int currCell = 0;
        for (String header : headers)
        {
            HSSFCell cell = headRow.createCell((short) currCell);
            cell.setCellValue(header);
            cell.setCellStyle(headerCellStyle);
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

            short cellNum = 0;
            for (String field : fields)
            {
                HSSFCell cell = row.createCell(cellNum++);
                cell.setCellStyle(dataCellStyle);
                cell.setCellValue(ReflectionHelp.getValue(item, field).toString());
            }
        }
    }

    public HSSFCellStyle getHeaderCellStyle()
    {
        return headerCellStyle;
    }

    public void setHeaderCellStyle(HSSFCellStyle headerCellStyle)
    {
        this.headerCellStyle = headerCellStyle;
    }

    public HSSFCellStyle getDataCellStyle()
    {
        return dataCellStyle;
    }

    public void setDataCellStyle(HSSFCellStyle dataCellStyle)
    {
        this.dataCellStyle = dataCellStyle;
    }
}
