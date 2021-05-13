package phonehome.phone;

import java.io.IOException;
import java.net.Socket;

import phonehome.Comm;

/**
 * Client side of HomePhone. Sends and recieves messages from the Home server.
 * 
 * @author Stevie Alvarez
 */
public class PhoneClient extends Comm {

    public PhoneClient(Socket socket) throws IOException {
        super(socket);
    }
    
    
    public static void main(String[] args) {
        
    }
}
