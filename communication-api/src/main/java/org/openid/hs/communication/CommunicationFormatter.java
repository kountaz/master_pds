package org.openid.hs.communication;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.openid.hs.communication.exception.FormatObjectException;

/**
 * Communication formatter interface implemented.
 * @version R1
 * @author Steven
 * 
 */
public abstract class CommunicationFormatter {
	/**
	 * Keyword of the output format methods.
	 */
	public static final String OUTPUT_FORMAT_METHOD_KEYWORD = "outputFormat";
	/**
	 * Keyword of the input format methods.
	 */
	public static final String INPUT_FORMAT_METHOD_KEYWORD = "inputFormat";
	/**
	 * Output format methods.
	 */
	private Map<String, Method> outputFormatMethods;
	/**
	 * Input format methods.
	 */
	private Map<String, Method> inputFormatMethods;
	public CommunicationFormatter() {
		outputFormatMethods = new HashMap<String, Method>();
		inputFormatMethods = new HashMap<String, Method>();
		
		addOutputFormatMethods(this.getClass());
		addInputFormatMethods(this.getClass());
	}
	/**
	 * Tests if a type can be format for output.
	 * @param pType A type to test.
	 * @return True if the given type can be format for output.
	 */
	public final boolean canOutputFormat(String pType) {
		return outputFormatMethods.containsKey(pType);
	}
	/**
	 * Tests if a type can be format for input.
	 * @param pType A type to test.
	 * @return True if the given type can be format for input.
	 */
	public final boolean canInputFormat(String pType) {
		return inputFormatMethods.containsKey(pType);
	}
	/**
	 * Formats an object of the given type for output.
	 * @param pType A type which conducts the formatting.
	 * @param pObject An object to format.
	 * @return A formatting of the given object.
	 * @throws FormatObjectException When the given object can't be format for the given type.
	 */
	public final Object outputFormat(String pType, Object pObject) 
			throws FormatObjectException {
		
		try {
			String type = pType.substring(0, 1).toUpperCase().concat(pType.substring(1));
			if (!canOutputFormat(type))
				throw new FormatObjectException(String.format("The %s object can t be formatted for output: no method exists to format this type.", type));
			
			return format(pObject, outputFormatMethods.get(type));
		} catch (Exception e) {
			throw new FormatObjectException(e);
		}
	}
	/**
	 * Formats an object of the given type for input.
	 * @param pType A type which conducts the formatting.
	 * @param pObject An object to format.
	 * @return A formatting of the given object.
	 * @throws FormatObjectException When the given object can't be format for the given type.
	 */
	public final Object inputFormat(String pType, Object pObject) 
			throws FormatObjectException {
		
		String type = pType.substring(0, 1).toUpperCase().concat(pType.substring(1));
		if (!canInputFormat(type))
			throw new FormatObjectException(String.format("The %s object can t be formatted for input: no method exists to format this type.", type));
		
		return format(pObject, inputFormatMethods.get(type));
	}
	/**
	 * Formats an object by using a given format method.
	 * @param pObject An object to format.
	 * @param pFormatterMethods A format methods.
	 * @return A formatting of the given object.
	 * @throws FormatObjectException When the given object can't be format for the given type.
	 */
	private Object format(Object pObject, Method pMethod) 
			throws FormatObjectException {
		
		Object formatObject;
		try {
			formatObject = pMethod.invoke(this, pObject);
		} catch (Exception e) {
			throw new FormatObjectException("Formatting has failed.", e);
		}
		return formatObject;		
	}
	/**
	 * Adds output format methods from a given class.
	 * @param pFormatterClass A class which contains input format methods.
	 */
	private void addOutputFormatMethods(Class<?> pFormatterClass) {
		for (Method m : pFormatterClass.getMethods()) {
			if (!m.getName().equals(OUTPUT_FORMAT_METHOD_KEYWORD) && m.getName().startsWith(OUTPUT_FORMAT_METHOD_KEYWORD)) {
				addOutputFormatMethod(m);
			}
		}
	}
	/**
	 * Adds input format methods from a given class.
	 * @param pFormatterClass A class which contains input format methods.
	 */
	private void addInputFormatMethods(Class<?> pFormatterClass) {
		for (Method m : pFormatterClass.getMethods()) {
			if (!m.getName().equals(INPUT_FORMAT_METHOD_KEYWORD) && m.getName().startsWith(INPUT_FORMAT_METHOD_KEYWORD)) {
				addInputFormatMethod(m);
			}
		}
	}
	/**
	 * Adds output format method.
	 * @param pMethod An output format method.
	 */
	private void addOutputFormatMethod(Method pMethod) {
		outputFormatMethods.put(
				pMethod.getName().substring(OUTPUT_FORMAT_METHOD_KEYWORD.length()), 
				pMethod
			);
	}
	/**
	 * Adds input format method.
	 * @param pMethod An input format method.
	 */
	private void addInputFormatMethod(Method pMethod) {
		inputFormatMethods.put(
				pMethod.getName().substring(INPUT_FORMAT_METHOD_KEYWORD.length()), 
				pMethod
			);
	}
}
