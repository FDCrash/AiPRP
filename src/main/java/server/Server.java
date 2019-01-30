package main.java.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server extends Thread {
    private static LinkedList<Bank> serverList; // список всех нитей
    private ServerSocket server;

    public Server(){
        try {
            server = new ServerSocket(2525);
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverList = new LinkedList<>();
        start();
    }

    public void run() {
        try {
            while (true) {
                Socket socket = server.accept();
                serverList.add(new Bank(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
