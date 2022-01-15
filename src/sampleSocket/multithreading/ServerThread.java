package sampleSocket.multithreading;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread extends Thread {

    Socket socket;
    OutputStream output;
    InputStream input;
    public static List<Socket> users = new ArrayList<>();

    public ServerThread(Socket socket) {
        this.socket = socket;
        users.add(socket);
    }

    @Override
    public void run() {

        while (true) {
            try {

                input = socket.getInputStream();
                byte[] buffer = new byte[1024];

                // Wait to receive a Client message
                input.read(buffer);

                String message = new String(buffer).trim();

                if (message.equals("close")) {
                    System.out.println("Disconnecting Socket " + socket.getInetAddress() + ".");
                    socket.close();
                    System.out.println("Socket disconnected succesfully.");
                    break;
                } else if (message.equals("show sockets")) {
                    System.out.println(users);
                }

                // Send out the message from a Client to all other Clients
                for (Socket user : users) {
                    if (!user.equals(socket)) {
                        // Get the other user's output
                        OutputStream userOutput = user.getOutputStream();
                        // Send out the message to the other Client
                        userOutput.write(message.getBytes());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        users.remove(socket);
        this.interrupt();
    }
}
