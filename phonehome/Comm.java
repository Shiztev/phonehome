package phonehome;

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
}
