package server;

import java.io.*;
import java.net.*;

class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public String clientName;

    public ClientHandler(Socket socket) {
        System.out.println("new client connected.");
        this.socket = socket;
        try {
        	in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void run() {
        try {
        	this.clientName = in.readLine();
        	System.out.println(clientName + " is conneted.");
        	
            String message;
            while (true) {
            	while ((message = in.readLine()) != null) {
            		System.out.println("Received message : " + message);
            		ServerThread.broadcast(message, this);
            	}
            	Thread.sleep(100);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerThread.removeClient(this);
            System.out.println(clientName + "is disconnected.");
        }
    }

    void sendMessage(String message) {
        out.println(message);
    }
    
    boolean equals(ClientHandler obj) {
    	if(this.clientName == obj.clientName)
    		return true;
    	else return false;
    }
}
