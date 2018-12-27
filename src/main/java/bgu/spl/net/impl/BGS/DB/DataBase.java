package bgu.spl.net.impl.BGS.DB;

import bgu.spl.net.srv.bidi.ConnectionHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class DataBase {
    ConcurrentHashMap<String,User> usersList;

    private static class DBHolder {
        private static DataBase instance = new DataBase();
    }

    public static DataBase getInstance() {
        return DBHolder.instance;
    }

    private DataBase() {
    }

    public boolean containsUserName(String s){
        return usersList.containsKey(s);
    }

    public void addUser(String s,User u){
        usersList.put(s,u);
    }

    public User getUserByName(String s){
        return usersList.get(s);
    }

    public User getUserById(int id){
        Iterator it = usersList.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            User u = (User)pair.getValue();
            if(u.getId()==id){
                return u;
            }
        }
        return null;
    }
}
