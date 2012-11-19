package ua.sitronics.Help;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 12.11.12
 * Time: 11:49
 */
public enum Encoding {
    UTF_8("utf-8"),
    UTF_16("utf-16"),
    WIN_1251("Cp1251"),
    WIN_1252("Cp1252"),
    ISO8859_5("ISO8859_5"),
    CP_866("Cp866");

    private Encoding(String value)
    {
        this.value = value;
    }

    private final String value;

    public String getValue()
    {
        return value;
    }
}
