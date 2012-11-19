package ua.sitronics.Report;

import ua.sitronics.Help.Encoding;
import ua.sitronics.Help.ReflectionHelp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Phoen-X
 * Date: 10.11.12
 * Time: 22:11
 */
public class CsvReport extends SimpleReport
{
	private String divider = ",";  // разделитель для CSV файла
    private PrintWriter writer;


    public CsvReport(LinkedHashMap<String, String> map)
    {
        super(map);
    }

    public CsvReport(LinkedHashMap<String, String> fieldData, Encoding encoding)
    {
        super(fieldData, encoding);
    }

    @Override
    protected void after()
    {
        writer.flush();
        writer.close();
    }

    @Override
    protected void prepare(File file) throws FileNotFoundException, UnsupportedEncodingException
    {
        writer = new PrintWriter(file, encoding.getValue());
    }

    public String getDivider()
	{
		return divider;
	}

	public void setDivider(String divider)
	{
        this.divider = divider;
	}

	protected void writeData(ArrayList data) throws InvocationTargetException,
	NoSuchMethodException, NoSuchFieldException, IllegalAccessException
	{

		for (Object item : data)
		{
			String row = getItemDataRow(item);
			writer.println(row);
		}
	}

	private String getItemDataRow(Object item) throws InvocationTargetException, NoSuchMethodException,
	NoSuchFieldException, IllegalAccessException
	{
		StringBuilder hdr = new StringBuilder();
		for (String field : fields)
		{
			if(hdr.length() > 0)
				hdr.append(divider);
			hdr.append(filter(ReflectionHelp.getValue(item, field).toString()));
		}

		return hdr.toString();
	}


	protected void writeHeader()
	{
		StringBuilder hdr = new StringBuilder();
		for (String header : headers)
		{
			if(hdr.length() > 0)
				hdr.append(divider);
			hdr.append(header);
		}

		writer.println(hdr.toString());
	}

    protected void writeFooter(ArrayList data)
    {

    }


    public LinkedList<String> getHeaders()
	{
		return headers;
	}

	public LinkedList<String> getFields()
	{
		return fields;
	}

    private String filter(String value)
    {
        if(value == null || "null".equals(value))
            value = "";
        return value.trim();
    }
}
