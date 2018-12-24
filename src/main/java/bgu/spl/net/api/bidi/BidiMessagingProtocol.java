package bgu.spl.net.api.bidi;

public interface BidiMessagingProtocol<T>  {
	//in the implementing class:
	//private int id
	//private hashmap connections
	/**
	 * Used to initiate the current client protocol with it's personal connection ID and the connections implementation
	**/
    void start(int connectionId, Connections<T> connections);
    
    void process(T message);
    //need to use .send()
	
	/**
     * @return true if the connection should be terminated
     */
    boolean shouldTerminate();
}
