package proxy;

import com.google.gson.*;

/**
 * 
 * @author Epper Marshall
 * This interface is used to set the type of communication the http body will contain (json, xml). Based on 
 * what the translator used is, it will convert the Java Object into the correct String type.
 */
public interface ITranslator {
	/**
	 * Translates Java Object to correct language.
	 * @param obj
	 * @return String
	 */
	public String translateTo(Object obj);
	/**
	 * Translates from the set language into a Java Object.
	 * @param message
	 * @return Java Object
	 */
	public Object translateFrom(String message, Class<?> responseCastClass);
}
