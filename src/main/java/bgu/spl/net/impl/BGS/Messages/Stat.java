package bgu.spl.net.impl.BGS.Messages;

public class Stat extends Message {
    String userName;

    public Stat(String userName){
        this.userName=userName;
    }
}
