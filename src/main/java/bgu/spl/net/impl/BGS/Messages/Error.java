package bgu.spl.net.impl.BGS.Messages;

public class Error extends Message {
    private short opcode;
    private short msgOpcode;

    public Error(short opcode, short msgOpcode){
        this.msgOpcode = msgOpcode;
        this.opcode=opcode;
    }
}
