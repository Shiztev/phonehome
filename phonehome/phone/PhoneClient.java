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


    @Override
    public String getUserName() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void recieve() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void transmit(String msg) {
        // TODO Auto-generated method stub
        
    }
}
