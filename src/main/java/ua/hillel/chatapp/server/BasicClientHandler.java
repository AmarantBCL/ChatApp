package ua.hillel.chatapp.server;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Supplier;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BasicClientHandler implements ClientHandler {
    String name;
    DataInputStream in;
    DataOutputStream out;
    BroadcastMessenger messenger = new UTFBroadcastMessenger();

    public BasicClientHandler(String name, InputStream in, OutputStream out, Supplier<Iterable<OutputStream>> outStreams) {
        this.name = name;
        this.in = new DataInputStream(in);
        this.out = new DataOutputStream(out);
        listen(outStreams.get());
    }

    @SneakyThrows
    private void listen(Iterable<OutputStream> outStreams) {
        messenger.doBroadcast(name + " connected.", outStreams);
        while (true) {
            String message = this.in.readUTF();
            messenger.doBroadcast(message, outStreams);
        }
    }

    @Override
    public InputStream in() {
        return in;
    }

    @Override
    public OutputStream out() {
        return out;
    }
}
