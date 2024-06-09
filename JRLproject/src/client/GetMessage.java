package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.SwingUtilities;

public class GetMessage extends Thread {
    private ChatGUI chat;
    private Socket socket;
    private BufferedReader br;

    public GetMessage(ChatGUI chat, Socket socket) {
        this.chat = chat;
        this.socket = socket;
        try {
        	InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            this.br = new BufferedReader(isr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                String message = br.readLine();
                System.out.println("Received message: " + message);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        chat.getMessage(message);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}