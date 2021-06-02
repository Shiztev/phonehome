package phonehome.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Abstracts the basic communication for a {@link Socket}.
 * 
 * @author Stevie Alvarez
 */
public class Comm {

    /**
     * A socket that establishes the desired connection.
     */
    private Socket socket;
    
    /**
     * A handler for sending data through the desired connection.
     */
    private PrintWriter out;

    /**
     * A handler for receiving data through the desired connection.
     */
    private Scanner in;

    /**
     * Creates a new {@link Comm} for the provided socket connection.
     * 
     * @param socket A socket, establishing the desired connection.
     * @throws IOException Thrown from creating a {@link Scanner} and {@link PrintWriter} from
     * the socket's input and output streams, respectivly. 
     */
    public Comm(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new Scanner(socket.getInputStream());
        this.out = new PrintWriter(socket.getOutputStream());
    }


    /**
     * Send provided message through established connection.
     * 
     * @param message Message to send.
     */
    public void send(String message) {
        out.println(message);
        out.flush();
    }

    /**
     * Receive message through established connection.
     * 
     * @return Message received.
     */
    public String read() {
        return in.nextLine();
    }

    /**
     * Close established connection.
     * 
     * @throws IOException Thrown from closing the {@link Socket}, {@link Scanner}, 
     * and {@link PrintWriter}.
     */
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
