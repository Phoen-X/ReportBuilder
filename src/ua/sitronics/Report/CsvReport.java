package ua.sitronics.Report;

import ua.sitronics.Help.Encoding;
import ua.sitronics.Help.ReflectionHelp;

import java.io.PrintWriter;
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
	private String divider = ",";

    public CsvReport(LinkedHashMap<String, String> map)
    {
        super(map);
    }

    public CsvReport(LinkedHashMap<String, String> fieldData, Encoding encoding)
    {
        super(fieldData, encoding);
    }

    public String getDivider()
	{
		return divider;
	}

	public void setDivider(String divider)
	{
        this.divider = divider;
	}

	protected void writeData(PrintWriter writer, ArrayList data) throws InvocationTargetException,
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
			hdr.append(ReflectionHelp.getValue(item, field));
		}

		return hdr.toString();
	}


	protected void writeHeader(PrintWriter writer)
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

    @Override
    protected void writeFooter(PrintWriter writer)
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
}
