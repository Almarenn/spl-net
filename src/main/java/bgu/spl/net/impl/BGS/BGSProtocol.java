package bgu.spl.net.impl.BGS;

import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.impl.BGS.DB.DataBase;
import bgu.spl.net.srv.BlockingConnectionHandler;
import bgu.spl.net.srv.ConnectionsImpl;
import bgu.spl.net.srv.bidi.ConnectionHandler;

import java.sql.Connection;
import java.util.concurrent.ConcurrentHashMap;

public class BGSProtocol implements BidiMessagingProtocol {
    private int id;
    private Connections connections;
    private DataBase DB= DataBase.getInstance();

    @Override
    public void start(int connectionId, Connections connections) {
        this.id=connectionId;
        this.connections=connections;
    }

    @Override
    public void process(Object message) {

    }

    @Override
    public boolean shouldTerminate() {
        return false;
    }
}
