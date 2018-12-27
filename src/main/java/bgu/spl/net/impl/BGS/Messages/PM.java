package bgu.spl.net.impl.BGS.Messages;

public class PM extends Message {
    String recipient;
    String content;

    public PM(String recipient,String content){
        this.recipient=recipient;
        this.content=content;
    }
}
