import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    private static final int PORT = 5000;
    private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static Map<String, List<ClientHandler>> chatRooms = new ConcurrentHashMap<>();

    private static String getLog(String msg) {
        return "[" + sdfDate.format(new Date()) + "] Server thread: " + msg;
    }

    public void run() {
        System.out.println(getLog("server start."));
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void broadcast(String chatRoom, String message, ClientHandler sender) {
        List<ClientHandler> clients = chatRooms.get(chatRoom);
        if (clients != null) {
            for (ClientHandler client : clients) {
                if (client != sender) {
                    client.sendMessage(message);
                }
            }
        }
    }

    static void addClientToRoom(String chatRoom, ClientHandler client) {
        chatRooms.computeIfAbsent(chatRoom, k -> new ArrayList<>()).add(client);
    }

    static void removeClientFromRoom(String chatRoom, ClientHandler client) {
        List<ClientHandler> clients = chatRooms.get(chatRoom);
        if (clients != null) {
            clients.remove(client);
        }
    }
}
