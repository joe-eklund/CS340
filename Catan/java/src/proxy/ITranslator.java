package proxy;

import com.google.gson.*;

/**
 * This interface is used to set the type of communication the http body will contain (json, xml). Based on 
 * what the translator used is, it will convert the Java Object into the correct String type.
 * <br><b>Domain:</b>a string in a specific language (xml, json, etc) or a java object
 * @author Epper Marshall
 * 
 */
public interface ITranslator {
	/**
	 * Translates Java Object to correct language.
	 * @pre gets a Java object
	 * @post returns a string
	 */
	public String translateTo(Object obj);
	/**
	 * Translates from the set language into a Java Object.
	 * @pre takes in a string and the type of object to translate to 
	 * @post returns a java object of type reponseCastClass
	 */
	public Object translateFrom(String message, Class<?> responseCastClass);
}
