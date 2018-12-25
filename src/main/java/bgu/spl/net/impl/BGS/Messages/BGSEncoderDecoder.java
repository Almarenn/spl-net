package bgu.spl.net.impl.BGS.Messages;

import bgu.spl.net.api.MessageEncoderDecoder;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

public class BGSEncoderDecoder implements MessageEncoderDecoder<Message> {

    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    HashMap<Short, Character> finishBytes;
    Character finishByte;
    short opcode;

    public BGSEncoderDecoder(){
        finishBytes.put((short)1,'\0');
        finishBytes.put((short)2,'\0');
        finishBytes.put((short)3,'3');
        finishBytes.put((short)4,'\0');
        finishBytes.put((short)5,'\0');
        finishBytes.put((short)6,'\0');
        finishBytes.put((short)7,'7');
        finishBytes.put((short)8,'\0');
    }


    @Override
    public Message decodeNextByte(byte nextByte) {
    if (finishByte!=null && nextByte == finishByte) {
            return popString();
        }

        pushByte(nextByte);
        if(bytes.length==2){
            byte[] twoBytesArray= new byte[2];
            for(int i=0; i<2;i++){
                twoBytesArray[i]=bytes[i];
            }
            this.opcode=bytesToShort(twoBytesArray);
            finishByte = finishBytes.get(this.opcode);

        }
        return null; //not a message yet
    }

    @Override
    public byte[] encode(Message message) {
        byte[] bytes = (message + "\n").getBytes();
        return bytes; //uses utf8 by default
    }


   private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
    }

    private Message popString() {
        Message m=null;
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        len = 0;
        if(opcode=='1'){
            m= new Register();
        }
        if(opcode=='2'){

        }
        return m;
    }


    public short bytesToShort(byte[] byteArr)
    {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }

}
