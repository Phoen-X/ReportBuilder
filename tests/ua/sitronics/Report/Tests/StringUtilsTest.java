package ua.sitronics.Report.Tests;

import junit.framework.Assert;
import org.junit.Test;
import ua.sitronics.Help.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 13.11.12
 * Time: 10:37
 */
public class StringUtilsTest
{
    @Test
    public void testJoinArray() throws Exception 
    {
        System.out.println("=== JOIN TEST ===");
        String[] test1 = {"1", "2", "3"};
        Assert.assertEquals("1,2,3", StringUtils.joinArray(test1, ","));
        Assert.assertEquals("1 2 3", StringUtils.joinArray(test1, " "));
        Assert.assertEquals("", StringUtils.joinArray(null, " "));
        Assert.assertEquals("123", StringUtils.joinArray(test1, ""));
        Assert.assertEquals("123", StringUtils.joinArray(test1, null));
    }
}
