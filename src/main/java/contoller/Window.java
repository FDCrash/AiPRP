package main.java.contoller;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Window extends Frame {

    private Button exit = new Button("Exit");
    private Button search = new Button("Search");
    private TextArea textArea = new TextArea();
    private Button clear = new Button("Clear");

    public Window() {
        super("LAB_1");
        setLayout(null);
        setBackground(new Color(0, 200, 0));
        setSize(350, 250);
        add(clear);
        add(exit);
        add(search);
        add(textArea);
        exit.setBounds(110, 215, 100, 20);
        search.setBounds(110, 190, 100, 20);
        textArea.setBounds(20, 50, 300, 100);
        clear.setBounds(110, 165, 100, 20);
        this.show();
        this.setLocationRelativeTo(null);
        start();
    }

    public void start() {
        search.addActionListener(e -> {
            String[] keywords = textArea.getText().split(",");
            Arrays.stream(keywords).forEach(System.out::println);
            File f = new File("D:\\Java\\lab_1\\src\\main\\resource");
            ArrayList<File> files = new ArrayList<>(Arrays.asList(Objects.requireNonNull(f.listFiles())));
            textArea.setText("");
            int quantity = 0;
            int previous = 0;
            File fopen = null;
            for (File file : files) {
                quantity = test_url(file, keywords);
                textArea.append("\n" + file + "  :" + quantity);
                if (quantity > previous) {
                    previous = quantity;
                    fopen = file;
                }
            }
            openBrowse(fopen);
        });
        exit.addActionListener(e -> System.exit(0));
        clear.addActionListener(e -> textArea.setText(""));
    }

    public int test_url(File page, String[] keywords) {
        int res = 0;
        URL url = null;
        URLConnection con = null;
        int i;
        try {
            String string = "" + page;
            url = new URL("file:/" + string.trim());
            con = url.openConnection();
            //File file = new File("src/main/rezult.html");
            BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
            //BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            StringBuilder bHtml = new StringBuilder();
            while ((i = bis.read()) != -1) {
                //bos.write(i);
                bHtml.append((char) i);
            }
           // bos.flush();
            bis.close();
            String htmlContent = (bHtml.toString()).toLowerCase();
            System.out.println("New url content is: " + htmlContent);
            for (String keyword : keywords) {
                if (htmlContent.contains(keyword.trim().toLowerCase()))
                    res++;
            }
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    public void openBrowse(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            System.out.println("error " + e.getMessage());
            e.printStackTrace();
        }
    }
}