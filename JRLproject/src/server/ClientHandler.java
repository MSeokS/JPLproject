import java.io.*;
import java.net.*;

class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String clientName;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            
            clientName = in.readLine();
            System.out.println(clientName + " connected.");
            ServerThread.broadcast(clientName + " has joined the chat", this);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received message from " + clientName + ": " + message);
                ServerThread.broadcast(clientName + ": " + message, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerThread.removeClient(this);
            System.out.println(clientName + " disconnected.");
            ServerThread.broadcast(clientName + " has left the chat", this);
        }
    }

    void sendMessage(String message) {
        out.println(message);
    }
}
