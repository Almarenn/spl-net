package bgu.spl.net.impl.BGSServer;

import bgu.spl.net.impl.BGSServer.Messages.Message;
import bgu.spl.net.srv.BaseServer;
import bgu.spl.net.srv.Server;

public class ReactorMain {

    public static void main(String[] args) {
            Server.reactor(
                    0,
                    7777, //port
                    () -> new BGSProtocol<Message>(),//protocol factory
                    BGSEncoderDecoder::new //message encoder decoder factory
            ).serve();
        }
    }

