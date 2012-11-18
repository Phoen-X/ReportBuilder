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
        files.add(new File("C:\\2.txt"));
        files.add(new File("C:\\3.txt"));
        files.add(new File("C:\\1.jpg"));
        files.add(new File("C:\\AMD\\Support\\12-2_vista_win7_64_dd_ccc\\Setup.exe"));

        Archiver archive = new Archiver(files);
        archive.create(new File("C:\\arch.zip"));
    }
}
