package proxy;

import client.model.ClientModel;

import com.google.gson.*;
/**
 * 
 * @author Epper Marshall
 * This is used to translate a Java Object into JSON or to convert JSON back into an Object.
 * 
 * <br><b>Domain</b> a string in json or a java obj (gameModel)
 */
public class TranslatorJSON implements ITranslator{
	private Gson gson;
	public TranslatorJSON(){
		gson = new GsonBuilder().create();
	}
	/**
	 * Translates an object into JSON.
	 * @pre a java object (gameModel)
	 * @post returns a json string
	 */
	public String translateTo(Object obj){
		String json = gson.toJson(obj);
		return json;
	}
	/**
	 * Translates JSON into type responseCastClass(GameModel). If it returns true no translation/update is necessary.
	 * @pre json string
	 * @post returns the equivalent Java Object
	 */
	public Object translateFrom(String message, Class<?> responseCastClass){
		if(message.equals(new String("\"true\"")))
			return null;
		else {
			Object obj = gson.fromJson(message, responseCastClass);
			return obj;
		}
	}
}
