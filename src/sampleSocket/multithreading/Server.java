package sampleSocket.multithreading;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        // Create a Socket Server to listen to Client Socket
        ServerSocket serverSocket = new ServerSocket(8080);

        try {
            while (true) {
                // Wait until a Client Socket gets connected to it
                Socket socket = serverSocket.accept();

                // Create a new Thread for the Client Socket connected
                ServerThread socketThread = new ServerThread(socket);

                // Start the new Client Socket Thread
                socketThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the Server Socket
            serverSocket.close();
        }
    }

}
