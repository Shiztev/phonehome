package phonehome;

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
     * Add a new proxy to the set of proxies.
     */
    public void addProxy() {

    }


    /**
     * Remove a proxy when connection is lost.
     */
    public void removeProxy() {

    }


    /**
     * Forward a message to all the proxies/clients.
     */
    public void sendMsg() {

    }

    
    public static void main(String[] args) {
        // Declare server's socket and HomeServer

        // while true
            // Create a new proxy when connected to (accept and pass socket into proxy)
            // Pass proxy into a new thread and start it
            // Add proxy to set of proxies
    }
}