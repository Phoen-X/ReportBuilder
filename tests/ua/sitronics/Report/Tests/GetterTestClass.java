package ua.sitronics.Report.Tests;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Phoen-X
 * Date: 10.11.12
 * Time: 21:25
 */
public class GetterTestClass
{
	String stringField;
	boolean boolField;
	Boolean BooleanField;
	int intField;
	ArrayList<Integer> arrField;
	ArrayList<Integer> arrFieldWithoutGetter;

	public GetterTestClass()
	{
	}

	public GetterTestClass(String stringField, int intField)
	{
		this.stringField = stringField;
		this.intField = intField;
	}

	public String getStringField()
	{
		return stringField;
	}

	public void setStringField(String stringField)
	{
		this.stringField = stringField;
	}

	public boolean isBoolField()
	{
		return boolField;
	}

	public void setBoolField(boolean boolField)
	{
		this.boolField = boolField;
	}

	public Boolean getBooleanField()
	{
		return BooleanField;
	}

	public void setBooleanField(Boolean booleanField)
	{
		BooleanField = booleanField;
	}

	public int getIntField()
	{
		return intField;
	}

	public void setIntField(int intField)
	{
		this.intField = intField;
	}

	public ArrayList<Integer> getArrField()
	{
		return arrField;
	}

	public void setArrField(ArrayList<Integer> arrField)
	{
		this.arrField = arrField;
	}
}
