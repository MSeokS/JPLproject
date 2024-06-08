package client;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JTextArea;

class GetMessage extends Thread {
	ChatGUI chat;
	
	public GetMessage(ChatGUI chat) {
		this.chat = chat;
	}
	
    public void run() {
        try {
            Socket soc = new Socket("localhost", 5000);
            DataInputStream dis = new DataInputStream(soc.getInputStream());
            chat.getMessage(dis.readUTF());
            
            dis.close();
            soc.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

public class ConnectManager extends Thread {
	ChatGUI chat;
	
	public ConnectManager(ChatGUI chat) {
		this.chat = chat;
	}
	
	public void run() {
		while (true) {
			GetMessage msg = new GetMessage(chat);
			msg.start();
			try {
				msg.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}