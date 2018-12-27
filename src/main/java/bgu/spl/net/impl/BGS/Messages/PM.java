package bgu.spl.net.impl.BGS.Messages;

public class PM extends Message {
    String recipient;
    String content;

    public PM(String recipient,String content){
        this.recipient=recipient;
        this.content=content;
    }

    public String getRecipient(){
        return this.recipient;
    }

    public String getContent(){
        return this.content;
    }
}
