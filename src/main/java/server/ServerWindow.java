package main.java.server;

import main.java.client.User;
import main.java.client.UserWindow;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerWindow extends Frame {
    private Button client = new Button("Client");
    private TextArea textArea = new TextArea();
    private Button clear = new Button("Clear");
    private Button current = new Button("Current");
    private Server server;
    private Bank bank;

    public ServerWindow() {
        super("Server");
        server = new Server();
        bank = new Bank();
        setLayout(null);
        setBackground(new Color(0, 200, 0));
        setSize(300, 200);
        add(clear);
        add(current);
        add(client);
        add(textArea);
        textArea.setBounds(110, 50, 100, 50);
        client.setBounds(110, 100, 100, 20);
        current.setBounds(110, 125, 100, 20);
        clear.setBounds(110, 150, 100, 20);
        this.show();
        this.setLocationRelativeTo(null);
        start();
        server.run();
    }

    public void start() {
        client.addActionListener(e -> new UserWindow());
        clear.addActionListener(e -> textArea.setText(""));
        current.addActionListener(e -> textArea.setText(String.valueOf(bank.getAmount())));
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
