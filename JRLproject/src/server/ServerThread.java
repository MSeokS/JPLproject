import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
    private String name = null;
    private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyy-MM-dd HH:mm:SSS");

    private static String getLog(String msg) {
        return "[" + sdfDate.format(new Date()) + "] Server thread: " + msg;
    }

    public ServerThread() {
        this.name = "ServerThread";
    }

    public void run() {
    	
    	ServerSocket ss;
        Scanner scn = new Scanner(System.in);
    	System.out.println(getLog("server start."));
        try {
        	ss = new ServerSocket(5000);
        	Socket soc = ss.accept();
        	java.io.OutputStream out = soc.getOutputStream();
        	DataOutputStream dos = new DataOutputStream(out);
        	
            while (true) {
            	dos.writeUTF(scn.nextLine());
            	System.out.println(getLog("sent message."));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
