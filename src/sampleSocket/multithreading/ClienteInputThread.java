package sampleSocket.multithreading;

import java.net.Socket;
import java.io.InputStream;

public class ClienteInputThread extends Thread {

    Socket socket;

    public ClienteInputThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return this.socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (socket.isClosed()) {
                    socket.close();
                    break;
                }
                InputStream input = socket.getInputStream();
                byte[] buffer = new byte[1024];

                // Wait to another Client message
                input.read(buffer);

                String message = new String(buffer).trim();

                System.out.println("Got : " + message);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.interrupt();
    }

}
