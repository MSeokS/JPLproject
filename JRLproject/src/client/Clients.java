package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Clients {	
    public static void main(String[] args) {
        Socket socket = null;
        ChatGUI chat = new ChatGUI();
        LoginGUI login = new LoginGUI(chat);
        
        while(true) {
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	if(chat.getName().equals(""))
        		continue;
        	if(socket == null || !socket.isConnected() || socket.isClosed())
        	{
        		try {
        			System.out.println("Attempting to connect...");
        			socket = new Socket("localhost", 5000);
        			System.out.println("Connected to server.");
        			
        			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        			chat.setWriter(writer);
        			
        			SendMessage send = new SendMessage(writer);
                    send.run(chat.getName());
        			
        	        GetMessage manager = new GetMessage(chat, socket);
        	        manager.run();
        		} catch (IOException e) {
                    e.printStackTrace();
        		}
        	}
        }
        
    }
}