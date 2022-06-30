package ua.hillel.chatapp.server;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {
    InputStream in();

    OutputStream out();
}
