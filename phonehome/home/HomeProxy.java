package phonehome.home;

import java.io.IOException;
import java.net.Socket;

import phonehome.Comm;

/**
 * PhoneHome Home proxy handler. Handles communication between a {@link PhoneClient}
 * and the {@link HomeServer}. A {@link HomeProxy} is created by the {@link HomeServer}
 * for each new connection.
 * 
 * Note: Might be moved to an inner class in {@link HomeServer}.
 * 
 * @author Stevie Alvarez
 */
public class HomeProxy extends Comm implements Runnable  {

    /**
     * User's Name.
     */
    private String name;

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
        this.home = home;
        this.name = "";  // will be assigned in body of run()
    }


    /**
     * Disconnect from server.
     */
    private void disconnect() {
        try {
            home.removeProxy(this);
        } catch (IOException e) {}
        send("\n>> Connection Closed");
    }


    @Override
    public void run() {
        String cmd;

        // get Username
        try {
            // name should be first message sent by client
            boolean t = false;
            while (!t) {
                this.name = read();
                t = home.addProxy(this);
            }
        } catch (IOException ioe) {
            return;
        }

        send(" " + name + "\n>> Connection Established");

        do {
            // get next cmd from client
            cmd = read();

            // check if 
            if (cmd.strip().toLowerCase().equals("quit") || cmd.strip().toLowerCase().equals("disconnect")) {
                cmd = ">> " + name + " disconnected";
            } else {
                cmd = name + ":: " + cmd;
            }

            // Send reformatted cmd
            home.sendMsg(cmd);

        } while (!(cmd.startsWith(">> ") && cmd.endsWith(" disconnected")));

        // notify server that proxy is dead
        home.removeProxy(this);

        send("\n>> Connection Closed");

        // close connection
        try {
            close();
        } catch (IOException ioe) {}
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HomeProxy) {
            HomeProxy p = (HomeProxy)obj;
            return name.equals(p.name);

        } else {
            return false;
        }
    }
}
