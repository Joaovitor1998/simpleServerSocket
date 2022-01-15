package sampleSocket.multithreading;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.Scanner;
import java.io.OutputStream;

public class Client {

    public static void welcome() {
        System.out.println("Welcome.");
        System.out.println("In order to close this connection, please write \"close\".");
        System.out.println("In order to show all the sockets connected, please write \"show sockets\".");
    }

    public static void main(String[] args) throws UnknownHostException, IOException {

        Scanner sc = new Scanner(System.in);

        Socket socket = new Socket("127.0.0.1", 8080);

        welcome();

        // Create a Thread for the Client to receive message
        ClienteInputThread socketInput = new ClienteInputThread(socket);
        socketInput.start();

        OutputStream output = socket.getOutputStream();

        while (true) {

            if (socket.isClosed()) {
                System.out.println("Closing your connection with the server.");
                break;
            }

            // Wait to the Client for a new message
            System.out.print("ME: ");
            String message = sc.nextLine().trim();

            // Send out the Client's message to the Server
            output.write(message.getBytes());

            // if (message.equals("close")) {
            // System.out.println("Closing connection with the server.");
            // break;
            // }

        }

        socket.close();
        sc.close();
        System.out.println("Connection with server disconnected successfully.");
        System.out.println("Trying to close Socket InputStream.");
        System.out.println("Socket InputStream closed successfully.");
    }

}
