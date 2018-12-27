package bgu.spl.net.impl.BGS.Messages;

import java.util.List;

public class ACK extends Message{
    protected short opcode;
    protected short msgOpcode;

   public ACK(short opcode, short msgOpcode){
        this.opcode=opcode;
        this.msgOpcode = msgOpcode;
    }








}
