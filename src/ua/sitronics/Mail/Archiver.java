package ua.sitronics.Mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 13.11.12
 * Time: 11:50
 */
public class Archiver
{
    ArrayList<File> files = new ArrayList<File>(1);

    public Archiver(ArrayList<File> files)
    {
        this.files = files;
    }

    public Archiver(File file)
    {
        files.add(file);
    }

    public ArrayList<File> getFiles()
    {
        return files;
    }

    public void setFiles(ArrayList<File> files)
    {
        this.files = files;
    }

    public void create(File pathTo) throws IOException
    {
        ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(pathTo));
        byte[] bytes = new byte[4096];
        int bytesRead;

        for (File file : files)
        {
            //пропускаем неподходящие нам файлы
            if (file == null || !file.exists() || !file.isFile() || !file.canRead())
                continue;
            ZipEntry entry = new ZipEntry(file.getName());
            zip.putNextEntry(entry);
            FileInputStream reader = new FileInputStream(file);
            while ((bytesRead = reader.read(bytes)) != -1)
            {
                zip.write(bytes,0,bytesRead);
            }
            reader.close();
        }
        zip.flush();
        zip.close();
    }
}
