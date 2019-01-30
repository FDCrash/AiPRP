package main.java.client;

import java.io.*;
import java.net.Socket;

public class User extends Thread {
    private PrintStream printStream;
    private DataInputStream dataInputStream;
    private int amount;

    public User() {
        try {
            Socket socket = new Socket("127.0.0.1", 2525);
            dataInputStream = new DataInputStream(socket.getInputStream());
            printStream = new PrintStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println("Ошибка: кон  " + e);
        }
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public void run() {
        try {
            printStream.println(Commands.GET.name());
            amount = Integer.valueOf(dataInputStream.readLine());
        } catch (Exception e) {
            System.out.println("ERROR+" + e);
        }
    }

    public void closeConnection() {
        printStream.println(Commands.CLOSE.name());
        printStream.flush();
        try {
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

