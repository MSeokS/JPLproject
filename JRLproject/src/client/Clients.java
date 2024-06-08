package client;

import javax.swing.JTextArea;

public class Clients {
	static JTextArea chatArea;
	
    public static void main(String[] args) {
        ChatGUI chat = new ChatGUI();
        ConnectManager manager = new ConnectManager(chat);
        manager.start();
    }
} 