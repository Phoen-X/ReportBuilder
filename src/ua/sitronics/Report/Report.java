package ua.sitronics.Report;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 12.11.12
 * Time: 11:40
 */
public interface Report
{
    public void create(File file, ArrayList data) throws IOException, InvocationTargetException,
            NoSuchMethodException, NoSuchFieldException, IllegalAccessException;
}
