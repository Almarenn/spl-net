package bgu.spl.net.impl.BGSServer;

import bgu.spl.net.impl.BGSServer.Messages.Message;
import bgu.spl.net.srv.BaseServer;

public class TPCMain {

    public static void main(String[] args) {
        BaseServer.threadPerClient(
               7777, //port
                () -> new BGSProtocol<Message>(),//protocol factory
               BGSEncoderDecoder::new //message encoder decoder factory
        ).serve();
    }
}
