package bgu.spl.net.impl.BGS.Messages;

public class Error extends Message {
    private short opcode;

    public Error(short opcode){
        this.opcode=opcode;
    }
}
