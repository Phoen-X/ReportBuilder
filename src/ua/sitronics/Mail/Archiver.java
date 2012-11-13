package ua.sitronics.Mail;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 13.11.12
 * Time: 11:50
 */
public class Archiver
{
    ArrayList<File> files = new ArrayList<File>();

    public Archiver(ArrayList<File> files)
    {
        this.files = files;
    }

    public ArrayList<File> getFiles()
    {
        return files;
    }

    public void setFiles(ArrayList<File> files)
    {
        this.files = files;
    }
}
