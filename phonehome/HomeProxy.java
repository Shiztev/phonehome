package phonehome;

import java.io.IOException;
import java.net.Socket;

/**
 * PhoneHome Home proxy handler. Handles communication between a {@link PhoneClient}
 * and the {@link HomeServer}. A {@link HomeProxy} is created by the {@link HomeServer}
 * for each new connection.
 * 
 * Note: Might be moved to an inner class in {@link HomeServer}.
 * 
 * @author Stevie Alvarez
 */
public class HomeProxy extends Comm implements Runnable {

    /**
     * User's Name
     */
    private String name;

    /**
     * Client socket connection.
     */
    private Socket phone;

    /**
     * Parent {@link HomeServer}, which client connected to.
     */
    private HomeServer home;

    /**
     * Create a proxy between a client {@link PhoneClient} connection and 
     * the {@link HomeServer} home it connected to.
     * 
     * @param phone Clients socket connection, accepted from {@link HomeServer}.
     * @param home This proxies parent {@link HomeServer}.
     * @throws IOException Thrown from creating abstract handlers for the phone's I/O streams.
     */
    public HomeProxy(Socket phone, HomeServer home) throws IOException {
        super(phone);
        this.phone = phone;
        this.home = home;

        // name should be first message sent by client
        this.name = read();
    }


    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
