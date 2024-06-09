import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    private static final int PORT = 5000;
    private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static List<ClientHandler> clients = new CopyOnWriteArrayList<>();

    private static String getLog(String msg) {
        return "[" + sdfDate.format(new Date()) + "] Server thread: " + msg;
    }

    public void run() {
        System.out.println(getLog("server start."));
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    static void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}
