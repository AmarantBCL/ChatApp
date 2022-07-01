package ua.hillel.chatapp.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.StreamSupport;

public class UTFBroadcastMessenger implements BroadcastMessenger {
    @Override
    public void doBroadcast(String message, Iterable<OutputStream> streams) {
        StreamSupport.stream(streams.spliterator(), false)
                .map(DataOutputStream::new)
                .forEach(out -> {
                    try {
                        out.writeUTF(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
