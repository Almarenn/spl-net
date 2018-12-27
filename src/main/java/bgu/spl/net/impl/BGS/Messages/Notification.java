package bgu.spl.net.impl.BGS.Messages;

public class Notification extends Message {
    private short opcode;
    private int privateOrPublic;
    private String user;
    private String post;

    public Notification(short opcode, int privateOrPublic,String user, String post){
        this.opcode=opcode;
        this.privateOrPublic=privateOrPublic;
        this.user=user;
        this.post=post;
    }
}
