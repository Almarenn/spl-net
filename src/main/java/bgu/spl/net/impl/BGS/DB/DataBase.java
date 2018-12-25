package bgu.spl.net.impl.BGS.DB;

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
}
