package ua.hillel.chatapp.security;

import java.io.IOException;
import java.net.Socket;

public interface AuthenticationManager {
    void doFilter(Socket socket, AuthenticationRequest request) throws IOException;
}
