package bgu.spl.net.impl.BGS.Messages;

import java.util.List;

public class ACKUsersList extends ACK {

    private int numOfUsers;
    private List<String> users;

    public ACKUsersList(short opcode, short userListOpcode, int numOfUsers, List<String> users) {
        super(opcode, userListOpcode);
        this.numOfUsers = numOfUsers;
        this.users = users;
    }
}
