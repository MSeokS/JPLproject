import java.io.*;
import java.net.*;

class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String chatRoom;
    private String clientName;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            clientName = in.readLine();
            chatRoom = in.readLine();
            System.out.println(clientName + " joined " + chatRoom);
            ServerThread.addClientToRoom(chatRoom, this);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received message from " + clientName + ": " + message);
                ServerThread.broadcast(chatRoom, clientName + ": " + message, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerThread.removeClientFromRoom(chatRoom, this);
            System.out.println(clientName + " disconnected from " + chatRoom);
        }
    }

    void sendMessage(String message) {
        out.println(message);
    }
}