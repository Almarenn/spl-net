package bgu.spl.net.impl.BGS;

import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.impl.BGS.DB.DataBase;
import bgu.spl.net.impl.BGS.DB.User;
import bgu.spl.net.impl.BGS.Messages.*;
import bgu.spl.net.impl.BGS.Messages.Error;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BGSProtocol implements BidiMessagingProtocol {
    private int id;
    private Connections connections;
    private DataBase DB = DataBase.getInstance();
    private boolean shouldTerminate;

    @Override
    public void start(int connectionId, Connections connections) {
        this.id = connectionId;
        this.connections = connections;
        shouldTerminate = false;
    }

    @Override
    public void process(Object message) {
        Message m = (Message) message;
        if (m instanceof Register) {
            processRegister((Register) m);
        }
        if (m instanceof LogIn) {
            processLogIn((LogIn) m);
        }
        if (m instanceof LogOut) {
            processLogOut((LogOut) m);
        }
        if (m instanceof Follow) {
            processFollow((Follow) m);
        }
        if (m instanceof Post) {
            processPost((Post) m);
        }
        if (m instanceof PM) {
            processPM((PM) m);
        }
        if (m instanceof UserList) {
            processUserList((UserList) m);
        }
        if (m instanceof Stat) {
            processStat((Stat) m);
        }

    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }

    private void processRegister(Register m) {
        if (DB.containsUserName(m.getUserName())) {
            connections.send(id, new Error((short) 1));
        } else {
            User u = new User(m.getUserName(), m.getPassword());
            DB.addUser(m.getUserName(), u);
            connections.send(id, new ACK((short) 1));
        }
    }

    private void processLogIn(LogIn m) {
        String userName = m.getUserName();
        if (!DB.containsUserName(userName) || !(DB.getUserByName(userName).getPassword()).equals(m.getPassword()) || DB.getUserByName(userName).isLoggedIn()) {
            connections.send(id, new Error((short) 2));
        } else {
            User u= DB.getUserByName(userName);
            u.logIn(id);
            connections.send(id, new ACK((short) 2));
            if(u.getUnsentNotification()!=null && !u.getUnsentNotification().isEmpty()){
                for(Notification n: u.getUnsentNotification()){
                    connections.send(id,n);
                }
            }
        }
    }

    private void processLogOut(LogOut m) {
        User u = DB.getUserById(id);
        if (u == null || !u.isLoggedIn()) {
            connections.send(id, new Error((short) 3));
        } else {
            u.logOut();
            this.shouldTerminate = true;
            connections.send(id, new ACK((short) 3));
            connections.disconnect(id);
        }
    }

    private void processFollow(Follow m) {
        User u = DB.getUserById(id);
        if (u == null || !u.isLoggedIn()) {
            connections.send(id, new Error((short) 4));
        }
        int numOfUsers = m.getNumOfUsers();
        int namOfSuccessful = 0;
        List usersNames = new LinkedList();
        List<String> names = m.getNameList();
        for (String name : names) {
            if (m.getFollow()==0 && !u.isUserOnMyList(name)) {
                u.addToFollow(name);
                namOfSuccessful++;
                usersNames.add(name);
                User following =  DB.getUserByName(name);
                following.addFollower(u.getUserName());
            }
            if (m.getFollow()==1 && u.isUserOnMyList(name)) {
                u.removeFollowing(name);
                namOfSuccessful++;
                usersNames.add(name);
                User unfollowing =  DB.getUserByName(name);
                unfollowing.removeFollower(u.getUserName());
            }
        }
        if(namOfSuccessful==0 && numOfUsers!=0) {
            connections.send(id, new Error((short) 4));
        }
        else{
            connections.send(id, new ACK((short)4,m.getFollow(),namOfSuccessful, usersNames));
        }
    }



    private void processPost(Post m) {
        User u = DB.getUserById(id);
        if (u == null || !u.isLoggedIn()) {
            connections.send(id, new Error((short) 5));
        }
        else{
            String post= m.getContent();
            u.addPost(post);
            List<String> toSend= new LinkedList<>();
            int i=0;
            while(i<post.length()) {
                int first = post.indexOf("@",i);
                int last= post.indexOf(" ",i);
                String s="";
                if(last!=-1){
                s= post.substring(first+1,last);}
                else {
                    s = post.substring(first + 1);
                }
                toSend.add(s);
                i=last+1;
            }

            toSend.addAll(u.getFollowers());
            connections.send(id,new ACK((short)5));
            for(String user:toSend){
                User recipient= DB.getUserByName(user);
                if(recipient!=null) {
                    Notification n = new Notification((short) 5, 1, u.getUserName(), post);
                    boolean wasSent = connections.send(recipient.getId(), n);
                    if (!wasSent) {
                        recipient.addNotification(n);
                    }
                }
            }
        }
    }

    private void processPM(PM m) {
    }

    private void processUserList(UserList m) {
    }

    private void processStat(Stat m) {
    }
}
