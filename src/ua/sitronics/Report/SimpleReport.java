package ua.sitronics.Report;

import ua.sitronics.Help.Encoding;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 12.11.12
 * Time: 11:43
 */
public abstract class SimpleReport implements Report
{
    protected LinkedList<String> headers;
    protected LinkedList<String> fields;
    protected Encoding encoding = Encoding.UTF_8;

    protected SimpleReport()
    {

    }

    public SimpleReport(LinkedHashMap<String, String> fieldData)
    {
        headers = new LinkedList<String>();
        fields = new LinkedList<String>();
        for (Map.Entry<String, String> entry : fieldData.entrySet())
        {
            fields.add(entry.getKey());
            headers.add(entry.getValue());
        }
    }

    public Encoding getEncoding()
    {
        return encoding;
    }

    public LinkedList<String> getHeaders() {
        return headers;
    }

    public LinkedList<String> getFields() {
        return fields;
    }

    public void setEncoding(Encoding encoding) {
        this.encoding = encoding;
    }

    public SimpleReport(LinkedHashMap<String, String> fieldData, Encoding encoding)
    {
        this(fieldData);
        this.encoding = encoding;
    }

    public void create(File file, ArrayList data) throws IOException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException
    {
        PrintWriter writer = new PrintWriter(file, encoding.getValue());

        writeHeader(writer);
        writeData(writer, data);
        writeFooter(writer);

        writer.flush();
        writer.close();
    }

    protected abstract void writeHeader(PrintWriter writer);

    protected abstract void writeFooter(PrintWriter writer);

    protected abstract void writeData(PrintWriter writer, ArrayList data) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException;
}
