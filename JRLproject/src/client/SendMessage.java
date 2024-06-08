package client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SendMessage extends Thread {
	public void run(String message) {
		try {
			Socket soc = new Socket("localhost", 5000);
	        OutputStream os = soc.getOutputStream();
	        PrintWriter writer = new PrintWriter(os, true);
	        writer.println(message);

	        writer.close();
	        os.close();
	        soc.close();
		} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
