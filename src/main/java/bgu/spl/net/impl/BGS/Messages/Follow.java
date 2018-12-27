package bgu.spl.net.impl.BGS.Messages;

import java.util.List;

public class Follow extends Message {
    boolean follow;
    int numOfUsers;
    List nameList;

    public Follow(boolean follow, int numOfUsers, List nameList){
        this.follow=follow;
        this.numOfUsers=numOfUsers;
        this.nameList=nameList;
    }
}
