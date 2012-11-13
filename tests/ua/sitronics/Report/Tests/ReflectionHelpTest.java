package ua.sitronics.Report.Tests;

import org.junit.Test;
import org.junit.Assert;
import ua.sitronics.Help.ReflectionHelp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by IntelliJ IDEA.
 * User: Phoen-X
 * Date: 10.11.12
 * Time: 21:13
 */
public class ReflectionHelpTest
{
	@Test
	public void testGetGetterName() throws Exception
	{
		System.out.println("=== GETTER METHOD TEST ===");
		Class cl = GetterTestClass.class;
		doGetterTest(cl.getDeclaredField("intField"), "getIntField");
		doGetterTest(cl.getDeclaredField("boolField"), "isBoolField");
		doGetterTest(cl.getDeclaredField("BooleanField"), "getBooleanField");
		doGetterTest(cl.getDeclaredField("arrField"), "getArrField");
		doGetterTest(null, null);
		System.out.println("=============================");
	}

	@Test
	public void testGetValue() throws Exception
	{
		System.out.println("=== GETTER METHOD TEST ===");

		GetterTestClass testClass = new GetterTestClass();
		testClass.BooleanField = Boolean.TRUE;
		testClass.boolField = false;
		testClass.stringField = null;
		testClass.intField = -15;

		doValueTest(testClass, "intField", -15);
		doValueTest(testClass, "stringField", null);
		doValueTest(testClass, "boolField", false);
		doValueTest(testClass, "BooleanField", true);
		doValueTest(testClass, "arrField", null);
		System.out.println("=============================");
	}

	@Test(expected = NoSuchFieldException.class)
	public void testWrongFieldGetValue() throws Exception
	{
		GetterTestClass testClass = new GetterTestClass();
		System.out.println("Testing getting value for field: WrongField");
		ReflectionHelp.getValue(testClass, "WrongField");
	}

	@Test(expected = NoSuchMethodException.class)
	public void testWrongMethodGetValue() throws Exception
	{
		GetterTestClass testClass = new GetterTestClass();
		System.out.println("Testing getting value for field without getter: arrFieldWithoutGetter");
		ReflectionHelp.getValue(testClass, "arrFieldWithoutGetter");
	}
	
	private void doValueTest(GetterTestClass obj, String field, Object expected) throws InvocationTargetException,
	NoSuchMethodException, NoSuchFieldException, IllegalAccessException
	{
		System.out.print("Testing getting value for field: " + field);
		Assert.assertEquals(expected, ReflectionHelp.getValue(obj, field));
		System.out.println("\t |PASS|");
	}
	
	private void doGetterTest(Field fld, String expected)
	{
		System.out.print("Testing getter generation for field: " + fld);
		Assert.assertEquals(expected, ReflectionHelp.getGetterName(fld));
		System.out.println("\t |PASS|");

	}
}
