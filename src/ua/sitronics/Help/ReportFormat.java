package ua.sitronics.Help;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 12.11.12
 * Time: 12:15
 */
public enum ReportFormat 
{
    HTML(".html", "text/html"),
    HTM(".htm",   "text/html"),
    CSV(".csv",   "text/csv"),
    XML(".xml",   "application/xml"),
    XLS(".xls",   "application/vnd.ms-excel"),
    PDF(".pdf",   "application/pdf"),
    PS (".ps",    "application/postscript"),
    TXT(".txt",   "text/plain");

    private ReportFormat(String extension, String contentType)
    {
        this.extension = extension;
        this.contentType = contentType;
    }

    public String getExtension()
    {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    private String extension;
    private String contentType;

}
