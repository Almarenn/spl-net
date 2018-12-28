package bgu.spl.net.impl.BGSServer.Messages;

import java.util.List;

public class Follow extends Message {
    int follow;
    int numOfUsers;
    List nameList;

    public Follow(int follow, int numOfUsers, List nameList){
        this.follow=follow;
        this.numOfUsers=numOfUsers;
        this.nameList=nameList;
    }

    public int getFollow(){
        return this.follow;
    }

    public List getNameList(){
        return nameList;
    }

    public int getNumOfUsers(){
        return numOfUsers;
    }
}
