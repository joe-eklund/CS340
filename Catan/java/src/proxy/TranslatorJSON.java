package proxy;

import com.google.gson.*;
/**
 * 
 * @author Epper Marshall
 * This is used to translate a Java Object into json or to convert a String back into an Object.
 */
public class TranslatorJSON {
	private Gson gson;
	public TranslatorJSON(){
		gson = new GsonBuilder().create();
	}
	/**
	 * 
	 * @param obj
	 * @return Json String
	 */
	public String translateToJSON(Object obj){
		String json = gson.toJson(obj);
		return json;
	}
	/**
	 * 
	 * @param message
	 * @return Java Object
	 */
	public Object translateFromJSON(String message){
		Object obj = gson.fromJson(message, Object.class);
		return obj;
	}
}
