package client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SendMessage extends Thread {
	PrintWriter writer;
	
	public SendMessage(PrintWriter writer) {
		this.writer = writer;
	}
	
	public void run(String message) {
		writer.println(message);
	}

}
