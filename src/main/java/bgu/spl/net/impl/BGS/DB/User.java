package bgu.spl.net.impl.BGS.DB;

import bgu.spl.net.impl.BGS.Messages.Notification;

import java.util.LinkedList;
import java.util.List;

public class User {
    private int id;
    private String userName;
    private String password;
    private boolean loggedIn;
    private List<String> following;
    private List<String> followers;
    private List<String> postMessages;
    private List<String> privateMessages;
    private List<Notification> unsentNotification;

    public User(String userName,String password){
        this.userName=userName;
        this.password=password;
        this.loggedIn=false;
        following= new LinkedList();
        followers= new LinkedList<>();
        postMessages= new LinkedList();
        privateMessages= new LinkedList();
        unsentNotification= new LinkedList<>();
    }

    public boolean isLoggedIn(){
        return this.loggedIn;
    }

    public void logIn(int id){
        loggedIn=true;
        this.id=id;
    }

    public void logOut(){loggedIn = false;}

    public boolean isUserOnMyList(String user){
        return following.contains(user);
    }

    public void addToFollow(String user){
        following.add(user);
    }

    public void addFollower(String user){followers.add(user);}


    public void addPost(String post) {
        postMessages.add(post);
    }

    public void addPM(String post) {
        privateMessages.add(post);
    }

    public void removeFollowing(String s){
        following.remove(s);
    }

    public void removeFollower(String s){
        followers.remove(s);
    }

    public String getPassword(){
        return this.password;
    }

    public String getUserName(){
        return this.userName;
    }

    public int getId(){
        return  id;
    }

    public List getFollowers(){
        return this.followers;
    }

    public void addNotification(Notification n){
        unsentNotification.add(n);
    }

    public void removeNotification(Notification n){
        unsentNotification.remove(n);
    }

    public List<Notification> getUnsentNotification(){
        return this.unsentNotification;
    }

    }


