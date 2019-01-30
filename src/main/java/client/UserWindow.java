package main.java.client;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserWindow extends Frame {
    private Button check = new Button("Check");
    private TextArea textArea = new TextArea();
    private Button clear = new Button("Clear");
    private Button previous = new Button("Previous");
    private User user;

    public UserWindow() {
        super("Client");
        user = new User();
        setLayout(null);
        setBackground(new Color(0, 200, 0));
        setSize(300, 200);
        add(clear);
        add(previous);
        add(check);
        add(textArea);
        textArea.setBounds(110, 50, 100, 50);
        check.setBounds(110, 100, 100, 20);
        previous.setBounds(110, 125, 100, 20);
        clear.setBounds(110, 150, 100, 20);
        this.show();
        start();
    }

    public void start() {
        check.addActionListener(e -> {
            user.run();
            textArea.setText(String.valueOf(user.getAmount()));
        });
        previous.addActionListener(e -> textArea.setText(String.valueOf(user.getAmount())));
        clear.addActionListener(e -> textArea.setText(""));
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                user.closeConnection();
                dispose();
            }
        });
    }
}
