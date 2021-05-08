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
}