package proxy;

import client.model.ClientModel;

import com.google.gson.*;
/**
 * 
 * @author Epper Marshall
 * This is used to translate a Java Object into json or to convert a String back into an Object.
 */
public class TranslatorJSON implements ITranslator{
	private Gson gson;
	public TranslatorJSON(){
		gson = new GsonBuilder().create();
	}
	/**
	 * 
	 * @param obj
	 * @return Json String
	 */
	public String translateTo(Object obj){
		String json = gson.toJson(obj);
		return json;
	}
	/**
	 * 
	 * @param message
	 * @return Java Object
	 */
	public Object translateFrom(String message, Class<?> responseCastClass){
		Object obj = gson.fromJson(message, responseCastClass);
		return obj;
	}
}
