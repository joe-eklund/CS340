package proxy;

import com.google.gson.*;

/**
 * 
 * @author Epper Marshall
 * This interface is used to set the type of communication the http body will contain (json, xml). Based on 
 * what the language is it will convert the Java Object into the correct String type.
 */
public class ITranslator {
	private TranslatorJSON JSONTranslator;
	private String language;
	public ITranslator(){
		language="json";
	}
	public ITranslator(String language){
		this.language=language;
	}
	public void setLanguage(String language){
		this.language=language;
	}
	/**
	 * Translates Java Object to correct language.
	 * @param obj
	 * @return String
	 */
	public String translateTo(Object obj){
		String message="";
		switch(language){
		case "json":
			JSONTranslator=new TranslatorJSON();
			message=JSONTranslator.translateToJSON(obj);
			break;
		}
		return message;
	}
	/**
	 * Translates from the set language into a Java Object.
	 * @param message
	 * @return Java Object
	 */
	public Object translateFrom(String message){
		Object obj = null;
		switch(language){
		case "json":
			JSONTranslator=new TranslatorJSON();
			obj=JSONTranslator.translateFromJSON(message);
			break;
		}
		return obj;
	}
}
