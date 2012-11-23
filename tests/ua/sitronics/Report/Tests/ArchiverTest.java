package ua.sitronics.Report.Tests;

import junit.framework.TestCase;
import ua.sitronics.Mail.Archiver;

import java.io.File;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: VVYGULYARNIY
 * Date: 18.11.12
 * Time: 23:24
 */
public class ArchiverTest extends TestCase
{
    public void testCreate() throws Exception
    {
        System.out.println("ARCHIVER TEST");
        ArrayList<File> files = new ArrayList<File>(3);
        files.add(new File("C:\\1.txt"));
        files.add(new File("C:\\AUTOBUILD"));

        Archiver archive = new Archiver(files);
        archive.create(new File("C:\\arch.zip"));
    }
}
