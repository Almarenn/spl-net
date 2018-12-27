package bgu.spl.net.impl.BGS.Messages;

import java.util.List;

public class ACK extends Message{
    private short opcode;
    private int follow;
    private int numOfUsers;
    private List users;


    public ACK(short opcode){
        this.opcode=opcode;
    }

    public ACK(short opcode, int follow, int numOfUsers, List users){
        this.opcode=opcode;
        this.follow=follow;
        this.numOfUsers=numOfUsers;
        this.users = users;
    }

}
