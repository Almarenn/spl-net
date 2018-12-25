package bgu.spl.net.impl.BGS.DB;

import bgu.spl.net.impl.BGS.Messages.Message;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String userName;
    private String password;
    boolean loggedIn;
    List<String> followers;
    List<String> postMessages;
    List<String> privateMessages;

    public User(String userName,String password){
        this.userName=userName;
        this.password=password;
        this.loggedIn=false;
        followers= new LinkedList();
        postMessages= new LinkedList();
        privateMessages= new LinkedList();
    }

    public boolean isLoggedIn(){
        return this.loggedIn;
    }

    public void loggIn(){
        loggedIn=true;
    }

    public boolean isUserOnMyList(String user){
        return followers.contains(user);
    }

    public void addFollower(String user){
        followers.add(user);
    }

    public void addPost(String post) {
        postMessages.add(post);
    }

    public void addPM(String post) {
        privateMessages.add(post);
    }

    }


