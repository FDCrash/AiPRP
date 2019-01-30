package main.java.server;

import main.java.client.Commands;

import java.io.*;
import java.net.Socket;

public class Bank extends Thread {
    private Socket socket;
    private static int amount = 200;
    private Commands commands;
    private DataInputStream dataInputStream;
    private PrintStream printStream;

    public Bank() {
    }

    public Bank(Socket socket) throws IOException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        printStream = new PrintStream(socket.getOutputStream());
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                commands = Commands.valueOf(dataInputStream.readLine());
                if (commands == Commands.CLOSE) {
                    break;
                }
                getInfoBalance();
            }
            socket.close();
            printStream.flush();
            dataInputStream.close();
        } catch (IOException e) {
            System.out.println("Bank error");
            e.printStackTrace();
        }

    }

    public Integer getAmount() {
        return amount;
    }

    private void getInfoBalance() {
        if (commands == Commands.GET) {
            balanceChange();
            sendMessage();
        }
    }

    public void sendMessage() {
        printStream.println(amount);
        printStream.flush();
    }

    private void balanceChange() {
        int amountCur = ((int) (Math.random() * 1000));
        if (amountCur < 500) {
            amount -= amountCur;
        } else {
            amount += amountCur;
        }
    }
}
