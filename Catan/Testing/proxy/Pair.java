package proxy;

/**
 * A template class for storing a key and value pair; to be used for header titles and values being passed to clientCommunicator that will be sent as http headers
 * @param <Key>
 * @param <Value>
 */
public class Pair<Key, Value> {
	private Key key;
	private Value value;
	
	/**
	 * @obvious
	 * @param key = header title
	 * @param value = header value
	 */
	public Pair(Key key, Value value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * 
	 * @obvious
	 */
	public Key getKey() {
		return key; 
	}
   
	/**
	 * 
	 * @obvious
	 */
	public Value getValue() {
		return value;
	}
}
