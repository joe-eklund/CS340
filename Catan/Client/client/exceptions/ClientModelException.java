package client.exceptions;

public class ClientModelException extends Exception{
	//Parameterless Constructor
    public ClientModelException() {}

    //Constructor that accepts a message
    public ClientModelException(String message)
    {
       super(message);
    }
}
