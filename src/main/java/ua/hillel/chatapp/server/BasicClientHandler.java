package ua.hillel.chatapp.server;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BasicClientHandler implements ClientHandler {
    String name;
    DataInputStream in;
    DataOutputStream out;
    BroadcastMessenger messenger = new UTFBroadcastMessenger();
    OnLogOutEvent event;

    public interface OnLogOutEvent {
        void onClientLogOut(ClientHandler clientHandler);
    }

    public BasicClientHandler(String name, InputStream in, OutputStream out,
                              Supplier<Iterable<OutputStream>> outStreams, OnLogOutEvent event) {
        this.name = name;
        this.in = new DataInputStream(in);
        this.out = new DataOutputStream(out);
        this.event = event;
        Executors.newSingleThreadExecutor().execute(() -> listen(outStreams));
    }

    @SneakyThrows
    private void listen(Supplier<Iterable<OutputStream>> outStreams) {
        messenger.doBroadcast(name + " connected.", outStreams.get());
        while (true) {
            String message = this.in.readUTF();
            if (message.startsWith("-logout")) {
                logout();
                return;
            } else {
                messenger.doBroadcast(name + ": " + message, outStreams.get());
            }
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

    @SneakyThrows
    private void logout() {
        in.close();
        out.close();
        event.onClientLogOut(this);
    }
}
