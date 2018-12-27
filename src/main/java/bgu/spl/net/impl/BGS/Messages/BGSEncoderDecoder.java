package bgu.spl.net.impl.BGS.Messages;

import bgu.spl.net.api.MessageEncoderDecoder;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BGSEncoderDecoder implements MessageEncoderDecoder<Message> {

    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    boolean endOfMessage=false;
    short opcode=0;

    public BGSEncoderDecoder(){}


    @Override
    public Message decodeNextByte(byte nextByte) {
        pushByte(nextByte);
        int counter=0;
        if(len==2){
            this.opcode=bytesToShort(bytes);
        }
        if(opcode==1 || opcode==2 || opcode==6){
            if(nextByte==0){
                counter++;
            }
            if(counter==2){
                endOfMessage=true;
            }
        }
        if(opcode==3 || opcode==7){
            endOfMessage=true;
        }
        if(opcode==4){
            if(len==5){
                byte[] usersNum=Arrays.copyOfRange(bytes, 4,6);
                short numOfUsers=bytesToShort(usersNum);
                if(nextByte=='\0'){
                    counter++;
                }
                if(counter==numOfUsers){
                    endOfMessage=true;
                }
            }
        }
        if(opcode==5 || opcode==8){
            if(nextByte=='\0'){
                counter++;
            }
            if(counter==1){
                endOfMessage=true;
            }
        }
        if (endOfMessage) {
            return popString();
        }
        else
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
        Message m = null;
        if (opcode == 1 || opcode == 2) { //Message type is Register or Log In
            popStringRegisterOrLogIn(m);
        }
        if (opcode == 3) { //Message type is Log Out
            m = new LogOut();
        }
        if (opcode == 4) { //Message type is Follow
            popStringFollow(m);
        }
        if(opcode==5){ //Message type is Post Message
            String content= new String(bytes, 3 , len, StandardCharsets.UTF_8);
            m= new Post(content);
        }
        if(opcode==6){ //Message type is PM
            popStringPM(m);
        }
        if(opcode==7){ //Message type is User List
            m= new UserList();
        }
        if(opcode==8){ //Message type is Stat
            String username= new String(bytes, 3 , len, StandardCharsets.UTF_8);
            m= new Stat(username);
        }
        len = 0;
        return m;

    }


    public short bytesToShort(byte[] byteArr)
    {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }

    private int findIndex(byte b) {
        // find length of array
        int len = this.len;
        int i = 0;

        // traverse in the array
        while (i < len) {
            // if the i-th element is t
            // then return the index
            if (bytes[i] == b) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }

    private void popStringRegisterOrLogIn(Message m) {
        int i = findIndex((byte) 0);
        String userName = new String(bytes, 3, i, StandardCharsets.UTF_8);
        String password = new String(bytes, i + 1, len, StandardCharsets.UTF_8);
        if (opcode == 1) {
            m = new Register(userName, password);
        } else
            m = new LogIn(userName, password);
    }

    private void popStringFollow(Message m){
        boolean follow;
        if (bytes[3] == '0') {
            follow = true;
        } else {
            follow = false;
        }
        byte[] usersNum = Arrays.copyOfRange(bytes, 4, 6);
        short numOfUsers = bytesToShort(usersNum);
        List <String> userList= new LinkedList<>();
        String s="";
        for(int i=6; i<=len; i++){
            if(bytes[i]==0){
                userList.add(s);
                s="";
            }
            else{
                String c= new String(bytes, i , i+1, StandardCharsets.UTF_8);
                s=s+c;
            }
        }
        m= new Follow(follow,numOfUsers,userList);
    }

    private void popStringPM(Message m){
        int i= findIndex((byte)0);
        String username= new String(bytes, 3 , i, StandardCharsets.UTF_8);
        String content= new String(bytes, i+1 , len, StandardCharsets.UTF_8);
        m= new PM(username,content);
    }
}
