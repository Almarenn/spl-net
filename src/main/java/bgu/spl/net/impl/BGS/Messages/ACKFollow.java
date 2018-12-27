package bgu.spl.net.impl.BGS.Messages;

import java.util.List;

public class ACKFollow extends ACK {

    private int follow;
    private int numOfUsers;
    private List<String> users;


    public ACKFollow(short opcode,short msgOpcode, int follow, int numOfUsers, List users){
        super(opcode,msgOpcode);
        this.follow=follow;
        this.numOfUsers=numOfUsers;
        this.users = users;
    }

}
