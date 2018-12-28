package bgu.spl.net.impl.BGSServer.Messages;

public class Notification extends Message {
    private short opcode;
    private char privateOrPublic;
    private String user;
    private String post;

    public Notification(short opcode, char privateOrPublic,String user, String post){
        this.opcode=opcode;
        this.privateOrPublic=privateOrPublic;
        this.user=user;
        this.post=post;
    }

    public short getOpcode(){
        return opcode;
    }

    public int getPrivateOrPublic(){
        return this.privateOrPublic;
    }

    public String getUser(){
        return this.user;
    }

    public String getPost(){
        return this.post;
    }
}
