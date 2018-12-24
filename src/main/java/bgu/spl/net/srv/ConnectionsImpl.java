package bgu.spl.net.srv;

import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.srv.bidi.ConnectionHandler;

import java.util.HashMap;

public class ConnectionsImpl<T> implements Connections<T> {

    private HashMap<Integer, ConnectionHandler<T>> connections;

    public ConnectionsImpl(){
        this.connections = new HashMap<>();
    }

    @Override
    public boolean send(int connectionId, T msg) {
        ConnectionHandler<T> handler = connections.get(connectionId);
        if(handler!=null){
            handler.send(msg);
            return true;
        }
        return false;
    }

    @Override
    public void broadcast(T msg) {

    }

    @Override
    public void disconnect(int connectionId) {

    }

    public void add(int id, ConnectionHandler<T> handler){
        connections.put(id,handler);
    }
}
