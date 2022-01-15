## Socket

This is a simple-yet-to-finish threaded Server/Client Socket. It was built for studying purposes.

## File Structure

It contains four files, where:

- Server.java: this file is used to receive a new Client Socket and create a new ServerThread.
- Client.java: this file is used to create a new Client Socket. It waits for the Client to write a new message to be sent to the Server.
- ServerThread.java: this file is used to supply a 'new Server' (Threading) for each new Client Socket. It keeps listening for any new Client message and then send it to all the others Client.
- ClientInputThread.java: this file is used to supply a 'background' InputStream for each Client Socket. It keeps wating for any new message sent by an other Client through the server.
