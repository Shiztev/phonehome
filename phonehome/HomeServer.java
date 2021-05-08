package phonehome;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * Server side of PhoneHome. Handles all client messaging and forwarding.
 * 
 * @author Stevie Alvarez
 */
public class HomeServer {
    
    /**
     * A set of proxies, connecting this server to a client.
     */
    private Set<HomeProxy> proxies;

    public HomeServer() {
        proxies = new HashSet<>();
    }


    /**
     * Add a new proxy to the set of proxies. If name is taken, closes the proxy and sends a 
     * notification to client.
     * 
     * @param proxy Fresh proxy for new client connection.
     * @throws IOException Thrown if proxy already exists (aka user name is taken)
     */
    public void addProxy(HomeProxy proxy) throws IOException {
        boolean t = proxies.add(proxy);
        if (!t) {
            proxy.send("\nUsername is taken!");
            proxy.close();
        }
    }


    /**
     * Remove a proxy when connection is lost.
     * 
     * @param proxy Dead proxy.
     */
    public void removeProxy(HomeProxy proxy) {
        proxies.remove(proxy);
    }


    /**
     * Forward a message to all the proxies/clients.
     * 
     * @param msg String message to send.
     */
    public void sendMsg(String msg, HomeProxy proxy) {
        for (HomeProxy p : proxies) {
            if (!p.equals(proxy)) {
                p.send(msg);
            }
        }
    }

    
    public static void main(String[] args) throws IOException {
        // Declare server's socket and HomeServer
        ServerSocket server = new ServerSocket(6403);

        // while true
            // Create a new proxy when connected to (accept and pass socket into proxy)
            // Pass proxy into a new thread and start it
            // Add proxy to set of proxies
    }
}