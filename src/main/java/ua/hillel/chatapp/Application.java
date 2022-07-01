package ua.hillel.chatapp;

import ua.hillel.chatapp.server.ChatServer;

public class Application {
    public static void main(String[] args) {
        new ChatServer(8080);
    }
}
