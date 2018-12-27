package bgu.spl.net.impl.BGS.Messages;

public class ACKStat extends ACK{

    private int numOfPosts;
    private int numOfFollowers;
    private int numOfFollowing;


    public ACKStat(short opcode, short msgOpcode, int numOfPosts, int numOfFollowers, int numOfFollowing){
        super(opcode,msgOpcode );
        this.numOfPosts = numOfPosts;
        this.numOfFollowers = numOfFollowers;
        this.numOfFollowing = numOfFollowing;
    }
}
