package ua.sitronics.Help;

import java.lang.reflect.*;

/**
 * Created by IntelliJ IDEA.
 * User: Phoen-X
 * Date: 10.11.12
 * Time: 21:08
 */
public class ReflectionHelp
{
    /**
     * Возвращает название GETTER-метода для получения значения указанного поля
     * @param field Поле, значение которого нужно получить
     * @return название GETTER-метода
     */
	public static String getGetterName(Field field)
	{
		if(field == null)
			return null;

		String fieldName = field.getName();
		Class<?> type = field.getType();
		String prefix = (type.getSimpleName().equals("boolean")) ? "is" : "get";

		char ch = fieldName.charAt(0);
		ch = Character.toUpperCase(ch);
		String ending = fieldName.substring(1);
		fieldName = prefix + ch + ending;
		return fieldName;
	}

    /**
     * Возвращает значение поля обьекта по его названию. Получение значения производится посредством вызова GETTER-метода.
     * т.е. для значения поля <pre>testField</pre> будет вызван метод <pre>getTestField</pre> и возвращенное геттером значение
     * будет отдано как результат выполнения
     * @param object Экземпляр класса, значение поля которого нужно получить
     * @param fieldName Название поля
     * @return Текущее значение значение поля
     * @throws NoSuchFieldException вызывается в случае, если данного поля не существует в классе
     * @throws NoSuchMethodException вызывается в случае, если в классе не найден GETTER-метод для получения значения
     * @throws InvocationTargetException вызывается при внутренней ошибке выполнения GETTER-метода
     * @throws IllegalAccessException вызывается при ошибке доступа к методу
     */
	public static Object getValue(Object object, String fieldName) throws NoSuchFieldException, NoSuchMethodException,
	InvocationTargetException, IllegalAccessException
	{
		Field field = object.getClass().getDeclaredField(fieldName);
		if(field != null)
		{
			String getter = getGetterName(field);
			Method method = object.getClass().getDeclaredMethod(getter);
			return method.invoke(object);
		}

		throw new NoSuchFieldException("There is no field called" + field);
	}
}
